package com.br.fiap.videostream.infra.bancodedados.projections;

public class QuantidadeTotalDeHistoriasFavoritadasProjection {

	private Integer totalDeHistoriasFavoritadas = Integer.parseInt("0");

	public Integer getTotalDeHistoriasFavoritadas() {
		return totalDeHistoriasFavoritadas;
	}

	public void setTotalDeHistoriasFavoritadas(Integer totalDeHistoriasFavoritadas) {
		this.totalDeHistoriasFavoritadas = totalDeHistoriasFavoritadas;
	}

}
