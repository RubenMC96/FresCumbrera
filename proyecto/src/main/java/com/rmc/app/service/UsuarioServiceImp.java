package com.rmc.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.rmc.app.Repositories.UsuarioRepository;
import com.rmc.app.domain.Usuario;

@Service
public class UsuarioServiceImp implements UsuarioService{
    @Autowired
    UsuarioRepository usuarioRepository;

    public Usuario añadir(Usuario usuario) {
        
        return usuarioRepository.save(usuario); 
    }
    public void borrar(Long id){
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if(usuario != null){
            usuarioRepository.delete(usuario);
        }
    }
     public Usuario editar(Usuario usuario ) {
        return usuarioRepository.save(usuario);
    }
    public Usuario obtenerPorId(Long id){
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if(usuario != null){
            return usuario;
        }
        else return null;

    }
    public List<Usuario> obtenerLista(){

        return usuarioRepository.findAll();

    }
    public Usuario obtenerUsuario(Long id){
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if(usuario != null){
            return usuario;
        }
        else return null;
    }  

    public Usuario obtenerUsuarioConectado() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String nombreUsuarioConectado = authentication.getName();

            return usuarioRepository.findByNombre(nombreUsuarioConectado);
        }
        return null;
    }

    public String obtenerRolUsuarioConectado() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String rolUsuarioConectado = authentication.getAuthorities().toString();

            return rolUsuarioConectado;
        }
        return null;
    }
}
    
 


