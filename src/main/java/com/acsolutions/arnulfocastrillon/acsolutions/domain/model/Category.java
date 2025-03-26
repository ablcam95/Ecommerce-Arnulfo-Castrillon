package com.acsolutions.arnulfocastrillon.acsolutions.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    private Integer id;
    private String name;
    private boolean activo;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
}
