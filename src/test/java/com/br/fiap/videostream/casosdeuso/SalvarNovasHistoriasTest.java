package com.br.fiap.videostream.casosdeuso;


import com.br.fiap.videostream.domain.entidades.Historia;
import com.br.fiap.videostream.domain.entidades.Midia;
import com.br.fiap.videostream.domain.enuns.Categoria;
import com.br.fiap.videostream.infra.bancodedados.HistoriasRepository;
import com.br.fiap.videostream.view.DTO.HistoriaDTO;
import com.br.fiap.videostream.view.forms.HistoriaForm;
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
public class SalvarNovasHistoriasTest {

	@Mock
	private HistoriasRepository historiasRepository;

	@InjectMocks
	private SalvarNovasHistorias salvarNovasHistorias;

	AutoCloseable mock;

	@BeforeEach
	void setup() {
		mock = MockitoAnnotations.openMocks(this);
		this.salvarNovasHistorias = new SalvarNovasHistorias(historiasRepository);
	}

	@Test
	public void deveSalvarNovasHistorias() {
		//Arrange
		var historiasForm = new HistoriaForm("um titulo", "uma Descricao", Arrays.asList(Categoria.ACAO));
		var historiaDTO = new HistoriaDTO(historiasForm).converterHistoriaFormParaHistoriaDTO();

		var historia = new Historia(
			historiaDTO.getTitulo(),
			historiaDTO.getDescricao(),
			historiaDTO.getCategorias(),
			new Midia(),
			0);

		when(historiasRepository.save(any(Historia.class))).thenReturn(Mono.just(historia));

		//Assert & Act
		StepVerifier
			.create(salvarNovasHistorias.salvar(historiasForm))
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
