package com.rmc.app.service;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rmc.app.Repositories.CategoriaRepository;
import com.rmc.app.Repositories.ProductoRepository;
import com.rmc.app.domain.Categoria;
import com.rmc.app.domain.Producto;

@Service
public class ProductoServiceImp implements ProductoService{

    @Autowired
    ProductoRepository proRepo;
    
    @Autowired
    CategoriaRepository catRepo;

    public Producto añadir(Producto producto) {
        try{
            return proRepo.save(producto);

        }catch(RuntimeErrorException e){
            throw new RuntimeException("Error al obtener la categoria por nombre");
        }
        
    }
    public List<Producto> obtenerLista() {
        try{
            return proRepo.findAll();

        }catch(RuntimeErrorException e){
            throw new RuntimeException("Error al obtener la categoria por nombre");
        }
    }
    public Producto obtenerPorId(long id) {
        
        try{
            Producto producto = proRepo.findById(id).orElse(null); // debería lanzar excepción si no encontrado
            if(producto == null) return null;
            return producto;
        }catch(RuntimeErrorException e){
            throw new RuntimeException("Error al obtener la categoria por nombre");
        }



    }
    public Producto editar(Producto producto) {

        try{

            if(producto.getCategoria() == null){
                Producto pr = proRepo.findById(producto.getId()).orElse(null);
                producto.setCategoria(pr.getCategoria());
            }
    
            return proRepo.save(producto);

        }catch(RuntimeErrorException e){
            throw new RuntimeException("Error al obtener la categoria por nombre");
        }

        
    }
    public void borrar(Long id) {

        try{

            Producto producto = proRepo.findById(id).orElse(null); // debería lanzar excepción si no encontrado
            if(producto != null)
            proRepo.delete(producto);

        }catch(RuntimeErrorException e){
            throw new RuntimeException("Error al obtener la categoria por nombre");
        }


    }
    
    public List<Producto> findByCategory(Long idCat){

        try{

            Categoria categoria = catRepo.findById(idCat).orElse(null);

            if(categoria == null) return null;
    
            List<Producto> productos = proRepo.findByCategoria(categoria);
    
            return productos;

        }catch(RuntimeErrorException e){
            throw new RuntimeException("Error al obtener la categoria por nombre");
        }
        
        
    }


    public void actualizarStock(Long idProducto,Integer nuevoStock){


        try{

            Producto producto = proRepo.findById(idProducto).orElse(null);
            if(producto != null){
                producto.setStock(nuevoStock);
            }

        }catch(RuntimeErrorException e){
            throw new RuntimeException("Error al obtener la categoria por nombre");
        }

    }
}
