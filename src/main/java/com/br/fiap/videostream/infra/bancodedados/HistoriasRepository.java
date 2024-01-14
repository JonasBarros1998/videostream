package com.br.fiap.videostream.infra.bancodedados;

import com.br.fiap.videostream.domain.entidades.Historia;
import com.br.fiap.videostream.infra.bancodedados.projections.QuantidadeTotalDeHistoriasFavoritadasProjection;
import com.br.fiap.videostream.infra.bancodedados.projections.QuantidadeTotalDeVideosEMediaDeVisualizacoesProjection;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface HistoriasRepository extends ReactiveMongoRepository<Historia, String> {

	@Aggregation(pipeline = {
		"{$match: {status: true}}",
		"{$group: {_id: null, mediaDeVisualizacoes: {$avg: '$visualizacao'}, totalDeHistorias:  {$sum:  1}}}"})
	Flux<QuantidadeTotalDeVideosEMediaDeVisualizacoesProjection> obterQuantidadeTotalDeVideosEMediaDeVisualizacoes();

	@Query("{'status':  true}")
	Flux<Historia> findAll(Pageable pageable);

	Mono<Historia> findByTituloAndStatusIsTrue(String titulo);

	Flux<Historia> findAllByCategoriasAndStatusIsTrue(String categoria, Pageable paginacao);
}
