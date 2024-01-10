package com.br.fiap.videostream.view.DTO;

import com.br.fiap.videostream.domain.entidades.Favoritos;

public class FavoritosDTO {

	private Favoritos favoritos;
	public FavoritosDTO(Favoritos favoritos) {
		this.favoritos = favoritos;
	}

	public FavoritosDTO() {}

	public FavoritosDTO converterFavoritosParaFavoritosDTO(Favoritos favoritos) {
		return new FavoritosDTO(favoritos);
	}

}
