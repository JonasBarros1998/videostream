package com.br.fiap.videostream.domain;

public class AdicionarUmaVisualizacao {

	private Integer visualizacao = 1;

	private Integer somarVisualizacoes;

	public Integer adicionar(Integer quantidadeTotalDeVisualizacoes) {
		somarVisualizacoes = quantidadeTotalDeVisualizacoes + visualizacao;
		return somarVisualizacoes;
	}

	public Integer getVisualizacao() {
		return somarVisualizacoes;
	}
}
