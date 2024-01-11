package com.br.fiap.videostream.domain.entidades;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
public class Favoritos {

	@Id
	private String id;

	private String videoId;

	@DBRef
	private List<Historia> historias = new ArrayList<>();

	public Favoritos(String videoId) {
		this.videoId = videoId;
	}

	public String getvideoId() {
		return videoId;
	}

	public String getId() {
		return id;
	}


	public List<Historia> getHistorias() {
		return historias;
	}

	public Favoritos addHistorias(Historia historia) {
		historias.add(historia);
		return this;
	}

}
