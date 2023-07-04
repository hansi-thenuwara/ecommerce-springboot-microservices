package com.hansi.ecommercespringbootmicroservices.productcatalogservice.repository;

import com.hansi.ecommercespringbootmicroservices.productcatalogservice.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@DataJpaTest
@Rollback(value = false)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    void testSave() {
        Product product = Product.builder()
                .name("soap")
                .price(BigDecimal.valueOf(55.00))
                .quantity(50L)
                .build();
        productRepository.save(product);
    }

    @Test
    void testUpdate() {
        Product product = Product.builder()
                .name("toothpaste medium")
                .price(BigDecimal.valueOf(60.00))
                .quantity(50L)
                .build();
        productRepository.save(product);

        product.setName("toothpaste large");
        product.setDescription("whitening gel toothpaste");

        productRepository.save(product);
    }

    @Test
    void testSaveAll() {
        List<Product> productList = new ArrayList<>();
        productList.add(Product.builder()
                .name("Milk 1L")
                .price(BigDecimal.valueOf(100.00))
                .quantity(20L)
                .build());
        productList.add(Product.builder()
                .name("Milk 500ml")
                .price(BigDecimal.valueOf(60.00))
                .quantity(50L)
                .build());
        productRepository.saveAll(productList);
    }

    @Test
    void testFindById() {
        Product product = Product.builder()
                .name("shampoo 250ml")
                .price(BigDecimal.valueOf(80.00))
                .quantity(30L)
                .build();
        productRepository.save(product);

        Assert.isTrue(productRepository.findById(product.getId()).isPresent(), "product not found");
    }

    @Test
    void testFindAll() {
        List<Product> productList = new ArrayList<>();
        productList.add(Product.builder()
                .name("Milk 1L")
                .price(BigDecimal.valueOf(100.00))
                .quantity(20L)
                .build());
        productList.add(Product.builder()
                .name("Milk 500ml")
                .price(BigDecimal.valueOf(60.00))
                .quantity(50L)
                .build());
        productRepository.saveAll(productList);

        Assert.isTrue(productRepository.findAll().size() >= 2, "not found");
    }

    @Test
    void testCount() {
        List<Product> productList = new ArrayList<>();
        productList.add(Product.builder()
                .name("Milk 1L")
                .price(BigDecimal.valueOf(100.00))
                .quantity(20L)
                .build());
        productList.add(Product.builder()
                .name("Milk 500ml")
                .price(BigDecimal.valueOf(60.00))
                .quantity(50L)
                .build());
        productList.add(Product.builder()
                .name("Milk 2500ml")
                .price(BigDecimal.valueOf(30.00))
                .quantity(50L)
                .build());
        productRepository.saveAll(productList);

        Assert.isTrue(productRepository.count() >= 3, "not found");
    }

    @Test
    void testDeleteById() {
        Product product = Product.builder()
                .name("soap")
                .price(BigDecimal.valueOf(55.00))
                .quantity(50L)
                .build();
        productRepository.save(product);

        productRepository.deleteById(product.getId());
    }

    @Test
    void testDelete() {
        Product product = Product.builder()
                .name("soap")
                .price(BigDecimal.valueOf(55.00))
                .quantity(50L)
                .build();
        productRepository.save(product);

        productRepository.delete(product);
    }
}