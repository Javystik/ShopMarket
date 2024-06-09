package com.zoi4erom.shop.mapper;

import com.zoi4erom.shop.dto.read.ProductReadDto;
import com.zoi4erom.shop.entity.Product;
import com.zoi4erom.shop.entity.ProductType;
import com.zoi4erom.shop.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductMapper implements Mapper<Product, ProductReadDto> {

	private final ProductTypeMapper productTypeMapper;
	private final UserMapper userMapper;

	@Override
	public ProductReadDto toDto(Product entity) {
		return ProductReadDto.builder()
		    .id(entity.getId())
		    .name(entity.getName())
		    .cost(entity.getCost())
		    .productTypeReadDto(productTypeMapper.toDto(entity.getProductType()))
		    .sellerReadDto(userMapper.toDto(entity.getSeller()))
		    .build();
	}

	@Override
	public Product toEntity(ProductReadDto dto) {
		return Product.builder()
		    .id(dto.getId())
		    .name(dto.getName())
		    .cost(dto.getCost())
		    .seller(User.builder().id(dto.getSellerReadDto().getId()).build())
		    .productType(ProductType.builder().id(dto.getProductTypeReadDto().getId()).build())
		    .build();
	}
}
