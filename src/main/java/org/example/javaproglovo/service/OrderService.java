package org.example.javaproglovo.service;

import lombok.AllArgsConstructor;
import org.example.javaproglovo.converter.OrderConverter;
import org.example.javaproglovo.dto.OrderDto;
import org.example.javaproglovo.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderDto findById(int id) {
        return orderRepository.findById(id).map(OrderConverter::toDto).orElseThrow();
    }

    public OrderDto save(OrderDto orderDto) {
        var order = orderRepository.save(OrderConverter.toEntity(orderDto));
        return OrderConverter.toDto(order);
    }

    public OrderDto update(OrderDto orderDto) {
        return save(orderDto);
    }

    public void delete(int id) {
        orderRepository.deleteById(id);
    }
}
