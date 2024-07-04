package org.example.javaproglovo.controller;

import lombok.AllArgsConstructor;
import org.example.javaproglovo.dto.ItemDto;
import org.example.javaproglovo.dto.OrderDto;
import org.example.javaproglovo.service.ItemService;
import org.example.javaproglovo.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {

    private OrderService orderService;
    private ItemService itemService;

    @GetMapping("/{id}")
    public OrderDto getOrder(@PathVariable int id) {
        return orderService.findById(id);
    }

    @PostMapping
    public OrderDto create(@RequestBody OrderDto orderDto) {
        return orderService.save(orderDto);
    }

    @PostMapping("/{orderId}/items")
    public OrderDto addItem(@PathVariable int orderId, @RequestBody ItemDto itemDto) {
        itemService.save(itemDto);
        return orderService.findById(orderId);
    }

    @GetMapping("/{orderId}/items")
    public List<ItemDto> getItems(@PathVariable int orderId) {
        return itemService.findByOrderId(orderId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        orderService.delete(id);
    }
}
