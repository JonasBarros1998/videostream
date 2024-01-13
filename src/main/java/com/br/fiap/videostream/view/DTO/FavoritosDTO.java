package com.br.fiap.videostream.view.DTO;

import com.br.fiap.videostream.domain.entidades.Favoritos;

public class FavoritosDTO {

	private String id;

	private String historiaId;

	public FavoritosDTO(String id, String historiaId) {
		this.id = id;
		this.historiaId = historiaId;
	}

	public FavoritosDTO() {}

	public FavoritosDTO converterFavoritosParaFavoritosDTO(Favoritos favoritos) {
		return new FavoritosDTO(favoritos.getId(), favoritos.getHistoriaId().toString());
	}

	public String getId() {
		return id;
	}

	public String getHistoriaId() {
		return historiaId;
	}
}
