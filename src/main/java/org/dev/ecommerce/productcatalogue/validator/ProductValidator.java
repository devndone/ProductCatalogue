package org.dev.ecommerce.productcatalogue.validator;

import org.dev.ecommerce.productcatalogue.model.Product;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ProductValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Product.class.isAssignableFrom(clazz);
	}

	/**
	 * If product id is not of length 11 and doesn't starts with character "p" 
	 * then throw error
	 */
	@Override
	public void validate(Object target, Errors errors) {
		Product detail = (Product) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "productId", "", "error.productId.notnull");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "productName", "", "error.productName.notnull");
		if (detail.getProductId().length() != 11) {
			errors.rejectValue("productId", "", "error.productId.size");
		}
	}
	
}
