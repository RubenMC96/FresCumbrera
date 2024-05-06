package com.rmc.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rmc.app.domain.Compra;
import com.rmc.app.domain.LineaProducto;

@Service
public interface CompraService {

    public Compra añadir(Compra compra);

    public List<Compra> obtenerTodos();

    public Compra obtenerPorId(long id);

    public Compra editar(Compra Compra);

    public void borrar(Long id);

    public List<Compra> obtenerPorUsuario(Long id);

    //public Compra obtenerCompraActiva(Usuario usuario);

    //public Compra crearCompra(listaLineasCompra listaLinea);
    public Compra crearCompra(List<LineaProducto> listaCompra);
}
