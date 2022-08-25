package com.pvbank.portal.service;

import com.pvbank.portal.request.DepositMoneyRequest;
import com.pvbank.portal.request.ViewTransactionsRequest;
import com.pvbank.portal.request.WithdrawMoneyRequest;
import com.pvbank.portal.response.DepositMoneyResponse;
import com.pvbank.portal.response.ViewTransactionsResponse;
import com.pvbank.portal.response.WithdrawMoneyResponse;

public interface ITransactionService {
    WithdrawMoneyResponse withdrawMoney(WithdrawMoneyRequest transactionRequest);
    DepositMoneyResponse depositMoney(DepositMoneyRequest transactionRequest);
    ViewTransactionsResponse viewTransactions(ViewTransactionsRequest transactionRequest);

}
