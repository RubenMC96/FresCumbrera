package com.rmc.app.service;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rmc.app.Repositories.CompraRepository;
import com.rmc.app.Repositories.LineaProductoRepository;
import com.rmc.app.Repositories.ProductoRepository;
import com.rmc.app.Repositories.UsuarioRepository;
import com.rmc.app.domain.LineaProducto;
import com.rmc.app.domain.Usuario;

@Service
public class LineaProductoServiceImp implements LineaProductoService {

    @Autowired
    LineaProductoRepository lineaProductoRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    ProductoRepository productoRepository;
    @Autowired
    CompraRepository compraRepository;
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    CompraService compraService;
    @Autowired
    ProductoService productoService;

    public List<LineaProducto> obtenerLista() {
          try{
            return lineaProductoRepository.findAll();

        }catch(RuntimeErrorException e){
            throw new RuntimeException("Error al obtener la categoria por nombre");
        }
    }

    public LineaProducto obtenerPorId(long id) {

        try{
            LineaProducto lineaProducto = lineaProductoRepository.findById(id).orElse(null);// debería lanzar excepción si no encontrado
                if (lineaProducto != null) {
                return lineaProducto;
                }
                return null;
        }catch(RuntimeErrorException e){
            throw new RuntimeException("Error al obtener la categoria por nombre");
        }

        

    }

    public void annadir(LineaProducto linea) {

        try{
            Usuario usuarioConectado = usuarioService.obtenerUsuarioConectado();
            Integer cantidad = linea.getCantidadProductos();
            if(cantidad != null){
                LineaProducto lineaProducto = new LineaProducto(0L, linea.getCantidadProductos(), usuarioConectado, linea.getProducto());
                lineaProductoRepository.save(lineaProducto);
            }

        }catch(RuntimeErrorException e){
            throw new RuntimeException("Error al obtener la categoria por nombre");
        }

    }

    public LineaProducto editar(LineaProducto lineaProducto) {
        try{
            return lineaProductoRepository.save(lineaProducto);

        }catch(RuntimeErrorException e){
            throw new RuntimeException("Error al obtener la categoria por nombre");
        }

    }

    public void borrar(Long id) {

        try{
            LineaProducto lineaProducto = lineaProductoRepository.findById(id).orElse(null);
            if (lineaProducto != null) {
                lineaProductoRepository.delete(lineaProducto);
            }
        }catch(RuntimeErrorException e){
            throw new RuntimeException("Error al obtener la categoria por nombre");
        }
        

    }

    public void borrarTodo() {

        try{
            lineaProductoRepository.deleteAll();

        }catch(RuntimeErrorException e){
            throw new RuntimeException("Error al obtener la categoria por nombre");
        }
    }

    public List<LineaProducto> obtenerPorUsuario(){

        try{
            Usuario usuarioConectado = usuarioService.obtenerUsuarioConectado();

            List <LineaProducto> lineaProducto = lineaProductoRepository.findByUsuario(usuarioConectado);
    
            return lineaProducto;
        }catch(RuntimeErrorException e){
            throw new RuntimeException("Error al obtener la categoria por nombre");
        }

        
    }

    public Double obtenerImporte(List<LineaProducto> lineas){

        
        try{
            Double importe = 0D;

        for(LineaProducto linea : lineas){

            importe += linea.getProducto().getPrecio() * linea.getCantidadProductos();
        }

        return importe;
        }catch(RuntimeErrorException e){
            throw new RuntimeException("Error al obtener la categoria por nombre");
        }
        
    }

}
