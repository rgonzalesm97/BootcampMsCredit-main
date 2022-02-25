package com.bank.credit.util;


import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;

import com.bank.credit.entity.Credit;
import com.bank.credit.model.Client;
import com.bank.credit.proxy.CreditProxy;
import com.bank.credit.repository.ICreditRepository;
import com.bank.credit.service.ICreditService;

import reactor.core.publisher.Mono;

public class PersonalCredit implements Product{

	private final CreditProxy creditProxy = new CreditProxy();
	
	@Autowired
    ICreditService service; //no funciona porque esta clase se instancia en la factory

	@Override
	public Mono<Credit> create(Credit credit) {
		return Mono.just(credit).flatMap(this::clientIsPersonal)
								.flatMap(this::clientHasCreditAlready);
		
	}
	
	
	public Mono<Credit> clientIsPersonal(Credit credit) {
		return getClient(credit).flatMap(resp -> {
			return resp.getType().equals("personal") ? Mono.just(credit)
													 : Mono.error(()->new IllegalArgumentException("Client is not personal"));
		});
	}
	
	public Mono<Credit> clientHasCreditAlready(Credit credit){
		return service.getCreditByIdClient(credit.getIdClient()).flatMap(resp -> {
			return resp.getId().equals(null) ? Mono.just(credit)
											 : Mono.error(()->new IllegalArgumentException("Client has a personal credit already"));
		});
	}
		
	public Mono<Client> getClient(Credit credit){
		return creditProxy.getClient(credit.getIdClient());
	}
	
	
}
