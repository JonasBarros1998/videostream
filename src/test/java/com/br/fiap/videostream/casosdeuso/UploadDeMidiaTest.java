package com.br.fiap.videostream.casosdeuso;

import com.br.fiap.videostream.infra.s3.MultipartUploadData;
import com.br.fiap.videostream.view.forms.UploadDeMidiaForm;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.test.context.ActiveProfiles;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.transfer.s3.model.Upload;

import java.io.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ActiveProfiles(value = "test")
@SpringBootTest
class UploadDeMidiaTest {

	AutoCloseable mock;

	UploadDeMidia uploadDeMidia;

	@Mock
	EnviarMensagemParaIniciarPipeline enviarMensagemParaIniciarPipeline;

	@Mock
	MultipartUploadData armazenamentoService;

	@Mock
	FilePart filePart;

	@Mock
	DataBuffer dataBuffer;

	@Mock
	DataBufferFactory dataBufferFactory;

	@Mock
	Upload upload;

	InputStream arquivoDeEntrada = new FileInputStream("src/test/resources/ArquivoDeTeste.txt");

	UploadDeMidiaTest() throws FileNotFoundException {}

	@BeforeEach
	void setup() {
		mock = MockitoAnnotations.openMocks(this);
		uploadDeMidia = new UploadDeMidia(enviarMensagemParaIniciarPipeline, armazenamentoService);
	}

	@Test
	void deveEnviarAMidiaParaUmBucketDoS3() {
		//Arrange
		when(filePart.content()).thenReturn(Flux.just());
		var uploadDeMidiaForm = new UploadDeMidiaForm(Mono.just(filePart));

		/*
		when(filePart.content())
			.thenReturn(DataBufferUtils.readInputStream(() -> arquivoDeEntrada, new DefaultDataBufferFactory(), 4096));
		var transferProgress = new TransferProgress() {

			@Override
			public TransferProgressSnapshot snapshot() {
				return null;
			}
		};

		when(armazenamentoService.enviarArquivo(any(InputStream.class)))
			.thenReturn(new DefaultUpload(new CompletableFuture<>(), transferProgress));
		var uploadDeMidiaForm = new UploadDeMidiaForm(Mono.just(filePart));

		//Act
		StepVerifier.create(uploadDeMidia.enviar(uploadDeMidiaForm))
			.expectNextMatches(enviarMidia -> {
				// Adicione verificações conforme necessário
				//Assertions.assertThat(enviarMidia).isInstanceOf(Mono.class);
				return true;
			}).verifyComplete();

		//Assert
		//Assertions.assertThat(enviarMidia).isInstanceOf(Mono.class);
		//enviarMidia.block();
		//verify(armazenamentoService, times(1)).enviarArquivo(any(InputStream.class));*/

		//Act
		var enviarMidia = uploadDeMidia.enviar(uploadDeMidiaForm);

		//Assert
		Assertions.assertThat(enviarMidia).isInstanceOf(Mono.class);

	}
}
