package com.br.fiap.videostream.controllers;

import com.br.fiap.videostream.services.IDadosEstatisticosDasHistorias;
import com.br.fiap.videostream.view.DTO.QuantidadeTotalDeHistoriasFavoritadasDTO;
import com.br.fiap.videostream.view.DTO.QuantidadeTotalDeVideosEMediaDeVisualizacoesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api")
public class DadosEstatisticosController {

	IDadosEstatisticosDasHistorias dadosEstatisticosDasHistorias;

	@Autowired
	public DadosEstatisticosController(IDadosEstatisticosDasHistorias dadosEstatisticosDasHistorias) {
		this.dadosEstatisticosDasHistorias = dadosEstatisticosDasHistorias;
	}

	@GetMapping("/buscar/totalDeHistoriasEMediaDeVisualizacoes")
	Flux<QuantidadeTotalDeVideosEMediaDeVisualizacoesDTO> buscarTotalDeHistoriasEMediaDeVisualizacoes() {
		return this.dadosEstatisticosDasHistorias.buscarQuantidadeTotalDeHistoriasEMediaDeVisualizacoes()
			.map(item -> item);
	}

	@GetMapping("/buscar/totalDeHistoriasFavoritadas")
	Flux<QuantidadeTotalDeHistoriasFavoritadasDTO> buscarTotalDeHistoriasFavoritadas() {
		return this.dadosEstatisticosDasHistorias.buscarQuantidadeTotalDeHistoriasFavoritadas()
			.map(item -> item);
	}
}
