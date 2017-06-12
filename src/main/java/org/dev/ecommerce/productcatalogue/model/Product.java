package org.dev.ecommerce.productcatalogue.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 687073094645471332L;

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", description=" + description
				+ "]";
	}

	@NotNull(message = "error.productId.notnull")
	@Size(min = 11, max = 11, message = "error.productId.size")
	@Id
	private String productId;
	
	@NotNull(message = "error.productName.notnull")
	private String productName;
	
	private String description;

	public Product() {}
	
	public Product(String productId, String productName, String description) {
		this.productId = productId;
		this.productName = productName;
		this.description = description;
	}
	
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
