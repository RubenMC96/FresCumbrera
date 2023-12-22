package com.rmc.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rmc.app.domain.Producto;

@Service
public interface ProductoService {
    
    public Producto añadir(Producto producto);
    public List<Producto> obteberLista();
    public Producto obtenerPorId(long id);
    public Producto editar(Producto producto);
    public void borrar(Long id);
    public List<Producto> findByCategory(Long idCat);
}
