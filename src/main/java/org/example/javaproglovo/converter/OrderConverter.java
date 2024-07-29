package org.example.javaproglovo.converter;

import org.example.javaproglovo.dto.OrderDto;
import org.example.javaproglovo.entity.ItemEntity;
import org.example.javaproglovo.entity.OrderEntity;

import java.util.ArrayList;

public class OrderConverter {

    public static OrderDto toDto(OrderEntity orderEntity) {
        return OrderDto.builder()
                .id(orderEntity.getId())
                .customerName(orderEntity.getCustomerName())
                .status(orderEntity.getStatus())
                .checkoutDate(orderEntity.getCheckoutDate())
                .items(orderEntity.getItems().stream().map(ItemEntity::getId).toList())
                .build();
    }

    public static OrderEntity toEntity(OrderDto orderDto) {
        return OrderEntity.builder()
                .id(orderDto.getId())
                .customerName(orderDto.getCustomerName())
                .status(orderDto.getStatus())
                .checkoutDate(orderDto.getCheckoutDate())
                .items(new ArrayList<>())
                .build();
    }
}
