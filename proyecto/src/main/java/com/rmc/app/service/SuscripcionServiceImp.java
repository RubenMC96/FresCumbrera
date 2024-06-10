package com.rmc.app.service;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rmc.app.Repositories.SuscripcionRepository;
import com.rmc.app.domain.Producto;
import com.rmc.app.domain.Suscripcion;


@Service
public class SuscripcionServiceImp implements SuscripcionService{

    private final SuscripcionRepository suscripcionRepository;

    @Autowired
    public SuscripcionServiceImp( SuscripcionRepository suscripcionRepository) {
        this.suscripcionRepository = suscripcionRepository;
    }

    public Suscripcion anadir(Suscripcion suscripcion){

             try{

                return suscripcionRepository.save(suscripcion);

            }catch(RuntimeErrorException e){
                throw new RuntimeException("Error al obtener la categoria por nombre");
            }


    }
}
