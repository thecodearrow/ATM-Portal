package com.pvbank.portal.exceptions;

import com.pvbank.portal.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class CustomExceptionHandler {
    @ExceptionHandler(value= CustomBadRequestException.class)
    public ResponseEntity<Object> handleAPIBadRequestException(CustomBadRequestException e){
        BaseResponse exceptionResponse=new BaseResponse();
        exceptionResponse.setMessage(e.getMessage());
        log.error("Exception: "+e+e.getMessage());
        exceptionResponse.setStatusCode(HttpStatus.BAD_REQUEST);
        exceptionResponse.setStatus(false);
        return new ResponseEntity<Object>(exceptionResponse,HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler(value= CustomInternalServerErrorException.class)
    public ResponseEntity<Object> handleAPIInternalServerErrorException(CustomInternalServerErrorException e){
        BaseResponse exceptionResponse=new BaseResponse();
        exceptionResponse.setMessage(e.getMessage());
        log.error("Exception: "+e+e.getMessage());
        exceptionResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        exceptionResponse.setStatus(false);
        return new ResponseEntity<Object>(exceptionResponse,HttpStatus.INTERNAL_SERVER_ERROR);

    }

}
