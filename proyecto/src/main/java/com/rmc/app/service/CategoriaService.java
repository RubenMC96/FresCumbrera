package com.rmc.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rmc.app.domain.Categoria;

@Service
public interface CategoriaService {
    
    public Categoria añadir(Categoria categoria);
    public List<Categoria> obteberLista();
    public Categoria obtenerPorId(long id);
    public Categoria editar(Categoria categoria);
    public void borrar(Long id);
    public Categoria obtenerPorCategoria(Long id);
    public Categoria obtenerPorNombre(String nombre);

    
}
