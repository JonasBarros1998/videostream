package com.br.fiap.videostream.infra.bancodedados.projections;

import com.br.fiap.videostream.domain.entidades.Midia;
import com.br.fiap.videostream.domain.enuns.Categoria;

import java.time.LocalDateTime;
import java.util.List;

public class RecomendarHistoriasComBaseNosFavoritosProjection {
	private String id;

	private String titulo;

	private String descricao;

	private List<Categoria> categorias;

	private Integer visualizacao;

	private Midia midia;

	private Boolean status;

	private LocalDateTime dataDePublicacao;

	public String getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public Integer getVisualizacao() {
		return visualizacao;
	}

	public Midia getMidia() {
		return midia;
	}

	public Boolean getStatus() {
		return status;
	}

	public LocalDateTime getDataDePublicacao() {
		return dataDePublicacao;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public void setVisualizacao(Integer visualizacao) {
		this.visualizacao = visualizacao;
	}

	public void setMidia(Midia midia) {
		this.midia = midia;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public void setDataDePublicacao(LocalDateTime dataDePublicacao) {
		this.dataDePublicacao = dataDePublicacao;
	}
}
