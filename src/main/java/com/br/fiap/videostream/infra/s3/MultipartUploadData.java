package com.br.fiap.videostream.infra.s3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.core.async.AsyncRequestBody;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.transfer.s3.S3TransferManager;
import software.amazon.awssdk.transfer.s3.model.Upload;
import software.amazon.awssdk.transfer.s3.model.UploadRequest;

import java.io.File;
import java.util.UUID;

@Component
public class MultipartUploadData {

	private S3TransferManager s3TransferManager;

	@Value("${aplicacao.s3.buckets.videos-nao-processados}")
	private String bucket;

	@Autowired
	public MultipartUploadData(S3TransferManager s3TransferManager) {
		this.s3TransferManager = s3TransferManager;
	}

	public Upload enviarArquivo(File arquivo) {
		System.out.println(arquivo.getAbsolutePath());
		System.out.println(arquivo.exists());

		var uploadRequestBody = UploadRequest
			.builder()
			.requestBody(adicionarArquivoNaRequisicao(arquivo))
			.putObjectRequest(prepararRequisicao(arquivo))
			.build();

		return this.s3TransferManager.upload(uploadRequestBody);
	}

	private String nomeDaPasta() {
		return UUID.randomUUID().toString();
	}

	private PutObjectRequest prepararRequisicao(File arquivo) {
		return PutObjectRequest
			.builder()
			.bucket(bucket)
			.key("%s/%s".formatted(nomeDaPasta(), arquivo.getName()))
			.build();

	}

	private AsyncRequestBody adicionarArquivoNaRequisicao(File arquivo) {
		return AsyncRequestBody.fromFile(arquivo);
	}

}
