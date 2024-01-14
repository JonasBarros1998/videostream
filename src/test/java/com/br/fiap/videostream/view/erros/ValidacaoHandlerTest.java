package com.br.fiap.videostream.view.erros;

import com.br.fiap.videostream.casosdeuso.SalvarNovasHistorias;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.core.MethodParameter;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.util.List;

@ActiveProfiles(value = "test")
@SpringBootTest
@AutoConfigureMockMvc
public class ValidacaoHandlerTest {

	@InjectMocks
	ValidacaoHandler ValidacaoHandler;

	@Mock
	MessageSource messageSource;

	@Mock
	MethodParameter methodParameter;

	@Mock
	BindingResult bindingResult;

	AutoCloseable mock;

	@BeforeEach
	void setup() {
		mock = MockitoAnnotations.openMocks(this);
		this.ValidacaoHandler = new ValidacaoHandler(messageSource);
	}

	@Test
	void deveCriarUmErroDoTipoMethodArgumentNotValidException() {

		Assertions.assertThat(this.ValidacaoHandler
			.handler(new MethodArgumentNotValidException(methodParameter, bindingResult))).isInstanceOf(List.class);

	}

	@Test
	void deveCriarUmErroDoTipoWebExchangeBindException() {

		Assertions.assertThat(this.ValidacaoHandler
			.handler(new WebExchangeBindException(methodParameter, bindingResult))).isInstanceOf(List.class);

	}

}
