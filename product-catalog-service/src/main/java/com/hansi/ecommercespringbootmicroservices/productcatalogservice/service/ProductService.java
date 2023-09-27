package com.hansi.ecommercespringbootmicroservices.productcatalogservice.service;

import com.hansi.ecommercespringbootmicroservices.productcatalogservice.model.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(Long id);

    Product createProduct(Product product);

    Product updateProduct(Long id, Product product);

    boolean deleteProduct(Long id);

    List<Product> list();

    void updateProductQuantity(Long productId, int quantityChange);
}
