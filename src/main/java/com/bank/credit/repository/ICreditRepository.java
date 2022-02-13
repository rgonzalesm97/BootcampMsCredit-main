package com.bank.credit.repository;

import com.bank.credit.entity.Credit;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICreditRepository extends ReactiveMongoRepository<Credit, String> {
}
