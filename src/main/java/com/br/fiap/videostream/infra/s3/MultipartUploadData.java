package com.br.fiap.videostream.infra.s3;

import com.br.fiap.videostream.adapters.armazenamento.ArmazenamentoService;
import com.br.fiap.videostream.domain.entidades.Midia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.core.async.AsyncRequestBody;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.transfer.s3.S3TransferManager;
import software.amazon.awssdk.transfer.s3.model.Upload;
import software.amazon.awssdk.transfer.s3.model.UploadRequest;

import java.io.IOException;
import java.io.InputStream;

@Component
public class MultipartUploadData implements ArmazenamentoService {

	private S3TransferManager s3TransferManager;

	@Value("${aplicacao.s3.buckets.videos-nao-processados}")
	private String bucket;

	@Autowired
	public MultipartUploadData(S3TransferManager s3TransferManager) {
		this.s3TransferManager = s3TransferManager;
	}

	public Upload enviarArquivo(Midia midia) {

		var uploadRequestBody = UploadRequest
			.builder()
			.requestBody(adicionarArquivoNaRequisicao(midia.getMidia()))
			.putObjectRequest(prepararRequisicao(midia.criarDestino()))
			.build();

		return this.s3TransferManager.upload(uploadRequestBody);
	}

	private PutObjectRequest prepararRequisicao(String destino) {
		return PutObjectRequest
			.builder()
			.bucket(bucket)
			.key(destino)
			.build();

	}

	private AsyncRequestBody adicionarArquivoNaRequisicao(InputStream arquivo) {
		try {
			return AsyncRequestBody.fromBytes(arquivo.readAllBytes());
		} catch (IOException ex) {
			throw new RuntimeException("Houve um erro ao ler a midia");
		}

	}

}
