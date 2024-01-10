package com.br.fiap.videostream.infra.bancodedados.conexao;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories
public class Conexao extends AbstractReactiveMongoConfiguration {

	@Override
	protected String getDatabaseName() {
		return "stream";
	}

	@Override
	@Bean
	public MongoClient reactiveMongoClient() {
		return MongoClients.create("mongodb://postech:123456789@localhost:27017");
	}

	@Bean
	public ReactiveMongoTemplate reactiveMongoTemplate() {
		return new ReactiveMongoTemplate(reactiveMongoClient(), getDatabaseName());
	}

}
