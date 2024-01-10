package com.br.fiap.videostream.controllers;

import com.br.fiap.videostream.casosdeuso.ConsultarHistorias;
import com.br.fiap.videostream.casosdeuso.SalvarNovasHistorias;
import com.br.fiap.videostream.domain.entidades.Historia;
import com.br.fiap.videostream.domain.enuns.Categoria;
import com.br.fiap.videostream.infra.bancodedados.HistoriasRepository;

import com.br.fiap.videostream.view.forms.HistoriaForm;
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

import reactor.core.publisher.Mono;

import static org.mockito.Mockito.*;


import static org.mockito.ArgumentMatchers.any;

@ActiveProfiles(value = "test")
@AutoConfigureMockMvc
@SpringBootTest
class HistoriaControllerTest {

	@Mock
	private HistoriasRepository historiasRepository;

	@InjectMocks
	private ConsultarHistorias consultarHistorias;

	@InjectMocks
	private SalvarNovasHistorias salvarNovasHistorias;

	@InjectMocks
	private HistoriasController historiasController;

	private WebTestClient webTestClient;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		salvarNovasHistorias = new SalvarNovasHistorias(historiasRepository);
		historiasController = new HistoriasController(salvarNovasHistorias, consultarHistorias);
		this.webTestClient = WebTestClient.bindToController(historiasController).build();
	}

	@Test
	void deveCriarHistorias() {
		//Arrange
		HistoriaForm requestBody = new HistoriaForm("Um titulo", "Uma descricao", Categoria.ACAO);
		var historia = new Historia(requestBody.titulo(), requestBody.descricao(), requestBody.categoria(), 0);
		when(historiasRepository.save(any(Historia.class))).thenReturn(Mono.just(historia));

		//Act & Assert
		webTestClient.post()
			.uri("/api/historias")
			.contentType(MediaType.APPLICATION_JSON)
			.bodyValue(requestBody)
			.exchange()
			.expectStatus()
			.isCreated();
	}

	@Test
	void quandoConsultarPorTituloDeveRetornarUmaHistoria() {
		//Arrange
		HistoriaForm requestBody = new HistoriaForm("Um titulo", "Uma descricao", Categoria.ACAO);
		var historia = new Historia(requestBody.titulo(), requestBody.descricao(), requestBody.categoria(), 0);

		when(historiasRepository.findByTitulo(any(String.class))).thenReturn(Mono.just(historia));

		//Act & Assert
		webTestClient.get()
			.uri(uriBuilder ->
				uriBuilder
					.path("/api/historias")
					.queryParam("titulo", "Toda a luz que nao podemos ver")
					.build())
			.accept(MediaType.APPLICATION_JSON)
			.exchange()
			.expectStatus()
			.isOk();
	}

}
