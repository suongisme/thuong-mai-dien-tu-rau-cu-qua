package com.ecolife.demo.service;

import com.ecolife.demo.entity.Product;
import com.ecolife.demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    public List<Product> findByIdCategory(Integer idCategory) {
        return this.productRepository.findByCategoryId(idCategory);
    }

    public Product findById(Integer productId) {
        return this.productRepository.findById(productId).orElseThrow( () -> {
            return new IllegalArgumentException("not found: " + productId);
        });
    }

}