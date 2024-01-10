package com.br.fiap.videostream.services;

import com.br.fiap.videostream.view.DTO.HistoriaDTO;
import com.br.fiap.videostream.view.forms.HistoriaForm;
import reactor.core.publisher.Mono;

public interface ISalvarNovasHistorias {

	Mono<HistoriaDTO> salvar(HistoriaForm historiasForm);
}
