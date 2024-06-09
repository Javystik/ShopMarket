package com.zoi4erom.shop.service;

import com.zoi4erom.shop.dto.read.ProductTypeReadDto;
import com.zoi4erom.shop.dto.save.ProductTypeSaveDto;
import com.zoi4erom.shop.entity.ProductType;
import com.zoi4erom.shop.mapper.ProductTypeMapper;
import com.zoi4erom.shop.repository.ProductTypeRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductTypeService {

	private final ProductTypeRepository productTypeRepository;
	private final ProductTypeMapper productTypeMapper;

	public boolean createProductType(ProductTypeSaveDto productTypeSaveDto) {
		try {
			productTypeRepository.save(ProductType.builder()
			    .name(productTypeSaveDto.getName())
			    .build());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public List<ProductTypeReadDto> findAllProducts() {
		return productTypeRepository.findAll().stream()
		    .map(productTypeMapper::toDto)
		    .toList();
	}

	public Optional<ProductTypeReadDto> findProductTypeById(Integer productTypeId) {
		return productTypeRepository.findById(productTypeId)
		    .map(productTypeMapper::toDto);
	}

	public boolean updateProductType(ProductTypeSaveDto productTypeSaveDto,
	    Integer productTypeId) {
		ProductTypeReadDto productType = findProductTypeById(productTypeId).orElseThrow(
		    () -> new RuntimeException("productType by id not found")
		);

		if (productTypeSaveDto.getName() != null && !productTypeSaveDto.getName().isBlank()) {
			productType.setName(productTypeSaveDto.getName());
		}

		productTypeRepository.save(productTypeMapper.toEntity(productType));
		return true;
	}

	public boolean deleteProductType(Integer productTypeId) {
		try {
			productTypeRepository.deleteById(productTypeId);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
