package org.example.javaproglovo.converter;

import org.example.javaproglovo.dto.ItemDto;
import org.example.javaproglovo.entity.ItemEntity;
import org.example.javaproglovo.entity.OrderEntity;
import org.example.javaproglovo.entity.ProductEntity;

public class ItemConverter {

    public static ItemDto toDto(ItemEntity orderItemEntity) {
        return ItemDto.builder()
                .id(orderItemEntity.getId())
                .price(orderItemEntity.getPrice())
                .quantity(orderItemEntity.getQuantity())
                .orderId(orderItemEntity.getOrder().getId())
                .productId(orderItemEntity.getProduct().getId())
                .build();
    }

    public static ItemEntity toEntity(ItemDto orderItemDto) {
        return ItemEntity.builder()
                .id(orderItemDto.getId())
                .price(orderItemDto.getPrice())
                .quantity(orderItemDto.getQuantity())
                .product(ProductEntity.builder().id(orderItemDto.getProductId()).build())
                .order(OrderEntity.builder().id(orderItemDto.getOrderId()).build())
                .build();
    }
}
