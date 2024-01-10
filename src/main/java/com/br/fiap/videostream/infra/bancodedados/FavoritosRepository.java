package com.br.fiap.videostream.infra.bancodedados;

import com.br.fiap.videostream.domain.entidades.Favoritos;
import com.br.fiap.videostream.infra.bancodedados.projections.QuantidadeTotalDosVideosFavoritadosProjection;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface FavoritosRepository extends ReactiveMongoRepository<Favoritos, String> {

	@Query(value="{ '$group': {_id: null, totalDeVideosFavoritados: {$sum: 1}}}")
	Mono<QuantidadeTotalDosVideosFavoritadosProjection> obterTodosOsVideosFavoritados();

}
