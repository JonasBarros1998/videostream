package com.br.fiap.videostream.infra.sqs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import software.amazon.awssdk.services.sqs.SqsAsyncClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageResponse;

import static org.mockito.Mockito.*;

@ActiveProfiles(value = "test")
@SpringBootTest
public class MensagensTest {


	Mensagens mensagens;

	@Mock
	SqsAsyncClient sqsAsyncClient;

	AutoCloseable mock;

	@BeforeEach
	void setup() {
		mock = MockitoAnnotations.openMocks(this);
		mensagens = new Mensagens(sqsAsyncClient);
	}

	@Test
	void deveEnviarMensagensParaAFilaDoSQS() {
		//Arrange
		var prepararMensagem = "{nomeDaMidia: \"minha_midia.mp4\"}";
		when(sqsAsyncClient.sendMessage(any(SendMessageRequest.class))).thenReturn(any());

		//Act
		mensagens.enviar(prepararMensagem);

		//Arrange
		verify(sqsAsyncClient, times(1)).sendMessage(any(SendMessageRequest.class));

	}
}
