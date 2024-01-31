package com.rmc.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rmc.app.domain.Compra;
import com.rmc.app.domain.LineaProducto;

@Service
public interface LineaProductoService {

    public List<LineaProducto> obtenerLista();

    public LineaProducto obtenerPorId(long id);

    public LineaProducto editar(LineaProducto lineaProducto);

    public void annadir(Long productoId, Integer canticantidadProductos);

    public void borrar(Long id);

    public void borrarTodo();

    public LineaProducto obtenerPorCompra(Compra compra);

}
