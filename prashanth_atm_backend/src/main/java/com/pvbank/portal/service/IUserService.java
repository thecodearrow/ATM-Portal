package com.pvbank.portal.service;

import com.pvbank.portal.request.UserLoginRequest;
import com.pvbank.portal.request.UserRegisterRequest;
import com.pvbank.portal.response.UserLoginResponse;
import com.pvbank.portal.response.UserRegisterResponse;

public interface IUserService {

    UserRegisterResponse saveUser(UserRegisterRequest userRequest);
    UserLoginResponse verifyUser(UserLoginRequest userRequest);
}
