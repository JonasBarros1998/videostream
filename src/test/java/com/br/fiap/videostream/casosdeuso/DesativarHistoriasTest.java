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

import static org.mockito.Mockito.*;

@ActiveProfiles(value = "test")
@SpringBootTest
public class DesativarHistoriasTest {

	AutoCloseable mock;

	@InjectMocks
	DesativarHistorias desativarHistorias;

	@Mock
	HistoriasRepository historiasRepository;

	final Historia historia = new Historia("Um titulo", "Uma descricao", Arrays.asList(Categoria.TERROR), new Midia(),10);

	@BeforeEach
	void setup() {
		mock = MockitoAnnotations.openMocks(this);
		desativarHistorias = new DesativarHistorias(historiasRepository);
	}

	@Test
	public void deveDesativarUmaHistoria() {

		//Arrange
		when(historiasRepository.findById(any(String.class))).thenReturn(Mono.just(historia));
		when(historiasRepository.save(any(Historia.class))).thenReturn(Mono.just(historia));

		//Act
		StepVerifier
			.create(desativarHistorias.desativar("123456789"))
			.expectComplete()
			.verify();

		//Assert
		verify(historiasRepository, times(1)).save(any(Historia.class));

	}
}
