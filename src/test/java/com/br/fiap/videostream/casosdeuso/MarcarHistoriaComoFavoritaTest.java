package com.br.fiap.videostream.casosdeuso;

import com.br.fiap.videostream.domain.entidades.Favoritos;
import com.br.fiap.videostream.domain.entidades.Historia;
import com.br.fiap.videostream.domain.entidades.Midia;
import com.br.fiap.videostream.domain.enuns.Categoria;
import com.br.fiap.videostream.infra.bancodedados.FavoritosRepository;
import com.br.fiap.videostream.infra.bancodedados.HistoriasRepository;
import com.br.fiap.videostream.view.forms.AdicionarHistoriaComoFavoritoForm;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;

import static org.mockito.Mockito.*;

@ActiveProfiles(value = "test")
@SpringBootTest
public class MarcarHistoriaComoFavoritaTest {

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
	public void deveMarcarUmVideComoFavorito() {
		//Arrange
		final var idDaMidia = "65a2d148630ba01b615fa898";
		var adicionarHistoriaComoFavorita = new AdicionarHistoriaComoFavoritoForm(idDaMidia);
		var historia = new Historia("Um titulo", "Uma descricao", Arrays.asList(Categoria.TERROR), new Midia(), 10);

		var favoritos = new Favoritos(new ObjectId(idDaMidia));
		favoritos.setId("65a2d148630ba01b615fa898");
		when(historiasRepository.findById(any(String.class))).thenReturn(Mono.just(historia));
		when(favoritosRepository.save(any(Favoritos.class))).thenReturn(Mono.just(favoritos));

		//Act & Assert
		StepVerifier
			.create(marcarHistoriaComoFavorita.adicionar(adicionarHistoriaComoFavorita))
			.expectNextMatches(verificar -> {
				System.out.println("verificar >> " + verificar);
				var tituloEhNulo = verificar.getHistoriaId() != null;
				var descricaoEhNulo = verificar.getId() != null;
				return tituloEhNulo & descricaoEhNulo;
			})
			.expectComplete()
			.verify();
	}
}
