package com.br.fiap.videostream.view.DTO;

import com.br.fiap.videostream.adapters.DTO.IHistoriaDTO;
import com.br.fiap.videostream.domain.entidades.Historia;
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

	public HistoriaDTO(){}

	HistoriaDTO(String titulo, String descricao, Categoria categoria) {
		this.titulo = titulo;
		this.descricao = descricao;
		this.categoria = categoria;
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
			this.historiaForm.categoria()
		);
	}

	public HistoriaDTO converterHistoriaParaHistoriaDTO(Historia historia) {
		return new HistoriaDTO(
			historia.getTitulo(),
			historia.getDescricao(),
			historia.getCategorias(),
			historia.getId()
		);
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
}
