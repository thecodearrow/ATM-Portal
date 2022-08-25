package com.pvbank.portal.controller;

import com.pvbank.portal.request.*;
import com.pvbank.portal.response.*;
import com.pvbank.portal.service.impl.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;

    @CrossOrigin
    @PostMapping(value = "/accounts", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ViewAccountsResponse> viewAccounts(@Validated @RequestBody ViewAccountsRequest accountRequest) {
        ViewAccountsResponse response = accountService.viewAccounts(accountRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @CrossOrigin
    @PostMapping(value="/view-balance", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ViewBalanceResponse> viewBalance(@Validated @RequestBody ViewBalanceRequest accountRequest) {
        ViewBalanceResponse response = accountService.viewBalance(accountRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @CrossOrigin
    @PostMapping(value = "/create-account", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountCreateResponse> createAccount(@Validated @RequestBody AccountCreateRequest accountRequest) {
        AccountCreateResponse response = accountService.createAccount(accountRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @CrossOrigin
    @PostMapping(value = "/change-pin", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PinChangeResponse> createAccount(@Validated @RequestBody PinChangeRequest accountRequest) {
        PinChangeResponse response = accountService.changePin(accountRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}


