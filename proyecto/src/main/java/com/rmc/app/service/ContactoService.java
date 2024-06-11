package com.rmc.app.service;

import org.springframework.stereotype.Service;



@Service
public interface ContactoService {
    
    public String enviarEmail(String email, String nombre, String mensaje);
}
