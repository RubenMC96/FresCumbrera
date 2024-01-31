package com.rmc.app.Repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rmc.app.domain.Compra;
import com.rmc.app.domain.Usuario;

public interface CompraRepository extends JpaRepository<Compra, Long>{


   public List<Compra> findByUsuario(Usuario usuario);

} 
