package com.br.fiap.videostream.domain.enuns;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.stream.Stream;

public enum Categoria {

	ACAO("ACAO"),
	ANIMACAO("ANIMACAO"),
	FICCAO("FICCAO"),
	TERROR("TERROR");

	private final String categoria;

	Categoria(String categoria) {
		this.categoria = categoria;
	}

	@JsonCreator
	public static Categoria decode(final String tipo) {
		return Stream.of(Categoria.values())
			.filter(targetEnum -> targetEnum.categoria.equals(tipo))
			.findFirst()
			.orElseThrow();
	}

	@JsonValue
	public String getCode() {
		return categoria;
	}

}
