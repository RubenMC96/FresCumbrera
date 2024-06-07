package com.rmc.app.domain.DTO;

import com.rmc.app.domain.Rol;
import com.rmc.app.domain.Usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UsuarioDTO {

    private Long id;
    private String nombre;
    private String apellidos;
    private String email;
    private String nombreUsuario;
    private String password;
    private String telefono;
    private Integer rol;
}