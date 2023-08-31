package com.hansi.ecommercespringbootmicroservices.productcatalogservice.service;

import com.hansi.ecommercespringbootmicroservices.productcatalogservice.exceptions.ProductNotFoundException;
import com.hansi.ecommercespringbootmicroservices.productcatalogservice.repository.ProductRepository;
import com.hansi.ecommercespringbootmicroservices.productcatalogservice.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public Product getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + id));
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Optional<Product> existingProduct = productRepository.findById(id);
        if (existingProduct.isPresent()) {
            product.setId(id);
            return productRepository.save(product);
        } else {
            throw new ProductNotFoundException("Product not found with ID: " + id);
        }
    }

    @Override
    public boolean deleteProduct(Long id) {
        Optional<Product> existingProduct = productRepository.findById(id);
        if (existingProduct.isPresent()) {
            productRepository.deleteById(id);
            return true;
        } else {
            throw new ProductNotFoundException("Product not found with ID: " + id);
        }
    }

    @Override
    public List<Product> list() {
        return productRepository.findAll();
    }
}
