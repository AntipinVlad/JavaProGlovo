package org.example.javaproglovo.service;

import org.example.javaproglovo.converter.ItemConverter;
import org.example.javaproglovo.dto.ItemDto;
import org.example.javaproglovo.entity.ItemEntity;
import org.example.javaproglovo.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ItemServiceTest {

    @Mock
    private ItemRepository itemRepository;
    @InjectMocks
    private ItemService itemService;

    @Test
    public void getByIdTest() {
        int id = 1;
        ItemDto orderItemDto = ItemDto.builder().id(id).build();
        ItemEntity orderItemEntity = ItemConverter.toEntity(orderItemDto);

        when(itemRepository.findById(1)).thenReturn(Optional.ofNullable(orderItemEntity));

        ItemDto actual = itemService.getById(id);

        assertEquals(actual, orderItemDto);
        assertEquals(actual.getId(), id);
        verify(itemRepository, times(1)).findById(id);
    }

    @Test
    public void getAllTest() {
        int orderId = 1;
        List<ItemDto> orderItemDtos = List.of(ItemDto.builder().orderId(orderId).build(), ItemDto.builder().orderId(orderId).build());
        List<ItemEntity> orderItemEntities = orderItemDtos.stream().map(ItemConverter::toEntity).toList();

        when(itemRepository.findAll()).thenReturn(orderItemEntities);

        List<ItemDto> actual = itemService.getAll();

        assertEquals(actual, orderItemDtos);
        assertEquals(actual.size(), orderItemEntities.size());
        verify(itemRepository, times(1)).findAll();
    }

    @Test
    public void findByOrderIdTest() {
        int orderId = 1;
        List<ItemDto> orderItemDtos = List.of(ItemDto.builder().orderId(orderId).build(), ItemDto.builder().orderId(orderId).build());
        List<ItemEntity> orderItemEntities = orderItemDtos.stream().map(ItemConverter::toEntity).toList();

        when(itemRepository.findByOrderId(orderId)).thenReturn(orderItemEntities);

        List<ItemDto> actual = itemService.findByOrderId(orderId);

        assertEquals(actual, orderItemDtos);
        assertEquals(actual.size(), orderItemEntities.size());
        verify(itemRepository, times(1)).findByOrderId(orderId);
    }

    @Test
    public void updateTest() {
        int id = 1;
        ItemDto orderItemDto = ItemDto.builder().id(id).build();
        ItemEntity orderItemEntity = ItemConverter.toEntity(orderItemDto);

        when(itemRepository.save(any(ItemEntity.class))).thenReturn(orderItemEntity);

        ItemDto actual = itemService.update(orderItemDto);

        assertEquals(actual, orderItemDto);
        verify(itemRepository, times(1)).save(any(ItemEntity.class));
    }

    @Test
    public void deleteByIdTest() {
        int id = 1;

        doNothing().when(itemRepository).deleteById(id);
        itemService.delete(id);

        verify(itemRepository, times(1)).deleteById(id);
    }
}
