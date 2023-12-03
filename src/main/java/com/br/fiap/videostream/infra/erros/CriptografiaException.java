package com.br.fiap.videostream.infra.erros;

public class CriptografiaException extends RuntimeException {
	public CriptografiaException(String mensagem) {
		super(mensagem);
	}
}
