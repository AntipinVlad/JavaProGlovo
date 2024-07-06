package org.example.javaproglovo.service;

import lombok.AllArgsConstructor;
import org.example.javaproglovo.converter.ItemConverter;
import org.example.javaproglovo.dto.ItemDto;
import org.example.javaproglovo.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public List<ItemDto> findByOrderId(int orderId) {
        return itemRepository.getByOrderId(orderId).stream().map(ItemConverter::toDto).toList();
    }

    public ItemDto save(ItemDto itemDto) {
        var item = itemRepository.save(ItemConverter.toEntity(itemDto));
        return ItemConverter.toDto(item);
    }

    public ItemDto update(ItemDto itemDto) {
        return save(itemDto);
    }

    public void delete(int id) {
        itemRepository.deleteById(id);
    }
}
