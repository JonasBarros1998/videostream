package com.br.fiap.videostream.services;

import reactor.core.publisher.Mono;

public interface IDesativarHistorias {

	Mono<Void> desativar(String id);
}
