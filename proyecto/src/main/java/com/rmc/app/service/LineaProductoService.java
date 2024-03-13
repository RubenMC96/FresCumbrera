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

    public void annadir(LineaProducto lineaProducto);

    public void borrar(Long id);

    public void borrarTodo();

    //public List<LineaProducto> obtenerPorCompra(Compra compra);

    //public LineaProducto addNuevaLinea(Long id, Integer cantidad);

    public List<LineaProducto> obtenerPorUsuario();
    public Double obtenerImporte(List<LineaProducto> lineas);
}
