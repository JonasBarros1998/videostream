package com.br.fiap.videostream.controllers;

import com.br.fiap.videostream.services.ISalvarNovasHistorias;
import com.br.fiap.videostream.view.DTO.HistoriaDTO;
import com.br.fiap.videostream.view.forms.HistoriaForm;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class HistoriasController {

	ISalvarNovasHistorias salvarNovasHistorias;

	HistoriasController(ISalvarNovasHistorias salvarNovasHistorias) {
		this.salvarNovasHistorias = salvarNovasHistorias;
	}

	@PostMapping(value = "/historias")
	public Mono<ResponseEntity<HistoriaDTO>> historias(@Valid @RequestBody HistoriaForm historiasForm) {
		return this.salvarNovasHistorias.salvar(historiasForm).map(item -> ResponseEntity.status(201).build());
	}


}
