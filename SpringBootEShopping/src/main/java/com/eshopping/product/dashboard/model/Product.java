package com.eshopping.product.dashboard.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.Formula;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Product {

	@Id
	private Long id;

	private String name;
	private String category;
	private Double retailPrice;
	private Double discountedPrice;
	private Boolean availability;

	@JsonIgnore
	@Formula("ceiling(((retail_price-discounted_price)/retail_price)*100)")
	private Integer discountedPercentage;

	public Product() {
	}

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@JsonProperty("retail_price")
	public Double getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(Double retailPrice) {
		this.retailPrice = retailPrice;
	}

	@JsonProperty("discounted_price")
	public Double getDiscountedPrice() {
		return discountedPrice;
	}

	public void setDiscountedPrice(Double discountedPrice) {
		this.discountedPrice = discountedPrice;
	}

	public Boolean getAvailability() {
		return availability;
	}

	public void setAvailability(Boolean availability) {
		this.availability = availability;
	}

}
