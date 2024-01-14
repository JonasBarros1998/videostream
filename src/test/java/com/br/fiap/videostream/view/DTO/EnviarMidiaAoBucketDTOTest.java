package com.br.fiap.videostream.view.DTO;

import com.br.fiap.videostream.view.forms.UploadDeMidiaForm;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.test.context.ActiveProfiles;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;

import java.io.*;

@ActiveProfiles(value = "test")
@SpringBootTest
public class EnviarMidiaAoBucketDTOTest {

	AutoCloseable mock;

	@Mock
	FilePart filePart;

	@Mock
	DataBuffer dataBuffer;

	OutputStream arquivoDeSaida = new FileOutputStream("src/test/resources/ArquivoDeTeste.txt");

	InputStream arquivoDeEntrada = new FileInputStream("src/test/resources/ArquivoDeTeste.txt");

	EnviarMidiaAoBucketDTOTest() throws FileNotFoundException {}

	@BeforeEach
	public void setup() {
		mock = MockitoAnnotations.openMocks(this);
	}

	@Test
	public void deveConverterDeUploadDeMidaFormParaUploadDeMidiaDTO() {
		//Arrange
		when(filePart.content())
			.thenReturn(DataBufferUtils
				.readInputStream(() -> arquivoDeEntrada, new DefaultDataBufferFactory(), 4096));

		var upload = new UploadDeMidiaForm(Mono.just(filePart));

		//Act
		var uploadMidiaDTO = new UploadDeMidiaDTO();
		var converterParaUploadMidiaDTO = uploadMidiaDTO.converterUploadMidiaFormParaUploadMidiaDTO(upload);

		//Assert
		Assertions.assertThat(converterParaUploadMidiaDTO).isInstanceOf(Mono.class);
	}

	@Test
	public void deveRetornarUmaInstanciaDeInputStream() {
		//Arrange
		when(filePart.content()).thenReturn(Flux.just(dataBuffer));
		var upload = new UploadDeMidiaForm(Mono.just(filePart));

		//Act
		var uploadMidiaDTO = new UploadDeMidiaDTO();
		uploadMidiaDTO.setArquivoStream(arquivoDeEntrada);
		uploadMidiaDTO.converterUploadMidiaFormParaUploadMidiaDTO(upload);

		//Assert
		Assertions.assertThat(uploadMidiaDTO.getArquivoStream()).isInstanceOf(InputStream.class);
	}
}
