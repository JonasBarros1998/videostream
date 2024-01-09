package com.br.fiap.videostream.casosdeuso;

import com.br.fiap.videostream.adapters.armazenamento.ArmazenamentoService;
import com.br.fiap.videostream.casosdeuso.erros.ProcessarJsonException;
import com.br.fiap.videostream.domain.entidades.Midia;
import com.br.fiap.videostream.view.DTO.UploadDeMidiaDTO;
import com.br.fiap.videostream.view.forms.UploadDeMidiaForm;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UploadDeMidia {

	private EnviarMensagemParaIniciarPipeline pipeline;

	private ArmazenamentoService armazenamentoService;

	private Midia midia = new Midia();

	@Autowired
	UploadDeMidia(EnviarMensagemParaIniciarPipeline pipeline, ArmazenamentoService armazenamentoService) {
		this.pipeline = pipeline;
		this.armazenamentoService = armazenamentoService;
	}
	
	public Mono<UploadDeMidia> enviar(UploadDeMidiaForm uploadDeMidiaForm) {
		var uploadDeMdiaDTO = new UploadDeMidiaDTO();
		midia.setNomeDaMidia(uploadDeMidiaForm.arquivo().block().filename());

		return uploadDeMdiaDTO.converterUploadMidiaFormParaUploadMidiaDTO(uploadDeMidiaForm)
			.flatMap(arquivo -> {
				midia.setMidiaStream(arquivo.getArquivoStream());
				armazenamentoService.enviarArquivo(midia)
					.completionFuture().thenApply((item) -> {
						try {
							pipeline.iniciarProcessamentoDeVideo(midia);
						} catch (JsonProcessingException e) {
							throw new ProcessarJsonException(e.getMessage());
						}
						return item;
					});
				return Mono.just(this);
			}).then(Mono.just(this));
	}
}
