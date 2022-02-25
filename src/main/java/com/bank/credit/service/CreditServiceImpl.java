package com.bank.credit.service;

import com.bank.credit.proxy.CreditProxy;
import com.bank.credit.entity.Credit;
import com.bank.credit.model.Client;
import com.bank.credit.repository.ICreditRepository;
import com.bank.credit.util.CreditFactory;
import com.bank.credit.util.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CreditServiceImpl implements ICreditService {

    @Autowired
    ICreditRepository repository;
    
    private CreditProxy creditProxy = new CreditProxy();

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
    	return CreditFactory.getProduct(credit)
						    .orElseThrow(() -> new IllegalArgumentException("Invalid Credit type"))
						    .create(credit)
						    .flatMap(validCredit -> {
					    		return repository.save(validCredit);
					    	});
    }

    @Override
    public Mono<Credit> update(Credit credit) {
        return repository.save(credit);
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id).subscribe();
    }

	@Override
	public Mono<Credit> getCreditByIdClient(String idClient) {
		return repository.findByIdClient(idClient);
	}

	@Override
	public Mono<Client> test(String idclient) {
		return creditProxy.getClient(idclient);
	}
}
