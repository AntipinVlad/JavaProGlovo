package org.example.javaproglovo.converter;

import org.example.javaproglovo.dto.ProductDto;
import org.example.javaproglovo.entity.ProductEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductConverterTest {

    @Test
    public void testToDto() {
        ProductEntity productEntity = ProductEntity.builder()
                .id(1)
                .name("Test")
                .country("Ukraine")
                .price(22.22)
                .build();

        ProductDto productDto = ProductConverter.toDto(productEntity);

        assertEquals(productEntity.getId(), productDto.getId());
        assertEquals(productEntity.getName(), productDto.getName());
        assertEquals(productEntity.getCountry(), productDto.getCountry());
        assertEquals(productEntity.getPrice(), productDto.getPrice());
    }

    @Test
    public void testToEntity() {
        ProductDto productDto = ProductDto.builder()
                .id(1)
                .name("Test")
                .country("Ukraine")
                .price(22.22)
                .build();

        ProductEntity productEntity = ProductConverter.toEntity(productDto);

        assertEquals(productDto.getId(), productEntity.getId());
        assertEquals(productDto.getName(), productEntity.getName());
        assertEquals(productDto.getCountry(), productEntity.getCountry());
        assertEquals(productDto.getPrice(), productEntity.getPrice());
    }
}
