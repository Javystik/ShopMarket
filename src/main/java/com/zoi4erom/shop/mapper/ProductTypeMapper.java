package com.zoi4erom.shop.mapper;

import com.zoi4erom.shop.dto.read.ProductTypeReadDto;
import com.zoi4erom.shop.entity.ProductType;
import org.springframework.stereotype.Component;

@Component
public class ProductTypeMapper implements Mapper<ProductType, ProductTypeReadDto> {

	@Override
	public ProductTypeReadDto toDto(ProductType entity) {
		return ProductTypeReadDto.builder()
		    .id(entity.getId())
		    .name(entity.getName())
		    .build();
	}

	@Override
	public ProductType toEntity(ProductTypeReadDto dto) {
		return ProductType.builder()
		    .id(dto.getId())
		    .name(dto.getName())
		    .build();
	}
}
