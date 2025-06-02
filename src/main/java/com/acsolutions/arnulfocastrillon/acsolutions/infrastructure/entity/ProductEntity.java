package com.acsolutions.arnulfocastrillon.acsolutions.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
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
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime dateCreated;
    @UpdateTimestamp
    private LocalDateTime dateUpdated;
    @ManyToOne
    private UserEntity userEntity;
    @ManyToOne
    private CategoryEntity categoryEntity;
}
