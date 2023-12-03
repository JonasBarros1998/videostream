package com.br.fiap.videostream.view;

import com.br.fiap.videostream.infra.seguranca.Criptografia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controlador {

	private Criptografia criptografia;

	@Autowired
	Controlador(Criptografia criptografia) {
		this.criptografia = criptografia;
	}

	@GetMapping("/jonas")
	public void test() {
		this.criptografia.criptografar("");
	}

}
