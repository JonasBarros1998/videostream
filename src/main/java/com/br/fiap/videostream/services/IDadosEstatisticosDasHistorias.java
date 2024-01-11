package com.br.fiap.videostream.services;

import com.br.fiap.videostream.view.DTO.QuantidadeTotalDeHistoriasFavoritadasDTO;
import com.br.fiap.videostream.view.DTO.QuantidadeTotalDeVideosEMediaDeVisualizacoesDTO;
import reactor.core.publisher.Flux;

public interface IDadosEstatisticosDasHistorias {
	Flux<QuantidadeTotalDeVideosEMediaDeVisualizacoesDTO> buscarQuantidadeTotalDeHistoriasEMediaDeVisualizacoes();

	Flux<QuantidadeTotalDeHistoriasFavoritadasDTO> buscarQuantidadeTotalDeHistoriasFavoritadas();

}
