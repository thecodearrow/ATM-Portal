package com.pvbank.portal.response;

import com.pvbank.portal.entity.Transaction;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ViewTransactionsResponse extends BaseResponse{
    List<Object> transactions;

}
