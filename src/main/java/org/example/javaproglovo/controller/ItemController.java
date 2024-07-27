package org.example.javaproglovo.controller;

import lombok.AllArgsConstructor;
import org.example.javaproglovo.dto.ItemDto;
import org.example.javaproglovo.service.ItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
@AllArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping()
    public List<ItemDto> getAll() {
        return itemService.getAll();
    }

    @GetMapping("/{id}")
    public ItemDto getById(@PathVariable int id) {
        return itemService.getById(id);
    }

    @PutMapping("/{id}")
    public ItemDto update(@PathVariable int id, @RequestBody ItemDto itemDto) {
        itemDto.setId(id);
        return itemService.update(itemDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        itemService.delete(id);
    }
}
