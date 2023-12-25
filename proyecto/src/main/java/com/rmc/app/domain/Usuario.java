package com.rmc.app.domain;

import java.time.LocalDate;

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
    private String dni;
    private String nombreUsuario;
    private String contrasena;
    private String telefono;
    private String calle;
    private String numero;
    private String localidad;
    private String provincia;
    private Long cp;
    private String direccion;
    private LocalDate fechaNacimiento;

    public Usuario(String nombreUsuario, String contrasena){
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
    }
    

    public String getDireccion(){
 
        direccion = calle + numero + localidad + 
                    provincia + cp;
        return direccion;    
    }
    public String getContrasena (){
        return contrasena;
    }

}



