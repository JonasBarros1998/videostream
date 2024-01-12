package com.br.fiap.videostream.services;

import com.br.fiap.videostream.view.DTO.HistoriaDTO;
import com.br.fiap.videostream.view.forms.AtualizarHistoriaForm;
import reactor.core.publisher.Mono;

public interface IAtualizarHistorias {
	Mono<HistoriaDTO> atualizar(String idHistoria, AtualizarHistoriaForm atualizarHistoriaForm);

}
