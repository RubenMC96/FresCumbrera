package com.rmc.app.domain;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of ="id")
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
    private String nombreUsuario;
    private String password;
    private String telefono;
   

    public Usuario(String nombreUsuario, String contrasena){
        this.nombreUsuario = nombreUsuario;
        this.password = contrasena;
    }
    

    public String getContrasena (){
        return password;
    }

    @ManyToOne
    @OnDelete(action=OnDeleteAction.CASCADE)
    private Compra compra;
}



