package com.zoi4erom.shop.mapper;

import com.zoi4erom.shop.dto.read.UserReadDto;
import com.zoi4erom.shop.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements Mapper<User, UserReadDto>{

	@Override
	public UserReadDto toDto(User entity) {
		return UserReadDto.builder()
		    .id(entity.getId())
		    .username(entity.getUsername())
		    .password(entity.getPassword())
		    .email(entity.getEmail())
		    .build();
	}

	@Override
	public User toEntity(UserReadDto dto) {
		return User.builder()
		    .id(dto.getId())
		    .username(dto.getUsername())
		    .email(dto.getEmail())
		    .password(dto.getPassword())
		    .build();
	}
}
