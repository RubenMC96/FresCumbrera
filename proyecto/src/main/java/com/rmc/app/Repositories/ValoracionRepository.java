package com.rmc.app.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rmc.app.domain.Producto;
import com.rmc.app.domain.Usuario_1;
import com.rmc.app.domain.Valoracion;

public interface ValoracionRepository extends JpaRepository<Valoracion, Long>{

   public List <Valoracion> findByUsuario (Usuario_1 usuario); 

   public List <Valoracion> findByProducto(Producto producto);
} 
