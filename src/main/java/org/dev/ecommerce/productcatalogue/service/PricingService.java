package org.dev.ecommerce.productcatalogue.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import org.dev.ecommerce.productcatalogue.model.Product;
import org.springframework.data.domain.Sort.Direction;

public interface PricingService {

	Iterable<Product> findAll(int page, int count, Direction direction, String sortProperty);

	Product save(Product detail);

	Product findOne(String id);

	void delete(String id);

	Product update(String id, Product product) throws IOException;
	//public Product update(String id, BufferedReader reader) throws IOException;

	List<Product> search(String queryTerm);

}
