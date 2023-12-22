package com.rmc.app.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rmc.app.domain.Categoria;
import com.rmc.app.domain.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long>{

       public List <Producto> findByCategoria (Categoria categoria); 

} 
