package com.rmc.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rmc.app.Repositories.CompraRepository;
import com.rmc.app.domain.Compra;
import com.rmc.app.domain.Usuario;

@Service
public class CompraServiceImp implements CompraService{


    @Autowired
    CompraRepository compraRepository;

    public Compra añadir(Compra compra) {
        compraRepository.save(compra);
        return compra; // podría no devolver nada, o boolean, etc.
    }

    public List<Compra> obtenerTodos() {
        return compraRepository.findAll();
    }

    public Compra obtenerPorId(long id) {
        
        Compra compra = compraRepository.findById(id).orElse(null);// debería lanzar excepción si no encontrado
        if(compra != null){
        return compra;
        }
        return null;
         
    }
    public Compra editar(Compra compra) {
          
        return compraRepository.save(compra);
    }
    public void borrar(Long id) {
        Compra compra = compraRepository.findById(id).orElse(null);
        if(compra != null){
            compraRepository.delete(compra);
        }
        
    }

    public List<Compra> obtenerPorUsuario(Usuario usuario){

        return compraRepository.findByUsuario(usuario);
    }
    public List<Compra> obtenerPedidos(){
        return compraRepository.findAll();
    }
}
