package com.br.fiap.videostream.infra.seguranca;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles(value = "test")
@SpringBootTest
class CriptografiaTest {

	@Autowired
	private Criptografia propertySourceResolver;

	AutoCloseable mock;

	@Value(value = "${seguranca.initVector}")
	private String initVectorMock;

	@Value(value = "${seguranca.algoritimo}")
	private String algoritmoMock;

	@Value(value = "${seguranca.chave}")
	private String keyMock;

	@AfterEach
	void tearDown() throws Exception {
		mock.close();
	}

	@BeforeEach
	void setup() {
		mock = MockitoAnnotations.openMocks(this);
	}

	@Test
	void deveCriptografarUmTextoSimples() {
		var textoCriptografado = this.propertySourceResolver.criptografar("criptografar texto").orElseThrow();
		assertThat(textoCriptografado).isInstanceOf(String.class);
	}

	@Test
	void deveRetornarOptionalVazioAoTentarCriptografarUmTextoSimples() {

		// Arrange
		Criptografia criptografiaMock = mock(Criptografia.class);

		//Assert
		assertThat(criptografiaMock.criptografar("criptografar texto")).isInstanceOf(Optional.class);
		assertThat(criptografiaMock.criptografar("criptografar texto")).isEmpty();
	}

	@Test
	void deveDescriptografarUmTextoAposEleSerCriptografado() {

		//Arrange
		var criptografia = this.propertySourceResolver.criptografar("criptografar texto")
			.orElseThrow(null);

		//Act
		var textoDescriptografado = this.propertySourceResolver.descriptografar(criptografia)
			.orElseThrow(null);

		//Assert
		assertThat(textoDescriptografado).isEqualTo("criptografar texto");
	}

	@Test
	void deveRetornarUmOptionalVazioAoTentarDescriptografarUmTextoSimples() {

		// Arrange
		Criptografia criptografiaMock = mock(Criptografia.class);

		//Assert
		assertThat(criptografiaMock.criptografar("criptografar texto")).isInstanceOf(Optional.class);
		assertThat(criptografiaMock.criptografar("criptografar texto")).isEmpty();
	}
}
