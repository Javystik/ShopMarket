package com.zoi4erom.shop.controller;

import com.zoi4erom.shop.dto.read.ProductTypeReadDto;
import com.zoi4erom.shop.dto.save.ProductTypeSaveDto;
import com.zoi4erom.shop.service.ProductTypeService;
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
@RequestMapping("/productTypes")
@RequiredArgsConstructor
public class ProductTypeController {

	private final ProductTypeService productTypeService;

	@PostMapping
	public ResponseEntity<String> createProductType(
	    @RequestBody ProductTypeSaveDto productTypeSaveDto) {
		return productTypeService.createProductType(productTypeSaveDto) ? ResponseEntity.ok()
		    .build() : ResponseEntity.badRequest().build();
	}

	@GetMapping
	public ResponseEntity<List<ProductTypeReadDto>> getAllProductTypes() {
		var allProductTypes = productTypeService.findAllProducts();

		return allProductTypes.isEmpty() ? ResponseEntity.noContent().build()
		    : ResponseEntity.ok(allProductTypes);
	}

	@GetMapping("/{productTypeId}")
	public ResponseEntity<ProductTypeReadDto> getProductTypeById(
	    @PathVariable Integer productTypeId) {
		return productTypeService.findProductTypeById(productTypeId)
		    .map(ResponseEntity::ok)
		    .orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PutMapping("/{productTypeId}")
	public ResponseEntity<String> updateProductType(
	    @RequestBody ProductTypeSaveDto productTypeSaveDto, @PathVariable Integer productTypeId) {
		return productTypeService.updateProductType(productTypeSaveDto, productTypeId)
		    ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
	}

	@DeleteMapping("/{productTypeId}")
	public ResponseEntity<String> deleteProductType(@PathVariable Integer productTypeId) {
		return productTypeService.deleteProductType(productTypeId) ? ResponseEntity.ok().build()
		    : ResponseEntity.badRequest().build();
	}
}