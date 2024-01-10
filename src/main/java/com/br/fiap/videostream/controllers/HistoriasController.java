package com.br.fiap.videostream.controllers;

import com.br.fiap.videostream.casosdeuso.UploadDeMidia;
import com.br.fiap.videostream.view.forms.HistoriaForm;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class HistoriasController {

	UploadDeMidia uploadDeMidia;

	HistoriasController(UploadDeMidia uploadDeMidia) {
		this.uploadDeMidia = uploadDeMidia;
	}

	@PostMapping(value = "/historias")
	public Mono<ResponseEntity<Void>> arquivos(@Valid @RequestBody HistoriaForm historiasForm) {
		return Mono.just(ResponseEntity.status(201).build());
	}
}
