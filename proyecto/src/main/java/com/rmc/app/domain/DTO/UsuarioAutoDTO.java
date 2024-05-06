package com.rmc.app.domain.DTO;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioAutoDTO {
    
    private Long id;
    private String nombre;
    private String apellidos;
    private String email;
    private String nombreUsuario;
    private String password;
    private String telefono;

}
