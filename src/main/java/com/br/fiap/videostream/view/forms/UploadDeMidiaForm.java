package com.br.fiap.videostream.view.forms;

import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

public record UploadDeMidiaForm(
	Mono<FilePart> arquivo
) {
}
