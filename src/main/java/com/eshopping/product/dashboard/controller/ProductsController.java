package com.eshopping.product.dashboard.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eshopping.product.dashboard.model.Product;
import com.eshopping.product.dashboard.service.ProductService;

/**
 * @author Vish Guntaka
 *
 */
@RestController
@RequestMapping(value = "/products")
public class ProductsController {

	@Autowired
	private ProductService productService;

	/**
	 * @param product
	 * @return
	 */
	@PostMapping
	public ResponseEntity<HttpStatus> saveProduct(@RequestBody Product product) {
		Optional<Product> existingProduct = productService.getProductById(product.getId());

		if (existingProduct.isPresent()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		productService.saveOrUpdate(product);

		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	/**
	 * @param product
	 * @param id
	 * @return
	 */
	@PutMapping(value = "/{product_id}")
	public ResponseEntity<HttpStatus> updateProduct(@RequestBody Product product, @PathVariable("product_id") Long id) {
		Optional<Product> existingProduct = productService.getProductById(id);

		if (!existingProduct.isPresent()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Product updateProduct = existingProduct.get();
		updateProduct.updateProduct(product);

		productService.saveOrUpdate(updateProduct);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/{product_id}")
	public ResponseEntity<Product> getProduct(@PathVariable("product_id") Long id) {
		System.out.println("GET call ###### id=:"+ id);

		Optional<Product> existingProduct = productService.getProductById(id);

		if (!existingProduct.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return ResponseEntity.ok(existingProduct.get());
	}

	/**
	 * @param category
	 * @param availability
	 * @return
	 */
	@GetMapping(params = { "category", "availability" })
	public ResponseEntity<List<Product>> getProductByCategoryAndAvailability(@RequestParam String category,
			@RequestParam Boolean availability) {
		List<Product> productsByCategoryAndAvailability = productService.gerProductByCategoryAndAvailability(category,
				availability);

		return ResponseEntity.ok(productsByCategoryAndAvailability);
	}

	/**
	 * @param category
	 * @return
	 */
	@GetMapping(params = "category")
	public ResponseEntity<List<Product>> getProductByCategory(@RequestParam String category) {
		List<Product> productsByCategory = productService.gerProductByCategory(category);

		return ResponseEntity.ok(productsByCategory);
	}

	/**
	 * @return
	 */
	@GetMapping
	public ResponseEntity<Iterable<Product>> findAllProducts() {
		Iterable<Product> allProducts = productService.findAllProducts();

		return ResponseEntity.ok(allProducts);
	}

}
