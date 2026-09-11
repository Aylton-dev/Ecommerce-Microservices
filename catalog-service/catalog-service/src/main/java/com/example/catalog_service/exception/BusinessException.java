package com.example.catalog_service.exception;

public class BusinessException extends RuntimeException{
    public BusinessException(String message){
        super(message);
    }

}
