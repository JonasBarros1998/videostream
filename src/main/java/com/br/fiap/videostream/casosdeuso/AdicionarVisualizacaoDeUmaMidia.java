package com.br.fiap.videostream.casosdeuso;

import com.br.fiap.videostream.domain.AdicionarUmaVisualizacao;
import com.br.fiap.videostream.infra.bancodedados.HistoriasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class AdicionarVisualizacaoDeUmaMidia {

	private HistoriasRepository historiasRepository;

	@Autowired
	AdicionarVisualizacaoDeUmaMidia(HistoriasRepository historiasRepository) {
		this.historiasRepository = historiasRepository;
	}

	public Mono<AdicionarUmaVisualizacao> adicionarVisualizacao(String id) {
		var adicionarVisualizacao = new AdicionarUmaVisualizacao();
		return this.historiasRepository.findById(id).map(historia -> {
			adicionarVisualizacao.adicionar(historia.getVisualizacao());
			return adicionarVisualizacao;
		});
	}

}
