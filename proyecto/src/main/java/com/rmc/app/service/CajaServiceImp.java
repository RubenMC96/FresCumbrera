package com.rmc.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rmc.app.Repositories.CajaRepository;
import com.rmc.app.Repositories.ProductoRepository;
import com.rmc.app.Repositories.UsuarioRepository;
import com.rmc.app.domain.Caja;
import com.rmc.app.domain.Producto;
import com.rmc.app.domain.Usuario;

@Service
public class CajaServiceImp implements CajaService{


    @Autowired
    CajaRepository cajaRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    ProductoRepository productoRepository;

    public List<Caja> obtenerLista() {
        return cajaRepository.findAll();
    }
    public Caja obtenerPorIdUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);

        if(usuario != null){
            Caja caja = cajaRepository.findByUsuario(usuario);

            return caja;
        }
        else return null;
    }
    public Caja obtenerPorId(long id) {
        
        Caja caja = cajaRepository.findById(id).orElse(null);// debería lanzar excepción si no encontrado
        if(caja != null){
        return caja;
        }
        return null;
         
    }

    public void annadir(Long usuarioId, Long productoId){
        Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);
        if(usuario != null){
            Producto producto = productoRepository.findById(productoId).orElse(null);
            if(producto != null){
                Caja caja = new Caja();
                caja.setUsuario(usuario);
                caja.setProducto(producto);
                cajaRepository.save(caja);
            }
        }  
    }


    public Caja editar(Caja caja) {
          
        return cajaRepository.save(caja);
    }
    public void borrar(Long id) {
        Caja caja = cajaRepository.findById(id).orElse(null);
        if(caja != null){
            cajaRepository.delete(caja);
        }
        
    }
    public void borrarTodo() {
        
            cajaRepository.deleteAll();
    }

    public Caja obtenerPorUsuario(Usuario usuario){
        return cajaRepository.findByUsuario(usuario);
    }
}
