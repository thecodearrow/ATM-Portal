package com.pvbank.portal.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WithdrawMoneyResponse extends BaseResponse{
    private Double balanceAmount;
    private String message;
    private Boolean status;
}
