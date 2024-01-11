package com.br.fiap.videostream.services;

import com.br.fiap.videostream.view.DTO.FavoritosDTO;
import com.br.fiap.videostream.view.DTO.HistoriaDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface IConsultarHistorias {

	Mono<List<HistoriaDTO>> consultarTodas(Pageable paginacao);
	Mono<HistoriaDTO> consultarPorTitulo(String titulo);
	Mono<List<HistoriaDTO>> consultarPorCategorias(String categoria, Pageable paginacao);
	Mono<List<FavoritosDTO>> consultarPorFavoritos(Pageable paginacao);
	Mono<List<HistoriaDTO>> consultarPorDataDePublicacao(Pageable paginacao);

}
