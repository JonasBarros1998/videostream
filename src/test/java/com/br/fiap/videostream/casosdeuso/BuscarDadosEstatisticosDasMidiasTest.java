package com.br.fiap.videostream.casosdeuso;

import com.br.fiap.videostream.infra.bancodedados.FavoritosRepository;
import com.br.fiap.videostream.infra.bancodedados.HistoriasRepository;
import com.br.fiap.videostream.infra.bancodedados.projections.InformacoesBasicaDosVideosProjection;
import com.br.fiap.videostream.infra.bancodedados.projections.QuantidadeTotalDosVideosFavoritadosProjection;
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
class BuscarDadosEstatisticosDasMidiasTest {

	@Mock
	private HistoriasRepository historiasRepository;

	@Mock
	private FavoritosRepository favoritosRepository;

	@InjectMocks
	private BuscarDadosEstatisticosDasMidias buscarDadosEstatisticosDasMidias;

	AutoCloseable mock;

	@BeforeEach
	void setup() {
		mock = MockitoAnnotations.openMocks(this);
		this.buscarDadosEstatisticosDasMidias = new BuscarDadosEstatisticosDasMidias(historiasRepository, favoritosRepository);
	}

	InformacoesBasicaDosVideosProjection informacoesBasicaDosVideosProjectionImplementacao() {
		return new InformacoesBasicaDosVideosProjection() {

			@Override
			public Integer getTotalDeVideos() {
				return 10;
			}

			@Override
			public Double getMediaVisualizacoes() {
				return 20.0;
			}
		};
	}

	QuantidadeTotalDosVideosFavoritadosProjection quantidadeTotalDosVideosFavoritadosProjectionImplementacao() {
		return () -> 20;
	}

	@Test
	void deveBuscarAQuantidadeTotalDeVideosEAMediaDeVisualizacoes() {

		//Arrange
		when(historiasRepository
			.obterQuantidadeTotalDeVideosEMediaDeVisualizacoes())
			.thenReturn(Mono.just(informacoesBasicaDosVideosProjectionImplementacao()));

		//Act & Assert
		StepVerifier
			.create(buscarDadosEstatisticosDasMidias.buscarQuantidadeTotalDeVideosEMediaDeVisualizacoes())
			.expectNextMatches(verificar -> verificar.totalDeVideos() != null && verificar.mediaDeVisualizacoes() != null)
			.expectComplete()
			.verify();
	}

	@Test
	void deveBuscarQuantidadeTotalDosVideosFavoritados() {

		//Arrange
		when(favoritosRepository
			.obterTodosOsVideosFavoritados())
			.thenReturn(Mono.just(quantidadeTotalDosVideosFavoritadosProjectionImplementacao()));

		//Act & Assert
		StepVerifier
			.create(buscarDadosEstatisticosDasMidias.obterTodosOsVideosFavoritados())
			.expectNextMatches(verificar -> verificar.totalDeFavoritos() != null)
			.expectComplete()
			.verify();
	}



}
