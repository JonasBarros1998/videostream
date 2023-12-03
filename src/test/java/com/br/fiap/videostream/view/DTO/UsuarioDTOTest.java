package com.br.fiap.videostream.view.DTO;

import com.br.fiap.videostream.view.forms.UsuarioForm;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class UsuarioDTOTest {

	@Test
	void deveCriarUmUsuarioDTO() {
		//Arrange
		var usuarioForm = new UsuarioForm(
			"Jonas",
			"teste@gmail.com",
			"1234567"
		);
		var usuarioDTO = new UsuarioDTO(usuarioForm);

		//Act
		var converterUsuario = usuarioDTO.converterUsuarioFormParaUsuarioDTO();

		//Asserts
		assertThat(converterUsuario)
			.isInstanceOf(UsuarioDTO.class);

		assertThat(converterUsuario.getEmail()).isEqualTo("teste@gmail.com");
		assertThat(converterUsuario.getNome()).isEqualTo("Jonas");

	}
}
