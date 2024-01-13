package com.br.fiap.videostream.domain.entidades;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Favoritos {

	@Id
	private String id;

	private ObjectId historiaId;

	public Favoritos(ObjectId historiaId) {
		this.historiaId = historiaId;
	}

	public ObjectId getHistoriaId() {
		return historiaId;
	}

	public String getId() {
		return id;
	}

	/*
	public List<Historia> getHistorias() {
		return historias;
	}

	public Favoritos addHistorias(Historia historia) {
		historias.add(historia);
		return this;
	}*/

}
