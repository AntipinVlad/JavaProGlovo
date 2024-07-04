package org.example.javaproglovo.service;

import lombok.AllArgsConstructor;
import org.example.javaproglovo.converter.ProductConverter;
import org.example.javaproglovo.dto.ProductDto;
import org.example.javaproglovo.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;

    public ProductDto getById(int id){
        return productRepository.findById(id)
                .map(ProductConverter::toDto)
                .orElseThrow();
    }

    public List<ProductDto> getAll(){
        return productRepository.findAll().stream()
                .map(ProductConverter::toDto)
                .toList();
    }

    public ProductDto save(ProductDto productDto){
        var product = productRepository.save(ProductConverter.toEntity(productDto));
        return ProductConverter.toDto(product);
    }

    public ProductDto update(ProductDto productDto){
        return save(productDto);
    }

    public void delete(int id){
        productRepository.deleteById(id);
    }
}
