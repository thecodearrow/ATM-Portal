package com.pvbank.portal.service.impl;

import com.pvbank.portal.enums.TransactionType;
import com.pvbank.portal.entity.Account;
import com.pvbank.portal.entity.Transaction;
import com.pvbank.portal.exceptions.CustomBadRequestException;
import com.pvbank.portal.exceptions.CustomInternalServerErrorException;
import com.pvbank.portal.repository.AccountRepository;
import com.pvbank.portal.repository.TransactionRepository;
import com.pvbank.portal.request.DepositMoneyRequest;
import com.pvbank.portal.request.ViewTransactionsRequest;
import com.pvbank.portal.request.WithdrawMoneyRequest;
import com.pvbank.portal.response.DepositMoneyResponse;
import com.pvbank.portal.response.ViewTransactionsResponse;
import com.pvbank.portal.response.WithdrawMoneyResponse;
import com.pvbank.portal.service.ITransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Service
@Slf4j
@Transactional
public class TransactionService implements ITransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Override
    @Transactional
    public WithdrawMoneyResponse withdrawMoney(WithdrawMoneyRequest transactionRequest) {
        WithdrawMoneyResponse transactionResponse=new WithdrawMoneyResponse();
        try {
            Account account = accountRepository.findByDebitCardNumberAndPin(transactionRequest.getDebitCardNumber(), transactionRequest.getPin());
            if (account != null) {
                Double currentBalanceAmount = account.getBalanceAmount();
                Double amount = transactionRequest.getAmount();
                if(amount<0){
                    throw new CustomBadRequestException("Invalid Request. Amount should be positive.");
                }
                else if (currentBalanceAmount < amount) {
                    throw new CustomBadRequestException("Insufficient Balance");
                } else {
                        Double updatedBalanceAmount = currentBalanceAmount - amount;
                        account.setBalanceAmount(updatedBalanceAmount);
                        accountRepository.save(account);
                        Transaction transaction = new Transaction();
                        transaction.setType(TransactionType.WITHDRAW);
                        transaction.setTransactionAmount(amount);
                        transaction.setAccount(account);
                        transaction.setUser(account.getUser());
                        transactionRepository.save(transaction);
                        transactionResponse.setBalanceAmount(updatedBalanceAmount);
                        transactionResponse.setStatusCode(HttpStatus.OK);
                        transactionResponse.setMessage("Withdraw operation successful.");
                        transactionResponse.setStatus(true);
                    }

                }
             else {
               throw new CustomBadRequestException("Invalid Debit Card Number/Pin. ");
            }
            return transactionResponse;
        }
        catch(Exception e){
            throw new CustomInternalServerErrorException("Withdraw Money Operation Failed",e);
        }
    }

    @Override
    public DepositMoneyResponse depositMoney(DepositMoneyRequest transactionRequest) {
        DepositMoneyResponse transactionResponse = new DepositMoneyResponse();
        try {
            Account account = accountRepository.findByDebitCardNumberAndPin(transactionRequest.getDebitCardNumber(), transactionRequest.getPin());
            if (account != null) {
                Transaction transaction = new Transaction();
                Double currentBalanceAmount = account.getBalanceAmount();
                Double amount = transactionRequest.getAmount();
                if(amount<0){
                    throw new CustomBadRequestException("Invalid Request. Amount should be positive.");
                }
                Double updatedBalanceAmount = currentBalanceAmount + amount;
                account.setBalanceAmount(updatedBalanceAmount);
                accountRepository.save(account);
                transaction.setType(TransactionType.DEPOSIT);
                transaction.setTransactionAmount(amount);
                transaction.setAccount(account);
                transaction.setUser(account.getUser());
                transactionRepository.save(transaction);
                transactionResponse.setBalanceAmount(updatedBalanceAmount);
                transactionResponse.setStatusCode(HttpStatus.OK);
                transactionResponse.setMessage("Deposit operation successful.");
                transactionResponse.setStatus(true);
            } else {
                throw new CustomBadRequestException("Invalid Debit Card/ Pin.");
            }
            return transactionResponse;
        }
         catch (Exception e) {
             throw new CustomInternalServerErrorException("Deposit Money operation failed.",e);
            }

    }
    @Override
    public ViewTransactionsResponse viewTransactions(ViewTransactionsRequest transactionRequest){
        ViewTransactionsResponse transactionResponse = new ViewTransactionsResponse();
        Account account=accountRepository.findByDebitCardNumberAndPin(transactionRequest.getDebitCardNumber(),transactionRequest.getPin());
        if(account!=null){
            List<Object> transactions=transactionRepository.findByAccount(account);
            transactionResponse.setTransactions(transactions);
            transactionResponse.setStatusCode(HttpStatus.OK);
            transactionResponse.setMessage("Fetch Transactions successful! ");
            transactionResponse.setStatus(true);

        }
        else{
            throw new CustomBadRequestException("Could not fetch transactions. ");
        }
        return transactionResponse;
    }
}
