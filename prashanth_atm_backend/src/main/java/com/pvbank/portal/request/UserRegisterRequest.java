package com.pvbank.portal.request;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserRegisterRequest  {
    @NotBlank @NotNull
    private String userFirstName;
    @NotBlank @NotNull
    private String userLastName;
    @NotBlank @NotNull
    private String loginUserName;
    @NotBlank @NotNull
    private String loginPassword;
    @NotBlank @NotNull
    private String emailID;
    @NotBlank @NotNull
    private String mobileNumber;
}
