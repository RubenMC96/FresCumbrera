package com.rmc.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rmc.app.Repositories.SuscripcionRepository;
import com.rmc.app.domain.Suscripcion;


@Service
public class SuscripcionServiceImp implements SuscripcionService{

    private final SuscripcionRepository suscripcionRepository;

    @Autowired
    public SuscripcionServiceImp( SuscripcionRepository suscripcionRepository) {
        this.suscripcionRepository = suscripcionRepository;
    }

    public Suscripcion anadir(Suscripcion suscripcion){


        return suscripcionRepository.save(suscripcion);

    }
}
