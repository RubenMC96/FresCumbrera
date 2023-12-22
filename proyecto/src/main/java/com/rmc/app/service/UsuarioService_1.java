package com.rmc.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rmc.app.domain.Usuario_1;

@Service
public interface UsuarioService_1 {
    
    public Usuario_1 añadir(Usuario_1 usuario);
    public List<Usuario_1> obteberLista();
    public Usuario_1 obtenerPorId(long id);
    public Usuario_1 editar(Usuario_1 usuario);
    public void borrar(Long id);
    public Usuario_1 obtenerPorUsuario(Long id);
    public Usuario_1 obtenerPorNombre(String nombre);

    
}
