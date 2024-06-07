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

import jakarta.validation.Valid;
/**
 * 
 */
@Controller
public class MainController {

    @Autowired
    public ContactoService contactoService;

    @GetMapping({ "/", "/inicio" })
    public String showInicio() {
        return "Generales/indexView";
    }

    @GetMapping("/contacto/")
    public String showContacto(Model model){
        model.addAttribute("contactoForm", new Contacto());
        return "Contacto/contactoForm";
    }

    // BindingResult bindingResult, 
    @PostMapping("/contacto/submit")
    public String showContactoSubmit(@RequestParam("nombre") String nombre,
                                    @RequestParam("email") String email,
                                    @RequestParam("mensaje") String mensaje,
                                    Model model){

        // if(bindingResult.hasErrors())
        //         return "redirect:/contacto/";
        String cuerpoMensaje = "Muchas gracias por contactar " + nombre + ". <br><br> En breve le contactaremos para resolver todas sus dudas. <br><br><br> FresCumcumbreras. La tradición del sabor, con responsabilidad.<br><br><br> No responder a este correo.";
        Boolean envioOk = contactoService.enviarEmail("frescumbrera.noreply@gmail.com",cuerpoMensaje, email, nombre, mensaje);
                model.addAttribute("mensaje", envioOk);



        return "Contacto/enviado";
    }
    @GetMapping("/autoRegistro")
    public String showAutoRegistro(Model model){
        model.addAttribute("usuarioForm", new UsuarioAutoDTO());
        return "UsuarioView/UsuAutoRegistro";
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
