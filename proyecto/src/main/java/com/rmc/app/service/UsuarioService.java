package com.rmc.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rmc.app.domain.Usuario;

@Service
public interface UsuarioService {

   
    public Usuario añadir(Usuario usuario);
    public void borrar(Long id);
    public Usuario editar (Usuario usuario);
    public Usuario obtenerPorId(Long id);
    public List<Usuario> obtenerLista();
    public Usuario obtenerUsuario(Long id);
}
