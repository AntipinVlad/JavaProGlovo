package org.example.javaproglovo.controller;

import lombok.AllArgsConstructor;
import org.example.javaproglovo.dto.ProductDto;
import org.example.javaproglovo.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<ProductDto> getAll() {
        return productService.getAll();
    }

    @GetMapping("{id}")
    public ProductDto getById(@PathVariable int id) {
        return productService.getById(id);
    }

    @PostMapping
    public ProductDto save(@RequestBody ProductDto productDto) {
        return productService.save(productDto);
    }

    @PutMapping("{id}")
    public ProductDto update(@PathVariable int id, @RequestBody ProductDto productDto) {
        productDto.setId(id);
        return productService.update(productDto);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable int id) {
        productService.delete(id);
    }
}
