package com.wut.ersms.paymentservice.transactions.repository;

import com.wut.ersms.paymentservice.transactions.model.TransactionResponse;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionRepository extends MongoRepository<TransactionResponse, String> {
}
