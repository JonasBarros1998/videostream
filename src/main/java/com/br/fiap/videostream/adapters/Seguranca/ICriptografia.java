package com.br.fiap.videostream.adapters.Seguranca;

import java.util.Optional;

public interface ICriptografia {

	public Optional<String> descriptografar(String valorCriptografado);

	public Optional<String> criptografar(String valor);
}
