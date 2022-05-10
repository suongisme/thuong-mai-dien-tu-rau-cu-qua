package com.ecolife.demo.entity;

import javax.persistence.*;
import java.sql.Date;
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
public class Blog {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Id")
    private int id;
    @Basic
    @Column(name = "Image")
    private String image;
    @Basic
    @Column(name = "Title")
    private String title;
    @Basic
    @Column(name = "Content")
    private String content;
    @Basic
    @Column(name = "CreateAt")
    private Date createAt;
    @Basic
    @Column(name = "CreateBy")
    private String createBy;
}