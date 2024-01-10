package com.br.fiap.videostream.controllers;

import com.br.fiap.videostream.casosdeuso.UploadDeMidia;
import com.br.fiap.videostream.domain.enuns.Categoria;
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

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static reactor.core.publisher.Mono.when;

@ActiveProfiles(value = "test")
@AutoConfigureMockMvc
@SpringBootTest
class HistoriaControllerTest {

	@Mock
	private UploadDeMidia uploadDeMidia;

	@InjectMocks
	private HistoriasController historiasController;

	private WebTestClient webTestClient;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		this.webTestClient = WebTestClient.bindToController(historiasController).build();
	}

	@Test
	void deveCriarHistorias() {
		//Arrange
		HistoriaForm requestBody = new HistoriaForm("Um titulo", "Uma descricao", Categoria.ACAO);

		//Act & Assert
		webTestClient.post()
			.uri("/api/historias")
			.contentType(MediaType.APPLICATION_JSON)
			.bodyValue(requestBody)
			.exchange()
			.expectStatus().isCreated();

	}

}
