package com.rmc.app.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rmc.app.Repositories.CompraRepository;
import com.rmc.app.domain.Compra;
import com.rmc.app.domain.LineaProducto;
import com.rmc.app.domain.Usuario;

@Service
public class CompraServiceImp implements CompraService {

    @Autowired
    CompraRepository compraRepository;
    @Autowired
    UsuarioService usuarioService;

    public Compra añadir(Compra compra) {
        
       try{
        compraRepository.save(compra);
        return compra;
        }catch(RuntimeErrorException e){
            throw new RuntimeException("Error al obtener la categoria por nombre");
        }
    }

    public List<Compra> obtenerTodos() {
        try{
            return compraRepository.findAll();
        }catch(RuntimeErrorException e){
            throw new RuntimeException("Error al obtener la categoria por nombre");
        }
    }

    public Compra obtenerPorId(long id) {

        try{
            Compra compra = compraRepository.findById(id).orElse(null);// debería lanzar excepción si no encontrado
                if (compra != null) {
                    return compra;
                }
                return null;
        }catch(RuntimeErrorException e){
            throw new RuntimeException("Error al obtener la categoria por nombre");
        }

    }

    public Compra editar(Compra compra) {

        try{
            return compraRepository.save(compra);
        }catch(RuntimeErrorException e){
            throw new RuntimeException("Error al obtener la categoria por nombre");
        }
        
    }

    public void borrar(Long id) {
        try{
            Compra compra = compraRepository.findById(id).orElse(null);
            if (compra != null) {
                compraRepository.delete(compra);
            }
        }catch(RuntimeErrorException e){
            throw new RuntimeException("Error al obtener la categoria por nombre");
        }
       
    }

    public List<Compra> obtenerPorUsuario(Long id) {
      
        try{
            Usuario usuario = usuarioService.obtenerPorId(id);
            return compraRepository.findByUsuario(usuario);        
        }catch(RuntimeErrorException e){
            throw new RuntimeException("Error al obtener la categoria por nombre");
        }

    }

    public List<Compra> obtenerPedidos() {
        try{
            return compraRepository.findAll();
        }catch(RuntimeErrorException e){
            throw new RuntimeException("Error al obtener la categoria por nombre");
        }
    }


    public Compra crearCompra(List<LineaProducto> listaCompra){
        
        try{

            Usuario usuario = usuarioService.obtenerUsuarioConectado();
            LocalDateTime fecha = LocalDateTime.now();
            LocalDate fechaCompra = LocalDate.now();
            String numFactura = usuario.getNombre() + fecha;
            Integer numProductos = 0;
            for(LineaProducto linea : listaCompra){
                   numProductos += linea.getCantidadProductos();
                }
            Double importe = 0D;
            for(LineaProducto linea : listaCompra){
                     importe += linea.getProducto().getPrecio() * linea.getCantidadProductos();
                }
    
            Compra compra = new Compra(0L, numFactura, fechaCompra, numProductos, importe,usuario);
            return compra;

        }catch(RuntimeErrorException e){
            throw new RuntimeException("Error al obtener la categoria por nombre");
        }

        
    }
}
