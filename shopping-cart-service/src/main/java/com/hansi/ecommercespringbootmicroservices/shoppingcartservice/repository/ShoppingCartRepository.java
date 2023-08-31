package com.hansi.ecommercespringbootmicroservices.shoppingcartservice.repository;

import com.hansi.ecommercespringbootmicroservices.shoppingcartservice.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
}
