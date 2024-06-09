package com.zoi4erom.shop.repository;

import com.zoi4erom.shop.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findUserByUsername(String username);

	Optional<User> findUserByEmail(String email);
}
