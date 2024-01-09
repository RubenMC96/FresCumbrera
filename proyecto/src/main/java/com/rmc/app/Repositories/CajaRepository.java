package com.rmc.app.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.rmc.app.domain.Caja;
import com.rmc.app.domain.Compra;


public interface CajaRepository extends JpaRepository<Caja, Long>{

   public Caja findByCompra(Compra compra);
} 
