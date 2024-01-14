package com.br.fiap.videostream.casosdeuso;

import com.br.fiap.videostream.adapters.armazenamento.ArmazenamentoService;
import com.br.fiap.videostream.domain.entidades.Historia;
import com.br.fiap.videostream.domain.entidades.Midia;
import com.br.fiap.videostream.domain.enuns.Categoria;
import com.br.fiap.videostream.infra.bancodedados.HistoriasRepository;
import com.br.fiap.videostream.infra.s3.MultipartUploadData;
import com.br.fiap.videostream.view.forms.UploadDeMidiaForm;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.test.context.ActiveProfiles;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import software.amazon.awssdk.transfer.s3.S3TransferManager;
import software.amazon.awssdk.transfer.s3.internal.TransferManagerFactory;
import software.amazon.awssdk.transfer.s3.internal.model.DefaultUpload;
import software.amazon.awssdk.transfer.s3.internal.progress.DefaultTransferProgress;
import software.amazon.awssdk.transfer.s3.model.CompletedObjectTransfer;
import software.amazon.awssdk.transfer.s3.model.CompletedUpload;
import software.amazon.awssdk.transfer.s3.model.Upload;
import software.amazon.awssdk.transfer.s3.model.UploadRequest;
import software.amazon.awssdk.transfer.s3.progress.TransferProgress;

import java.io.*;
import java.util.concurrent.CompletableFuture;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ActiveProfiles(value = "test")
@SpringBootTest
public class EnviarMidiaAoBucketTest {

	AutoCloseable mock;

	@InjectMocks
	EnviarMidiaAoBucket enviarMidiaAoBucket;

	@InjectMocks
	EnviarMensagemParaIniciarPipeline enviarMensagemParaIniciarPipeline;

	MultipartUploadData armazenamentoService;

	@Mock
	HistoriasRepository historiasRepository;

	@Mock
	S3TransferManager s3TransferManager;

	@Mock
	UploadDeMidiaForm uploadDeMidiaForm;

	@Mock
	FilePart filePart;

	@Mock
	DataBuffer dataBuffer;

	@Mock
	DataBufferFactory dataBufferFactory;

	@Mock
	TransferProgress transferProgress;

	@Mock
	CompletedObjectTransfer completedObjectTransfer;

	@Mock
	Upload upload;

	InputStream arquivoDeEntrada = new FileInputStream("src/test/resources/ArquivoDeTeste.txt");

	EnviarMidiaAoBucketTest() throws FileNotFoundException {}

	@BeforeEach
	void setup() {
		mock = MockitoAnnotations.openMocks(this);
		armazenamentoService = new MultipartUploadData(s3TransferManager);
		enviarMidiaAoBucket = new EnviarMidiaAoBucket(enviarMensagemParaIniciarPipeline, armazenamentoService, historiasRepository);
	}

	@Test
	public void deveEnviarAMidiaParaUmBucketDoS3() throws FileNotFoundException {
		//Arrange
		/*
		var historiaID = "123456789";
		when(filePart.content()).thenReturn(Flux.just());
		var uploadDeMidiaForm = new UploadDeMidiaForm(Mono.just(filePart));

		var midia = new Midia();
		midia.setNomeDaMidia("um_titulo");
		midia.criarDestino();
		midia.setMidiaStream(new FileInputStream("src/test/resources/ArquivoDeTeste.txt"));

		var historia = new Historia(
			"Um titulo",
			"Uma descricao",
			Categoria.ANIMACAO,
			midia,
			0);

		when(s3TransferManager.upload(any(UploadRequest.class))).thenReturn(upload);
		when(s3TransferManager.upload(any(UploadRequest.class)).completionFuture()).thenReturn(new CompletableFuture<CompletedUpload>());
		when(historiasRepository.findById(any(String.class))).thenReturn(Mono.just(historia));
		when(armazenamentoService.enviarArquivo(midia)).thenReturn(upload);


		//Act & Assert
		StepVerifier
			.create(enviarMidiaAoBucket.enviar(uploadDeMidiaForm, historiaID))
			.expectNextMatches(item -> item != null);*/

	}
}
