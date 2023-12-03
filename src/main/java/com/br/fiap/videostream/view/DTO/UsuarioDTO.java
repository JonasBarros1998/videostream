package com.br.fiap.videostream.view.DTO;

import com.br.fiap.videostream.view.forms.UsuarioForm;
import com.br.fiap.videostream.adapters.DTO.IUsuarioDTO;
import org.springframework.stereotype.Component;

@Component
public class UsuarioDTO implements IUsuarioDTO {

	private String nome;
	private String email;
	private String senha;
	private UsuarioForm usuarioForm;

	public UsuarioDTO() {}

	public UsuarioDTO(UsuarioForm usuarioForm) {
		this.usuarioForm = usuarioForm;
	}

	private UsuarioDTO(String nome, String email, String senha) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}

	@Override
	public UsuarioDTO converterUsuarioFormParaUsuarioDTO() {
		return new UsuarioDTO(
			this.usuarioForm.nome(),
			this.usuarioForm.email(),
			this.usuarioForm.senha()
		);
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}
}
