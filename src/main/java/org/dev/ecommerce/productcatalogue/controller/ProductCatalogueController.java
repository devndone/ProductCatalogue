package org.dev.ecommerce.productcatalogue.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.dev.ecommerce.productcatalogue.model.Product;
import org.dev.ecommerce.productcatalogue.service.PricingService;
import org.dev.ecommerce.productcatalogue.validator.ProductValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(basePath = "/products", value = "Products", description = "Operations with Products", produces = "application/json")
@RestController
@RequestMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductCatalogueController {

	public static final Logger logger = LoggerFactory.getLogger(ProductCatalogueController.class);

	private final PricingService pricingService;
	private final ProductValidator productValidator;

	@Autowired
	public ProductCatalogueController(PricingService pricingService, ProductValidator productValidator) {
		this.pricingService = pricingService;
		this.productValidator = productValidator;
	}

	@InitBinder
	protected void dataBinding(WebDataBinder binder) {
		binder.addValidators(productValidator);
	}

	@ApiOperation(value = "Get Product Detail", notes = "Get info about a Product")
	@RequestMapping(method = RequestMethod.GET)
	public Iterable<Product> findAll(@RequestParam(value = "page", defaultValue = "0", required = false) int page,
			@RequestParam(value = "count", defaultValue = "10", required = false) int count,
			@RequestParam(value = "order", defaultValue = "ASC", required = false) Sort.Direction direction,
			@RequestParam(value = "sort", defaultValue = "productName", required = false) String sortProperty) {
		return pricingService.findAll(page, count, direction, sortProperty);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public HttpEntity<?> find(@PathVariable String id) {
		Product detail = pricingService.findOne(id);
		if (detail == null) {
			logger.error("Product with id {} not found.", id);
			return new ResponseEntity<ProductException>(new ProductException("Product with id " + id + " not found"),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Product>(detail, HttpStatus.OK);
	}

	@ApiOperation(value = "Create new Product", notes = "Creates new Product")
	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.CREATED)
	public Product create(@RequestBody @Valid Product detail) {
		return pricingService.save(detail);
	}

	@ApiOperation(value = "Update a Product", notes = "Updates a Product")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	// public HttpEntity<?> update(@PathVariable String id, HttpServletRequest
	// request) throws IOException {
	public HttpEntity<?> update(@PathVariable String id, @Valid @RequestBody Product product) throws IOException {
		Product updated = pricingService.update(id, product);// request.getReader());
		if (updated == null) {
			logger.error("Product with id {} not found.", id);
			return new ResponseEntity<ProductException>(new ProductException("Product with id " + id + " not found"),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Product>(updated, HttpStatus.ACCEPTED);
		// MutablePropertyValues propertyValues = new MutablePropertyValues();
		// propertyValues.add("productId", updated.getProductId());
		// propertyValues.add("productName", updated.getProductName());
		// propertyValues.add("description", updated.getDescription());
		// DataBinder binder = new DataBinder(updated);
		// binder.addValidators(productValidator);
		// binder.bind(propertyValues);
		// binder.validate();
		// if (binder.getBindingResult().hasErrors()) {
		// return new ResponseEntity<>(binder.getBindingResult().getAllErrors(),
		// HttpStatus.BAD_REQUEST);
		// } else {
		// return new ResponseEntity<Product>(updated, HttpStatus.ACCEPTED);
		// }
	}

	@ApiOperation(value = "Delete a Product", notes = "Deletes a Product")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public HttpEntity<?> delete(@PathVariable String id) {
		pricingService.delete(id);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}

	@Bean(name = "messageSource")
	public ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageBundle = new ReloadableResourceBundleMessageSource();
		messageBundle.setBasename("classpath:messages/error");
		messageBundle.setDefaultEncoding("UTF-8");
		return messageBundle;
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	static class ProductException extends RuntimeException {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		private String errorMessage;

		public ProductException(String errorMessage) {
			this.errorMessage = errorMessage;
		}

		public String getErrorMessage() {
			return errorMessage;
		}

		@Override
		public StackTraceElement[] getStackTrace() {
			// logger.error(Arrays.toString(super.getStackTrace()));
			return null;
		}
	}

}