package org.example.javaproglovo.converter;

import org.example.javaproglovo.dto.OrderDto;
import org.example.javaproglovo.entity.ItemEntity;
import org.example.javaproglovo.entity.OrderEntity;
import org.example.javaproglovo.enums.OrderStatus;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderConverterTest {

    @Test
    public void toDtoTest() {
        OrderEntity orderEntity = OrderEntity.builder()
                .id(1)
                .customerName("Vlad")
                .status(OrderStatus.IN_PROCESSING)
                .checkoutDate(LocalDateTime.of(2022, 1, 1, 1, 1, 1))
                .items(List.of(ItemEntity.builder().id(1).build()))
                .build();

        OrderDto orderDto = OrderConverter.toDto(orderEntity);

        assertEquals(orderEntity.getId(), orderDto.getId());
        assertEquals(orderEntity.getCustomerName(), orderDto.getCustomerName());
        assertEquals(orderEntity.getStatus(), orderDto.getStatus());
        assertEquals(orderEntity.getCheckoutDate(), orderDto.getCheckoutDate());
        assertEquals(orderEntity.getItems().stream().map(ItemEntity::getId).toList(), orderDto.getItems());
    }

    @Test
    public void toEntityTest() {
        OrderDto orderDto = OrderDto.builder()
                .id(1)
                .customerName("Vlad")
                .status(OrderStatus.IN_PROCESSING)
                .checkoutDate(LocalDateTime.of(2022, 1, 1, 1, 1, 1))
                .items(List.of(1))
                .build();

        OrderEntity orderEntity = OrderConverter.toEntity(orderDto);

        assertEquals(orderDto.getId(), orderEntity.getId());
        assertEquals(orderDto.getCustomerName(), orderEntity.getCustomerName());
        assertEquals(orderDto.getStatus(), orderEntity.getStatus());
        assertEquals(orderDto.getCheckoutDate(), orderEntity.getCheckoutDate());
        assertEquals(new ArrayList<>(), orderEntity.getItems().stream().map(ItemEntity::getId).toList());
    }
}
