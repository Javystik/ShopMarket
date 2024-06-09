package com.zoi4erom.shop.dto.save;

import lombok.Getter;

@Getter
public class ProductSaveDto {

	private String name;
	private Integer cost;
	private Integer productTypeId;
	private Integer sellerId;
}
