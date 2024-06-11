package com.rmc.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rmc.app.Repositories.ColaboracionRepository;
import com.rmc.app.domain.Colaboracion;
import java.lang.String;


@Service
public class ColaboracionServiceImp implements ColaboracionService{
    
    @Autowired
    EmailService emailService;
    @Autowired
    ColaboracionRepository colaboracionRepository;

    @Override
    public String enviarEmail(String email,String rrss ,String nombre, String mensaje) throws RuntimeException{
        try{
            String destinatario= email;
            String cuerpoMensaje = "¡Gracias por la propuesta de colaboración " + nombre + "!, valoraremos la propuesta y pronto tendremos una respuesta.";
            String asunto = "Gracias por la propuesta de colaboración";           
            Boolean envio = emailService.sendEmail(destinatario,asunto,cuerpoMensaje);
            
            if(envio){
                Colaboracion colaboracion = new Colaboracion(0L,nombre,rrss,email,mensaje);
                colaboracionRepository.save(colaboracion);
            }
            
            return cuerpoMensaje;
           
        }catch(Exception e){

            throw new RuntimeException("Error al enviar el email de contacto", e);

        }
    }
}
