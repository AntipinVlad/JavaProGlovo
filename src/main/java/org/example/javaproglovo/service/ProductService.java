package org.example.javaproglovo.service;

import lombok.AllArgsConstructor;
import org.example.javaproglovo.converter.ProductConverter;
import org.example.javaproglovo.dto.ProductDto;
import org.example.javaproglovo.entity.ProductEntity;
import org.example.javaproglovo.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductDto> getAll() {
        return productRepository.findAll().stream().map(ProductConverter::toDto).toList();
    }

    public ProductDto getById(int id) {
        return productRepository.findById(id).map(ProductConverter::toDto).orElseThrow();
    }

    public ProductDto save(ProductDto productDto) {
        ProductEntity productEntity = productRepository.save(ProductConverter.toEntity(productDto));
        return ProductConverter.toDto(productEntity);
    }

    public ProductDto update(ProductDto productDto) {
        ProductEntity productEntity = productRepository.save(ProductConverter.toEntity(productDto));
        return ProductConverter.toDto(productEntity);
    }

    public void delete(int id) {
        productRepository.deleteById(id);
    }
}
