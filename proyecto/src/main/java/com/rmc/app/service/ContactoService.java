package com.rmc.app.service;

import org.springframework.stereotype.Service;



@Service
public interface ContactoService {
    
    public Boolean enviarEmail(String cuerpoMensaje, String email, String nombre, String mensaje);
}
