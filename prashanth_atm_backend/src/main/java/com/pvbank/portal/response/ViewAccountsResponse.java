package com.pvbank.portal.response;

import com.pvbank.portal.entity.Account;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ViewAccountsResponse extends BaseResponse{
    private List<String> debitCardNumbers;
}
