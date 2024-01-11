package com.br.fiap.videostream;

import com.br.fiap.videostream.domain.entidades.Midia;
import com.br.fiap.videostream.infra.s3.MultipartUploadData;
import com.br.fiap.videostream.infra.sqs.Mensagens;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.time.LocalDateTime;

@SpringBootApplication
public class VideostreamApplication {

	public static void main(String[] args) {
		SpringApplication.run(VideostreamApplication.class, args);
	}

}
