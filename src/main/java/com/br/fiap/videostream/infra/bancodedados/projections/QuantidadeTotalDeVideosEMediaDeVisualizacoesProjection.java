package com.br.fiap.videostream.infra.bancodedados.projections;


import org.springframework.data.annotation.Id;

public class QuantidadeTotalDeVideosEMediaDeVisualizacoesProjection {

	@Id
	private Integer id;

	private Integer totalDeHistorias = Integer.parseInt("0");

	private Double mediaDeVisualizacoes = Double.parseDouble("0.0");

	public void setTotalDeHistorias(Integer totalDeHistorias) {
		this.totalDeHistorias = totalDeHistorias;
	}

	public Integer getTotalDeHistorias() {
		return totalDeHistorias;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getMediaDeVisualizacoes() {
		return mediaDeVisualizacoes;
	}

	public void setMediaDeVisualizacoes(Double mediaDeVisualizacoes) {
		this.mediaDeVisualizacoes = mediaDeVisualizacoes;
	}
}

