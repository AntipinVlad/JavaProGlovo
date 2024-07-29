package org.example.javaproglovo.service;

import org.example.javaproglovo.converter.ItemConverter;
import org.example.javaproglovo.converter.OrderConverter;
import org.example.javaproglovo.dto.ItemDto;
import org.example.javaproglovo.dto.OrderDto;
import org.example.javaproglovo.entity.ItemEntity;
import org.example.javaproglovo.entity.OrderEntity;
import org.example.javaproglovo.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;
    @InjectMocks
    private OrderService orderService;

    @Test
    public void getAllTest() {
        List<OrderDto> orderDtos = List.of(OrderDto.builder().items(new ArrayList<>()).build(),
                OrderDto.builder().items(new ArrayList<>()).build());
        List<OrderEntity> orderEntities = List.of(OrderEntity.builder().items(new ArrayList<>()).build(),
                OrderEntity.builder().items(new ArrayList<>()).build());

        when(orderRepository.findAll()).thenReturn(orderEntities);

        List<OrderDto> actual = orderService.getAll();

        assertEquals(actual, orderDtos);
        assertEquals(actual.size(), orderEntities.size());
        verify(orderRepository, times(1)).findAll();
    }

    @Test
    public void getByIdTest() {
        int id = 1;
        OrderDto orderDto = OrderDto.builder().id(id).items(new ArrayList<>()).build();
        OrderEntity orderEntity = OrderConverter.toEntity(orderDto);

        when(orderRepository.findById(1)).thenReturn(Optional.ofNullable(orderEntity));

        OrderDto actual = orderService.getById(id);

        assertEquals(actual, orderDto);
        assertEquals(actual.getId(), id);
        verify(orderRepository, times(1)).findById(id);
    }

    @Test
    void saveTest() {
        int id = 1;
        OrderDto orderDto = OrderDto.builder().id(id).items(new ArrayList<>()).build();
        OrderEntity orderEntity = OrderConverter.toEntity(orderDto);

        when(orderRepository.save(any(OrderEntity.class))).thenReturn(orderEntity);

        OrderDto actual = orderService.save(orderDto);

        assertEquals(actual, orderDto);
        verify(orderRepository, times(1)).save(any(OrderEntity.class));
    }

    @Test
    public void updateWithoutItemsTest() {
        int id = 1;
        OrderDto orderDto = OrderDto.builder().id(id).items(new ArrayList<>()).build();
        OrderEntity orderEntity = OrderConverter.toEntity(orderDto);

        when(orderRepository.updateWithoutItems(any(OrderEntity.class))).thenReturn(orderEntity);

        OrderDto actual = orderService.update(orderDto);

        assertEquals(actual, orderDto);
        verify(orderRepository, times(1)).updateWithoutItems(any(OrderEntity.class));
    }

    @Test
    public void addItemTest() {
        int orderId = 1;
        int orderItemId = 12;

        ItemDto itemDto = ItemDto.builder().id(orderItemId).build();
        ItemEntity orderItemEntity = ItemConverter.toEntity(itemDto);

        OrderDto orderDto = OrderDto.builder().id(orderId).items(List.of(orderItemId)).build();
        OrderEntity orderEntity = OrderEntity.builder().id(orderId).items(List.of(orderItemEntity)).build();

        when(orderRepository.addItem(orderId, orderItemEntity)).thenReturn(orderEntity);

        OrderDto actual = orderService.addItem(orderId, itemDto);

        assertEquals(actual, orderDto);
        assertTrue(actual.getItems().contains(orderItemId));
        verify(orderRepository, times(1)).addItem(orderId, orderItemEntity);
    }

    @Test
    public void deleteByIdTest() {
        int id = 1;

        doNothing().when(orderRepository).deleteById(id);
        orderService.delete(id);

        verify(orderRepository, times(1)).deleteById(id);
    }
}
