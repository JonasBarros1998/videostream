package com.br.fiap.videostream.infra.seguranca;

import com.br.fiap.videostream.adapters.Seguranca.ICriptografia;
import com.br.fiap.videostream.infra.erros.CriptografiaException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Optional;

@Component
public class Criptografia implements ICriptografia {

	@Value(value = "${seguranca.chave}")
	private String key;

	@Value(value = "${seguranca.algoritimo}")
	private String algoritimo;

	@Value(value = "${seguranca.initVector}")
	private String initVector;

	public Optional<String> descriptografar(String valorCriptografado) {

		try {
			/*
			SecureRandom random = new SecureRandom();
			byte[] iv = new byte[16];
			random.nextBytes(iv);
			var ivParameterSpec = new IvParameterSpec(iv);*/

			IvParameterSpec ivParameterSpec = new IvParameterSpec(initVector.getBytes("UTF-8"));

			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");

			Cipher cipher = Cipher.getInstance(algoritimo);
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivParameterSpec);
			var b64 = Base64.getDecoder().decode(valorCriptografado);
			byte[] original = cipher.doFinal(b64);
			return Optional.of(new String(original));

		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

	public Optional<String> criptografar(String valor) {

		try {
			/*
			SecureRandom random = new SecureRandom();
			byte[] iv = new byte[16];
			random.nextBytes(iv);
			var ivParameterSpec = new IvParameterSpec(iv);*/

			var ivParameterSpec = new IvParameterSpec(initVector.getBytes(StandardCharsets.UTF_8));

			SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");

			Cipher cipher = Cipher.getInstance(algoritimo);
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivParameterSpec);

			byte[] encrypted = cipher.doFinal(valor.getBytes());

			return Optional.of(Base64.getEncoder().encodeToString(encrypted));
		}
		catch (Exception ex) {
			throw new CriptografiaException(ex.getMessage());
		}

	}
}
