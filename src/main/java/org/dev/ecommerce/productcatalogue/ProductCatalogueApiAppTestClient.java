package org.dev.ecommerce.productcatalogue;

import java.util.LinkedHashMap;
import java.util.List;

import org.dev.ecommerce.productcatalogue.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

public class ProductCatalogueApiAppTestClient {

	public static final Logger logger = LoggerFactory.getLogger(ProductCatalogueApiAppTestClient.class);

	public static final String REST_SERVICE_URI = "http://localhost:8080/ProductCatalogue";

	/* GET */
	@SuppressWarnings("unchecked")
	private static void listAllProducts() {

		try {
			logger.debug("\n\n");
			logger.debug("Testing list All Products API-----------");

			RestTemplate restTemplate = new RestTemplate();
			List<LinkedHashMap<String, Object>> productsMap = restTemplate.getForObject(REST_SERVICE_URI + "/products/",
					List.class);

			if (productsMap != null) {
				for (LinkedHashMap<String, Object> map : productsMap) {
					logger.debug("Product : productId=" + map.get("productId") + ", productName="
							+ map.get("productName") + ", description=" + map.get("description"));
					;
				}
			} else {
				logger.debug("No product exist----------");
			}
		} catch (Exception ex) {

		}
	}

	/* GET */
	private static void getProduct() {
		try {
			logger.debug("\n\n");
			logger.debug("Testing get Product API----------");
			RestTemplate restTemplate = new RestTemplate();
			Product product = restTemplate.getForObject(REST_SERVICE_URI + "/products/p1234567890", Product.class);
			logger.debug(product.toString());
		} catch (Exception ex) {

		}
	}

	/* POST */
	private static void createProduct() {
		try {
			logger.debug("\n\n");
			logger.debug("Testing create Product API----------");
			RestTemplate restTemplate = new RestTemplate();
			Product product = null;
			product = new Product("p1234567890", "Shoe", "Nike Shoe");
			restTemplate.postForLocation(REST_SERVICE_URI + "/products/", product, Product.class);
			product = new Product("p1234567891", "Shoe", "Adi Shoe");
			restTemplate.postForLocation(REST_SERVICE_URI + "/products/", product, Product.class);
		} catch (Exception ex) {

		}
	}

	/* PUT */
	private static void updateProduct() {
		try {
			logger.debug("\n\n");
			logger.debug("Testing update Product API----------");
			RestTemplate restTemplate = new RestTemplate();
			Product product = new Product("p1234567890", "Shoe", "Rebock Shoe");
			restTemplate.put(REST_SERVICE_URI + "/products/p1234567890", product);
			logger.debug(product.toString());
		} catch (Exception ex) {

		}
	}

	/* DELETE */
	private static void deleteProduct() {
		try {
			logger.debug("\n\n");
			logger.debug("Testing delete Product API----------");
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.delete(REST_SERVICE_URI + "/products/p1234567891");
		} catch (Exception ex) {

		}
	}

	public static void main(String args[]) {
		listAllProducts();
		getProduct();
		createProduct();
		listAllProducts();
		updateProduct();
		listAllProducts();
		deleteProduct();
		listAllProducts();
	}
	
}