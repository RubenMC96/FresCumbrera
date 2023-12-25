package com.rmc.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rmc.app.domain.Usuario;

@Service
public interface UsuarioService {

    @Autowired
   
    public Boolean añadir(Usuario usuario);
    public void borrar(Long id);
    public Boolean editar (Usuario usuario);
    public Usuario obtenerPorId(Long id);
    public List<Usuario> obtenerLista();
    public Usuario obtenerUsuario(Long id);
    public void crearUsuario(String username, String password);
    public Boolean existeUsuario(String email);
    public Usuario obtenerPorEmail(String email);


}
