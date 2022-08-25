package com.pvbank.portal.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountCreateResponse extends BaseResponse{
    private String accountNumber;
    private String ifscCode;
    private String debitCardNumber;
    private String pin;

}
