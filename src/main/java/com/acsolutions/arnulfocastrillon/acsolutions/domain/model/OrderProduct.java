package com.acsolutions.arnulfocastrillon.acsolutions.domain.model;

import com.acsolutions.arnulfocastrillon.acsolutions.infrastructure.entity.OrderEntity;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderProduct {
    private Integer id;
    private BigDecimal quantity;
    private BigDecimal price;
    private Integer productId;
    private OrderEntity orderEntity;


    public BigDecimal getTotalItem(){
        return this.price.multiply(quantity);
    }

}
