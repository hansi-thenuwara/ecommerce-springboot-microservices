package com.hansi.ecommercespringbootmicroservices.shoppingcartservice.exceptions;

public class ProductUpdateException extends RuntimeException {
    public ProductUpdateException(String message) {
        super(message);
    }
}
