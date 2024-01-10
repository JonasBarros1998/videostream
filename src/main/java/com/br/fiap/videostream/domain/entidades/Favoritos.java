package com.br.fiap.videostream.domain.entidades;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Favoritos {

	@Id
	private String id;

	private String videoID;

	@DBRef
	private List<Historia> historias;

	public Favoritos(String videoID) {
		this.videoID = videoID;
	}

	public String getVideoID() {
		return videoID;
	}

	public String getId() {
		return id;
	}

	public List<Historia> getHistorias() {
		return historias;
	}

}
