package com.nelioalves.cursomc.repositories;

import com.nelioalves.cursomc.domain.OrderedItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderedItemRepository extends JpaRepository<OrderedItem, Integer> {
}
