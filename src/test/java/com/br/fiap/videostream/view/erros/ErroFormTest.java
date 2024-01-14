package com.br.fiap.videostream.view.erros;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(value = "test")
@SpringBootTest
public class ErroFormTest {

	ErroForm erroForm;


	@Test
	public void deveCriarUmErroForm() {
		var erroForm = new ErroForm("categorias", "Um erro ocorreu ao adicionar categorias");
		Assertions.assertThat(erroForm.getError()).isInstanceOf(String.class);
		Assertions.assertThat(erroForm.getField()).isInstanceOf(String.class);
	}

}
