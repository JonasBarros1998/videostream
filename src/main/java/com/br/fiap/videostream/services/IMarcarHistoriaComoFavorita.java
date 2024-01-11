package com.br.fiap.videostream.services;

import com.br.fiap.videostream.view.DTO.HistoriaDTO;
import com.br.fiap.videostream.view.forms.AdicionarHistoriaComoFavoritoForm;
import reactor.core.publisher.Mono;

public interface IMarcarHistoriaComoFavorita {

	public Mono<HistoriaDTO> adicionar( AdicionarHistoriaComoFavoritoForm favoritoForm);
}
