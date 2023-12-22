package com.rmc.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rmc.app.Repositories.CategoriaRepository;
import com.rmc.app.domain.Categoria;

@Service
public class CategoriaServiceImp implements CategoriaService{


    @Autowired
    CategoriaRepository catRepo;

    public Categoria añadir(Categoria categoria) {
        catRepo.save(categoria);
        return categoria; // podría no devolver nada, o boolean, etc.
    }
    public List<Categoria> obteberLista() {
        return catRepo.findAll();
    }
    public Categoria obtenerPorId(long id) {
        
        Categoria categoria = catRepo.findById(id).orElse(null);// debería lanzar excepción si no encontrado
        if(categoria != null){
        return categoria;
        }
        return null;
         
    }
    public Categoria editar(Categoria categoria) {
          
        return catRepo.save(categoria);
    }
    public void borrar(Long id) {
        Categoria categoria = catRepo.findById(id).orElse(null);
        if(categoria != null){
            catRepo.delete(categoria);
        }
        
    }

    public Categoria obtenerPorCategoria(Long id){
        Categoria cat=catRepo.findById(id).orElse(null);
        if(cat == null) return null;
        
        return cat;
    }

    public Categoria obtenerPorNombre(String nombre){

        return catRepo.findByNombre(nombre);
    }
}
