package com.rmc.app.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@Entity
public class Usuario {

    @Id
    @GeneratedValue
    private Long id;
    private String nombre;
    private String apellidos;
    private String email;
    @Column(unique = true)
    private String nombreUsuario;
    private String password;
    private String telefono;
    private Rol rol;

}
