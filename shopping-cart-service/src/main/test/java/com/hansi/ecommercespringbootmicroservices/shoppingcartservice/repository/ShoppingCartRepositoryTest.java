package com.hansi.ecommercespringbootmicroservices.shoppingcartservice.repository;

import com.hansi.ecommercespringbootmicroservices.productcatalogservice.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.util.Assert;
import shoppingcartservice.ShoppingCart;
import om.hansi.ecommercespringbootmicroservices.shoppingcartservice.repository.ShoppingCartRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@DataJpaTest
@Rollback(value = false)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ShoppingCartRepositoryTest {

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @Test
    void testSave() {
        ShoppingCart shoppingCart = ShoppingCart.builder()
                .userId(123)
                .build();
        shoppingCartRepository.save(shoppingCart);
    }

    @Test
    void testUpdate() {
        ShoppingCart shoppingCart = ShoppingCart.builder()
                .userId(123)
                .build();
        shoppingCartRepository.save(shoppingCart);

        shoppingCart.setUserId(234);

        shoppingCartRepository.save(shoppingCart);
    }

    @Test
    void testFindById() {
        ShoppingCart shoppingCart = ShoppingCart.builder()
                .userId(1236)
                .build();
        shoppingCartRepository.save(shoppingCart);

        Assert.isTrue(shoppingCartRepository.findById(shoppingCart.getId()).isPresent(), "cart not found");
    }

    @Test
    void testDeleteById() {
        ShoppingCart shoppingCart = ShoppingCart.builder()
                .userId(123)
                .build();
        shoppingCart = shoppingCartRepository.save(shoppingCart);

        shoppingCartRepository.deleteById(shoppingCart.getId());
    }
}