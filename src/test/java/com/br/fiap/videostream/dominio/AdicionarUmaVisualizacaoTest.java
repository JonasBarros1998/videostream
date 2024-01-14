package com.br.fiap.videostream.dominio;


import com.br.fiap.videostream.domain.AdicionarUmaVisualizacao;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(value = "test")
@SpringBootTest
public class AdicionarUmaVisualizacaoTest {

	@Test
	public void deveAdicionarUmaNovaVisualizacaEmUmaMidia() {
		//Arrange
		var adicionarVisualizacao = new AdicionarUmaVisualizacao();

		//Act
		Integer quantidadeDeVisualizacoes = adicionarVisualizacao.adicionar(10);

		//Assert
		Assertions.assertThat(quantidadeDeVisualizacoes).isEqualTo(11);
	}
}
