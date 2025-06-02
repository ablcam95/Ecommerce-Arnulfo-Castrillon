package com.acsolutions.arnulfocastrillon.acsolutions.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Integer id;
    private String name;
    private String code;
    private String capacidad;
    private String tipo;
    private Boolean jacuzzi;
    private String description;
    private String urlImage;
    private BigDecimal price;
    private Boolean activo;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
    private Integer userId;
    private Integer categoryId;
}
