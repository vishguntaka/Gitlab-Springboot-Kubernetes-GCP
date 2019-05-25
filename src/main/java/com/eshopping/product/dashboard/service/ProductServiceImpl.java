package com.eshopping.product.dashboard.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshopping.product.dashboard.model.Product;
import com.eshopping.product.dashboard.repository.ProductRepository;

/**
 * @author Vish Guntaka
 *
 */
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hackerrank.eshopping.product.dashboard.service.ProductService#
	 * saveOrUpdate(com.hackerrank.eshopping.product.dashboard.model.Product)
	 */
	@Override
	public void saveOrUpdate(Product product) {
		productRepository.save(product);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hackerrank.eshopping.product.dashboard.service.ProductService#
	 * gerProductById(java.lang.Long)
	 */
	@Override
	public Optional<Product> getProductById(Long id) {
		return productRepository.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hackerrank.eshopping.product.dashboard.service.ProductService#
	 * gerProductByCategory(java.lang.String)
	 */
	@Override
	public List<Product> gerProductByCategory(String category) {
		return productRepository.findByCategoryOrderByAvailabilityDescDiscountedPriceAscIdAsc(category);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hackerrank.eshopping.product.dashboard.service.ProductService#
	 * gerProductByCategoryAndAvailability(java.lang.String, java.lang.Boolean)
	 */
	@Override
	public List<Product> gerProductByCategoryAndAvailability(String category, Boolean availability) {
		return productRepository.findByCategoryAndAvailabilityOrderByDiscountedPercentageDescDiscountedPriceAscIdAsc(
				category, availability);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hackerrank.eshopping.product.dashboard.service.ProductService#
	 * findAllProducts()
	 */
	@Override
	public Iterable<Product> findAllProducts() {

		return productRepository.findAll();
	}

}
