package com.br.fiap.videostream.casosdeuso;

import com.br.fiap.videostream.infra.bancodedados.FavoritosRepository;
import com.br.fiap.videostream.infra.bancodedados.HistoriasRepository;
import com.br.fiap.videostream.view.DTO.QuantidadeTotalDeFavoritosDTO;
import com.br.fiap.videostream.view.DTO.QuantidadeTotalDeVideosEMediaDeVisualizacoesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class BuscarDadosEstatisticosDasMidias {

	private HistoriasRepository historiasRepository;

	private FavoritosRepository favoritosRepository;

	@Autowired
	public BuscarDadosEstatisticosDasMidias(HistoriasRepository historiasRepository, FavoritosRepository favoritosRepository) {
		this.historiasRepository = historiasRepository;
		this.favoritosRepository = favoritosRepository;
	}

	public Mono<QuantidadeTotalDeVideosEMediaDeVisualizacoesDTO> buscarQuantidadeTotalDeVideosEMediaDeVisualizacoes() {
		return this.historiasRepository
			.obterQuantidadeTotalDeVideosEMediaDeVisualizacoes()
			.map(dadosEstatisticos ->
				new QuantidadeTotalDeVideosEMediaDeVisualizacoesDTO(
					dadosEstatisticos.getMediaVisualizacoes(),
					dadosEstatisticos.getTotalDeVideos()
				));
	}

	public Mono<QuantidadeTotalDeFavoritosDTO> obterTodosOsVideosFavoritados() {
		return this.favoritosRepository
			.obterTodosOsVideosFavoritados()
			.map(favoritos -> new QuantidadeTotalDeFavoritosDTO(favoritos.totalDeVideosFavoritados()));
	}
}
