package com.br.fiap.videostream.casosdeuso;

import com.br.fiap.videostream.infra.bancodedados.HistoriasRepository;
import com.br.fiap.videostream.services.IDesativarHistorias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class DesativarHistorias implements IDesativarHistorias {

	HistoriasRepository historiasRepository;

	@Autowired
	DesativarHistorias(HistoriasRepository historiasRepository) {
		this.historiasRepository = historiasRepository;
	}

	@Override
	public Mono<Void> desativar(String id) {
		return this.historiasRepository
			.findById(id)
			.flatMap(historia -> {
				historia.setStatus(false);
				return this.historiasRepository.save(historia);
			}).then();
	}
}
