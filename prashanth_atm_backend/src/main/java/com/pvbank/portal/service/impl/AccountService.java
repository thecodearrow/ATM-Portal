package com.pvbank.portal.service.impl;

import com.pvbank.portal.entity.Account;
import com.pvbank.portal.entity.Transaction;
import com.pvbank.portal.entity.User;
import com.pvbank.portal.exceptions.CustomBadRequestException;
import com.pvbank.portal.exceptions.CustomInternalServerErrorException;
import com.pvbank.portal.repository.AccountRepository;
import com.pvbank.portal.repository.UserRepository;
import com.pvbank.portal.request.*;
import com.pvbank.portal.response.*;
import com.pvbank.portal.service.IAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AccountService implements IAccountService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Override
    public AccountCreateResponse createAccount(AccountCreateRequest accountRequest) {
        Account account=new Account();
        AccountCreateResponse accountResponse=new AccountCreateResponse();
        User user=userRepository.findById(accountRequest.getUserID()).get();
        if(user!=null) {
            account.setUser(user);
            accountRepository.save(account);
            accountResponse.setDebitCardNumber(account.getDebitCardNumber());
            accountResponse.setAccountNumber(account.getAccountNumber());
            accountResponse.setIfscCode(account.getIfscCode());
            accountResponse.setPin(account.getPin());
            accountResponse.setMessage("Account created successfully!");
            accountResponse.setStatus(true);
        }
        else{
            throw new CustomBadRequestException("Account creation failed! ");
        }
        return accountResponse;
    }

    @Override
    public ViewBalanceResponse viewBalance(ViewBalanceRequest accountRequest) {
        ViewBalanceResponse accountResponse=new ViewBalanceResponse();
        Account account=accountRepository.findByDebitCardNumberAndPin(accountRequest.getDebitCardNumber(),accountRequest.getPin());
        if(account==null){
            throw new CustomBadRequestException("Invalid card number/pin. ");
        }
        accountResponse.setBalanceAmount(account.getBalanceAmount());
        accountResponse.setStatusCode(HttpStatus.OK);
        accountResponse.setStatus(true);
        accountResponse.setMessage("Balance Request Successful. ");
        return accountResponse;
    }


    @Override
    public PinChangeResponse changePin(PinChangeRequest accountRequest) {
        PinChangeResponse accountResponse=new PinChangeResponse();
        Account account=accountRepository.findByDebitCardNumberAndPin(accountRequest.getDebitCardNumber(),accountRequest.getOldPin());
        try {
            if (account != null) {
                account.setPin(accountRequest.getNewPin());
                accountRepository.save(account);
                accountResponse.setStatusCode(HttpStatus.OK);
                accountResponse.setStatus(true);
                accountResponse.setMessage("Pin Change Successful.");
            }
            else{
                throw new CustomBadRequestException("Pin Change Unsuccessful.");
            }
        }
        catch(Exception e){
            throw new CustomInternalServerErrorException("Pin change unsuccessful",e);
        }

        return accountResponse;
    }
    @Override
    public ViewAccountsResponse viewAccounts(ViewAccountsRequest accountRequest){
        ViewAccountsResponse accountsResponse = new ViewAccountsResponse();
        User user=userRepository.findById(accountRequest.getUserID()).get();
        if(user!=null){
            List<String> debitCardNumbers=accountRepository.findDebitCardNumbersByUser(user);
            accountsResponse.setDebitCardNumbers(debitCardNumbers);
            accountsResponse.setStatusCode(HttpStatus.OK);
            accountsResponse.setMessage("Fetch User Accounts successful! ");
            accountsResponse.setStatus(true);

        }
        else{
            throw new CustomBadRequestException("Could not fetch user accounts. ");
        }
        return accountsResponse;
    }
}
