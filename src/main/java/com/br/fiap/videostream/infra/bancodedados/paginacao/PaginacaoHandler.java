package com.br.fiap.videostream.infra.bancodedados.paginacao;

import org.springframework.core.MethodParameter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.reactive.BindingContext;
import org.springframework.web.reactive.result.method.HandlerMethodArgumentResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

public class PaginacaoHandler implements HandlerMethodArgumentResolver {

	private static final String pagina = "0";
	private static final String tamanho = "1";
	private static final Integer tamanhoMaximo = 50;

	@Override
	public boolean supportsParameter(MethodParameter parametro) {
		return Pageable.class.equals(parametro.getParameterType());
	}

	@Override
	public Mono<Object> resolveArgument(MethodParameter methodParameter, BindingContext bindingContext, ServerWebExchange serverWebExchange) {

		List<String> paginas = serverWebExchange
			.getRequest()
			.getQueryParams()
			.getOrDefault("page", List.of(pagina));

		List<String> tamanhoDosItensDasPaginas = serverWebExchange
			.getRequest()
			.getQueryParams()
			.getOrDefault("size", List.of(serverWebExchange.getRequest().getQueryParams().getFirst("size")));

		return Mono.just(
			PageRequest.of(
				Integer.parseInt(paginas.get(0)),
				Math.min(Integer.parseInt(tamanhoDosItensDasPaginas.get(0)), tamanhoMaximo)
			)
		);


	}
}
