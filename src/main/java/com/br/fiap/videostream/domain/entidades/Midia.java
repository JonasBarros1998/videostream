package com.br.fiap.videostream.domain.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Transient;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public String criarDestino() {
		DateTimeFormatter dataEHoraFormatada = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH-mm-ss");
		var dataEHora = LocalDateTime.now().format(dataEHoraFormatada).toString();
		this.destino = "%s/%s.m3u8".formatted(dataEHora, this.nomeDaMidia);
		return this.destino;
	}
}
