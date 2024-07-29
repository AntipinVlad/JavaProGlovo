package org.example.javaproglovo.converter;

import org.example.javaproglovo.dto.ItemDto;
import org.example.javaproglovo.entity.ItemEntity;
import org.example.javaproglovo.entity.OrderEntity;
import org.example.javaproglovo.entity.ProductEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemConverterTest {

    @Test
    public void toDto() {
        ItemEntity itemEntity = ItemEntity.builder()
                .id(1)
                .price(22)
                .quantity(33)
                .order(OrderEntity.builder().id(1).build())
                .product(ProductEntity.builder().id(1).build())
                .build();

        ItemDto itemDto = ItemConverter.toDto(itemEntity);

        assertEquals(itemEntity.getId(), itemDto.getId());
        assertEquals(itemEntity.getPrice(), itemDto.getPrice());
        assertEquals(itemEntity.getQuantity(), itemDto.getQuantity());
        assertEquals(itemEntity.getOrder().getId(), itemDto.getOrderId());
        assertEquals(itemEntity.getProduct().getId(), itemDto.getProductId());
    }

    @Test
    public void toEntity() {
        ItemDto itemDto = ItemDto.builder()
                .id(1)
                .price(11)
                .quantity(22)
                .orderId(1)
                .productId(1)
                .build();

        ItemEntity itemEntity = ItemConverter.toEntity(itemDto);

        assertEquals(itemDto.getId(), itemEntity.getId());
        assertEquals(itemDto.getPrice(), itemEntity.getPrice());
        assertEquals(itemDto.getQuantity(), itemEntity.getQuantity());
        assertEquals(itemDto.getOrderId(), itemEntity.getOrder().getId());
        assertEquals(itemDto.getProductId(), itemEntity.getProduct().getId());
    }
}
