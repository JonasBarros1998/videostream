package com.br.fiap.videostream.infra.s3;

import com.br.fiap.videostream.view.DTO.HistoriaDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.s3.S3Utilities;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;

import java.time.Duration;

@Component
public class AcessarUrlDaMidia {

	@Value("${aplicacao.s3.buckets.videos-processados}")
	private String bucket;

	public String buscarUrlDaMidia(String bucketDeDestino) {

		S3Presigner presigner = S3Presigner.create();

		var acessarUrl = GetObjectRequest
			.builder()
			.bucket(bucket)
			.key(bucketDeDestino)
			.build();

		GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
			.signatureDuration(Duration.ofMinutes(10))
			.getObjectRequest(acessarUrl)
			.build();

		PresignedGetObjectRequest presignedRequest = presigner.presignGetObject(presignRequest);
		return presignedRequest.url().toString();
	}

}
