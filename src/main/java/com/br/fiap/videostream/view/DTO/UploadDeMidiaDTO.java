package com.br.fiap.videostream.view.DTO;

import com.br.fiap.videostream.view.forms.UploadDeMidiaForm;
import org.springframework.core.io.buffer.DataBufferUtils;
import reactor.core.publisher.Mono;

import java.io.*;

public class UploadDeMidiaDTO {

	private InputStream arquivoStream;

	public UploadDeMidiaDTO() {}

	public Mono<UploadDeMidiaDTO> converterUploadMidiaFormParaUploadMidiaDTO(UploadDeMidiaForm uploadDeMidiaForm) {

		var uploadDeMedia = new UploadDeMidiaDTO();

		return uploadDeMidiaForm.arquivo().flatMap(item -> {
			return DataBufferUtils.join(item.content()).flatMap(itens -> {
				uploadDeMedia.setArquivoStream(itens.asInputStream());
				return Mono.just(uploadDeMedia);
			}).then();
		}).then(Mono.just(uploadDeMedia));
	}

	public InputStream getArquivoStream() {
		return arquivoStream;
	}

	public void setArquivoStream(InputStream arquivoStream) {
		this.arquivoStream = arquivoStream;
	}

}
