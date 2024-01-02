package com.rmc.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.rmc.app.Repositories.UsuarioRepository;
import com.rmc.app.domain.Usuario;

@Service
public class UsuarioServiceImp implements UsuarioService{
    @Autowired
    UsuarioRepository usuarioRepository;
    
    private BCryptPasswordEncoder passwordEncoder;
    public UsuarioServiceImp(BCryptPasswordEncoder passwordEncoder) {
      this.passwordEncoder = passwordEncoder;
    }

    public Boolean añadir(Usuario usuario){

        if(!usuarioRepository.existsByEmail(usuario.getEmail())){
            usuarioRepository.save(usuario);
            return true;
        }
        else{
            return false;//Comprobar en controller que no sea null;
        }

    }
    public void borrar(Long id){
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if(usuario != null){
            usuarioRepository.delete(usuario);
        }
    }
    public Boolean editar (Usuario usuario){

        if(!usuarioRepository.existsByEmail(usuario.getEmail())){
            usuarioRepository.save(usuario);
            return true;
        }
        else{
            return false;//Comprobar en controller que no sea null;
        }

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

    public void crearUsuario(String nombreUsuario, String contrasena){
        String encodedPassword = passwordEncoder.encode(contrasena);
        new Usuario(nombreUsuario, encodedPassword);
    }   

    public Boolean existeUsuario(String email){
        return usuarioRepository.existsByEmail(email);
    }

    public Usuario obtenerPorEmail(String email){
        return usuarioRepository.findByEmail(email);
    }

}
    
 


