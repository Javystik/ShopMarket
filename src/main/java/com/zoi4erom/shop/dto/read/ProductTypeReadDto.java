package com.zoi4erom.shop.dto.read;

import java.io.Serializable;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ProductTypeReadDto implements Serializable {

	private Integer id;
	private String name;
}
