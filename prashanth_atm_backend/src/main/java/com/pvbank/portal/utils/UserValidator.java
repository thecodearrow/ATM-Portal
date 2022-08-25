package com.pvbank.portal.utils;

import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class UserValidator {
    public static Boolean isValidEmail(String email){
        Pattern regexPattern = Pattern.compile("^(.+)@(\\S+)$");
        return regexPattern.matcher(email).matches();

    }
    public static Boolean isValidMobileNo(String mobile){
    Pattern regexPattern = Pattern.compile("^[0-9]{10}$");
    return regexPattern.matcher(mobile).matches();
    }
}
