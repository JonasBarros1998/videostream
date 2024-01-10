package com.br.fiap.videostream.controllers;

import com.br.fiap.videostream.services.IConsultarHistorias;
import com.br.fiap.videostream.services.ISalvarNovasHistorias;
import com.br.fiap.videostream.view.DTO.HistoriaDTO;
import com.br.fiap.videostream.view.forms.HistoriaForm;
import jakarta.validation.Valid;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/api")
public class HistoriasController {

	ISalvarNovasHistorias salvarNovasHistorias;

	IConsultarHistorias consultarHistorias;

	HistoriasController(ISalvarNovasHistorias salvarNovasHistorias, IConsultarHistorias consultarHistorias) {
		this.salvarNovasHistorias = salvarNovasHistorias;
		this.consultarHistorias = consultarHistorias;
	}

	@PostMapping(value = "/historias")
	public Mono<ResponseEntity<HistoriaDTO>> historias(@Valid @RequestBody HistoriaForm historiasForm) {
		return this.salvarNovasHistorias.salvar(historiasForm).map(item -> ResponseEntity.status(201).build());
	}

	@GetMapping(value = "/historias")
	public Mono<Page<List<HistoriaDTO>>> buscarTodasHistorias(Pageable paginacao) {
		return this.consultarHistorias.consultarTodas(paginacao)
			.map(items -> new PageImpl(
					items,
					PageRequest.of(paginacao.getPageNumber(), paginacao.getPageSize()),
					paginacao.getPageSize())
			);
	}

	@GetMapping(value = "/historias", params = {"titulo"})
	public Mono<ResponseEntity<HistoriaDTO>> buscarPorTitulo(@RequestParam(name="titulo") String titulo) {
		return this.consultarHistorias.consultarPorTitulo(titulo)
			.map(item -> ResponseEntity.status(200).body(item));
	}

	@GetMapping(value = "/historias/favoritos")
	public Mono<Page<List<HistoriaDTO>>> buscarPorTitulo(Pageable paginacao) {
		return this.consultarHistorias.consultarPorFavoritos(paginacao)
			.mapNotNull(items -> new PageImpl(
				items,
				PageRequest.of(paginacao.getPageNumber(), paginacao.getPageSize()),
				paginacao.getPageSize())
			);
	}


}
