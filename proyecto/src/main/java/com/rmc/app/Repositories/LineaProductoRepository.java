package com.rmc.app.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rmc.app.domain.LineaProducto;
import com.rmc.app.domain.Compra;

public interface LineaProductoRepository extends JpaRepository<LineaProducto, Long> {

   public LineaProducto findByCompra(Compra compra);
}
