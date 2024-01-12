package com.br.fiap.videostream.casosdeuso;

import com.br.fiap.videostream.domain.entidades.Historia;
import com.br.fiap.videostream.infra.bancodedados.FavoritosRepository;
import com.br.fiap.videostream.infra.bancodedados.HistoriasRepository;
import com.br.fiap.videostream.services.IConsultarHistorias;
import com.br.fiap.videostream.view.DTO.FavoritosDTO;
import com.br.fiap.videostream.view.DTO.HistoriaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ConsultarHistorias implements IConsultarHistorias {

	private HistoriasRepository historiasRepository;

	private FavoritosRepository favoritosRepository;

	private GerarUrlTemporaria gerarUrlTemporaria;

	@Autowired
	public ConsultarHistorias(
		HistoriasRepository historiasRepository,
		FavoritosRepository favoritosRepository,
		GerarUrlTemporaria gerarUrlTemporaria) {
		this.historiasRepository = historiasRepository;
		this.favoritosRepository = favoritosRepository;
		this.gerarUrlTemporaria = gerarUrlTemporaria;
	}

	public Mono<List<HistoriaDTO>> consultarTodas(Pageable paginacao) {
		return this.historiasRepository
			.findAll(paginacao)
			.map(this::converterHistoriaParahistoriaDTO)
			.collectList()
			.map(item -> item);

	}

	public Mono<HistoriaDTO> consultarPorTitulo(String titulo) {
		return this.historiasRepository
			.findByTitulo(titulo)
			.map(this::converterHistoriaParahistoriaDTO);
	}

	public Mono<List<HistoriaDTO>> consultarPorCategorias(String categoria, Pageable paginacao) {
		return this.historiasRepository.findAllByCategorias(categoria, paginacao)
			.map(this::converterHistoriaParahistoriaDTO)
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
			.map(this::converterHistoriaParahistoriaDTO)
			.collectList()
			.map(item -> item);
	}

	private String gerarUrlAssinada(HistoriaDTO historiaDTO) {
		return this.gerarUrlTemporaria.gerarUrl(historiaDTO);
	}


	private HistoriaDTO converterHistoriaParahistoriaDTO(Historia historia) {
		var historiaDTO = new HistoriaDTO();
		historiaDTO.converterHistoriaParaHistoriaDTO(historia);
		historiaDTO.setUrlDaMidia(this.gerarUrlAssinada(historiaDTO));
		return historiaDTO;
	}


}
