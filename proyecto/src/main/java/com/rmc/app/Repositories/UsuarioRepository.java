package com.rmc.app.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rmc.app.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

   //public List <Usuario> findByUsuario (Usuario usuario); 

   public Usuario findByNombre(String nombre);
} 
