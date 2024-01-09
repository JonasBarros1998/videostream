package com.br.fiap.videostream.casosdeuso;

import com.br.fiap.videostream.adapters.mensagens.IMensagens;
import com.br.fiap.videostream.domain.entidades.Midia;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnviarMensagemParaIniciarPipeline {

	private final IMensagens mensagens;

	@Autowired
	public EnviarMensagemParaIniciarPipeline(IMensagens mensagens) {
		this.mensagens = mensagens;
	}

	public void iniciarProcessamentoDeVideo(Midia midia) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		var converterObjetoParaJson = mapper.writeValueAsString(midia);
		mensagens.enviar(converterObjetoParaJson);
	}
}
