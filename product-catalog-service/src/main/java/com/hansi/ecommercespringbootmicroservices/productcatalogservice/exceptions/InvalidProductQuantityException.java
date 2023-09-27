package com.hansi.ecommercespringbootmicroservices.productcatalogservice.exceptions;

public class InvalidProductQuantityException extends RuntimeException{
    public InvalidProductQuantityException(String message) {
        super(message);
    }
}
