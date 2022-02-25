package com.bank.credit.util;

import java.util.Optional;

import com.bank.credit.entity.Credit;

import reactor.core.publisher.Mono;


public interface Product {
	Mono<Credit> create(Credit credit);
}
