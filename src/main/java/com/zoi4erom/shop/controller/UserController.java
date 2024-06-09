package com.zoi4erom.shop.controller;

import com.zoi4erom.shop.dto.read.UserReadDto;
import com.zoi4erom.shop.dto.save.UserSaveDto;
import com.zoi4erom.shop.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@PostMapping
	public ResponseEntity<String> createUser(@RequestBody UserSaveDto userSaveDto) {
		return userService.createUser(userSaveDto) ? ResponseEntity.ok().build()
		    : ResponseEntity.badRequest().build();
	}

	@GetMapping
	public ResponseEntity<List<UserReadDto>> getAllUsers() {
		var users = userService.findAllUsers();

		return users.isEmpty() ? ResponseEntity.noContent().build()
		    : ResponseEntity.ok(users);
	}

	@GetMapping("/username/{username}")
	public ResponseEntity<UserReadDto> getUserByUsername(@PathVariable String username) {
		return userService.findUserByUsername(username)
		    .map(ResponseEntity::ok)
		    .orElseGet(() -> ResponseEntity.notFound().build());
	}

	@GetMapping("/email/{email}")
	public ResponseEntity<UserReadDto> getUserByEmail(@PathVariable String email) {
		return userService.findUserByEmail(email)
		    .map(ResponseEntity::ok)
		    .orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> updateUser(@RequestBody UserSaveDto userSaveDto,
	    @PathVariable Integer id) {
		return userService.updateUser(userSaveDto, id) ? ResponseEntity.ok().build()
		    : ResponseEntity.badRequest().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
		return userService.deleteUserById(id) ? ResponseEntity.ok().build()
		    : ResponseEntity.badRequest().build();
	}
}
