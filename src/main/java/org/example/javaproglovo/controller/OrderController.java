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

    @GetMapping()
    public List<OrderDto> getAll() {
        return orderService.getAll();
    }

    @GetMapping("/{id}")
    public OrderDto getById(@PathVariable int id) {
        return orderService.getById(id);
    }

    @PostMapping()
    public OrderDto save(@RequestBody OrderDto orderDto) {
        return orderService.save(orderDto);
    }

    @PutMapping("/{id}")
    public OrderDto update(@PathVariable int id, @RequestBody OrderDto orderDto) {
        orderDto.setId(id);
        return orderService.update(orderDto);
    }

    @GetMapping("/{id}/items")
    public List<ItemDto> getItems(@PathVariable int id) {
        return itemService.findByOrderId(id);
    }

    @PostMapping("/{id}/items")
    public OrderDto addItem(@PathVariable int id, @RequestBody ItemDto orderItemDto) {
        return orderService.addItem(id, orderItemDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        orderService.delete(id);
    }
}
