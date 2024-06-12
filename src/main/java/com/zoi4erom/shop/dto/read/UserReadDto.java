package com.zoi4erom.shop.dto.read;

import java.io.Serializable;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class UserReadDto implements Serializable {

	private Integer id;
	private String username;
	private String password;
	private String email;
}
