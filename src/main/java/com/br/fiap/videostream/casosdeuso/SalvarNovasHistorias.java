package com.br.fiap.videostream.casosdeuso;

import com.br.fiap.videostream.domain.entidades.Historia;
import com.br.fiap.videostream.infra.bancodedados.HistoriasRepository;
import com.br.fiap.videostream.view.DTO.HistoriaDTO;
import com.br.fiap.videostream.view.forms.HistoriaForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class SalvarNovasHistorias {

	private final HistoriasRepository historiasRepository;

	@Autowired
	public SalvarNovasHistorias(HistoriasRepository historiasRepository) {
		this.historiasRepository = historiasRepository;
	}

	public Mono<HistoriaDTO> salvar(HistoriaForm historiasForm) {
		var historiaDTO = new HistoriaDTO(historiasForm).converterHistoriaFormParaHistoriaDTO();
		return this.historiasRepository
			.save(new Historia(historiaDTO.getTitulo(), historiaDTO.getDescricao(), historiaDTO.getCategoria()))
			.map(item -> historiaDTO);
	}
}
