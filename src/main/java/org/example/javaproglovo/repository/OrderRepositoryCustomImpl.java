package org.example.javaproglovo.repository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.example.javaproglovo.entity.ItemEntity;
import org.example.javaproglovo.entity.OrderEntity;
import org.example.javaproglovo.entity.ProductEntity;

import java.util.Optional;

@AllArgsConstructor
@Repository
public class OrderRepositoryCustomImpl implements OrderRepositoryCustom {
    private final EntityManager entityManager;

    @Override
    @Transactional
    public OrderEntity updateWithoutItems(OrderEntity orderModified) {
        OrderEntity order = entityManager.find(OrderEntity.class, orderModified.getId());
        if (order == null) {
            throw new IllegalArgumentException("Order not found for id: " + orderModified.getId());
        }
        order.setCustomerName(orderModified.getCustomerName());
        order.setCheckoutDate(Optional.ofNullable(orderModified.getCheckoutDate()).orElse(order.getCheckoutDate()));
        order.setStatus(Optional.ofNullable(orderModified.getStatus()).orElse(order.getStatus()));

        order = entityManager.merge(order);

        return order;
    }

    @Override
    @Transactional
    public OrderEntity addItem(int orderId, ItemEntity orderItemEntity) {
        OrderEntity orderEntity = entityManager.find(OrderEntity.class, orderId);
        if (orderEntity == null) {
            throw new IllegalArgumentException("Order not found for id: " + orderId);
        }
        ProductEntity productEntity = entityManager.find(ProductEntity.class, orderItemEntity.getProduct().getId());
        if (productEntity == null) {
            throw new IllegalArgumentException("Product not found for id: " + orderItemEntity.getProduct().getId());
        }

        orderItemEntity.setProduct(productEntity);
        orderItemEntity.setOrder(orderEntity);
        orderEntity.getItems().add(orderItemEntity);

        entityManager.merge(orderEntity);

        return entityManager.find(OrderEntity.class,orderEntity.getId());
    }
}
