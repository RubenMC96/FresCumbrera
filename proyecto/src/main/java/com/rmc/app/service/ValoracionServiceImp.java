package com.rmc.app.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rmc.app.Repositories.ProductoRepository;
import com.rmc.app.Repositories.UsuarioRepository;
import com.rmc.app.Repositories.ValoracionRepository;
import com.rmc.app.domain.Producto;
import com.rmc.app.domain.Usuario;
import com.rmc.app.domain.Valoracion;
import com.rmc.app.domain.DTO.ValoracionDTO;

@Service
public class ValoracionServiceImp implements ValoracionService {
    
    @Autowired
    ValoracionRepository valRepo;

    @Autowired
    UsuarioRepository usuRepo;

    @Autowired
    ProductoRepository proRepo;
    
    @Autowired
    UsuarioService usuarioService;

    @Autowired
    ProductoService productoService;

    public Valoracion añadir(Valoracion valoracion){
        
        //Valoracion valoracionSave = new Valoracion(valoracion.getId(),valoracion.getComentario(),valoracion.getPuntuacion(),valoracion.getFechaPublicacion(),valoracion.getUsuario(), valoracion.getProducto());
        valRepo.save(valoracion);
        return valoracion;
    }
    public Valoracion obtenerPorId(long id){
       Valoracion valoracion = valRepo.findById(id).orElse(null);

       if(valoracion!= null){
        return valoracion;
       }
       else{
        return null;
       }



    }
    public Valoracion editar(Valoracion valoracion){
        valRepo.save(valoracion);
        return valoracion;
    }
    public void borrar(Long id){
        valRepo.deleteById(id);
    }
    public List<Valoracion> obtenerPorUsuario(Long id){

        Usuario usuario = usuRepo.findById(id).orElse(null);
        if(usuario!= null){
            return valRepo.findByUsuario(usuario);
        }
        else{
            return null;
        }
    }
    public List<Valoracion> obtenerPorProducto(Long id){

        Producto producto = proRepo.findById(id).orElse(null);
        if(producto != null){
            return valRepo.findByProducto(producto);
        }
        else{
            return null;
        }

    }

    public Valoracion crearValoracion(ValoracionDTO valoracionDTO){
        Usuario usuario = usuarioService.obtenerUsuarioConectado();
        Producto producto = productoService.obtenerPorId(valoracionDTO.getIdProducto());
        Valoracion valoracion = new Valoracion(0L, valoracionDTO.getComentario(), valoracionDTO.getPuntuacion(), LocalDate.now(), usuario, producto);
        return valoracion;
    }

    
}

