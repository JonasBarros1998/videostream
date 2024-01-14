package com.br.fiap.videostream.casosdeuso;

import com.br.fiap.videostream.domain.entidades.Midia;
import com.br.fiap.videostream.domain.enuns.Categoria;
import com.br.fiap.videostream.infra.bancodedados.FavoritosRepository;
import com.br.fiap.videostream.infra.bancodedados.RecomendacaoRepository;
import com.br.fiap.videostream.infra.bancodedados.projections.BuscarAsCategoriasDosFavoritosProjection;
import com.br.fiap.videostream.infra.bancodedados.projections.RecomendarHistoriasComBaseNosFavoritosProjection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@ActiveProfiles(value = "test")
@SpringBootTest
public class RecomendacoesDeHistoriasTest {

	@InjectMocks
	RecomendacoesDeHistorias recomendacoesDeHistoriasTest;

	@Mock
	RecomendacaoRepository recomendacoesRepository;

	@Mock
	FavoritosRepository favoritosRepository;

	AutoCloseable mock;

	RecomendarHistoriasComBaseNosFavoritosProjection criarRecomendarHistoriasComBaseNosFavoritosProjection() {
		var recomendacoesDeHistorias = new RecomendarHistoriasComBaseNosFavoritosProjection();
		recomendacoesDeHistorias.setDescricao("Uma Descricao");
		recomendacoesDeHistorias.setTitulo("Um titulo");
		recomendacoesDeHistorias.setDataDePublicacao(LocalDateTime.now());
		recomendacoesDeHistorias.setCategorias(Arrays.asList(Categoria.TERROR));
		recomendacoesDeHistorias.setStatus(Boolean.TRUE);
		recomendacoesDeHistorias.setVisualizacao(2);
		recomendacoesDeHistorias.setId("65a2d148630ba01b615fa898");
		recomendacoesDeHistorias.setMidia(new Midia());

		return recomendacoesDeHistorias;
	}

	@BeforeEach
	void setup() {
		mock = MockitoAnnotations.openMocks(this);
		recomendacoesDeHistoriasTest = new RecomendacoesDeHistorias(recomendacoesRepository, favoritosRepository);
	}

	@Test
	void deveBuscarAsRecomendacoesComBaseNosFavoritos() {
		//Arrange
		var buscarCategoriasDosFavoritos = new BuscarAsCategoriasDosFavoritosProjection();
		buscarCategoriasDosFavoritos.setCategorias(Arrays.asList(Categoria.TERROR));

		when(favoritosRepository.buscarCategoriasDosFavoritos()).thenReturn(Mono.just(buscarCategoriasDosFavoritos));

		when(recomendacoesRepository.recomendarHistoriasComBaseNosFavoritos(any(List.class)))
			.thenReturn(Flux.just(criarRecomendarHistoriasComBaseNosFavoritosProjection()));

		//Act & Assert
		StepVerifier
			.create(recomendacoesDeHistoriasTest.buscarRecomendacoes())
			.expectNextMatches(verificar -> {
				var DescricaoEhDiferenteDeNull = verificar.getDescricao() != null;
				var tituloEhDiferenteDeNull = verificar.getTitulo() != null;
				var categoriaEhDiferenteDeNull = verificar.getCategorias() != null;
				return DescricaoEhDiferenteDeNull && tituloEhDiferenteDeNull && categoriaEhDiferenteDeNull;
			})
			.expectComplete()
			.verify();
	}


}
