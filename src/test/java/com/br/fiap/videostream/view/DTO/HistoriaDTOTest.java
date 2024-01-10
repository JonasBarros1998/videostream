package com.br.fiap.videostream.view.DTO;

import com.br.fiap.videostream.domain.enuns.Categoria;
import com.br.fiap.videostream.view.forms.HistoriaForm;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HistoriaDTOTest {

	@Test
	void deveCriarUmaHistoriaDTO() {
		//Arrange
		var historiaForm = new HistoriaForm(
			"Minha nova serie",
			"serie de verão",
			Categoria.ACAO
		);

		var historiaDTO = new HistoriaDTO(historiaForm);

		//Act
		var converterParaHistoriaDTO = historiaDTO.converterHistoriaFormParaHistoriaDTO();

		//Asserts
		assertThat(converterParaHistoriaDTO).isInstanceOf(HistoriaDTO.class);
		assertThat(converterParaHistoriaDTO.getTitulo()).isEqualTo("Minha nova serie");
		assertThat(converterParaHistoriaDTO.getDescricao()).isEqualTo("serie de verão");

	}
}
