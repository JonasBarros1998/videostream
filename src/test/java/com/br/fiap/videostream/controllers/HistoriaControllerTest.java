package com.br.fiap.videostream.controllers;

import com.br.fiap.videostream.VideostreamApplication;
import com.br.fiap.videostream.casosdeuso.*;
import com.br.fiap.videostream.domain.entidades.Favoritos;
import com.br.fiap.videostream.domain.entidades.Historia;
import com.br.fiap.videostream.domain.entidades.Midia;
import com.br.fiap.videostream.domain.enuns.Categoria;
import com.br.fiap.videostream.infra.bancodedados.FavoritosRepository;
import com.br.fiap.videostream.infra.bancodedados.HistoriasRepository;

import com.br.fiap.videostream.infra.s3.AcessarUrlDaMidia;
import com.br.fiap.videostream.view.DTO.HistoriaDTO;
import com.br.fiap.videostream.view.forms.AdicionarHistoriaComoFavoritoForm;
import com.br.fiap.videostream.view.forms.AtualizarHistoriaForm;
import com.br.fiap.videostream.view.forms.HistoriaForm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import reactor.core.publisher.Mono;

import static org.mockito.Mockito.*;


import static org.mockito.ArgumentMatchers.any;

@ActiveProfiles(value = "test")
@AutoConfigureMockMvc
@SpringBootTest(classes = VideostreamApplication.class)
class HistoriaControllerTest {

	@Mock
	private HistoriasRepository historiasRepository;

	@Mock
	private FavoritosRepository favoritosRepository;

	@Mock
	private MarcarHistoriaComoFavorita marcarHistoriaComoFavorita;

	@MockBean
	AcessarUrlDaMidia acessarUrlDaMidia;

	@InjectMocks
	private ConsultarHistorias consultarHistorias;

	@InjectMocks
	private SalvarNovasHistorias salvarNovasHistorias;

	@InjectMocks
	private AtualizarHistorias atualizarHistorias;

	@InjectMocks
	private DesativarHistorias desativarHistorias;


	@InjectMocks
	private GerarUrlTemporaria gerarUrlTemporaria;

	@InjectMocks
	private HistoriasController historiasController;

	private AdicionarVisualizacaoDeUmaHistoria adicionarVisualizacaoDeUmaHistoria;

	private WebTestClient webTestClient;

	private Historia historia = new Historia("Um titulo", "Uma descricao", Categoria.TERROR, new Midia(), 10);

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		salvarNovasHistorias = new SalvarNovasHistorias(historiasRepository);
		marcarHistoriaComoFavorita = new MarcarHistoriaComoFavorita(favoritosRepository, historiasRepository);
		consultarHistorias = new ConsultarHistorias(historiasRepository, favoritosRepository, gerarUrlTemporaria);
		historiasController = new HistoriasController(
			salvarNovasHistorias,
			consultarHistorias,
			marcarHistoriaComoFavorita,
			atualizarHistorias,
			desativarHistorias,
			adicionarVisualizacaoDeUmaHistoria);
		this.webTestClient = WebTestClient.bindToController(historiasController).build();
	}

	@Test
	void deveCriarHistorias() {
		//Arrange
		HistoriaForm requestBody = new HistoriaForm(this.historia.getTitulo(), this.historia.getDescricao(), this.historia.getCategorias());
		when(historiasRepository.save(any(Historia.class))).thenReturn(Mono.just(historia));

		//Act & Assert
		webTestClient.post()
			.uri("/api/historias")
			.contentType(MediaType.APPLICATION_JSON)
			.bodyValue(requestBody)
			.exchange()
			.expectStatus()
			.isCreated();
	}

	@Test
	void quandoConsultarPorTituloDeveRetornarUmaHistoriaEStatus200() {
		//Arrange
		when(historiasRepository.findByTituloAndStatusIsTrue(any(String.class))).thenReturn(Mono.just(historia));
		when(acessarUrlDaMidia.buscarUrlDaMidia("arquivo.mp4")).thenReturn("http://gerar-url-temporaria");

		when(gerarUrlTemporaria
			.gerarUrl(new HistoriaDTO().converterHistoriaParaHistoriaDTO(historia)))
			.thenReturn("http://gerar-url-temporaria");

		//Act & Assert
		webTestClient.get()
			.uri(uriBuilder ->
				uriBuilder
					.path("/api/historias")
					.queryParam("titulo", historia.getTitulo())
					.build())
			.accept(MediaType.APPLICATION_JSON)
			.exchange()
			.expectStatus()
			.isOk();
	}

	@Test
	void AdicionarHistoriaComoFavoritaDeveRetornarStatus201() {
		//Arrange
		var midiaID = "123456789";

		var adicionarHistoriaComoFavoritoForm = new AdicionarHistoriaComoFavoritoForm("");

		var favoritos = new Favoritos(midiaID);
		favoritos.addHistorias(historia);

		when(historiasRepository.findById(any(String.class))).thenReturn(Mono.just(historia));
		when(favoritosRepository.save(any(Favoritos.class))).thenReturn(Mono.just(favoritos));

		//Act & Assert
		webTestClient.post()
			.uri("/api/historias/favoritos")
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)
			.bodyValue(adicionarHistoriaComoFavoritoForm)
			.exchange()
			.expectStatus()
			.isCreated();
	}

	@Test
	void aoAtualizarUmaHistoriaDeveRetornarstatus200() {
		//Arrange
		var atualizarHistoriaForm = new AtualizarHistoriaForm("Um titulo", "Uma descricao", Categoria.ACAO);

		when(historiasRepository.findById(any(String.class))).thenReturn(Mono.just(historia));
		when(historiasRepository.save(any(Historia.class))).thenReturn(Mono.just(historia));

		//Act & Assert
		webTestClient.put()
			.uri("/api/historias/{id}", "123456789")
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)
			.bodyValue(atualizarHistoriaForm)
			.exchange()
			.expectStatus()
			.isOk();
	}


	@Test
	void deveDesativarHistoriasERetornarStatus204() {
		//Arrange
		when(historiasRepository.findById(any(String.class))).thenReturn(Mono.just(historia));
		when(historiasRepository.save(any(Historia.class))).thenReturn(Mono.just(historia));

		//Act e Assert
		webTestClient.delete()
			.uri("/api/historias/{id}", "123456789")
			.exchange()
			.expectStatus()
			.isOk();
	}

	@Test
	void deveAdicionarUmaVisualizacaoNaHistoriaERetornarStatus200() {
		//Arrange
		when(historiasRepository.findById(any(String.class))).thenReturn(Mono.just(historia));
		when(historiasRepository.save(any(Historia.class))).thenReturn(Mono.just(historia));

		//Act & Assert
		webTestClient.delete()
			.uri("/api/historias/visualizacao/{id}", "123456789")
			.exchange()
			.expectStatus()
			.isOk();
	}

}
