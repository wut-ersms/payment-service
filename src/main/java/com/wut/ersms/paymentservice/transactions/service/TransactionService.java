package com.wut.ersms.paymentservice.transactions.service;

import com.wut.ersms.paymentservice.transactions.model.TransactionResponse;
import com.wut.ersms.paymentservice.transactions.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionResponse saveTransaction(TransactionResponse transactionResponse) {
        return transactionRepository.save(transactionResponse);
    }
}
