package org.example.javaproglovo.service;

import org.example.javaproglovo.converter.ProductConverter;
import org.example.javaproglovo.dto.ProductDto;
import org.example.javaproglovo.entity.ProductEntity;
import org.example.javaproglovo.repository.ProductRepository;
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
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private ProductService productService;

    @Test
    public void getAllTest() {
        List<ProductDto> productDtos = List.of(new ProductDto(), new ProductDto());
        List<ProductEntity> productEntities = productDtos.stream().map(ProductConverter::toEntity).toList();

        when(productRepository.findAll()).thenReturn(productEntities);

        List<ProductDto> actual = productService.getAll();

        assertEquals(actual, productDtos);
        assertEquals(actual.size(), productEntities.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    public void getByIdTest() {
        int id = 1;
        ProductDto productDto = ProductDto.builder().id(id).build();
        ProductEntity productEntity = ProductConverter.toEntity(productDto);

        when(productRepository.findById(1)).thenReturn(Optional.ofNullable(productEntity));

        ProductDto actual = productService.getById(id);

        assertEquals(actual, productDto);
        assertEquals(actual.getId(), id);
        verify(productRepository, times(1)).findById(id);
    }

    @Test
    void saveTest() {
        int id = 1;
        ProductDto productDto = ProductDto.builder().id(id).build();
        ProductEntity productEntity = ProductConverter.toEntity(productDto);

        when(productRepository.save(any(ProductEntity.class))).thenReturn(productEntity);

        ProductDto actual = productService.save(productDto);

        assertEquals(actual, productDto);
        verify(productRepository, times(1)).save(any(ProductEntity.class));
    }

    @Test
    public void updateTest() {
        int id = 1;
        ProductDto productDto = ProductDto.builder().id(id).build();
        ProductEntity productEntity = ProductConverter.toEntity(productDto);

        when(productRepository.save(any(ProductEntity.class))).thenReturn(productEntity);

        ProductDto actual = productService.save(productDto);

        assertEquals(actual, productDto);
        verify(productRepository, times(1)).save(any(ProductEntity.class));
    }

    @Test
    public void deleteByIdTest() {
        int id = 1;

        doNothing().when(productRepository).deleteById(id);
        productService.delete(id);

        verify(productRepository, times(1)).deleteById(id);
    }
}