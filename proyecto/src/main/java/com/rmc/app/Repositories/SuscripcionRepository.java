package com.rmc.app.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rmc.app.domain.Suscripcion;
import com.rmc.app.domain.Usuario;
import com.rmc.app.domain.Valoracion;

public interface SuscripcionRepository extends JpaRepository<Valoracion, Long>{

   public Suscripcion save(Suscripcion suscripcion);

} 
