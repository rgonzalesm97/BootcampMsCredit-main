package com.bank.credit.proxy;

import org.springframework.web.reactive.function.client.WebClient;

import com.bank.credit.model.Client;

import reactor.core.publisher.Mono;

public class CreditProxy {
	
	private final WebClient.Builder webClientBuilder = WebClient.builder();
	
	//get client by id
	public Mono<Client> getClient(String idClient){
		return webClientBuilder.build()
								.get()
								.uri("http://localhost:8090/client/{idClient}", idClient)
								.retrieve()
								.bodyToMono(Client.class);
	}

}
