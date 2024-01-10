package com.br.fiap.videostream.casosdeuso;

import com.br.fiap.videostream.domain.entidades.Favoritos;
import com.br.fiap.videostream.domain.entidades.Historia;
import com.br.fiap.videostream.domain.enuns.Categoria;
import com.br.fiap.videostream.infra.bancodedados.FavoritosRepository;
import com.br.fiap.videostream.infra.bancodedados.HistoriasRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;

@ActiveProfiles(value = "test")
@SpringBootTest
class ConsultarHistoriasTest {

	@Mock
	private HistoriasRepository historiasRepository;

	@Mock
	private FavoritosRepository favoritosRepository;

	@InjectMocks
	ConsultarHistorias consultarHistorias;

	AutoCloseable mock;

	@BeforeEach
	void setup() {
		mock = MockitoAnnotations.openMocks(this);
		consultarHistorias = new ConsultarHistorias(historiasRepository, favoritosRepository);
	}

	@Test
	void deveConsultarTodasAsHistorias() {
		//Arrange
		Pageable paginacao = PageRequest.of(0, 10);
		var historia = new Historia("Um titulo", "Uma descricao", Categoria.TERROR, 10);
		when(historiasRepository.findAll(any(Pageable.class))).thenReturn(Flux.just(historia));

		//Assert & Act
		StepVerifier
			.create(consultarHistorias.consultarTodas(paginacao))
			.expectNextMatches(verificar -> verificar.size() == 1)
			.expectComplete()
			.verify();
	}

	@Test
	void deveConsultarAsHistoriasPorTitulo() {
		//Assert
		final var tituloDaHistoria = "Um titulo";
		var historia = new Historia("Um titulo", "Uma descricao", Categoria.TERROR, 10);

		when(historiasRepository.findByTitulo(any(String.class))).thenReturn(Mono.just(historia));

		//Assert & Act
		StepVerifier
			.create(consultarHistorias.consultarPorTitulo(tituloDaHistoria))
			.expectNextMatches(verificar -> verificar.getTitulo() .equals(tituloDaHistoria))
			.expectComplete()
			.verify();
	}

	@Test
	void deveConsultarHistoriasPorCategoria() {
		//Arrange
		final var tituloDaHistoria = "Um titulo";
		var historia = new Historia("Um titulo", "Uma descricao", Categoria.ANIMACAO, 10);
		when(historiasRepository.findAllByCategorias(tituloDaHistoria)).thenReturn(Flux.just(historia));

		//Act & Assert
		StepVerifier
			.create(consultarHistorias.consultarPorCategorias(tituloDaHistoria))
			.expectNextMatches(verificar -> verificar.getTitulo() .equals(tituloDaHistoria))
			.expectComplete()
			.verify();
	}

	@Test
	void deveConsultarHistoriasPorFavoritos() {
		//Arrange
		final var video = "123456789";
		Pageable paginacao = PageRequest.of(0, 10);
		when(favoritosRepository.findAll(any(Pageable.class))).thenReturn(Flux.just(new Favoritos(video)));

		//Act & Assert
		StepVerifier
			.create(consultarHistorias.consultarPorFavoritos(paginacao))
			.expectNextMatches(verificar -> verificar.size() == 1)
			.expectComplete()
			.verify();
	}

}