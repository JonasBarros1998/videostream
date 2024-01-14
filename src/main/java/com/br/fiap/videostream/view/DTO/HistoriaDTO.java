package com.br.fiap.videostream.view.DTO;

import com.br.fiap.videostream.domain.entidades.Historia;
import com.br.fiap.videostream.domain.entidades.Midia;
import com.br.fiap.videostream.domain.enuns.Categoria;
import com.br.fiap.videostream.view.forms.HistoriaForm;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HistoriaDTO {

	private HistoriaForm historiaForm;

	private String titulo;
	private String descricao;
	private String id;
	private List<Categoria> categorias;

	private Midia midia;
	private String urlDaMidia;

	public HistoriaDTO(){}

	HistoriaDTO(String titulo, String descricao, List<Categoria> categorias, Midia midia) {
		this.titulo = titulo;
		this.descricao = descricao;
		this.categorias = categorias;
		this.midia = midia;
	}

	HistoriaDTO(String titulo, String descricao, List<Categoria> categorias, String id) {
		this.titulo = titulo;
		this.descricao = descricao;
		this.id = id;
		this.categorias = categorias;
	}

	public HistoriaDTO(HistoriaForm historiaForm) {
		this.historiaForm = historiaForm;
	}

	public HistoriaDTO converterHistoriaFormParaHistoriaDTO() {
		return new HistoriaDTO(
			this.historiaForm.titulo(),
			this.historiaForm.descricao(),
			this.historiaForm.categorias(),
			this.midia
		);
	}

	public HistoriaDTO converterHistoriaParaHistoriaDTO(Historia historia) {
		this.titulo = historia.getTitulo();
		this.descricao = historia.getDescricao();
		this.id = historia.getId();
		this.categorias = historia.getCategorias();
		this.midia = historia.getMidia();
		return this;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getId() {
		return id;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public Midia getMidia() {
		return midia;
	}

	@JsonInclude(JsonInclude.Include.NON_NULL)
	public String getUrlDaMidia() {
		return urlDaMidia;
	}

	public void setUrlDaMidia(String urlDaMidia) {
		this.urlDaMidia = urlDaMidia;
	}

}
