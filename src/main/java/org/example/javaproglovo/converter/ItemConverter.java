package org.example.javaproglovo.converter;

import org.example.javaproglovo.dto.ItemDto;
import org.example.javaproglovo.entity.ItemEntity;
import org.example.javaproglovo.entity.OrderEntity;
import org.example.javaproglovo.entity.ProductEntity;

public class ItemConverter {

    public static ItemDto toDto(ItemEntity itemEntity) {
        return ItemDto.builder()
                .id(itemEntity.getId())
                .price(itemEntity.getPrice())
                .quantity(itemEntity.getQuantity())
                .orderId(itemEntity.getOrder().getId())
                .productId(itemEntity.getProduct().getId())
                .build();
    }

    public static ItemEntity toEntity(ItemDto itemDto) {
        return ItemEntity.builder()
                .id(itemDto.getId())
                .price(itemDto.getPrice())
                .quantity(itemDto.getQuantity())
                .order(OrderEntity.builder().id(itemDto.getOrderId()).build())
                .product(ProductEntity.builder().id(itemDto.getProductId()).build())
                .build();
    }
}
