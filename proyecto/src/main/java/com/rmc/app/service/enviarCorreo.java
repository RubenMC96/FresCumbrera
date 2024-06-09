package com.rmc.app.service;

import org.springframework.beans.factory.annotation.Autowired;

public class enviarCorreo {
    

    @Autowired
EmailService emailService;

public void enviarCorreos() {
    String destinatario = "rubenmunozcumbreras@gmail.com";
    String asunto = "Asunto del correo";
    String cuerpoMensaje = "Este es el cuerpo del mensaje";
    boolean resultadoEnvio = emailService.sendEmail(destinatario, asunto, cuerpoMensaje);

    if (resultadoEnvio) {
        System.out.println("Correo enviado exitosamente.");
    } else {
        System.out.println("Error al enviar el correo.");
    }
}

}
