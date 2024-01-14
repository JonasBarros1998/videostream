package com.br.fiap.videostream.casosdeuso;

import com.br.fiap.videostream.domain.entidades.Historia;
import com.br.fiap.videostream.domain.entidades.Midia;
import com.br.fiap.videostream.domain.enuns.Categoria;
import com.br.fiap.videostream.infra.s3.AcessarUrlDaMidia;
import com.br.fiap.videostream.view.DTO.HistoriaDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;

import static org.mockito.Mockito.*;

@ActiveProfiles(value = "test")
@SpringBootTest
public class GerarUrlTemporariaTest {

	@Mock
	AcessarUrlDaMidia acessarUrlDaMidia;

	GerarUrlTemporaria gerarUrlTemporaria;

	AutoCloseable mock;

	@BeforeEach
	void setup() {
		mock = MockitoAnnotations.openMocks(this);
		gerarUrlTemporaria = new GerarUrlTemporaria(acessarUrlDaMidia);
	}

	@Test
	void deveBuscarUmaURLParaAcessarAMidia() {
		//Assert
		var midia = new Midia();
		midia.setNomeDaMidia("Uma serie");
		midia.criarDestino();

		var historia = new Historia(
			"Uma serie",
			"Uma descricao",
			Arrays.asList(Categoria.FICCAO),
			midia,
			20);

		HistoriaDTO historiaDTO = new HistoriaDTO().converterHistoriaParaHistoriaDTO(historia);

		when(acessarUrlDaMidia.buscarUrlDaMidia(any(String.class))).thenReturn("https://url-autoassinada-bucket-s3");

		//Act
		String url = this.gerarUrlTemporaria.gerarUrl(historiaDTO);

		//Assert
		verify(acessarUrlDaMidia, times(1)).buscarUrlDaMidia(any(String.class));
		Assertions.assertThat(url).isInstanceOf(String.class);
	}
}
