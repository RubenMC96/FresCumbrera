package com.rmc.app.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rmc.app.domain.LineaProducto;
import com.rmc.app.domain.Usuario;

public interface LineaProductoRepository extends JpaRepository<LineaProducto, Long> {

   public List<LineaProducto> findByUsuario(Usuario usuario);
}
