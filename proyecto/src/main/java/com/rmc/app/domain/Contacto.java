package com.rmc.app.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contacto {
    
    private String nombre;
    private String email;
    private String mensaje;
}
