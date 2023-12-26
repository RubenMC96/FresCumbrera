package com.rmc.app.domain;

import java.time.LocalDate;

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
public class Caja {
    @Id
    @GeneratedValue
    private Long id;
    private Integer totalProductos;
    private Integer numProductos;
    private LocalDate fechaFactura;
    private Usuario usuario;
    private Producto producto;

}
