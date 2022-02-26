package com.bank.credit.service;

import com.bank.credit.proxy.CreditProxy;
import com.bank.credit.entity.Credit;
import com.bank.credit.model.Client;
import com.bank.credit.repository.ICreditRepository;

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
    public Flux<Credit> getAllByIdClient(String idClient) {
        return repository.findAllByIdClient(idClient);
    }

    @Override
    public Mono<Credit> getCreditById(String id) {
        return repository.findById(id);
    }

    @Override
    public Mono<Credit> save(Credit credit) {
    	switch (credit.getTypeCredit()) {
		case "personal":
			return createPersonalCredit(credit).flatMap(repository::save);
		case "business":
			return createBusinessCredit(credit).flatMap(repository::save);
		case "personal credit card":
			return createPersonalCreditCard(credit).flatMap(repository::save);
		case "business credit card":
			return createBusinessCreditCard(credit).flatMap(repository::save);
		default:
			return Mono.error(() -> new IllegalArgumentException("Invalid Credit type"));
		}
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

	
	//PRODUCT VALIDATION METHODS
	public Mono<Credit> createPersonalCredit(Credit credit){
		return Mono.just(credit).flatMap(this::clientIsPersonal)
								.flatMap(this::clientHasCreditAlready);
	}
	
	public Mono<Credit> createBusinessCredit(Credit credit){
		return Mono.just(credit).flatMap(this::clientIsBusiness);
	}
	
	public Mono<Credit> createPersonalCreditCard(Credit credit){
		return Mono.just(credit).flatMap(this::clientIsPersonal);
	}
	
	public Mono<Credit> createBusinessCreditCard(Credit credit){
		return Mono.just(credit).flatMap(this::clientIsBusiness);
	}
	
	
	//PRODUCT UTIL METHODS
	public Mono<Client> getClient(Credit credit){
		return creditProxy.getClient(credit.getIdClient());
	}
	
	public Mono<Credit> clientIsPersonal(Credit credit) {
		return getClient(credit).flatMap(resp -> {
			return resp.getType().equals("personal") ? Mono.just(credit)
													 : Mono.error(()->new IllegalArgumentException("Client is not personal"));
		});
	}
	
	public Mono<Credit> clientIsBusiness(Credit credit) {
		return getClient(credit).flatMap(resp -> {
			return resp.getType().equals("business") ? Mono.just(credit)
													 : Mono.error(()->new IllegalArgumentException("Client is not business"));
		});
	}
	
	public Mono<Credit> clientHasCreditAlready(Credit credit){
		return getCreditByIdClient(credit.getIdClient()).switchIfEmpty(Mono.just(new Credit()))
														.flatMap(resp -> {
															if(resp.getId()==null || resp.getId().equals(credit.getId())) {
																return Mono.just(credit);
															}
															return Mono.error(()->new IllegalArgumentException("Client has a personal credit already"));
		});
	}
}
