package com.ecolife.demo.entity;

import javax.persistence.*;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Id")
    private Integer id;
    @Basic
    @Column(name = "Name")
    private String name;
    @Basic
    @Column(name = "Price")
    private Integer price;
    @Basic
    @Column(name = "Quantity")
    private Integer quantity;
    @Basic
    @Column(name = "Type")
    private String type;
    @Basic
    @Column(name = "Image")
    private String image;
    @Basic
    @Column(name = "Weight")
    private String weight;
    @Basic
    @Column(name = "Dimensions")
    private String dimensions;
    @Basic
    @Column(name = "Materials")
    private String materials;
    @Basic
    @Column(name = "Description")
    private String description;
    @Basic
    @Column(name = "Status")
    private Boolean status;
    @Basic
    @Column(name = "Rate")
    private Integer rate;
    @Basic
    @Column(name = "CategoryId")
    private Integer categoryId;

    @Column(name = "discount")
    private Integer discount;
}