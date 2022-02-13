package com.bank.credit.service;

import com.bank.credit.entity.Credit;
import com.bank.credit.repository.ICreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CreditServiceImpl implements ICreditService {

    @Autowired
    ICreditRepository repository;

    @Override
    public Flux<Credit> getAll() {
        return repository.findAll();
    }

    @Override
    public Mono<Credit> getCreditById(String id) {
        return repository.findById(id);
    }

    @Override
    public Mono<Credit> save(Credit credit) {
        return repository.save(credit);
    }

    @Override
    public Mono<Credit> update(Credit credit) {
        return repository.save(credit);
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id).subscribe();
    }
}
