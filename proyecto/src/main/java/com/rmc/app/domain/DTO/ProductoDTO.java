package com.rmc.app.domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDTO {
    
    private long idProducto;
    private String nombreProducto;
}
