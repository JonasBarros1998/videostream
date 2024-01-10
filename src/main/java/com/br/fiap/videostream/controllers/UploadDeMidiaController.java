package com.br.fiap.videostream.controllers;

import com.br.fiap.videostream.casosdeuso.UploadDeMidia;
import com.br.fiap.videostream.view.forms.UploadDeMidiaForm;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class UploadDeMidiaController {

	UploadDeMidia uploadDeMidia;

	UploadDeMidiaController(UploadDeMidia uploadDeMidia) {
		this.uploadDeMidia = uploadDeMidia;
	}

	@PostMapping(value = "/arquivos")
	public Mono<ResponseEntity<Void>> arquivos(@RequestPart(value = "arquivo") Mono<FilePart> arquivo) {
		var uploadMidaForm = new UploadDeMidiaForm(arquivo);
		return this.uploadDeMidia.enviar(uploadMidaForm).thenReturn(ResponseEntity.status(201).build());
	}
}