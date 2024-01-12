package com.br.fiap.videostream.adapters.armazenamento;

import software.amazon.awssdk.transfer.s3.model.Upload;

import java.io.InputStream;

public interface ArmazenamentoService {

	Upload enviarArquivo(InputStream arquivo, String localizacao);
}
