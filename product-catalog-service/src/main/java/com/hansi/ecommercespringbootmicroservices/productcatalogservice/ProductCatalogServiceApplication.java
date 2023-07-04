package com.hansi.ecommercespringbootmicroservices.productcatalogservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.hansi.ecommercespringbootmicroservices.productcatalogservice.repository")
@EntityScan("com.hansi.ecommercespringbootmicroservices.productcatalogservice.model")
public class ProductCatalogServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductCatalogServiceApplication.class, args);
	}

}
