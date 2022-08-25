package com.pvbank.portal.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepositMoneyResponse extends BaseResponse {
    private Double balanceAmount;

}
