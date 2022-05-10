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
public class Category {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Id")
    private int id;
    @Basic
    @Column(name = "Name")
    private String name;
    @Basic
    @Column(name = "Description")
    private String description;
    @Basic
    @Column(name = "DisplayOrder")
    private Integer displayOrder;

    @Column(name = "Image")
    private String image;
}