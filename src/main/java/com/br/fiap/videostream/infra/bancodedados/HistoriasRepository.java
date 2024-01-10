package com.br.fiap.videostream.infra.bancodedados;

import com.br.fiap.videostream.domain.entidades.Historia;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface HistoriasRepository extends ReactiveMongoRepository<Historia, String> {
	Flux<Historia> findAllByDataDePublicacao(Pageable pageable);

	Mono<Historia> findAllByTitulo(String titulo);

	Flux<Historia> findAllByCategorias(String categoria);
}
