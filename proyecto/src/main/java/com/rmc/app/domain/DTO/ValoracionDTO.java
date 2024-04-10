package com.rmc.app.domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValoracionDTO {
    
    private String comentario;
    private Integer puntuacion; 
    private Long idProducto;
}
