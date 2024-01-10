package com.br.fiap.videostream.casosdeuso;

import com.br.fiap.videostream.domain.entidades.Favoritos;
import com.br.fiap.videostream.infra.bancodedados.FavoritosRepository;
import com.br.fiap.videostream.infra.bancodedados.HistoriasRepository;
import com.br.fiap.videostream.view.DTO.FavoritosDTO;
import com.br.fiap.videostream.view.DTO.HistoriaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ConsultasDeHistorias {

	private HistoriasRepository historiasRepository;

	private FavoritosRepository favoritosRepository;

	@Autowired
	public ConsultasDeHistorias(HistoriasRepository historiasRepository, FavoritosRepository favoritosRepository) {
		this.historiasRepository = historiasRepository;
		this.favoritosRepository = favoritosRepository;
	}

	public Flux<HistoriaDTO> consultarTodas(Pageable paginacao) {
		return this.historiasRepository
			.findAllByDataDePublicacao(paginacao)
			.map(historia -> new HistoriaDTO().converterHistoriaParaHistoriaDTO(historia));
	}

	public Mono<HistoriaDTO> consultarPorTitulo(String titulo) {
		return this.historiasRepository
			.findAllByTitulo(titulo)
			.map(historia -> new HistoriaDTO().converterHistoriaParaHistoriaDTO(historia));
	}

	public Flux<HistoriaDTO> consultarPorCategorias(String categoria) {
		return this.historiasRepository.findAllByCategorias(categoria)
			.map(historia -> new HistoriaDTO().converterHistoriaParaHistoriaDTO(historia));
	}

	public Flux<FavoritosDTO> consultarPorFavoritos() {
		var historiaDTO = new FavoritosDTO();
		return this.favoritosRepository.findAll()
			.map(favoritos -> historiaDTO.converterFavoritosParaFavoritosDTO(favoritos));
	}

}
