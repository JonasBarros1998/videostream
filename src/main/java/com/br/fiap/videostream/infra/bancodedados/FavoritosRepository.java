package com.br.fiap.videostream.infra.bancodedados;

import com.br.fiap.videostream.domain.entidades.Favoritos;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoritosRepository extends ReactiveMongoRepository<Favoritos, String> {
}
