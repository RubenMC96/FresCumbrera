package com.rmc.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rmc.app.domain.Compra;
import com.rmc.app.domain.LineaProducto;
import com.rmc.app.domain.Usuario;
import com.rmc.app.domain.DTO.listaLineasCompra;

@Service
public interface CompraService {

    public Compra añadir(Compra compra);

    public List<Compra> obtenerTodos();

    public Compra obtenerPorId(long id);

    public Compra editar(Compra Compra);

    public void borrar(Long id);

    public List<Compra> obtenerPorUsuario();

    //public Compra obtenerCompraActiva(Usuario usuario);

    //public Compra crearCompra(listaLineasCompra listaLinea);
    public Compra crearCompra(List<LineaProducto> listaCompra);
}
