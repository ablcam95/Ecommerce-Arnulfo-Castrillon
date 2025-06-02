package com.acsolutions.arnulfocastrillon.acsolutions.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String cellphone;
    private String password;
    private Boolean activo;
    private UserType userType;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
}
