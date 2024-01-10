package com.br.fiap.videostream.controllers;

import com.br.fiap.videostream.adapters.armazenamento.ArmazenamentoService;
import com.br.fiap.videostream.casosdeuso.EnviarMensagemParaIniciarPipeline;
import com.br.fiap.videostream.casosdeuso.UploadDeMidia;
import com.br.fiap.videostream.view.forms.UploadDeMidiaForm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;

import java.io.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ActiveProfiles(value = "test")
@AutoConfigureMockMvc
@SpringBootTest
class UploadDeMidiaControllerTest {

	@InjectMocks
	private UploadDeMidiaController uploadDeMidiaController;

	private WebTestClient webTestClient;

	@Mock
	UploadDeMidia uploadDeMidia;

	@Mock
	private EnviarMensagemParaIniciarPipeline pipeline;

	@Mock
	private ArmazenamentoService armazenamentoService;


	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		this.webTestClient = WebTestClient.bindToController(uploadDeMidiaController).build();
	}

	@Test
	void deveFazerUploadDasMidias() throws IOException {
		//Arrange
		MultipartBodyBuilder multipartBodyBuilder = new MultipartBodyBuilder();

		Resource resource = new InputStreamResource(new FileInputStream("src/test/resources/ArquivoDeTeste.txt"));

		when(uploadDeMidia.enviar(any(UploadDeMidiaForm.class)))
			.thenReturn(Mono.just(new UploadDeMidia(pipeline, armazenamentoService)));

		multipartBodyBuilder.asyncPart("ArquivoDeTeste.txt", Mono.just(resource), Resource.class)
			.contentType(MediaType.MULTIPART_FORM_DATA);

		//Act & Assert
		webTestClient.post()
			.uri("/api/arquivos")
			.contentType(MediaType.MULTIPART_FORM_DATA)
			.body(BodyInserters.fromMultipartData(multipartBodyBuilder.build()))
			.exchange()
			.expectStatus()
			.isCreated();

	}

}
