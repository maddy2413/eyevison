package com.in28minutes.microservices.apigateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@Component
public class LoggingFilter implements GlobalFilter{
	
	private Logger logger=LoggerFactory.getLogger(LoggingFilter.class);

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

		logger.info("Looging the details 1-> {} ",exchange.getApplicationContext());
		logger.info("Looging the details 2-> {} ",exchange.getRequest().getBody());
		logger.info("Looging the details 3-> {} ",exchange.getRequest().getPath());


		return chain.filter(exchange);
	}

}
