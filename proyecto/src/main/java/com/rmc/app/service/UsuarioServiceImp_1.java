package com.rmc.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rmc.app.Repositories.UsuarioRepository;
import com.rmc.app.domain.Usuario_1;

@Service
public class UsuarioServiceImp_1 implements UsuarioService_1{


    @Autowired
    UsuarioRepository usuRepo;

    public Usuario_1 añadir(Usuario_1 usuario) {
        usuRepo.save(usuario);
        return usuario; // podría no devolver nada, o boolean, etc.
    }
    public List<Usuario_1> obteberLista() {
        return usuRepo.findAll();
    }
    public Usuario_1 obtenerPorId(long id) {
        
        Usuario_1 usuario = usuRepo.findById(id).orElse(null);// debería lanzar excepción si no encontrado
        if(usuario != null){
        return usuario;
        }
        return null;
         
    }
    public Usuario_1 editar(Usuario_1 usuario) {
          
        return usuRepo.save(usuario);
    }
    public void borrar(Long id) {
        Usuario_1 usuario = usuRepo.findById(id).orElse(null);
        if(usuario != null){
            usuRepo.delete(usuario);
        }
        
    }

    public Usuario_1 obtenerPorUsuario(Long id){
        Usuario_1 usu=usuRepo.findById(id).orElse(null);
        if(usu == null) return null;
        
        return usu;
    }

    public Usuario_1 obtenerPorNombre(String nombre){

        return usuRepo.findByNombre(nombre);
    }
}
