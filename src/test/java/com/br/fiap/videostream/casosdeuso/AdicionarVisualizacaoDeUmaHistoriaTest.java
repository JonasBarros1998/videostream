package com.br.fiap.videostream.casosdeuso;

import com.br.fiap.videostream.domain.entidades.Historia;
import com.br.fiap.videostream.domain.entidades.Midia;
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

import java.util.Arrays;
import java.util.UUID;

import static org.mockito.Mockito.*;

@ActiveProfiles(value = "test")
@SpringBootTest
public class AdicionarVisualizacaoDeUmaHistoriaTest {

	@Mock
	private HistoriasRepository historiasRepository;

	@InjectMocks
	private AdicionarVisualizacaoDeUmaHistoria adicionarVisualizacaoDeUmaMidia;

	AutoCloseable mock;

	private final Historia historia = new Historia("Uma serie", "Uma descricao", Arrays.asList(Categoria.FICCAO), new Midia(),20);

	@BeforeEach
	void setup() {
		mock = MockitoAnnotations.openMocks(this);
		this.adicionarVisualizacaoDeUmaMidia = new AdicionarVisualizacaoDeUmaHistoria(historiasRepository);
	}

	@Test
	public void deveAdicionarUmaNovaVisualizacaoEmUmaMidia() {
		//Arrange
		var id = UUID.randomUUID().toString();
		when(historiasRepository.findById(id))
			.thenReturn(Mono.just(historia));
		when(historiasRepository.save(any(Historia.class))).thenReturn(Mono.just(historia));

		//Act & Assert
		StepVerifier
			.create(this.adicionarVisualizacaoDeUmaMidia.adicionarVisualizacao(id))
			.expectNextMatches(verificar -> verificar != null)
			.expectComplete()
			.verify();
	}
}
