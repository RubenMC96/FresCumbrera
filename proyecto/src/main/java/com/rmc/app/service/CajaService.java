package com.rmc.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rmc.app.domain.Caja;
import com.rmc.app.domain.Usuario;

@Service
public interface CajaService {
        
    public List<Caja> obtenerLista();
    public Caja obtenerPorId(long id);
    public Caja editar(Caja caja);
    public void annadir(Long usuarioId, Long productoId);
    public void borrar(Long id);
    public void borrarTodo();
    public Caja obtenerPorUsuario(Usuario usuario);
    
}
