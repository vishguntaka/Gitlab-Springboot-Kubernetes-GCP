package com.eshopping.product.dashboard.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Formula;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	private String name;
	private String category;
	@JsonProperty("retail_price")
	private Double retailPrice;
	@JsonProperty("discounted_price")
	private Double discountedPrice;
	private Boolean availability;

	@JsonIgnore
	@Formula("ceiling(((retail_price-discounted_price)/retail_price)*100)")
	private Integer discountedPercentage;

	public Product(Long id, String name, String category, Double retailPrice, Double discountedPrice,
			Boolean availability) {
		this.id = id;
		this.name = name;
		this.category = category;
		this.retailPrice = retailPrice;
		this.discountedPrice = discountedPrice;
		this.availability = availability;
	}

	public void updateProduct(Product updatedProduct) {
		this.retailPrice = updatedProduct.getRetailPrice();
		this.discountedPrice = updatedProduct.getDiscountedPrice();
		this.availability = updatedProduct.getAvailability();
	}
}
