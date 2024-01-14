package com.br.fiap.videostream.casosdeuso;

import com.br.fiap.videostream.domain.entidades.Historia;
import com.br.fiap.videostream.domain.entidades.Midia;
import com.br.fiap.videostream.domain.enuns.Categoria;
import com.br.fiap.videostream.infra.bancodedados.HistoriasRepository;
import com.br.fiap.videostream.view.forms.AtualizarHistoriaForm;
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
public class AtualizarHistoriasTest {

	AutoCloseable mock;

	@InjectMocks
	AtualizarHistorias atualizarHistorias;

	@Mock
	HistoriasRepository historiasRepository;

	@BeforeEach
	void setup() {
		mock = MockitoAnnotations.openMocks(this);
	}

	@Test
	public void deveAtualizarUmaHistoria() {

		//Arrange
		var historiaID = "123456789";
		var atualizarHistoriaForm = new AtualizarHistoriaForm("Um titulo", "Uma descricao", Arrays.asList(Categoria.ACAO));

		var historia = new Historia("Um titulo", "Uma descricao", Arrays.asList(Categoria.TERROR), new Midia(),10);
		when(historiasRepository.findById(any(String.class))).thenReturn(Mono.just(historia));
		when(historiasRepository.save(any(Historia.class))).thenReturn(Mono.just(historia));

		//Act & Assert
		StepVerifier
			.create(	atualizarHistorias.atualizar(historiaID, atualizarHistoriaForm))
			.expectNextMatches(verificar -> {
				var tituloEstaAtualizado = verificar.getTitulo().equals(historia.getTitulo());
				var descricaoEstaAtualizada = verificar.getDescricao().equals(historia.getDescricao());
				var categoriaEstaAtualizada = verificar.getCategorias().equals(historia.getCategorias());

				return tituloEstaAtualizado & descricaoEstaAtualizada & categoriaEstaAtualizada;
			})
			.expectComplete()
			.verify();
	}


}
