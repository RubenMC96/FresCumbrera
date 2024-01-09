package com.rmc.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rmc.app.domain.Caja;
import com.rmc.app.domain.Compra;

@Service
public interface CajaService {
        
    public List<Caja> obtenerLista();
    public Caja obtenerPorId(long id);
    public Caja editar(Caja caja);
    public void annadir(Long productoId, Integer canticantidadProductos);
    public void borrar(Long id);
    public void borrarTodo();
    public Caja obtenerPorCompra(Compra compra); 
    
}
