package org.dev.ecommerce.productcatalogue.repository;

import java.util.List;

import org.dev.ecommerce.productcatalogue.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, String> {

	@Query("select p from Product p where UPPER(p.productName) like UPPER(?1) or "
			+ "UPPER(p.description) like UPPER(?1)")
	public List<Product> search(String term);
	
}
