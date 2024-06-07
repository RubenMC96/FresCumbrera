package com.rmc.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rmc.app.Repositories.ContactoRepository;
import com.rmc.app.domain.Contacto;


@Service
public class ContactoServiceImp implements ContactoService{
    
    @Autowired
    EmailService emailService;
    @Autowired
    ContactoRepository contactoRepository;

    @Override
    public Boolean enviarEmail(String remitente,String texto, String email, String nombre, String mensaje) throws RuntimeException{
        try{
            String cuerpoMensaje = texto;
            String destinatario= email;
            String asunto = "Gracias por contactar con nosotros";
            String from = remitente;
            Boolean envio = emailService.sendEmail(from,destinatario,asunto,cuerpoMensaje);
            
            if(envio){
                System.out.println(asunto);
                Contacto contacto = new Contacto(0L,nombre,email,mensaje);
                contactoRepository.save(contacto);
            }
            
            return true;
           
        }catch(Exception e){

            throw new RuntimeException("Error al enviar el email de contacto", e);

        }
    }
}
