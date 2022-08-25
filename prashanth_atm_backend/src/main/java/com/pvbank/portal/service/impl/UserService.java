package com.pvbank.portal.service.impl;

import com.pvbank.portal.entity.User;
import com.pvbank.portal.exceptions.CustomBadRequestException;
import com.pvbank.portal.exceptions.CustomInternalServerErrorException;
import com.pvbank.portal.repository.UserRepository;
import com.pvbank.portal.request.UserLoginRequest;
import com.pvbank.portal.request.UserRegisterRequest;
import com.pvbank.portal.response.UserLoginResponse;
import com.pvbank.portal.response.UserRegisterResponse;
import com.pvbank.portal.service.IUserService;
import com.pvbank.portal.utils.UserValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
@Slf4j


public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserValidator validator;

    @Override
    public UserRegisterResponse saveUser(UserRegisterRequest userRequest) {
        User user = new User();
        UserRegisterResponse userResponse = new UserRegisterResponse();
        try {
            Boolean isValidEmail = validator.isValidEmail(userRequest.getEmailID());
            Boolean isValidMobileNo = validator.isValidMobileNo(userRequest.getMobileNumber());
            if (isValidEmail && isValidMobileNo) {
                user.setUserFirstName(userRequest.getUserFirstName());
                user.setUserLastName(userRequest.getUserLastName());
                user.setLoginUserName(userRequest.getLoginUserName());
                user.setLoginPassword(userRequest.getLoginPassword());
                user.setEmailID(userRequest.getEmailID());
                user.setMobileNumber(userRequest.getMobileNumber());
                userRepository.save(user); //write to db!
                userResponse.setStatusCode(HttpStatus.OK);
                userResponse.setMessage("User successfully created!");
                userResponse.setStatus(true);

            } else {
                throw new CustomBadRequestException("Could not create new user. ");
            }
        }
        catch(Exception e){
            throw new CustomInternalServerErrorException("Something went wrong!",e);
        }
        return userResponse;
    }

    @Override
    public UserLoginResponse verifyUser(UserLoginRequest userRequest) {
        UserLoginResponse response = new UserLoginResponse();
        //authenticate
        User user = userRepository.findByLoginUserNameAndLoginPassword(userRequest.getLoginUserName(),userRequest.getLoginPassword());
        if(user==null){
            throw new CustomBadRequestException("User Credentials invalid");
        }
        response.setStatus(true);
        response.setUserID(user.getId());
        response.setMessage("Login successful!");
        response.setStatusCode(HttpStatus.OK);
        return response;
    }
}
