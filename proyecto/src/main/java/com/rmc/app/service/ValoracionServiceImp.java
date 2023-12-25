package com.rmc.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rmc.app.Repositories.ProductoRepository;
import com.rmc.app.Repositories.UsuarioRepository;
import com.rmc.app.Repositories.ValoracionRepository;
import com.rmc.app.domain.Producto;
import com.rmc.app.domain.Usuario;
import com.rmc.app.domain.Valoracion;

@Service
public class ValoracionServiceImp implements ValoracionService {
    
    @Autowired
    ValoracionRepository valRepo;

    @Autowired
    UsuarioRepository usuRepo;

    @Autowired
    ProductoRepository proRepo;

    public Valoracion añadir(Valoracion valoracion){
        valRepo.save(valoracion);
        return valoracion;
    }
    public Valoracion obtenerPorId(long id){
       Valoracion valoracion = valRepo.findById(id).orElse(null);

       if(valoracion!= null){
        return valoracion;
       }
       else{
        return null;
       }



    }
    public Valoracion editar(Valoracion valoracion){
        valRepo.save(valoracion);
        return valoracion;
    }
    public void borrar(Long id){
        valRepo.deleteById(id);
    }
    public List<Valoracion> obtenerPorUsuario(Long id){

        Usuario usuario = usuRepo.findById(id).orElse(null);
        if(usuario!= null){
            return valRepo.findByUsuario(usuario);
        }
        else{
            return null;
        }
    }
    public List<Valoracion> obtenerPorProducto(Long id){

        Producto producto = proRepo.findById(id).orElse(null);
        if(producto != null){
            return valRepo.findByProducto(producto);
        }
        else{
            return null;
        }

    }

    
}

