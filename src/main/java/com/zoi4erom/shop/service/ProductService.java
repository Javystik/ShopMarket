package com.zoi4erom.shop.service;

import com.zoi4erom.shop.dto.read.ProductReadDto;
import com.zoi4erom.shop.dto.save.ProductSaveDto;
import com.zoi4erom.shop.entity.Product;
import com.zoi4erom.shop.entity.ProductType;
import com.zoi4erom.shop.entity.User;
import com.zoi4erom.shop.mapper.ProductMapper;
import com.zoi4erom.shop.repository.ProductRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;
	private final ProductMapper productMapper;

	public boolean createProduct(ProductSaveDto productSaveDto) {
		try {
			productRepository.save(Product.builder()
			    .name(productSaveDto.getName())
			    .cost(productSaveDto.getCost())
			    .productType(
				  ProductType.builder().id(productSaveDto.getProductTypeId()).build())
			    .seller(User.builder().id(productSaveDto.getSellerId()).build())
			    .build());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public List<ProductReadDto> findAllProducts() {
		return productRepository.findAll().stream()
		    .map(productMapper::toDto)
		    .toList();
	}

	@Cacheable(value = "ProductService::findProductsByProductTypeName", key = "#productTypeName")
	public List<ProductReadDto> findProductsByProductTypeName(String productTypeName) {
		return productRepository.findProductsByProductType_Name(productTypeName).stream()
		    .map(productMapper::toDto)
		    .toList();
	}

	@Cacheable(value = "ProductService::findProductById", key = "#productId")
	public Optional<ProductReadDto> findProductById(Integer productId) {
		return productRepository.findById(productId)
		    .map(productMapper::toDto);
	}

	@Cacheable(value = "ProductService::findProductByName", key = "#productName")
	public Optional<ProductReadDto> findProductByName(String productName) {
		return productRepository.findProductsByName(productName)
		    .map(productMapper::toDto);
	}

	public boolean updateProduct(ProductSaveDto productSaveDto, Integer productId) {
		var updateProduct = findProductById(productId).orElseThrow(
		    () -> new RuntimeException("product by id: " + productId + " not found")
		);

		if (productSaveDto.getName() != null && !productSaveDto.getName().isBlank()) {
			updateProduct.setName(productSaveDto.getName());
		}
		if (productSaveDto.getCost() != null) {
			updateProduct.setCost(productSaveDto.getCost());
		}

		productRepository.save(productMapper.toEntity(updateProduct));
		return true;
	}

	@CacheEvict(value = "ProductService::findProductById", key = "#productId")
	public boolean deleteProduct(Integer productId) {
		try {
			productRepository.deleteById(productId);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
