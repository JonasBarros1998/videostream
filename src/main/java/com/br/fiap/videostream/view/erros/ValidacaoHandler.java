package com.br.fiap.videostream.view.erros;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ValidacaoHandler {
	private final MessageSource messageSource;

	@Autowired
	ValidacaoHandler(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErroForm> handler(MethodArgumentNotValidException exception) {
		List<ErroForm> erroFormList = new ArrayList<>();
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		fieldErrors.forEach(e -> {
			String message = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			ErroForm erroForm = new ErroForm(e.getField(), message);
			erroFormList.add(erroForm);
		});
		return erroFormList;
	}

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(WebExchangeBindException.class)
	public List<ErroForm> handler(WebExchangeBindException exception) {
		List<ErroForm> erroFormList = new ArrayList<>();
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		fieldErrors.forEach(e -> {
			String message = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			ErroForm erroForm = new ErroForm(e.getField(), message);
			erroFormList.add(erroForm);
		});
		return erroFormList;
	}




}
