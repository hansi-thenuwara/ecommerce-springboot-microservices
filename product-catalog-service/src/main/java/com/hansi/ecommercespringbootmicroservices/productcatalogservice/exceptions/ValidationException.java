package com.hansi.ecommercespringbootmicroservices.productcatalogservice.exceptions;

public class ValidationException extends RuntimeException{

    public ValidationException(String message) {
        super(message);
    }
}
