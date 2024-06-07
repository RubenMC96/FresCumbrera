package com.rmc.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rmc.app.Repositories.UsuarioRepository;
import com.rmc.app.domain.Rol;
import com.rmc.app.domain.Usuario;

@Service
public class UsuarioServiceImp implements UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Usuario añadir(Usuario usuario) {
        // Mirar no duplicados
        String passCryString = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(passCryString);
        try {
            System.out.println("Usuario:" + usuario);
            return usuarioRepository.save(usuario);

        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void borrar(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario != null) {
            usuarioRepository.delete(usuario);
        }
    }

    public Usuario editar(Usuario usuario) {
        if(!usuario.getPassword().isEmpty()){
            String passCryString = passwordEncoder.encode(usuario.getPassword());
            usuario.setPassword(passCryString);
            }
            else{
                Usuario usu = usuarioRepository.findById(usuario.getId()).orElse(null);
                String pass = usu.getPassword();
                usuario.setPassword(pass);
            }
           if(usuario.getRol() == null){
                Usuario usu = usuarioRepository.findById(usuario.getId()).orElse(null);
                Rol rol = usu.getRol();
                usuario.setRol(rol);
           }
            try {
                

                return usuarioRepository.save(usuario);
            } catch (DataIntegrityViolationException e) {
                e.printStackTrace();
                return null;
            }
    }

    public Usuario obtenerPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario != null) {
            return usuario;
        } else
            return null;

    }

    public List<Usuario> obtenerLista() {

        return usuarioRepository.findAll();

    }

    public Usuario obtenerUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario != null) {
            return usuario;
        } else
            return null;
    }

    public Usuario obtenerUsuarioConectado() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String nombreUsuarioConectado = authentication.getName();

            return usuarioRepository.findByNombreUsuario(nombreUsuarioConectado);
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

    public Usuario añadirAutoRegistro(Usuario autoRegistro){

        String passCryString = passwordEncoder.encode(autoRegistro.getPassword());
        autoRegistro.setPassword(passCryString);
        try {
            Usuario usuario = new Usuario(0L,autoRegistro.getNombre(), autoRegistro.getApellidos(), autoRegistro.getEmail(), autoRegistro.getNombreUsuario(), autoRegistro.getPassword(), autoRegistro.getTelefono(),Rol.USER);
            return usuarioRepository.save(usuario);

        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            return null;
        }


    }

    public Usuario editarUsuario(Usuario usuario){

        if(usuario.getPassword() != null){
        String passCryString = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(passCryString);
        }
        else{
            Usuario usu = usuarioRepository.findById(usuario.getId()).orElse(null);
            String pass = usu.getPassword();
            Rol rol = usu.getRol();
            usuario.setRol(rol);
            usuario.setPassword(pass);
        }
       
        try {
            Usuario usu = usuarioRepository.findById(usuario.getId()).orElse(null);
            Rol rol = usu.getRol();
            usuario.setRol(rol);
            return usuarioRepository.save(usuario);
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            return null;
        }
    }


}
