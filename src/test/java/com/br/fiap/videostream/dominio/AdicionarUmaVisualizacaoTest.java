package com.br.fiap.videostream.dominio;


import com.br.fiap.videostream.domain.AdicionarUmaVisualizacao;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

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
