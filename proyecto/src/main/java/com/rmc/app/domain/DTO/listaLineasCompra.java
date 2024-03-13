package com.rmc.app.domain.DTO;

import java.util.List;

import com.rmc.app.domain.LineaProducto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class listaLineasCompra {
    private List<LineaProducto> listaLineaProducto;
}
