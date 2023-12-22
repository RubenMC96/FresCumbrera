package com.rmc.app.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rmc.app.domain.Usuario_1;

public interface UsuarioRepository extends JpaRepository<Usuario_1, Long>{

   //public List <Usuario> findByUsuario (Usuario usuario); 

   public Usuario_1 findByNombre(String nombre);
} 
