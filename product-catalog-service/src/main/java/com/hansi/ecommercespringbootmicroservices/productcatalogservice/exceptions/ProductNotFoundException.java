package com.hansi.ecommercespringbootmicroservices.productcatalogservice.exceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message) {
        super(message);
    }
}
