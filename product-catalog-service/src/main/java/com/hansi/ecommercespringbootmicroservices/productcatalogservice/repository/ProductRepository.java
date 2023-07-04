package com.hansi.ecommercespringbootmicroservices.productcatalogservice.repository;

import com.hansi.ecommercespringbootmicroservices.productcatalogservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
