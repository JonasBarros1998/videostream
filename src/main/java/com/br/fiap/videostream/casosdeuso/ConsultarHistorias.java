package com.br.fiap.videostream.casosdeuso;

import com.br.fiap.videostream.infra.bancodedados.FavoritosRepository;
import com.br.fiap.videostream.infra.bancodedados.HistoriasRepository;
import com.br.fiap.videostream.services.IConsultarHistorias;
import com.br.fiap.videostream.view.DTO.FavoritosDTO;
import com.br.fiap.videostream.view.DTO.HistoriaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ConsultarHistorias implements IConsultarHistorias {

	private HistoriasRepository historiasRepository;

	private FavoritosRepository favoritosRepository;

	@Autowired
	public ConsultarHistorias(HistoriasRepository historiasRepository, FavoritosRepository favoritosRepository) {
		this.historiasRepository = historiasRepository;
		this.favoritosRepository = favoritosRepository;
	}

	public Mono<List<HistoriaDTO>> consultarTodas(Pageable paginacao) {
		return this.historiasRepository
			.findAll(paginacao)
			.map(historia -> new HistoriaDTO().converterHistoriaParaHistoriaDTO(historia))
			.collectList()
			.map(item -> item);

	}

	public Mono<HistoriaDTO> consultarPorTitulo(String titulo) {
		return this.historiasRepository
			.findByTitulo(titulo)
			.map(historia -> new HistoriaDTO().converterHistoriaParaHistoriaDTO(historia));
	}

	public Mono<List<HistoriaDTO>> consultarPorCategorias(String categoria, Pageable paginacao) {
		return this.historiasRepository.findAllByCategorias(categoria, paginacao)
			.map(historia -> new HistoriaDTO().converterHistoriaParaHistoriaDTO(historia))
			.collectList()
			.map(item -> item);
	}

	public Mono<List<FavoritosDTO>> consultarPorFavoritos(Pageable paginacao) {
		return this.favoritosRepository.findAll(paginacao)
			.map(favoritos -> new FavoritosDTO().converterFavoritosParaFavoritosDTO(favoritos))
			.collectList()
			.map(item -> item);
	}

	public Mono<List<HistoriaDTO>> consultarPorDataDePublicacao(Pageable paginacao) {
		return this.historiasRepository.findAllByDataDePublicacaoDate(paginacao)
			.map(historia -> new HistoriaDTO().converterHistoriaParaHistoriaDTO(historia))
			.collectList()
			.map(item -> item);
	}

}
