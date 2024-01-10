package com.br.fiap.videostream.casosdeuso;

import com.br.fiap.videostream.infra.bancodedados.FavoritosRepository;
import com.br.fiap.videostream.infra.bancodedados.HistoriasRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import reactor.test.StepVerifier;

@ActiveProfiles(value = "test")
@SpringBootTest
public class MarcarVideoComoFavoritoTest {

	@Mock
	private HistoriasRepository historiasRepository;

	@Mock
	private FavoritosRepository favoritosRepository;

	@InjectMocks
	MarcarVideoComoFavorito marcarVideoComoFavorito;

	AutoCloseable mock;

	@BeforeEach
	void setup() {
		mock = MockitoAnnotations.openMocks(this);
		marcarVideoComoFavorito = new MarcarVideoComoFavorito(favoritosRepository, historiasRepository);
	}


	@Test
	void deveMarcarUmVideComoFavorito() {
		//Arrange
		final var idDoVideo = "123456789";
		/*
		//Act & Assert
		StepVerifier
			.create(marcarVideoComoFavorito.adicionar(idDoVideo))
			.expectNextMatches(verificar -> {
				var tituloEhNulo = verificar.getTitulo() != null;
				var descricaoEhNulo = verificar.getDescricao() != null;
				return tituloEhNulo & descricaoEhNulo;
			})
			.expectComplete()
			.verify();*/
	}
}
