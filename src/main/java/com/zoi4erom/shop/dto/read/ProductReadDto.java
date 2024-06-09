package com.zoi4erom.shop.dto.read;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ProductReadDto {

	private Integer id;
	private String name;
	private Integer cost;
	private ProductTypeReadDto productTypeReadDto;
	private UserReadDto sellerReadDto;
}
