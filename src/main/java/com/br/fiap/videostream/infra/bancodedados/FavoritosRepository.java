package com.br.fiap.videostream.infra.bancodedados;

import com.br.fiap.videostream.domain.entidades.Favoritos;
import com.br.fiap.videostream.infra.bancodedados.projections.QuantidadeTotalDeHistoriasFavoritadasProjection;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface FavoritosRepository extends ReactiveMongoRepository<Favoritos, String> {

	@Query("{}")
	Flux<Favoritos> findAll(Pageable pageable);

	@Aggregation(value="{ $group: {_id: 0, totalDeHistoriasFavoritadas: {$sum: 1} } }")
	Flux<QuantidadeTotalDeHistoriasFavoritadasProjection> obterTodosOsVideosFavoritados();

}
