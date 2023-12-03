package com.br.fiap.videostream.domain.entidades;

import java.util.UUID;

public class Usuario {

	private UUID id;

	private String nome;

	private String email;

	private String senha;

	public UUID getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}
}
