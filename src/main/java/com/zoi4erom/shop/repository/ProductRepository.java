package com.zoi4erom.shop.repository;

import com.zoi4erom.shop.entity.Product;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	Optional<Product> findProductsByName(String productName);

	List<Product> findProductsByProductType_Name(String productTypeName);
}
