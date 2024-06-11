package com.rmc.app.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
public class Colaboracion {
    
    @Id
    @GeneratedValue
    private Long id;
    private String nombre;
    private String rrss;
    private String email;
    private String mensaje;
}
