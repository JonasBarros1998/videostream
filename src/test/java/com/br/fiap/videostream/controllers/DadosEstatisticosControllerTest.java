package com.br.fiap.videostream.controllers;


import com.br.fiap.videostream.casosdeuso.DadosEstatisticosDasHistorias;
import com.br.fiap.videostream.infra.bancodedados.HistoriasRepository;
import com.br.fiap.videostream.infra.bancodedados.projections.QuantidadeTotalDeVideosEMediaDeVisualizacoesProjection;
import com.br.fiap.videostream.view.DTO.QuantidadeTotalDeVideosEMediaDeVisualizacoesDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.*;

@ActiveProfiles(value = "test")
@AutoConfigureMockMvc
@SpringBootTest
class DadosEstatisticosControllerTest {

	@Mock
	private HistoriasRepository historiasRepository;

	@Mock
	private DadosEstatisticosDasHistorias dadosEstatisticosDasHistorias;

	@InjectMocks
	private DadosEstatisticosController dadosEstatisticosController;

	private WebTestClient webTestClient;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		this.webTestClient = WebTestClient.bindToController(dadosEstatisticosController).build();
		dadosEstatisticosController = new DadosEstatisticosController(dadosEstatisticosDasHistorias);
	}

	@Test
	void deveRetornarStatus200AoCalcularOTotalDeHistoriasEMediaDeVisualizacoes() {
		//Arrange
		when(dadosEstatisticosDasHistorias.buscarQuantidadeTotalDeHistoriasEMediaDeVisualizacoes())
			.thenReturn(Flux.just(new QuantidadeTotalDeVideosEMediaDeVisualizacoesDTO(20.0, 10)));

		//Act & Assert
		webTestClient.get()
			.uri("/api/buscar/totalDeHistoriasEMediaDeVisualizacoes")
			.accept(MediaType.APPLICATION_JSON)
			.exchange()
			.expectStatus()
			.isOk();
	}

}
