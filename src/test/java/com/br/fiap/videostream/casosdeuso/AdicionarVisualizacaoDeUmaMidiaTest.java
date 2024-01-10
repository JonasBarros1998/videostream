package com.br.fiap.videostream.casosdeuso;

import com.br.fiap.videostream.domain.entidades.Historia;
import com.br.fiap.videostream.domain.enuns.Categoria;
import com.br.fiap.videostream.infra.bancodedados.HistoriasRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.UUID;

import static org.mockito.Mockito.*;

@ActiveProfiles(value = "test")
@SpringBootTest
class AdicionarVisualizacaoDeUmaMidiaTest {

	@Mock
	private HistoriasRepository historiasRepository;

	@InjectMocks
	private AdicionarVisualizacaoDeUmaMidia adicionarVisualizacaoDeUmaMidia;

	AutoCloseable mock;

	@BeforeEach
	void setup() {
		mock = MockitoAnnotations.openMocks(this);
		this.adicionarVisualizacaoDeUmaMidia = new AdicionarVisualizacaoDeUmaMidia(historiasRepository);
	}

	@Test
	void deveAdicionarUmaNovaVisualizacaoEmUmaMidia() {
		//Arrange
		var id = UUID.randomUUID().toString();
		when(historiasRepository.findById(id))
			.thenReturn(Mono.just(new Historia("Uma serie", "Uma descricao", Categoria.FICCAO, 20)));

		//Act & Assert
		StepVerifier
			.create(this.adicionarVisualizacaoDeUmaMidia.adicionarVisualizacao(id))
			.expectNextMatches(verificar -> verificar.getVisualizacao() == 21)
			.expectComplete()
			.verify();
	}
}
