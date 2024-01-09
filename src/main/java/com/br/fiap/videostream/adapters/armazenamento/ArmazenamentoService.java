package com.br.fiap.videostream.adapters.armazenamento;

import com.br.fiap.videostream.domain.entidades.Midia;
import software.amazon.awssdk.transfer.s3.model.Upload;

public interface ArmazenamentoService {

	Upload enviarArquivo(Midia midia);
}
