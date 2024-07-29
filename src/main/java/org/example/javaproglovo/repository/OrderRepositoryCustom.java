package org.example.javaproglovo.repository;

import org.example.javaproglovo.entity.ItemEntity;
import org.example.javaproglovo.entity.OrderEntity;

public interface OrderRepositoryCustom {

    OrderEntity updateWithoutItems(OrderEntity order);
    OrderEntity addItem(int orderId, ItemEntity itemEntity);
}
