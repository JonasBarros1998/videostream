package com.br.fiap.videostream.infra.bancodedados;

import com.br.fiap.videostream.domain.entidades.Favoritos;
import com.br.fiap.videostream.infra.bancodedados.projections.BuscarAsCategoriasDosFavoritosProjection;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface FavoritosRepository extends ReactiveMongoRepository<Favoritos, String> {

	@Query("{'status':  true}")
	Flux<Favoritos> findAll(Pageable pageable);

	@Aggregation(pipeline = {
		"{$lookup: {from: 'historia',localField: 'historiaId',foreignField: '_id',as: 'historia'}}",
		"{$unwind: '$historia'}",
		"{$unwind: '$historia.categorias' }",
		"{$group: {_id: 0,categorias: {$addToSet: '$historia.categorias' }}}",
		"{$unset: '_id'}"})
	Mono<BuscarAsCategoriasDosFavoritosProjection> buscarCategoriasDosFavoritos();
}
