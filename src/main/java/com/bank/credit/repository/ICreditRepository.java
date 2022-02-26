package com.bank.credit.repository;

import com.bank.credit.entity.Credit;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICreditRepository extends ReactiveMongoRepository<Credit, String> {
	public Mono<Credit> findByIdClient(String idClient);
	public Flux<Credit> findAllByIdClient(String idClient);
}
