package org.example.javaproglovo.service;

import lombok.AllArgsConstructor;
import org.example.javaproglovo.converter.ItemConverter;
import org.example.javaproglovo.dto.ItemDto;
import org.example.javaproglovo.entity.ItemEntity;
import org.example.javaproglovo.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemDto getById(int id) {
        return itemRepository.findById(id).map(ItemConverter::toDto).orElseThrow();
    }

    public List<ItemDto> getAll() {
        return itemRepository.findAll().stream().map(ItemConverter::toDto).toList();
    }

    public List<ItemDto> findByOrderId(int orderId) {
        return itemRepository.findByOrderId(orderId).stream().map(ItemConverter::toDto).toList();
    }

    public ItemDto update(ItemDto orderItemDto) {
        ItemEntity orderItemEntity = itemRepository.save(ItemConverter.toEntity(orderItemDto));
        return ItemConverter.toDto(orderItemEntity);
    }

    public void delete(int id) {
        itemRepository.deleteById(id);
    }
}
