package com.hansi.ecommercespringbootmicroservices.shoppingcartservice.exceptions;

public class CartItemNotFoundException extends RuntimeException{

    public CartItemNotFoundException(String message) {
        super(message);
    }
}
