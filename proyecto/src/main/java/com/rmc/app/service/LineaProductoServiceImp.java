package com.rmc.app.service;

import java.util.List;

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
        return lineaProductoRepository.findAll();
    }

    public LineaProducto obtenerPorId(long id) {

        LineaProducto lineaProducto = lineaProductoRepository.findById(id).orElse(null);// debería lanzar excepción si
                                                                                        // no encontrado
        if (lineaProducto != null) {
            return lineaProducto;
        }
        return null;

    }

    public void annadir(LineaProducto linea) {

        Usuario usuarioConectado = usuarioService.obtenerUsuarioConectado();
        Integer cantidad = linea.getCantidadProductos();
        if(cantidad == null){
            LineaProducto lineaProducto = new LineaProducto(0L, linea.getCantidadProductos(), usuarioConectado, linea.getProducto());
            lineaProductoRepository.save(lineaProducto);
        }
        
        

    }

    public LineaProducto editar(LineaProducto lineaProducto) {

        return lineaProductoRepository.save(lineaProducto);
    }

    public void borrar(Long id) {
        LineaProducto lineaProducto = lineaProductoRepository.findById(id).orElse(null);
        if (lineaProducto != null) {
            lineaProductoRepository.delete(lineaProducto);
        }

    }

    public void borrarTodo() {

        lineaProductoRepository.deleteAll();
    }

    // public List<LineaProducto> obtenerPorCompra(Compra compra) {

    //     List<LineaProducto> lineaProducto = lineaProductoRepository.findByCompra(compra);

    //     if (lineaProducto != null) {
    //         return lineaProducto;
    //     } else
    //         return null;
    // }

    // public LineaProducto addNuevaLinea(Long id, Integer cantidad) {

    //     Usuario usuarioConectado = usuarioService.obtenerUsuarioConectado();
    //     Compra compra = compraService.obtenerCompraActiva(usuarioConectado);
    //     Producto producto = productoService.obtenerPorId(id);
    //     LineaProducto lineaProducto = lineaProductoRepository.save(
    //             new LineaProducto(null, cantidad, compra, producto));
    //     return lineaProducto;
    // }

    public List<LineaProducto> obtenerPorUsuario(){

        Usuario usuarioConectado = usuarioService.obtenerUsuarioConectado();

        List <LineaProducto> lineaProducto = lineaProductoRepository.findByUsuario(usuarioConectado);

        return lineaProducto;
    }

    public Double obtenerImporte(List<LineaProducto> lineas){

        Double importe = 0D;

        for(LineaProducto linea : lineas){

            importe += linea.getProducto().getPrecio() * linea.getCantidadProductos();
        }

        return importe;
    }

}
