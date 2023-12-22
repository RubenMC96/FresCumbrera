package com.rmc.app.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.rmc.app.domain.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

   public Categoria findByNombre(String nombre);
} 
