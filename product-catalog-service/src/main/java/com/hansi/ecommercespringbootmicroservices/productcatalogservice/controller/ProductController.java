package com.hansi.ecommercespringbootmicroservices.productcatalogservice.controller;

import com.hansi.ecommercespringbootmicroservices.productcatalogservice.exceptions.InvalidProductQuantityException;
import com.hansi.ecommercespringbootmicroservices.productcatalogservice.exceptions.ProductNotFoundException;
import com.hansi.ecommercespringbootmicroservices.productcatalogservice.model.Product;
import com.hansi.ecommercespringbootmicroservices.productcatalogservice.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody @Valid Product product) {
        Product createdProduct = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(id, product);
        if (updatedProduct != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        boolean deleted = productService.deleteProduct(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Product>> list() {
        return ResponseEntity.ok(productService.list());
    }

    @PutMapping("/{productId}/update-quantity")
    public ResponseEntity<String> updateProductQuantity(
            @PathVariable Long productId,
            @RequestParam int quantityChange) {

        try {
            productService.updateProductQuantity(productId, quantityChange);
            return ResponseEntity.ok("Product quantity updated successfully.");
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Product not found.");
        } catch (InvalidProductQuantityException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid product quantity change.");
        }
    }
}
