package com.ecolife.demo.service;

import com.ecolife.demo.dto.CategoryDto;
import com.ecolife.demo.entity.Product;
import com.ecolife.demo.repository.CategoryRepository;
import com.ecolife.demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public List<CategoryDto> finAll() {
        return this.categoryRepository.findAll().stream()
                .map(category -> {
                    CategoryDto categoryDto = new CategoryDto(category);
                    List<Product> products = this.productRepository.findByCategoryId(category.getId());
                    categoryDto.setProducts(products);
                    return categoryDto;
                }).collect(Collectors.toList());
    }
}