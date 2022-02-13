package com.bank.credit.service;

import com.bank.credit.entity.Credit;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface ICreditService {

    Flux<Credit> getAll();

    Mono<Credit> getCreditById(String id);

    Mono<Credit> save(Credit credit);

    Mono<Credit> update(Credit credit);

    void delete(String id);

}
