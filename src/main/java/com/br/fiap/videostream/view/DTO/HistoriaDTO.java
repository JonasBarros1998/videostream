package com.br.fiap.videostream.view.DTO;

import com.br.fiap.videostream.adapters.DTO.IHistoriaDTO;
import com.br.fiap.videostream.domain.entidades.Historia;
import com.br.fiap.videostream.domain.entidades.Midia;
import com.br.fiap.videostream.domain.enuns.Categoria;
import com.br.fiap.videostream.view.forms.HistoriaForm;
import org.springframework.stereotype.Component;

@Component
public class HistoriaDTO implements IHistoriaDTO {

	private HistoriaForm historiaForm;
	private String titulo;
	private String descricao;
	private String id;
	private Categoria categoria;

	private Midia midia;
	private String urlDaMidia;

	public HistoriaDTO(){}

	HistoriaDTO(String titulo, String descricao, Categoria categoria, Midia midia) {
		this.titulo = titulo;
		this.descricao = descricao;
		this.categoria = categoria;
		this.midia = midia;
	}

	HistoriaDTO(String titulo, String descricao, Categoria categoria, String id) {
		this.titulo = titulo;
		this.descricao = descricao;
		this.id = id;
		this.categoria = categoria;
	}

	public HistoriaDTO(HistoriaForm historiaForm) {
		this.historiaForm = historiaForm;
	}

	@Override
	public HistoriaDTO converterHistoriaFormParaHistoriaDTO() {
		return new HistoriaDTO(
			this.historiaForm.titulo(),
			this.historiaForm.descricao(),
			this.historiaForm.categoria(),
			this.midia
		);
	}

	public HistoriaDTO converterHistoriaParaHistoriaDTO(Historia historia) {
		this.titulo = historia.getTitulo();
		this.descricao = historia.getDescricao();
		this.id = historia.getId();
		this.categoria = historia.getCategorias();
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

	public Categoria getCategoria() {
		return categoria;
	}

	public Midia getMidia() {
		return midia;
	}

	public String getUrlDaMidia() {
		return urlDaMidia;
	}

	public void setUrlDaMidia(String urlDaMidia) {
		this.urlDaMidia = urlDaMidia;
	}

}
