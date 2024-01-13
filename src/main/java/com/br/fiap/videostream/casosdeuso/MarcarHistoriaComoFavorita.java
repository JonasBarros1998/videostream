package com.br.fiap.videostream.casosdeuso;

import com.br.fiap.videostream.domain.entidades.Favoritos;
import com.br.fiap.videostream.infra.bancodedados.FavoritosRepository;
import com.br.fiap.videostream.infra.bancodedados.HistoriasRepository;
import com.br.fiap.videostream.services.IMarcarHistoriaComoFavorita;
import com.br.fiap.videostream.view.DTO.FavoritosDTO;
import com.br.fiap.videostream.view.DTO.HistoriaDTO;
import com.br.fiap.videostream.view.forms.AdicionarHistoriaComoFavoritoForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class MarcarHistoriaComoFavorita implements IMarcarHistoriaComoFavorita {

	private FavoritosRepository favoritosRepository;

	private HistoriasRepository historiasRepository;

	@Autowired
	public MarcarHistoriaComoFavorita(FavoritosRepository favoritosRepository, HistoriasRepository historiasRepository) {
		this.favoritosRepository = favoritosRepository;
		this.historiasRepository = historiasRepository;
	}

	public Mono<FavoritosDTO> adicionar(AdicionarHistoriaComoFavoritoForm favoritoForm) {
		var favoritos = new Favoritos(favoritoForm.id());
		var favoritosDTO = new FavoritosDTO();

		return this.historiasRepository
			.findById(favoritos.getHistoriaId())
			.flatMap(historia -> this.favoritosRepository.save(favoritos))
			.map(favoritosDTO::converterFavoritosParaFavoritosDTO);
	}

}
