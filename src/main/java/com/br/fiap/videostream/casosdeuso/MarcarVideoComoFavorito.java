package com.br.fiap.videostream.casosdeuso;

import com.br.fiap.videostream.domain.entidades.Favoritos;
import com.br.fiap.videostream.infra.bancodedados.FavoritosRepository;
import com.br.fiap.videostream.infra.bancodedados.HistoriasRepository;
import com.br.fiap.videostream.view.DTO.HistoriaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class MarcarVideoComoFavorito {

	private FavoritosRepository favoritosRepository;

	private HistoriasRepository historiasRepository;

	@Autowired
	public MarcarVideoComoFavorito(FavoritosRepository favoritosRepository, HistoriasRepository historiasRepository) {
		this.favoritosRepository = favoritosRepository;
		this.historiasRepository = historiasRepository;
	}

	public Mono<HistoriaDTO> marcarComoFavorito(String id) {
		var historiaDTO = new HistoriaDTO();

		return this.historiasRepository
			.findById(id).map(historia -> {
				historiaDTO.converterHistoriaParaHistoriaDTO(historia);
				this.favoritosRepository.save(new Favoritos(historiaDTO.getId()));
				return historiaDTO;
			});
	}

}
