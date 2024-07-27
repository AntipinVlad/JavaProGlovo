package org.example.javaproglovo.service;

import lombok.AllArgsConstructor;
import org.example.javaproglovo.converter.ItemConverter;
import org.example.javaproglovo.converter.OrderConverter;
import org.example.javaproglovo.dto.ItemDto;
import org.example.javaproglovo.dto.OrderDto;
import org.example.javaproglovo.entity.OrderEntity;
import org.example.javaproglovo.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public List<OrderDto> getAll() {
        return orderRepository.findAll().stream().map(OrderConverter::toDto).toList();
    }

    public OrderDto getById(int id) {
        return orderRepository.findById(id).map(OrderConverter::toDto).orElseThrow();
    }

    public OrderDto save(OrderDto orderDto) {
        OrderEntity orderEntity = orderRepository.save(OrderConverter.toEntity(orderDto));
        return OrderConverter.toDto(orderEntity);
    }

    public OrderDto update(OrderDto orderDto) {
        OrderEntity orderEntity = orderRepository.updateWithoutItems(OrderConverter.toEntity(orderDto));
        return OrderConverter.toDto(orderEntity);
    }

    public OrderDto addItem(int orderId, ItemDto orderItemDto) {
        OrderEntity orderEntity = orderRepository.addItem(orderId, ItemConverter.toEntity(orderItemDto));
        return OrderConverter.toDto(orderEntity);
    }

    public void delete(int id) {
        orderRepository.deleteById(id);
    }
}
