package com.hansi.ecommercespringbootmicroservices.shoppingcartservice.exceptions;

public class ShoppingCartNotFoundException extends RuntimeException{

    public ShoppingCartNotFoundException(String message) {
        super(message);
    }
}
