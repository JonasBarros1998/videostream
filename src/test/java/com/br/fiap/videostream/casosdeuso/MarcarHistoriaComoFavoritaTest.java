package com.br.fiap.videostream.casosdeuso;

import com.br.fiap.videostream.domain.entidades.Favoritos;
import com.br.fiap.videostream.domain.entidades.Historia;
import com.br.fiap.videostream.domain.entidades.Midia;
import com.br.fiap.videostream.domain.enuns.Categoria;
import com.br.fiap.videostream.infra.bancodedados.FavoritosRepository;
import com.br.fiap.videostream.infra.bancodedados.HistoriasRepository;
import com.br.fiap.videostream.view.forms.AdicionarHistoriaComoFavoritoForm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import static org.mockito.Mockito.*;

@ActiveProfiles(value = "test")
@SpringBootTest
class MarcarHistoriaComoFavoritaTest {

	@Mock
	private HistoriasRepository historiasRepository;

	@Mock
	private FavoritosRepository favoritosRepository;

	@InjectMocks
	MarcarHistoriaComoFavorita marcarHistoriaComoFavorita;

	AutoCloseable mock;

	@BeforeEach
	void setup() {
		mock = MockitoAnnotations.openMocks(this);
		marcarHistoriaComoFavorita = new MarcarHistoriaComoFavorita(favoritosRepository, historiasRepository);
	}

	@Test
	void deveMarcarUmVideComoFavorito() {
		//Arrange
		final var idDaMidia = "123456789";
		var adicionarHistoriaComoFavorita = new AdicionarHistoriaComoFavoritoForm(idDaMidia);
		var historia = new Historia("Um titulo", "Uma descricao", Categoria.TERROR, new Midia(), 10);

		var favoritos = new Favoritos(idDaMidia);
		favoritos.addHistorias(historia);

		when(historiasRepository.findById(any(String.class))).thenReturn(Mono.just(historia));
		when(favoritosRepository.save(any(Favoritos.class))).thenReturn(Mono.just(favoritos));

		//Act & Assert
		StepVerifier
			.create(marcarHistoriaComoFavorita.adicionar(adicionarHistoriaComoFavorita))
			.expectNextMatches(verificar -> {
				var tituloEhNulo = verificar.getTitulo() != null;
				var descricaoEhNulo = verificar.getDescricao() != null;
				var categoriaEhNulo = verificar.getCategoria() != null;
				return tituloEhNulo & descricaoEhNulo & categoriaEhNulo;
			})
			.expectComplete()
			.verify();
	}
}
