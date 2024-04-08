package com.rmc.app.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rmc.app.Repositories.CompraRepository;
import com.rmc.app.domain.Compra;
import com.rmc.app.domain.LineaProducto;
import com.rmc.app.domain.Producto;
import com.rmc.app.domain.Usuario;
import com.rmc.app.domain.DTO.ProductoDTO;

@Service
public class CompraServiceImp implements CompraService {

    @Autowired
    CompraRepository compraRepository;
    @Autowired
    UsuarioService usuarioService;

    public Compra añadir(Compra compra) {
        
        compraRepository.save(compra);
        return compra; // podría no devolver nada, o boolean, etc.
    }

    public List<Compra> obtenerTodos() {
        return compraRepository.findAll();
    }

    public Compra obtenerPorId(long id) {

        Compra compra = compraRepository.findById(id).orElse(null);// debería lanzar excepción si no encontrado
        if (compra != null) {
            return compra;
        }
        return null;

    }

    public Compra editar(Compra compra) {

        return compraRepository.save(compra);
    }

    public void borrar(Long id) {
        Compra compra = compraRepository.findById(id).orElse(null);
        if (compra != null) {
            compraRepository.delete(compra);
        }
    }

    public List<Compra> obtenerPorUsuario() {
        Usuario usuario = usuarioService.obtenerUsuarioConectado();
        return compraRepository.findByUsuario(usuario);
    }

    public List<Compra> obtenerPedidos() {
        return compraRepository.findAll();
    }

    // public Compra obtenerCompraActiva(Usuario usuario) {

    //     List<Compra> compras = compraRepository.findByUsuario(usuario);
    //     Compra compraActiva;
    //     for (Compra compra : compras) {

    //         if (compra.getFinalizado() == false) {
    //             compraActiva = compra;
    //             return compraActiva;
    //         }
    //     }
    //     return null;// falta un neew compra
    // }

    // public Compra crearCompra(listaLineasCompra listaLinea){

    // if(listaLinea == null){
    //     return null;
    // }   
    // else{
    //     Usuario usuario = usuarioService.obtenerUsuarioConectado();
    //     LocalDateTime fecha = LocalDateTime.now();
    //     LocalDate fechaCompra = LocalDate.now();
    //     String numFactura = usuario.getNombre() + fecha;
    //     Integer numProductos = 0;
    //     for(LineaProducto linea : listaLinea.getListaLineaProducto()){
    //         numProductos += linea.getCantidadProductos();
    //     }
    //     Double importe = 0D;
    //     for(LineaProducto linea : listaLinea.getListaLineaProducto()){
    //         linea.getProducto().getPrecio();
    //     }
    
    //     Compra compra = new Compra(0L, numFactura, fechaCompra, numProductos, importe);
    
    //         return compra;
    // } 
 
    // }

    public Compra crearCompra(List<LineaProducto> listaCompra){

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

        /*List<ProductoDTO> listaProductos = new ArrayList<>();
        for(LineaProducto lista : listaCompra){
            ProductoDTO productoDTO = new ProductoDTO(lista.getProducto().getId(),lista.getProducto().getNombre());
            listaProductos.add(productoDTO);
        }*/

        
        // List<Producto> listaProductos = new ArrayList<>();
        // for(LineaProducto lista : listaCompra){
        //     listaProductos.add(lista.getProducto());
        // }
        
        Compra compra = new Compra(0L, numFactura, fechaCompra, numProductos, importe,usuario);
        return compra;
    }
}
