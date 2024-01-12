package com.br.fiap.videostream.infra.s3;

import com.br.fiap.videostream.domain.entidades.Historia;
import com.br.fiap.videostream.domain.entidades.Midia;
import com.br.fiap.videostream.domain.enuns.Categoria;
import com.br.fiap.videostream.view.DTO.HistoriaDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(value = "test")
@SpringBootTest
class AcessarUrlDaMidiaTest {

	@Autowired
	AcessarUrlDaMidia acessarUrlDaMidia;

	AutoCloseable mock;

	@BeforeEach
	void setup() {
		mock = MockitoAnnotations.openMocks(this);
		//acessarUrlDaMidia = new AcessarUrlDaMidia();
	}


	@Test
	void deveBuscarUrlDoArquivo() {

		//Arrange
		var midia = new Midia();
		midia.setNomeDaMidia("Um titulo");
		midia.criarDestino();

		var historia = new Historia(
			"Um titulo",
			"Uma descricao",
			Categoria.ANIMACAO,
			midia,
			10);

		HistoriaDTO historiaDTO = new HistoriaDTO().converterHistoriaParaHistoriaDTO(historia);

		//act
		String url = acessarUrlDaMidia.buscarUrlDaMidia(midia.getDestino());

		//assert
		Assertions.assertThat(url).isInstanceOf(String.class);
	}


}
