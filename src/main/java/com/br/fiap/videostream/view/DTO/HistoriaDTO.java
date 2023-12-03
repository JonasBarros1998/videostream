package com.br.fiap.videostream.view.DTO;

import com.br.fiap.videostream.adapters.DTO.IHistoriaDTO;
import com.br.fiap.videostream.view.forms.HistoriaForm;
import org.springframework.stereotype.Component;

@Component
public class HistoriaDTO implements IHistoriaDTO {

	private HistoriaForm historiaForm;
	private String titulo;
	private String descricao;

	public HistoriaDTO(){}

	private HistoriaDTO(String titulo, String descricao) {
		this.titulo = titulo;
		this.descricao = descricao;
	}

	public HistoriaDTO(HistoriaForm historiaForm) {
		this.historiaForm = historiaForm;
	}

	@Override
	public HistoriaDTO converterHistoriaFormParaHistoriaDTO() {
		return new HistoriaDTO(
			this.historiaForm.titulo(),
			this.historiaForm.descricao()
		);
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescricao() {
		return descricao;
	}
}
