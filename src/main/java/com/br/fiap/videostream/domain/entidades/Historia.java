package com.br.fiap.videostream.domain.entidades;

import com.br.fiap.videostream.domain.enuns.Categoria;
import com.br.fiap.videostream.infra.bancodedados.projections.RecomendarHistoriasComBaseNosFavoritosProjection;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document
public class Historia {

	@Id
	private String id;

	private String titulo;

	private String descricao;

	private LocalDateTime dataDePublicacao = LocalDateTime.now();

	private List<Categoria> categorias;

	private Integer visualizacao;

	private Midia midia;

	private Boolean status = Boolean.TRUE;

	public Historia(String titulo, String descricao, List<Categoria> categorias,  Midia midia, Integer visualizacao) {
		this.titulo = titulo;
		this.descricao = descricao;
		this.categorias = categorias;
		this.visualizacao = visualizacao;
		this.midia = midia;
	}

	public Historia(RecomendarHistoriasComBaseNosFavoritosProjection recomendacoesDeHistorias) {
		this.setMidia(recomendacoesDeHistorias.getMidia());
		this.setTitulo(recomendacoesDeHistorias.getTitulo());
		this.setDescricao(recomendacoesDeHistorias.getDescricao());
		this.setStatus(recomendacoesDeHistorias.getStatus());
		this.setVisualizacao(recomendacoesDeHistorias.getVisualizacao());
		this.setCategorias(recomendacoesDeHistorias.getCategorias());
	}

	public Historia() {}

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

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public Integer getVisualizacao() {
		return visualizacao;
	}

	public Midia getMidia() {
		return midia;
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

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public void setVisualizacao(Integer visualizacao) {
		this.visualizacao = visualizacao;
	}

	public void setMidia(Midia midia) {
		this.midia = midia;
	}

}
