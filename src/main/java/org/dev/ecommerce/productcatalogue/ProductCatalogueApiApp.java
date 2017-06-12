package org.dev.ecommerce.productcatalogue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"org.dev.ecommerce.productcatalogue"})
public class ProductCatalogueApiApp {

	public static void main(String[] args) {
		SpringApplication.run(ProductCatalogueApiApp.class, args);
	}
}
