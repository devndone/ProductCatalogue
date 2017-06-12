package org.dev.ecommerce.productcatalogue.controller;

import java.util.List;

import org.dev.ecommerce.productcatalogue.model.Product;
import org.dev.ecommerce.productcatalogue.service.PricingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
public class ProductSearchController {

	private final PricingService pricingService;

	@Autowired
	public ProductSearchController(PricingService pricingService) {
		this.pricingService = pricingService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<Product> search(@RequestParam("q") String queryTerm) {
		return pricingService.search("%" + queryTerm + "%");
	}
	
}
