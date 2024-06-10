package com.rmc.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rmc.app.domain.Suscripcion;
import com.rmc.app.domain.DTO.SuscripcionDTO;
import com.rmc.app.service.SuscripcionService;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/api")

public class SuscripcionController {


    @Autowired
    private SuscripcionService suscripcionService;
    

    @PostMapping({"/suscripcion"})
    public ResponseEntity <?> newElement(@Valid @RequestBody SuscripcionDTO suscripcionDTO) {
    //@Valid si no se cumple la validación devuelve BAD_REQUEST 
    
        Suscripcion suscripcion = new Suscripcion(null, 
            suscripcionDTO.getNombre());

           Suscripcion suscrip = suscripcionService.anadir(suscripcion);
        return ResponseEntity.status(HttpStatus.CREATED).body(suscrip);
    }
   


}
