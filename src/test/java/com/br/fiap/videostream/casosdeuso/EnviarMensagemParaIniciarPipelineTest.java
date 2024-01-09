package com.br.fiap.videostream.casosdeuso;

import com.br.fiap.videostream.casosdeuso.erros.ProcessarJsonException;
import com.br.fiap.videostream.domain.entidades.Midia;
import com.br.fiap.videostream.infra.sqs.Mensagens;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.assertj.core.api.Assertions;
import reactor.core.publisher.Mono;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ActiveProfiles(value = "test")
@SpringBootTest
class EnviarMensagemParaIniciarPipelineTest {

	EnviarMensagemParaIniciarPipeline enviarMensagemParaIniciarPipeline;

	@Mock
	Mensagens mensagens;

	AutoCloseable mock;



	@BeforeEach
	void setup() {
		mock = MockitoAnnotations.openMocks(this);
		enviarMensagemParaIniciarPipeline = new EnviarMensagemParaIniciarPipeline(mensagens);
	}

	InputStream criarArquivoDeEntrada() throws FileNotFoundException {
		return new FileInputStream("src/test/resources/ArquivoDeTeste.txt");
	}

	@Test
	void deveEnviarAMensagemParaAFilaDeMensagensDoSqs() throws FileNotFoundException, JsonProcessingException {
		//Arrange
		var midia = new Midia();
		midia.setNomeDaMidia("ArquivoDeTeste.txt");
		midia.setMidiaStream(criarArquivoDeEntrada());
		var mapearObjeto = new ObjectMapper();
		var objetoMapeado = mapearObjeto.writeValueAsString(midia);

		//Act
		enviarMensagemParaIniciarPipeline.iniciarProcessamentoDeVideo(midia);

		//Assert
		verify(mensagens, times(1)).enviar(objetoMapeado);
	}

}
