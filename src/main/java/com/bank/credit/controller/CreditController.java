package com.bank.credit.controller;

import com.bank.credit.entity.Credit;
import com.bank.credit.service.ICreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/credit")
public class CreditController {

    @Autowired
    ICreditService service;

    @GetMapping
    public Flux<Credit> getCredits(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Mono<Credit> getCreditById(@PathVariable("id") String id){
        return service.getCreditById(id);
    }
    
    @GetMapping("/byPersonalClient/{id}")
    public Mono<Credit> getCreditByIdClient(@PathVariable("id") String idClient){
        return service.getCreditByIdClient(idClient);
    }

    @PostMapping
    public Mono<Credit> postCredit(@RequestBody Credit credit){
        return service.save(credit);
    }

    @PutMapping
    public Mono<Credit> updCredit(@RequestBody Credit credit){
        return service.save(credit);
    }

    @DeleteMapping("/{id}")
    void dltCredit(@PathVariable("id") String id){
        service.delete(id);
    }
}
