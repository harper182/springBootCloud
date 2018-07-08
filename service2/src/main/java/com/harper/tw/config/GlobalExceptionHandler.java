package com.harper.tw.config;

import com.harper.tw.Result;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.security.InvalidParameterException;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public Result<String> handlerException(MethodArgumentNotValidException e){
        StringBuffer stringBuffer = new StringBuffer();
        for(ObjectError objectError : e.getBindingResult().getAllErrors()){
            stringBuffer.append(" invalid field :" + ((FieldError)objectError).getField());
        }
        Result<String> result = new Result();
        result.setCode(HttpStatus.BAD_REQUEST.value());
        result.setMessage(stringBuffer.toString());
        return result;
    }
}
