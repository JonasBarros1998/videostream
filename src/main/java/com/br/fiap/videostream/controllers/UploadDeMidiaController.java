package com.br.fiap.videostream.controllers;

import com.br.fiap.videostream.casosdeuso.EnviarMidiaAoBucket;
import com.br.fiap.videostream.view.forms.UploadDeMidiaForm;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class UploadDeMidiaController {

	EnviarMidiaAoBucket enviarMidiaAoBucket;

	UploadDeMidiaController(EnviarMidiaAoBucket enviarMidiaAoBucket) {
		this.enviarMidiaAoBucket = enviarMidiaAoBucket;
	}

	@PostMapping(value = "/arquivos/{id}")
	public Mono<ResponseEntity<Void>> arquivos(@PathVariable String id, @RequestPart(value = "arquivo") Mono<FilePart> arquivo) {
		var uploadMidaForm = new UploadDeMidiaForm(arquivo);
		return this.enviarMidiaAoBucket.enviar(uploadMidaForm, id).thenReturn(ResponseEntity.status(201).build());
	}
}
