package com.rmc.app.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.rmc.app.domain.Caja;
import com.rmc.app.domain.Usuario;


public interface CajaRepository extends JpaRepository<Caja, Long>{

   public Caja findByUsuario(Usuario usuario);
} 
