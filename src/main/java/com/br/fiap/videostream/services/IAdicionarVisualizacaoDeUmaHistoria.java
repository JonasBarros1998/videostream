package com.br.fiap.videostream.services;

import com.br.fiap.videostream.view.DTO.HistoriaDTO;
import reactor.core.publisher.Mono;

public interface IAdicionarVisualizacaoDeUmaHistoria {

	Mono<HistoriaDTO> adicionarVisualizacao(String id);
}
