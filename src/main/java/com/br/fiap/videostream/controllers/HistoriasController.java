package com.br.fiap.videostream.controllers;

import com.br.fiap.videostream.services.*;
import com.br.fiap.videostream.view.DTO.FavoritosDTO;
import com.br.fiap.videostream.view.DTO.HistoriaDTO;
import com.br.fiap.videostream.view.forms.AdicionarHistoriaComoFavoritoForm;
import com.br.fiap.videostream.view.forms.AtualizarHistoriaForm;
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

	IAtualizarHistorias atualizarHistorias;

	IDesativarHistorias desativarHistorias;

	IAdicionarVisualizacaoDeUmaHistoria adicionarVisualizacaoDeUmaHistoria;

	@Autowired
	HistoriasController(
		ISalvarNovasHistorias salvarNovasHistorias,
		IConsultarHistorias consultarHistorias,
		IMarcarHistoriaComoFavorita marcarHistoriaComoFavorita,
		IAtualizarHistorias atualizarHistorias,
		IDesativarHistorias desativarHistorias,
		IAdicionarVisualizacaoDeUmaHistoria adicionarVisualizacaoDeUmaHistoria) {
		this.salvarNovasHistorias = salvarNovasHistorias;
		this.consultarHistorias = consultarHistorias;
		this.marcarHistoriaComoFavorita = marcarHistoriaComoFavorita;
		this.atualizarHistorias = atualizarHistorias;
		this.desativarHistorias = desativarHistorias;
		this.adicionarVisualizacaoDeUmaHistoria = adicionarVisualizacaoDeUmaHistoria;
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

	@PostMapping(value = "/historias/favoritos")
	public Mono<ResponseEntity<FavoritosDTO>> adicionarHistoriaComoFavorita(@Valid @RequestBody AdicionarHistoriaComoFavoritoForm favoritoForm) {
		return this.marcarHistoriaComoFavorita.adicionar(favoritoForm).map(item -> ResponseEntity.status(201).body(item));
	}

	@GetMapping(value = "/historias", params = {"categoria"})
	public Mono<Page<List<HistoriaDTO>>> buscarPorCategorias(
		@RequestParam(name="categoria") String categoria,
		Pageable paginacao) {
		return this.consultarHistorias.consultarPorCategorias(categoria, paginacao)
			.mapNotNull(items -> new PageImpl(
			items,
			PageRequest.of(paginacao.getPageNumber(), paginacao.getPageSize()),
			paginacao.getPageSize())
		);
	}

	@PutMapping(value = "/historias/{id}")
	public Mono<ResponseEntity<HistoriaDTO>> atualizarHistoria(
		@PathVariable String id,
		@RequestBody AtualizarHistoriaForm atualizarHistoriaForm) {
		return this.atualizarHistorias.atualizar(id, atualizarHistoriaForm)
			.map(historia -> ResponseEntity.status(200).body(historia));
	}

	@DeleteMapping(value = "/historias/{id}")
	public Mono<ResponseEntity<Void>> desativarHistorias(@PathVariable String id) {
		return this.desativarHistorias.desativar(id).map(historia -> ResponseEntity.status(200).build());
	}

	@PostMapping(value = "/historias/visualizacao/{id}")
	public Mono<ResponseEntity<Void>> adicionarVisualizacao(@PathVariable String id) {
		return this.adicionarVisualizacaoDeUmaHistoria.adicionarVisualizacao(id)
			.map(historia -> ResponseEntity.status(200).build());
	}

}
