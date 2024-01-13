package com.br.fiap.videostream.controllers;

import com.br.fiap.videostream.casosdeuso.RecomendacoesDeHistorias;
import com.br.fiap.videostream.view.DTO.HistoriaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api")
public class RecomendacaoController {

	RecomendacoesDeHistorias recomendacoesDeHistorias;

	@Autowired
	RecomendacaoController(RecomendacoesDeHistorias recomendacoesDeHistorias) {
		this.recomendacoesDeHistorias = recomendacoesDeHistorias;
	}

	@GetMapping("/recomendacoes")
	public Flux<HistoriaDTO> recomendacoes() {
		return this.recomendacoesDeHistorias.buscarRecomendacoes()
			.map(item -> item)
			.doOnError(item -> {
				throw new RuntimeException(item.getMessage());
			});
	}

}
