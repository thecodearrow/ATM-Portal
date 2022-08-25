package com.pvbank.portal.controller;

import com.pvbank.portal.request.DepositMoneyRequest;
import com.pvbank.portal.request.ViewTransactionsRequest;
import com.pvbank.portal.request.WithdrawMoneyRequest;
import com.pvbank.portal.response.DepositMoneyResponse;
import com.pvbank.portal.response.ViewTransactionsResponse;
import com.pvbank.portal.response.WithdrawMoneyResponse;
import com.pvbank.portal.service.impl.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
    @CrossOrigin
    @PostMapping(value = "/withdraw-money", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WithdrawMoneyResponse> withdrawMoney(@Validated @RequestBody WithdrawMoneyRequest transactionRequest) {
        WithdrawMoneyResponse response = transactionService.withdrawMoney(transactionRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @CrossOrigin
    @PostMapping(value = "/deposit-money", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DepositMoneyResponse> depositMoney(@Validated @RequestBody DepositMoneyRequest transactionRequest) {
        DepositMoneyResponse response = transactionService.depositMoney(transactionRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @CrossOrigin
    @PostMapping(value = "/view-transactions", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ViewTransactionsResponse> viewTransactions(@Validated @RequestBody ViewTransactionsRequest transactionRequest) {
        ViewTransactionsResponse response = transactionService.viewTransactions(transactionRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //TODO viewTransactionsByFilter

}
