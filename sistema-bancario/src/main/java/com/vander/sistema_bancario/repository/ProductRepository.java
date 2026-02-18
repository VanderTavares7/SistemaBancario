package com.vander.sistema_bancario.repository;

import com.vander.sistema_bancario.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}