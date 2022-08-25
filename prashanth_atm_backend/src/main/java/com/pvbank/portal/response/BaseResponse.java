package com.pvbank.portal.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;

@Getter
@Setter
public class BaseResponse {
    private String message;
    private Boolean status;
    private HttpStatus statusCode=HttpStatus.OK; //default
    private Timestamp timestamp=new Timestamp(System.currentTimeMillis());;

}
