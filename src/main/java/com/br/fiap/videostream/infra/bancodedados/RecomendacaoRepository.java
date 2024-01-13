package com.br.fiap.videostream.infra.bancodedados;

import com.br.fiap.videostream.domain.entidades.Historia;
import com.br.fiap.videostream.infra.bancodedados.projections.RecomendarHistoriasComBaseNosFavoritosProjection;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.List;

@Repository
public interface RecomendacaoRepository extends ReactiveMongoRepository<Historia, String> {

	@Aggregation(pipeline = {
		"{$lookup: {from: 'favoritos',localField: '_id',foreignField: 'videoId',as: 'favoritos'}}",
		"{$match: {'categorias': {$elemMatch: { $in: :#{#categorias}}}}}",
		"{$unset: 'favoritos'}"})
	Flux<RecomendarHistoriasComBaseNosFavoritosProjection> recomendarHistoriasComBaseNosFavoritos(@Param("categorias")List<String> categorias);
}
