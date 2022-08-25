package com.pvbank.portal.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserLoginRequest {
    @NotBlank @NotNull
    private String loginUserName;
    @NotBlank @NotNull
    private String loginPassword;
}
