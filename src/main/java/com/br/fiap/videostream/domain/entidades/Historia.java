package com.br.fiap.videostream.domain.entidades;

import com.br.fiap.videostream.domain.enuns.Categoria;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
public class Historia {

	@Id
	private String id;

	private String titulo;

	private String descricao;

	private LocalDateTime dataDePublicacao = LocalDateTime.now();

	private Categoria categorias;

	private Integer visualizacao;

	private Midia midia;

	public Historia(String titulo, String descricao, Categoria categorias,  Midia midia, Integer visualizacao) {
		this.titulo = titulo;
		this.descricao = descricao;
		this.categorias = categorias;
		this.visualizacao = visualizacao;
		this.midia = midia;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public LocalDateTime getDataDePublicacao() {
		return dataDePublicacao;
	}

	public String getId() {
		return id;
	}

	public Categoria getCategorias() {
		return categorias;
	}

	public Integer getVisualizacao() {
		return visualizacao;
	}

	public Midia getMidia() {
		return midia;
	}

}
