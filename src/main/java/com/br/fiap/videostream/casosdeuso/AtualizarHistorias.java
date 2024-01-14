package com.br.fiap.videostream.casosdeuso;

import com.br.fiap.videostream.infra.bancodedados.HistoriasRepository;
import com.br.fiap.videostream.services.IAtualizarHistorias;
import com.br.fiap.videostream.view.DTO.HistoriaDTO;
import com.br.fiap.videostream.view.forms.AtualizarHistoriaForm;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class AtualizarHistorias implements IAtualizarHistorias {

	private HistoriasRepository historiasRepository;

	public AtualizarHistorias(HistoriasRepository historiasRepository) {
		this.historiasRepository = historiasRepository;
	}

	@Override
	public Mono<HistoriaDTO> atualizar(String idHistoria, AtualizarHistoriaForm atualizarHistoriaForm) {
		var historiaDTO = new HistoriaDTO();

		return this.historiasRepository.findById(idHistoria).flatMap(historia -> {

			historia.setCategorias(atualizarHistoriaForm.categorias());
			historia.setTitulo(atualizarHistoriaForm.titulo());
			historia.setDescricao(atualizarHistoriaForm.descricao());

			historiaDTO.converterHistoriaParaHistoriaDTO(historia);

			return this.historiasRepository
				.save(historia)
				.map(historiaDTO::converterHistoriaParaHistoriaDTO);

		});
	}
}
