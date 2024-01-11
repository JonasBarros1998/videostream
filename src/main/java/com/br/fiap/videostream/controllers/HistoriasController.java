package com.br.fiap.videostream.controllers;

import com.br.fiap.videostream.services.IConsultarHistorias;
import com.br.fiap.videostream.services.IMarcarHistoriaComoFavorita;
import com.br.fiap.videostream.services.ISalvarNovasHistorias;
import com.br.fiap.videostream.view.DTO.HistoriaDTO;
import com.br.fiap.videostream.view.forms.AdicionarHistoriaComoFavoritoForm;
import com.br.fiap.videostream.view.forms.HistoriaForm;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;


@RestController
@RequestMapping("/api")
public class HistoriasController {

	ISalvarNovasHistorias salvarNovasHistorias;

	IConsultarHistorias consultarHistorias;

	IMarcarHistoriaComoFavorita marcarHistoriaComoFavorita;

	@Autowired
	HistoriasController(
		ISalvarNovasHistorias salvarNovasHistorias,
		IConsultarHistorias consultarHistorias,
		IMarcarHistoriaComoFavorita marcarHistoriaComoFavorita) {
		this.salvarNovasHistorias = salvarNovasHistorias;
		this.consultarHistorias = consultarHistorias;
		this.marcarHistoriaComoFavorita = marcarHistoriaComoFavorita;
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
	public Mono<Page<List<HistoriaDTO>>> buscarPorFavoritos(Pageable paginacao) {
		return this.consultarHistorias.consultarPorFavoritos(paginacao)
			.mapNotNull(items -> new PageImpl(
				items,
				PageRequest.of(paginacao.getPageNumber(), paginacao.getPageSize()),
				paginacao.getPageSize())
			);
	}

	@PostMapping(value = "/historias/favoritos")
	public Mono<ResponseEntity<HistoriaDTO>> adicionarHistoriaComoFavorita(@Valid @RequestBody AdicionarHistoriaComoFavoritoForm favoritoForm) {
		return this.marcarHistoriaComoFavorita.adicionar(favoritoForm).map(item -> ResponseEntity.status(201).body(item));
	}

	@GetMapping(value = "/historias", params = {"categoria"})
	public Mono<Page<List<HistoriaDTO>>> buscarPorCategorias(@RequestParam(name="categoria") String categoria, Pageable paginacao) {
		return this.consultarHistorias.consultarPorCategorias(categoria, paginacao)
			.mapNotNull(items -> new PageImpl(
			items,
			PageRequest.of(paginacao.getPageNumber(), paginacao.getPageSize()),
			paginacao.getPageSize())
		);
	}

}
