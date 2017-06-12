package org.dev.ecommerce.productcatalogue.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.dev.ecommerce.productcatalogue.model.Product;
import org.dev.ecommerce.productcatalogue.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service("productService")
public class ProductServiceImpl implements PricingService {

	private final ProductRepository productRepository;
	private final ObjectMapper objectMapper;

	@Autowired
	public ProductServiceImpl(ProductRepository productRepository, ObjectMapper objectMapper) {
		this.productRepository = productRepository;
		this.objectMapper = objectMapper;
	}

	@Override
	public Iterable<Product> findAll(int page, int count, Direction direction, String sortProperty) {
		Page<Product> result = productRepository
				.findAll(new PageRequest(page, count, new Sort(direction, sortProperty)));
		return result.getContent();
	}

	@Override
	public Product save(Product detail) {
		return productRepository.save(detail);
	}

	@Override
	public Product findOne(String id) {
		return productRepository.findOne(id);
	}

	@Override
	public void delete(String id) {
		Product existing = findOne(id);
		if(existing == null) {
			return;
		}
		productRepository.delete(existing);
	}

	@Override
	//public Product update(String id, BufferedReader reader) throws IOException {
	public Product update(String id, Product product) throws IOException {
		Product existing = findOne(id);
		if(existing == null) {
			return null;
		}
		existing = product;
		return productRepository.save(existing);
		//return objectMapper.readerForUpdating(existing).readValue(reader);
	}

	@Override
	public List<Product> search(String queryTerm) {
		List<Product> productDetails = productRepository.search(queryTerm);
		return productDetails == null ? new ArrayList<>() : productDetails;
	}

}
