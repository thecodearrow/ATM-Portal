package com.pvbank.portal.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
@Getter
@Setter
public class ViewAccountsRequest {
    @NotNull
    private Integer userID;
}
