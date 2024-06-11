package com.rmc.app.service;

import org.springframework.stereotype.Service;



@Service
public interface ColaboracionService {
    
    public String enviarEmail(String email,String rrss ,String nombre, String mensaje);
}
