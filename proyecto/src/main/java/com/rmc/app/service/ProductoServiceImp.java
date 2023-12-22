package com.rmc.app.service;
import java.util.List;

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
        
        return proRepo.save(producto); // podría no devolver nada, o boolean, etc.
    }
    public List<Producto> obteberLista() {
        return proRepo.findAll();
    }
    public Producto obtenerPorId(long id) {
        
        Producto producto = proRepo.findById(id).orElse(null); // debería lanzar excepción si no encontrado
        if(producto == null) return null;
        return producto;

    }
    public Producto editar(Producto producto) {
        return proRepo.save(producto);
    }
    public void borrar(Long id) {
        Producto producto = proRepo.findById(id).orElse(null); // debería lanzar excepción si no encontrado
        if(producto != null)
        proRepo.delete(producto);
    }
    public List<Producto> findByCategory(Long idCat){
        
        Categoria categoria = catRepo.findById(idCat).orElse(null);

        if(categoria == null) return null;

        return proRepo.findByCategoria(categoria);
    }
}
