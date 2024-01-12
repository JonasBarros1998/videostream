package com.br.fiap.videostream.casosdeuso;

import com.br.fiap.videostream.infra.s3.AcessarUrlDaMidia;
import com.br.fiap.videostream.view.DTO.HistoriaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GerarUrlTemporaria {

	private AcessarUrlDaMidia acessarUrlDaMidia;

	@Autowired
	public GerarUrlTemporaria(AcessarUrlDaMidia acessarUrlDaMidia) {
		this.acessarUrlDaMidia = acessarUrlDaMidia;
	}

	String gerarUrl(HistoriaDTO historiaDTO) {
		return this.acessarUrlDaMidia.buscarUrlDaMidia(historiaDTO.getMidia().getDestino());
	}

}
