package com.rmc.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.rmc.app.domain.Contacto;

import jakarta.validation.Valid;
/**
 * 
 */
@Controller
public class MainController {
    @GetMapping({ "/", "/inicio" })
    public String showInicio() {
        return "Generales/indexView";
    }

    @GetMapping("/contacto/")
    public String showContacto(Model model){
        model.addAttribute("contactoForm", new Contacto());
        return "Contacto/contactoForm";
    }

    @PostMapping("/contacto/submit")
    public String showContactoSubmit(@Valid Contacto contactoForm, BindingResult bindingResult){

        if(bindingResult.hasErrors())
                return "redirect:/contacto/";
                
        //Aqui se hace el envio del email

        return "Contacto/enviado";
    }
    @GetMapping("/autoRegistro")
    public String showAutoRegistro(){
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
}
