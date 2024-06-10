package com.rmc.app.service;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rmc.app.Repositories.CategoriaRepository;
import com.rmc.app.domain.Categoria;
import com.rmc.app.domain.Producto;

@Service
public class CategoriaServiceImp implements CategoriaService{


    @Autowired
    CategoriaRepository catRepo;

    public Categoria añadir(Categoria categoria){
        try{
            catRepo.save(categoria);
        return categoria;
        }catch(RuntimeException e){
            String error = "Error al guardar";
            throw new RuntimeException(error);
        }
        
    }

    public List<Categoria> obtenerLista() {
        try{
            return catRepo.findAll();
        }catch(RuntimeErrorException e){
            throw new RuntimeException("Error al recuperar los datos");
        }
        
    }

    public Categoria obtenerPorId(long id) {
        
        try{
            Categoria categoria = catRepo.findById(id).orElse(null);// debería lanzar excepción si no encontrado
            if(categoria != null){
                return categoria;
                }
                return null;
        }catch(RuntimeErrorException e){
            throw new RuntimeException("Error: No se ha podido encontrar la categoria por ese id");
        }
    }
    public Categoria editar(Categoria categoria) {
          try{
            return catRepo.save(categoria);
          }catch(RuntimeErrorException e){
            throw new RuntimeException("Error, no se ha podido actualizar los datos");
          }

    }
    public void borrar(Long id) {
        try{
            Categoria categoria = catRepo.findById(id).orElse(null);
            if(categoria != null){
                catRepo.delete(categoria);
            }
        }catch(RuntimeErrorException e){
            throw new RuntimeException("Error: No se ha podido borrar la categoria.");
        }
       
        
    }

    public Categoria obtenerPorCategoria(Long id){
        try{
            Categoria cat=catRepo.findById(id).orElse(null);
            if(cat == null) return null;
            
            return cat;
        }catch(RuntimeErrorException e){
            throw new RuntimeException("Error: Error a obtener la categoria");
        }
        
    }

    public Categoria obtenerPorNombre(String nombre){

        try{
            return catRepo.findByNombre(nombre);

        }catch(RuntimeErrorException e){
            throw new RuntimeException("Error al obtener la categoria por nombre");
        }
    }

}


