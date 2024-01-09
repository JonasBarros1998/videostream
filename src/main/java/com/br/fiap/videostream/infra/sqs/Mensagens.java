package com.br.fiap.videostream.infra.sqs;

import com.br.fiap.videostream.adapters.mensagens.IMensagens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageResponse;

import java.util.concurrent.CompletableFuture;

@Component
public final class Mensagens implements IMensagens {
	final SqsAsyncClient sqsAsyncClient;

	@Value("${aplicacao.sqs.url}")
	String urlFila;

	@Autowired
	public Mensagens(SqsAsyncClient sqsAsyncClient) {
		this.sqsAsyncClient = sqsAsyncClient;
	}

	public CompletableFuture<SendMessageResponse> enviar(String mensagem) {
		var enviarMensagem = SendMessageRequest
			.builder()
			.queueUrl(urlFila)
			.messageBody(mensagem).build();

		return this.sqsAsyncClient.sendMessage(enviarMensagem);
	}

}
