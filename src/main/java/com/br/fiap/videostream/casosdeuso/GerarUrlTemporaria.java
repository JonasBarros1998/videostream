package com.br.fiap.videostream.casosdeuso;

import com.br.fiap.videostream.infra.s3.AcessarUrlDaMidia;
import com.br.fiap.videostream.view.DTO.HistoriaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GerarUrlTemporaria {

	private AcessarUrlDaMidia acessarUrlDaMidia;

	private final String extensao = "m3u8";

	@Autowired
	public GerarUrlTemporaria(AcessarUrlDaMidia acessarUrlDaMidia) {
		this.acessarUrlDaMidia = acessarUrlDaMidia;
	}

	public String gerarUrl(HistoriaDTO historiaDTO) {
		final var localizacao = "%s/%s.%s".formatted(
			historiaDTO.getMidia().getDestino(),
			historiaDTO.getMidia().getNomeDaMidia(),
			this.extensao);

		return this.acessarUrlDaMidia.buscarUrlDaMidia(localizacao);
	}

}
