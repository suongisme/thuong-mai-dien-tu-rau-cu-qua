package com.ecolife.demo.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Orders {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Id")
    private int id;
    @Basic
    @Column(name = "CustomerId")
    private Integer customerId;
    @Basic
    @Column(name = "TotalMoney")
    private Integer totalMoney;
    @Basic
    @Column(name = "CreateAt")
    private Date createAt;
    @Basic
    @Column(name = "OrderStatus")
    private Integer orderStatus;
}