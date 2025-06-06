package com.acsolutions.arnulfocastrillon.acsolutions.infrastructure.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Entity
@Table( name = "order_product" )
@AllArgsConstructor
@NoArgsConstructor
public class OrderProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private BigDecimal quantity;
    private BigDecimal price;
    private Integer productId;
    @ManyToOne
    @JsonBackReference
    private OrderEntity orderEntity;
}
