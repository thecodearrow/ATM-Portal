package com.pvbank.portal.service;

import com.pvbank.portal.request.*;
import com.pvbank.portal.response.*;

public interface IAccountService {
    AccountCreateResponse createAccount(AccountCreateRequest accountRequest);
    ViewBalanceResponse viewBalance(ViewBalanceRequest accountRequest);
    PinChangeResponse changePin(PinChangeRequest accountRequest);
    ViewAccountsResponse viewAccounts(ViewAccountsRequest accountsRequest);

}
