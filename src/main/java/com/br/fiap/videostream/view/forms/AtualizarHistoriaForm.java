package com.br.fiap.videostream.view.forms;

import com.br.fiap.videostream.domain.enuns.Categoria;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record AtualizarHistoriaForm(

	@NotEmpty(message = "campo titulo e obrigatorio")
	String titulo,

	@NotEmpty(message = "campo descricao e obrigatorio")
	String descricao,

	@NotNull(message="o campo categoria e obrigatorio")
	@Valid
	List<Categoria> categorias
) {
}
