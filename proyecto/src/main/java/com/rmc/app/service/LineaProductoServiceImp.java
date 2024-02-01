package com.rmc.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rmc.app.Repositories.CompraRepository;
import com.rmc.app.Repositories.LineaProductoRepository;
import com.rmc.app.Repositories.ProductoRepository;
import com.rmc.app.Repositories.UsuarioRepository;
import com.rmc.app.domain.Compra;
import com.rmc.app.domain.LineaProducto;

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

    public void annadir(LineaProducto lineaProducto) {

        lineaProductoRepository.save(lineaProducto);

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

    public List<LineaProducto> obtenerPorCompra(Compra compra) {

        List<LineaProducto> lineaProducto = lineaProductoRepository.findByCompra(compra);

        if (lineaProducto != null) {
            return lineaProducto;
        } else
            return null;
    }
}
