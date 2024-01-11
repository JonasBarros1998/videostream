package com.br.fiap.videostream.domain.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.InputStream;
import java.time.LocalDateTime;

public class Midia {

	@Transient
	private InputStream midiaStream;

	private String nomeDaMidia;

	private String destino;

	public Midia() {}

	@JsonIgnore
	public InputStream getMidia() {
		return midiaStream;
	}

	public String getNomeDaMidia() {
		return nomeDaMidia;
	}

	public String getDestino() {
		return destino;
	}

	public void setMidiaStream(InputStream midiaStream) {
		this.midiaStream = midiaStream;
	}

	public void setNomeDaMidia(String nomeDaMidia) {
		this.nomeDaMidia = nomeDaMidia;
	}

	public String criarDestino() {
		this.destino = "%s/%s.mp4".formatted(LocalDateTime.now().toString(), this.nomeDaMidia);
		return this.destino;
	}
}
