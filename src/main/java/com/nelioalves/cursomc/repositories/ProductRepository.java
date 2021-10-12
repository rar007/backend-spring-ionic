package com.nelioalves.cursomc.repositories;

import com.nelioalves.cursomc.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
