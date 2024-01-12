package com.br.fiap.videostream.casosdeuso;

import com.br.fiap.videostream.adapters.armazenamento.ArmazenamentoService;
import com.br.fiap.videostream.casosdeuso.erros.ProcessarJsonException;
import com.br.fiap.videostream.domain.entidades.Historia;
import com.br.fiap.videostream.domain.entidades.Midia;
import com.br.fiap.videostream.infra.bancodedados.HistoriasRepository;
import com.br.fiap.videostream.services.IConsultarHistorias;
import com.br.fiap.videostream.view.DTO.HistoriaDTO;
import com.br.fiap.videostream.view.DTO.UploadDeMidiaDTO;
import com.br.fiap.videostream.view.forms.UploadDeMidiaForm;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.transfer.s3.model.Upload;

@Service
public class EnviarMidiaAoBucket {

	private EnviarMensagemParaIniciarPipeline pipeline;

	private ArmazenamentoService armazenamentoService;

	private HistoriasRepository historiasRepository;

	private HistoriaDTO historiaDTO = new HistoriaDTO();

	private Historia historia;

	@Autowired
	public EnviarMidiaAoBucket(
		EnviarMensagemParaIniciarPipeline pipeline,
		ArmazenamentoService armazenamentoService,
		HistoriasRepository historiasRepository) {
		this.pipeline = pipeline;
		this.armazenamentoService = armazenamentoService;
		this.historiasRepository = historiasRepository;
	}
	
	public Mono<EnviarMidiaAoBucket> enviar(UploadDeMidiaForm uploadDeMidiaForm, String historiaID) {
		var uploadDeMdiaDTO = new UploadDeMidiaDTO();

		this.consultarNomeDaMidia(historiaID).subscribe(historia -> {
			this.historia = historia;
			historiaDTO.converterHistoriaParaHistoriaDTO(historia);
		});

		return uploadDeMdiaDTO.converterUploadMidiaFormParaUploadMidiaDTO(uploadDeMidiaForm)
			.flatMap(arquivo -> {
				this.historia.getMidia().setMidiaStream(arquivo.getArquivoStream());

				armazenamentoService.enviarArquivo(this.historia.getMidia())
					.completionFuture().thenApply(item -> {
						try {
							pipeline.iniciarProcessamentoDeVideo(this.historia.getMidia());
						} catch (JsonProcessingException e) {
							throw new ProcessarJsonException(e.getMessage());
						}
						return item;
					});
				return Mono.just(this);
			}).then(Mono.just(this));
	}


	public Mono<Historia> consultarNomeDaMidia(String id) {
		return this.historiasRepository.findById(id)
			.flatMap(Mono::just)
			.switchIfEmpty(Mono.error(Throwable::new));
	}
}
