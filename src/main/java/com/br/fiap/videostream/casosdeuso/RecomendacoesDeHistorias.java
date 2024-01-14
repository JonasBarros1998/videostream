package com.br.fiap.videostream.casosdeuso;

import com.br.fiap.videostream.casosdeuso.erros.ProcessarJsonException;
import com.br.fiap.videostream.domain.entidades.Historia;
import com.br.fiap.videostream.domain.enuns.Categoria;
import com.br.fiap.videostream.infra.bancodedados.FavoritosRepository;
import com.br.fiap.videostream.infra.bancodedados.RecomendacaoRepository;
import com.br.fiap.videostream.view.DTO.HistoriaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class RecomendacoesDeHistorias {

	RecomendacaoRepository recomendacoesRepository;

	FavoritosRepository favoritosRepository;

	@Autowired
	RecomendacoesDeHistorias(RecomendacaoRepository recomendacoesRepository, FavoritosRepository favoritosRepository) {
		this.recomendacoesRepository = recomendacoesRepository;
		this.favoritosRepository = favoritosRepository;
	}

	public Flux<HistoriaDTO> buscarRecomendacoes() {
		var historiaDTO = new HistoriaDTO();

		return this.favoritosRepository.buscarCategoriasDosFavoritos()
			.map(categoriasDosFavoritos -> categoriasDosFavoritos)
			.flatMapMany(item -> this.recomendacoesRepository
					.recomendarHistoriasComBaseNosFavoritos(this.criarJsonDeCaegorias(item.getCategorias()))
					.map(Historia::new)
			).map(historiaDTO::converterHistoriaParaHistoriaDTO);
	}

	private List<String> criarJsonDeCaegorias(List<Categoria> categorias) {
		return categorias.stream().map(Categoria::getCode).toList();
	}
}



