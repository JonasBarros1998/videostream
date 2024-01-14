package com.br.fiap.videostream.view.DTO;

import com.br.fiap.videostream.domain.entidades.Historia;
import com.br.fiap.videostream.domain.entidades.Midia;
import com.br.fiap.videostream.domain.enuns.Categoria;
import com.br.fiap.videostream.view.forms.HistoriaForm;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class HistoriaDTOTest {

	@Test
	void deveCriarUmaHistoriaDTO() {
		//Arrange
		var historiaForm = new HistoriaForm(
			"Minha nova serie",
			"serie de verão",
			Arrays.asList(Categoria.ACAO)
		);

		var historiaDTO = new HistoriaDTO(historiaForm);

		//Act
		var converterParaHistoriaDTO = historiaDTO.converterHistoriaFormParaHistoriaDTO();

		//Asserts
		assertThat(converterParaHistoriaDTO).isInstanceOf(HistoriaDTO.class);
		assertThat(converterParaHistoriaDTO.getTitulo()).isEqualTo("Minha nova serie");
		assertThat(converterParaHistoriaDTO.getDescricao()).isEqualTo("serie de verão");
	}

	@Test
	void deveConverterHistoriaParaHistoriaDTO() {
		//Arrange
		var midia = new Midia();
		midia.criarDestino();
		midia.setNomeDaMidia("Uma serie");
		var historia = new Historia("Uma serie", "Uma descricao", Arrays.asList(Categoria.FICCAO), midia,20);

		//Act
		HistoriaDTO historiaDTO = new HistoriaDTO().converterHistoriaParaHistoriaDTO(historia);

		//Assert
		assertThat(historiaDTO).isInstanceOf(HistoriaDTO.class);
		assertThat(historiaDTO.getTitulo()).isEqualTo("Uma serie");
		assertThat(historiaDTO.getDescricao()).isEqualTo("Uma descricao");
		assertThat(historiaDTO.getCategorias()).isInstanceOf(List.class);
		assertThat(historiaDTO.getMidia().getNomeDaMidia()).isEqualTo("Uma-serie");
	}

	@Test
	void deveCriarUmaInstanciaDeHistoriaDTO() {
		//Act
		var historiaDTO = new HistoriaDTO("Um titulo", "Uma descricao", Arrays.asList(Categoria.TERROR), "123456789");

		//Assert
		assertThat(historiaDTO).isInstanceOf(HistoriaDTO.class);
		assertThat(historiaDTO.getId()).isEqualTo("123456789");
	}
}
