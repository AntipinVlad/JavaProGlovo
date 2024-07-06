package org.example.javaproglovo.controller;

import lombok.AllArgsConstructor;
import org.example.javaproglovo.dto.ItemDto;
import org.example.javaproglovo.service.ItemService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/items")
@AllArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PutMapping
    public ItemDto update(@RequestBody ItemDto itemDto) {
        return itemService.update(itemDto);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable int id) {
        itemService.delete(id);
    }
}
