package com.br.fiap.videostream.casosdeuso;

import com.br.fiap.videostream.domain.AdicionarUmaVisualizacao;
import com.br.fiap.videostream.domain.entidades.Historia;
import com.br.fiap.videostream.infra.bancodedados.HistoriasRepository;
import com.br.fiap.videostream.services.IAdicionarVisualizacaoDeUmaHistoria;
import com.br.fiap.videostream.view.DTO.HistoriaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class AdicionarVisualizacaoDeUmaHistoria implements IAdicionarVisualizacaoDeUmaHistoria {

	private HistoriasRepository historiasRepository;

	@Autowired
	AdicionarVisualizacaoDeUmaHistoria(HistoriasRepository historiasRepository) {
		this.historiasRepository = historiasRepository;
	}

	public Mono<HistoriaDTO> adicionarVisualizacao(String id) {
		var adicionarVisualizacao = new AdicionarUmaVisualizacao();
		var historiaDTO = new HistoriaDTO();

		return this.historiasRepository
			.findById(id)
			.flatMap(historia -> {
			adicionarVisualizacao.adicionar(historia.getVisualizacao());
			historia.setVisualizacao(adicionarVisualizacao.getVisualizacao());
			return this.historiasRepository.save(historia);
		}).map(historiaDTO::converterHistoriaParaHistoriaDTO);
	}

}
