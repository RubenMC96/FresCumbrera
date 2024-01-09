package com.rmc.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rmc.app.Repositories.CajaRepository;
import com.rmc.app.Repositories.CompraRepository;
import com.rmc.app.Repositories.ProductoRepository;
import com.rmc.app.Repositories.UsuarioRepository;
import com.rmc.app.domain.Caja;
import com.rmc.app.domain.Compra;
import com.rmc.app.domain.Producto;

@Service
public class CajaServiceImp implements CajaService{


    @Autowired
    CajaRepository cajaRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    ProductoRepository productoRepository;
    @Autowired
    CompraRepository compraRepository;


    public List<Caja> obtenerLista() {
        return cajaRepository.findAll();
    }
    public Caja obtenerPorId(long id) {
        
        Caja caja = cajaRepository.findById(id).orElse(null);// debería lanzar excepción si no encontrado
        if(caja != null){
        return caja;
        }
        return null;
         
    }

    public void annadir(Long productoId,Integer cantidadProductos){
        
            Producto producto = productoRepository.findById(productoId).orElse(null);
            if(producto != null){
                Caja caja = new Caja(producto, cantidadProductos);
                cajaRepository.save(caja);
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

    public Caja obtenerPorCompra(Compra compra){

        Caja caja =  cajaRepository.findByCompra(compra);

        if(caja != null){
            return caja;
        }
        else return null;
    }
}
