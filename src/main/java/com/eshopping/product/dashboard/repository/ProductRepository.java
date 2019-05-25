/**
 * 
 */
package com.eshopping.product.dashboard.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.eshopping.product.dashboard.model.Product;

import java.util.List;

/**
 * @author Vish Guntaka
 *
 */
@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
	/**
	 * @param category
	 * @return
	 */
	List<Product> findByCategoryOrderByAvailabilityDescDiscountedPriceAscIdAsc(String category);

	/**
	 * @param category
	 * @param availability
	 * @return
	 */

	List<Product> findByCategoryAndAvailabilityOrderByDiscountedPercentageDescDiscountedPriceAscIdAsc(String category,
			Boolean availability);
}