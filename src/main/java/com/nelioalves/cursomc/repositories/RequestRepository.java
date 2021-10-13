package com.nelioalves.cursomc.repositories;

import com.nelioalves.cursomc.domain.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Request, Integer> {
}
