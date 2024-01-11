package com.br.fiap.videostream.infra.bancodedados;

import com.br.fiap.videostream.domain.entidades.Historia;
import com.br.fiap.videostream.infra.bancodedados.projections.InformacoesBasicaDosVideosProjection;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface HistoriasRepository extends ReactiveMongoRepository<Historia, String> {

	@Query(value="{ '$group': {_id: null, totalDeVideos: {$sum: 1}, mediaVisualizacoes: {$avg: '$visualizacao'}}}")
	Mono<InformacoesBasicaDosVideosProjection> obterQuantidadeTotalDeVideosEMediaDeVisualizacoes();

	Flux<Historia> findAllByDataDePublicacaoDate(Pageable paginacao);

	@Query("{}")
	Flux<Historia> findAll(Pageable pageable);

	Mono<Historia> findByTitulo(String titulo);

	Flux<Historia> findAllByCategorias(String categoria, Pageable paginacao);
}
