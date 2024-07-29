package org.example.javaproglovo.repository;

import org.example.javaproglovo.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<ItemEntity, Integer> {

    List<ItemEntity> findByOrderId(int orderId);
}
