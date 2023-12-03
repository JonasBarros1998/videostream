package com.br.fiap.videostream.view.forms;

import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

public record UsuarioForm(

	@NotEmpty(message = "O Campo nome e obrigatorio")
	@Length(message = "O campo nome deve ter no maximo 100 caracteres", max = 100)
	String nome,

	@NotEmpty(message = "O Campo email e obrigatorio")
	@Length(message = "O campo email deve ter no maximo 100 caracteres", max = 100)
	String email,

	@NotEmpty(message = "O Campo senha e obrigatorio")
	@Length(message = "O campo senha deve ter no minimo 8", min = 8)
	String senha

) {}
