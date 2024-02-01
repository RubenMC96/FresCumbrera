package com.rmc.app.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rmc.app.domain.Compra;
import com.rmc.app.domain.LineaProducto;

public interface LineaProductoRepository extends JpaRepository<LineaProducto, Long> {

   public List<LineaProducto> findByCompra(Compra compra);
}
