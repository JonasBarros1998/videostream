package com.br.fiap.videostream.infra.bancodedados.projections;

import com.br.fiap.videostream.domain.enuns.Categoria;

import java.util.List;

public class BuscarAsCategoriasDosFavoritosProjection {

	private List<Categoria> categorias;

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

}
