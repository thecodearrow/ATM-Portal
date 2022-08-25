package com.pvbank.portal.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ViewTransactionsRequest {
    @NotBlank @NotNull
    private String debitCardNumber;
    @NotBlank @NotNull
    private String pin;

}
