package com.zoi4erom.shop.controller;

import com.zoi4erom.shop.dto.read.ProductReadDto;
import com.zoi4erom.shop.dto.save.ProductSaveDto;
import com.zoi4erom.shop.service.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

	private final ProductService productService;

	@PostMapping
	public ResponseEntity<String> createProduct(@RequestBody ProductSaveDto productSaveDto) {
		return productService.createProduct(productSaveDto) ? ResponseEntity.ok().build()
		    : ResponseEntity.badRequest().build();
	}

	@GetMapping
	public ResponseEntity<List<ProductReadDto>> getProducts() {
		var products = productService.findAllProducts();

		return products.isEmpty() ? ResponseEntity.noContent().build()
		    : ResponseEntity.ok(products);
	}

	@GetMapping("/productTypeName/{productTypeName}")
	public ResponseEntity<List<ProductReadDto>> getProducts(
	    @PathVariable String productTypeName) {
		var products = productService.findProductsByProductTypeName(productTypeName);

		return products.isEmpty() ? ResponseEntity.noContent().build()
		    : ResponseEntity.ok(products);
	}


	@GetMapping("/{productId}")
	public ResponseEntity<ProductReadDto> getProductById(@PathVariable Integer productId) {
		return productService.findProductById(productId)
		    .map(ResponseEntity::ok)
		    .orElseGet(() -> ResponseEntity.notFound().build());
	}

	@GetMapping("/productName/{productName}")
	public ResponseEntity<ProductReadDto> getProductByName(@PathVariable String productName) {
		return productService.findProductByName(productName)
		    .map(ResponseEntity::ok)
		    .orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PutMapping("/{productId}")
	public ResponseEntity<Void> updateProduct(@RequestBody ProductSaveDto productSaveDto,
	    @PathVariable Integer productId) {
		return productService.updateProduct(productSaveDto, productId) ? ResponseEntity.ok()
		    .build() : ResponseEntity.badRequest().build();
	}

	@DeleteMapping("/{productId}")
	public ResponseEntity<String> deleteProduct(@PathVariable Integer productId) {
		return productService.deleteProduct(productId) ? ResponseEntity.ok().build()
		    : ResponseEntity.badRequest().build();
	}
}
