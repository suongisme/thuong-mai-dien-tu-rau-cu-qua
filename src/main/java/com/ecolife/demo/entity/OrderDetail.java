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
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Basic
    @Column(name = "OrderId")
    private Integer orderId;

    @Basic
    @Column(name = "ProductId")
    private Integer productId;

    @Basic
    @Column(name = "Quantity")
    private int quantity;

    @Basic
    @Column(name = "Price")
    private Integer price;

    public OrderDetail(Integer orderId, Integer productId, Integer quantity, Integer price) {
        this.orderId = orderId;
        this.price = price;
        this.productId = productId;
        this.quantity = quantity;
    }
}