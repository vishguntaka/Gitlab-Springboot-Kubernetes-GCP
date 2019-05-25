package com.eshopping.product.dashboard.service;

import java.util.List;
import java.util.Optional;

import com.eshopping.product.dashboard.model.Product;

/**
 * @author Vish Guntaka
 *
 */
public interface ProductService {
	/**
	 * @param product
	 */
	void saveOrUpdate(Product product);

	/**
	 * @param id
	 * @return
	 */
	Optional<Product> getProductById(Long id);

	/**
	 * @param category
	 * @return
	 */
	List<Product> gerProductByCategory(String category);

	/**
	 * @param category
	 * @param availability
	 * @return
	 */
	List<Product> gerProductByCategoryAndAvailability(String category, Boolean availability);
	
	/**
	 * @return
	 */
	Iterable<Product> findAllProducts();
}
