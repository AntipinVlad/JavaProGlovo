package org.example.javaproglovo.converter;

import org.example.javaproglovo.dto.OrderDto;
import org.example.javaproglovo.entity.ItemEntity;
import org.example.javaproglovo.entity.OrderEntity;

public class OrderConverter {

    public static OrderDto toDto(OrderEntity orderEntity) {
        return OrderDto.builder()
                .id(orderEntity.getId())
                .checkoutDate(orderEntity.getCheckoutDate())
                .userName(orderEntity.getUserName())
                .totalPrice(orderEntity.getTotalPrice())
                .items(orderEntity.getItems().stream().map(ItemEntity::getId).toList())
                .build();
    }

    public static OrderEntity toEntity(OrderDto orderDto) {
        return OrderEntity.builder()
                .id(orderDto.getId())
                .checkoutDate(orderDto.getCheckoutDate())
                .userName(orderDto.getUserName())
                .totalPrice(orderDto.getTotalPrice())
                .build();
    }
}
