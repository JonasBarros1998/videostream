package com.br.fiap.videostream.casosdeuso;

import com.br.fiap.videostream.infra.bancodedados.FavoritosRepository;
import com.br.fiap.videostream.infra.bancodedados.HistoriasRepository;
import com.br.fiap.videostream.services.IDadosEstatisticosDasHistorias;
import com.br.fiap.videostream.view.DTO.QuantidadeTotalDeHistoriasFavoritadasDTO;
import com.br.fiap.videostream.view.DTO.QuantidadeTotalDeVideosEMediaDeVisualizacoesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class DadosEstatisticosDasHistorias implements IDadosEstatisticosDasHistorias {

	private HistoriasRepository historiasRepository;

	private FavoritosRepository favoritosRepository;

	@Autowired
	public DadosEstatisticosDasHistorias(
		HistoriasRepository historiasRepository,
		FavoritosRepository favoritosRepository) {
		this.historiasRepository = historiasRepository;
		this.favoritosRepository = favoritosRepository;
	}

	@Override
	public Flux<QuantidadeTotalDeVideosEMediaDeVisualizacoesDTO> buscarQuantidadeTotalDeHistoriasEMediaDeVisualizacoes() {
		return this.historiasRepository.obterQuantidadeTotalDeVideosEMediaDeVisualizacoes()
			.map(item ->
				new QuantidadeTotalDeVideosEMediaDeVisualizacoesDTO(item.getMediaDeVisualizacoes(), item.getTotalDeHistorias()));
	}

	public Flux<QuantidadeTotalDeHistoriasFavoritadasDTO> buscarQuantidadeTotalDeHistoriasFavoritadas() {
		return this.favoritosRepository
			.obterTodosOsVideosFavoritados()
			.map(favoritos -> new QuantidadeTotalDeHistoriasFavoritadasDTO(favoritos.getTotalDeHistoriasFavoritadas()));
	}
}
