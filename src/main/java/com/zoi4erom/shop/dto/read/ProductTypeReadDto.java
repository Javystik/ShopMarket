package com.zoi4erom.shop.dto.read;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ProductTypeReadDto {

	private Integer id;
	private String name;
}
