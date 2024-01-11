package com.br.fiap.videostream.infra.bancodedados.projections;

public class QuantidadeTotalDeHistoriasFavoritadasProjection {

	private String id;

	private Integer totalDeHistoriasFavoritadas = Integer.parseInt("0");

	public Integer getTotalDeHistoriasFavoritadas() {
		return totalDeHistoriasFavoritadas;
	}

	public void setTotalDeHistoriasFavoritadas(Integer totalDeHistoriasFavoritadas) {
		this.totalDeHistoriasFavoritadas = totalDeHistoriasFavoritadas;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
