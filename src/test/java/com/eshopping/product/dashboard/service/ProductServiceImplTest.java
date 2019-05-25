package com.eshopping.product.dashboard.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.eshopping.product.dashboard.model.Product;
import com.eshopping.product.dashboard.repository.ProductRepository;
import com.eshopping.product.dashboard.service.ProductServiceImpl;

public class ProductServiceImplTest {

	@InjectMocks
	private ProductServiceImpl classToTest;

	@Mock
	private ProductRepository productRepository;

	private Product product;
	List<Product> prodList;

	@Before
	public void setUp() throws Exception {
		classToTest = new ProductServiceImpl();
		product = new Product(Long.valueOf(123), "testName", "testCategory", Double.valueOf(123), Double.valueOf(456),
				Boolean.TRUE);

		prodList = new ArrayList<>();
		prodList.add(product);

		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testSaveOrUpdate() {
		when(productRepository.save(product)).thenReturn(product);

		classToTest.saveOrUpdate(product);

		verify(productRepository, times(1)).save(product);
	}

	@Test
	public void testGerProductById() {

		Long id = Long.valueOf(12);

		Optional<Product> ofNullable = Optional.ofNullable(product);
		when(productRepository.findById(id)).thenReturn(ofNullable);

		Optional<Product> getProductById = classToTest.getProductById(id);

		verify(productRepository, times(1)).findById(id);

		assertEquals(ofNullable, getProductById);
	}

	@Test
	public void testGerProductByCategory() {
		String category = "testcategory";

		when(productRepository.findByCategoryOrderByAvailabilityDescDiscountedPriceAscIdAsc(category))
				.thenReturn(prodList);

		List<Product> result = classToTest.gerProductByCategory(category);

		verify(productRepository, times(1)).findByCategoryOrderByAvailabilityDescDiscountedPriceAscIdAsc(category);
		assertEquals(prodList, result);
	}

	@Test
	public void testGerProductByCategoryAndAvailability() {
		String category = "testcategory";

		when(productRepository
				.findByCategoryAndAvailabilityOrderByDiscountedPercentageDescDiscountedPriceAscIdAsc(category, true))
						.thenReturn(prodList);

		List<Product> result = classToTest.gerProductByCategoryAndAvailability(category, true);

		verify(productRepository, times(1))
				.findByCategoryAndAvailabilityOrderByDiscountedPercentageDescDiscountedPriceAscIdAsc(category, true);
		assertEquals(prodList, result);
	}

	@Test
	public void testFindAllProducts() {

		when(productRepository.findAll()).thenReturn(prodList);

		Iterable<Product> result = classToTest.findAllProducts();

		verify(productRepository, times(1)).findAll();
		assertEquals(prodList, result);

	}

}
