package com.bank.credit.service;

import com.bank.credit.entity.Credit;
import com.bank.credit.model.Client;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface ICreditService {

    Flux<Credit> getAll();

    Mono<Credit> getCreditById(String id);

    Mono<Credit> save(Credit credit);

    Mono<Credit> update(Credit credit);
    
    Mono<Credit> getCreditByIdClient(String idClient);

    void delete(String id);

    Mono<Client> test(String idclient);
}
