package com.rmc.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rmc.app.domain.Producto;
import com.rmc.app.domain.Usuario;
import com.rmc.app.domain.Valoracion;

@Service
public interface ValoracionService {
    
    public Valoracion añadir(Valoracion valoracion);
    public Valoracion obtenerPorId(long id);
    public Valoracion editar(Valoracion valoracion);
    public void borrar(Long id);
    public List<Valoracion> obtenerPorUsuario(Long id);
    public List<Valoracion> obtenerPorProducto(Long id);
    public Valoracion crearValoracion(Producto producto, Usuario usuario);
    
}

