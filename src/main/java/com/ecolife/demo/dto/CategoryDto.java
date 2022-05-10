package com.ecolife.demo.dto;

import com.ecolife.demo.entity.Category;
import com.ecolife.demo.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    private int id;
    private String name;
    private String description;
    private Integer displayOrder;
    private String image;
    private List<Product> products = new ArrayList<>();

    public CategoryDto(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.description = category.getDescription();
        this.displayOrder = category.getDisplayOrder();
        this.image = category.getImage();
    }
}