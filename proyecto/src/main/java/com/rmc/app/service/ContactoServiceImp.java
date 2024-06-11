package com.rmc.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rmc.app.Repositories.ContactoRepository;
import com.rmc.app.domain.Contacto;
import java.lang.String;


@Service
public class ContactoServiceImp implements ContactoService{
    
    @Autowired
    EmailService emailService;
    @Autowired
    ContactoRepository contactoRepository;

    @Override
    public String enviarEmail(String email, String nombre, String mensaje) throws RuntimeException{
        try{
            String destinatario= email;
            String cuerpoMensaje = "Gracias por contactar con nosotros " + nombre + ", pronto nos pondremos en contacto con una respuesta.";
            String asunto = "Gracias por contactar";           
            Boolean envio = emailService.sendEmail(destinatario,asunto,cuerpoMensaje);
            
            if(envio){
                Contacto contacto = new Contacto(0L,nombre,email,mensaje);
                contactoRepository.save(contacto);
            }
            
            return cuerpoMensaje;
           
        }catch(Exception e){

            throw new RuntimeException("Error al enviar el email de contacto", e);

        }
    }
}
