package com.rmc.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rmc.app.domain.Compra;
import com.rmc.app.domain.Usuario;

@Service
public interface CompraService {
    
    public Compra añadir(Usuario usuario);
    public List<Compra> obtenerTodos();
    public Compra obtenerPorId(long id);
    public Compra editar(Compra Compra);
    public void borrar(Long id);
    public List<Compra> obtenerPorUsuario(Usuario usuario);
    //obtenerPorEstado
}
