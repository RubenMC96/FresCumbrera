package com.rmc.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rmc.app.domain.Contacto;
import com.rmc.app.domain.DTO.UsuarioAutoDTO;
import com.rmc.app.service.ContactoService;
import com.rmc.app.service.EmailService;

import jakarta.validation.Valid;
/**
 * 
 */
@Controller
public class MainController {

    @Autowired
    public ContactoService contactoService;
    @Autowired
    private EmailService emailService;

    @GetMapping({ "/", "/inicio" })
    public String showInicio() {
        return "Generales/indexView";
    }

    @GetMapping("/contacto/")
    public String showContacto(Model model){
        model.addAttribute("contactoForm", new Contacto());
        return "Contacto/contactoForm";
    }

    // 


    // BindingResult bindingResult, 
    @PostMapping("/contacto/submit")
    public String sendContactEmail(@RequestParam("nombre") String nombre,
                                   @RequestParam("email") String correo,
                                   @RequestParam("mensaje") String mensaje,
                                   Model model
                                   ) {
        //String textMessage = "Nombre: " + nombre + "\nCorreo: " + correo + "\n\nMensaje:\n" + mensaje;
        String textMessage = "Muchas gracias por contactar con nosotros " + nombre + ", en breves responderemos a todas sus dudas.";
        String asunto = "Gracias por contactar";
        //boolean isSent = emailService.sendEmail(correo, asunto, textMessage);
        System.out.println();

        // if (isSent) {
        //     model.addAttribute("mensaje", "Correo enviado.");
        //     return "/Contacto/enviado"; 

        // } else {
        //     model.addAttribute("mensaje", "Error al enviar el correo.");
        //     return "/Contacto/enviado"; 

        // }

        //enviarCorreos();

        model.addAttribute("mensaje", textMessage);
        return "/Contacto/enviado";

    }

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


    @GetMapping("/autoRegistro")
    public String showAutoRegistro(Model model){
        model.addAttribute("usuarioForm", new UsuarioAutoDTO());
        return "UsuarioView/UsuAutoRegistro";
    }



    @GetMapping("/suscripcionForm")
    public String showSuscripcion(Model model){
       // model.addAttribute("usuarioForm", new UsuarioAutoDTO());
        return "Suscripcion/suscripcionForm";
    }





    @GetMapping("/signin")
    public String showLogin() {
        return "Log/loginView";
    }

    @GetMapping("/signout")
    public String showLogout() {
        return "Log/logOutView";
    }

    @GetMapping("/somos/")
    public String showSomos(){
        return "Somos/quienesSomos";
    }
    @GetMapping("/preguntas/")
    public String showPreguntas(){
        return "Somos/preguntas";
    }
    @GetMapping("/funciona/")
    public String showFunciona(){
        return "Somos/funciona";
    }
    @GetMapping("/planet/")
    public String showPlaneta(){
        return "Somos/planet";
    }
    @GetMapping("/huella/")
    public String showHuella(){
        return "Somos/huella";
    }
    @GetMapping("/envases/")
    public String showEnvases(){
        return "Somos/envases";
    }
}
