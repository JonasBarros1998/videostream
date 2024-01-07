package com.br.fiap.videostream.infra.s3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import software.amazon.awssdk.transfer.s3.S3TransferManager;
import software.amazon.awssdk.transfer.s3.model.Upload;
import software.amazon.awssdk.transfer.s3.model.UploadRequest;

import java.io.File;

@ActiveProfiles(value = "test")
@SpringBootTest
class MultipartUploadDataTest {

	MultipartUploadData multipartUploadData;

	@Mock
	S3TransferManager s3TransferManager;

	AutoCloseable mock;

	@BeforeEach
	void setup() {
		mock = MockitoAnnotations.openMocks(this);
		multipartUploadData = new MultipartUploadData(s3TransferManager);
	}

	@Test
	void deveFazerOUploadDeUmaMidiaNoS3() {
		//Arrange
		var arquivo = new File("src/test/resources/ArquivoDeTeste.txt");
		when(s3TransferManager.upload(any(UploadRequest.class))).thenReturn(any(Upload.class));

		//Act
		this.multipartUploadData.enviarArquivo(arquivo);

		//Assert
		verify(s3TransferManager, times(1)).upload(any(UploadRequest.class));

	}
}
