package com.br.fiap.videostream.casosdeuso;

import com.br.fiap.videostream.domain.entidades.Favoritos;
import com.br.fiap.videostream.domain.entidades.Historia;
import com.br.fiap.videostream.domain.entidades.Midia;
import com.br.fiap.videostream.domain.enuns.Categoria;
import com.br.fiap.videostream.infra.bancodedados.FavoritosRepository;
import com.br.fiap.videostream.infra.bancodedados.HistoriasRepository;
import org.bson.types.ObjectId;
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

import java.util.Arrays;

import static org.mockito.Mockito.*;

@ActiveProfiles(value = "test")
@SpringBootTest
public class ConsultarHistoriasTest {

	@Mock
	private HistoriasRepository historiasRepository;

	@Mock
	private GerarUrlTemporaria gerarUrlTemporaria;

	@Mock
	private FavoritosRepository favoritosRepository;

	@InjectMocks
	ConsultarHistorias consultarHistorias;

	AutoCloseable mock;

	final Historia historia = new Historia("Um titulo", "Uma descricao", Arrays.asList(Categoria.TERROR), new Midia(),10);

	@BeforeEach
	void setup() {
		mock = MockitoAnnotations.openMocks(this);
		consultarHistorias = new ConsultarHistorias(historiasRepository, favoritosRepository, gerarUrlTemporaria);
	}

	@Test
	public void deveConsultarTodasAsHistorias() {
		//Arrange
		Pageable paginacao = PageRequest.of(0, 10);
		when(historiasRepository.findAll(any(Pageable.class))).thenReturn(Flux.just(historia));

		//Assert & Act
		StepVerifier
			.create(consultarHistorias.consultarTodas(paginacao))
			.expectNextMatches(verificar -> verificar.size() == 1)
			.expectComplete()
			.verify();
	}

	@Test
	public void deveConsultarAsHistoriasPorTitulo() {
		//Assert
		final var tituloDaHistoria = "Um titulo";

		when(historiasRepository.findByTituloAndStatusIsTrue(any(String.class))).thenReturn(Mono.just(historia));

		//Assert & Act
		StepVerifier
			.create(consultarHistorias.consultarPorTitulo(tituloDaHistoria))
			.expectNextMatches(verificar -> verificar.getTitulo() .equals(tituloDaHistoria))
			.expectComplete()
			.verify();
	}

	@Test
	public void deveConsultarHistoriasPorCategoria() {
		//Arrange
		final Pageable paginacao = PageRequest.of(1, 10);
		final var tituloDaHistoria = "Um titulo";
		when(historiasRepository.findAllByCategoriasAndStatusIsTrue(tituloDaHistoria, paginacao)).thenReturn(Flux.just(historia));

		//Act & Assert
		StepVerifier
			.create(consultarHistorias.consultarPorCategorias(tituloDaHistoria, paginacao))
			.expectNextMatches(verificar -> verificar.size() == 1)
			.expectComplete()
			.verify();
	}

	@Test
	public void deveConsultarHistoriasPorFavoritos() {
		//Arrange
		final var id = "65a2d148630ba01b615fa898";
		final Pageable paginacao = PageRequest.of(0, 10);
		when(favoritosRepository.findAll(any(Pageable.class))).thenReturn(Flux.just(new Favoritos(new ObjectId(id))));

		//Act & Assert
		StepVerifier
			.create(consultarHistorias.consultarPorFavoritos(paginacao))
			.expectNextMatches(verificar -> verificar.size() == 1)
			.expectComplete()
			.verify();
	}

}
