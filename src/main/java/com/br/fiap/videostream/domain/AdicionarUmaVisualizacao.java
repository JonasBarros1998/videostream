package com.br.fiap.videostream.domain;

public class AdicionarUmaVisualizacao {

	Integer visualizacao = 1;

	public Integer adicionar(Integer quantidadeTotalDeVisualizacoes) {
		return quantidadeTotalDeVisualizacoes + visualizacao;
	}
}
