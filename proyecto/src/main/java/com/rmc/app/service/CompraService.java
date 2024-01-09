package com.rmc.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rmc.app.domain.Compra;
import com.rmc.app.domain.Usuario;

@Service
public interface CompraService {
    
    public Compra añadir(Compra Compra);
    public List<Compra> obtenerLista();
    public List<Compra> obtenerPedidos(Compra compra);
    public Compra obtenerPorId(long id);
    public Compra editar(Compra Compra);
    public void borrar(Long id);
    public Compra obtenerPorCompra(Long id);
    public Compra obtenerPorUsuario(Usuario usuario);

    
}
