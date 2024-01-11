package com.br.fiap.videostream.casosdeuso;

import com.br.fiap.videostream.domain.entidades.Favoritos;
import com.br.fiap.videostream.infra.bancodedados.FavoritosRepository;
import com.br.fiap.videostream.infra.bancodedados.HistoriasRepository;
import com.br.fiap.videostream.services.IMarcarHistoriaComoFavorita;
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

	public Mono<HistoriaDTO> adicionar(AdicionarHistoriaComoFavoritoForm favoritoForm) {
		var favoritos = new Favoritos(favoritoForm.id());

		return this.historiasRepository
			.findById(favoritos.getvideoId())
			.flatMap(historia -> {
				favoritos.addHistorias(historia);
				return this.favoritosRepository.save(favoritos)
					.map(item -> new HistoriaDTO().converterHistoriaParaHistoriaDTO(item.getHistorias().get(0)));
			});
	}

}
