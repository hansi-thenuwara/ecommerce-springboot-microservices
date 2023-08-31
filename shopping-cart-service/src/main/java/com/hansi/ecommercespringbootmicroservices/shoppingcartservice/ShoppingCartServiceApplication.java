package com.hansi.ecommercespringbootmicroservices.shoppingcartservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.hansi.ecommercespringbootmicroservices.shoppingcartservice.repository")
@EntityScan("com.hansi.ecommercespringbootmicroservices.shoppingcartservice.model")
public class ShoppingCartServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShoppingCartServiceApplication.class, args);
    }

}
