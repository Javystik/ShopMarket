package com.zoi4erom.shop.service;

import com.zoi4erom.shop.dto.save.UserSaveDto;
import com.zoi4erom.shop.dto.read.UserReadDto;
import com.zoi4erom.shop.entity.User;
import com.zoi4erom.shop.mapper.UserMapper;
import com.zoi4erom.shop.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final UserMapper userMapper;

	public boolean createUser(UserSaveDto userSaveDto) {
		try {
			userRepository.save(User.builder()
			    .username(userSaveDto.getUsername())
			    .password(userSaveDto.getPassword())
			    .email(userSaveDto.getEmail())
			    .build());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public List<UserReadDto> findAllUsers() {
		return userRepository.findAll().stream()
		    .map(userMapper::toDto)
		    .toList();
	}

	public Optional<UserReadDto> findUserById(Integer userId) {
		return userRepository.findById(userId)
		    .map(userMapper::toDto);
	}

	public Optional<UserReadDto> findUserByUsername(String username) {
		return userRepository.findUserByUsername(username)
		    .map(userMapper::toDto);
	}

	public Optional<UserReadDto> findUserByEmail(String email) {
		return userRepository.findUserByEmail(email)
		    .map(userMapper::toDto);
	}

	public boolean updateUser(UserSaveDto userSaveDto, Integer id) {
		UserReadDto userReadDto = findUserById(id).orElseThrow(
		    () -> new RuntimeException("user by id not found")
		);

		User user = userMapper.toEntity(userReadDto);

		if (userSaveDto.getUsername() != null && !userSaveDto.getUsername().isBlank()) {
			user.setUsername(userSaveDto.getUsername());
		}
		if (userSaveDto.getPassword() != null && !userSaveDto.getPassword().isBlank()) {
			user.setPassword(userSaveDto.getPassword());
		}
		if (userSaveDto.getEmail() != null && !userSaveDto.getEmail().isBlank()) {
			user.setEmail(userSaveDto.getEmail());
		}

		userRepository.save(user);
		return true;
	}

	public boolean deleteUserById(Integer userId) {
		try {
			userRepository.deleteById(userId);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
