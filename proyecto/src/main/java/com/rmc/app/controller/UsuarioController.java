package com.rmc.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rmc.app.Repositories.UsuarioRepository;
import com.rmc.app.domain.Usuario;
import com.rmc.app.service.UsuarioService_1;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
    
    @Autowired
    Usuario usuario;
    @Autowired
    UsuarioService_1 usaurioService;
    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping("/list")
    public String showUsuarios(Model model){
        model.addAllAttributes("listaUsuarios", usuarioService.obtenerLista());//Fallos porque aun no exite
        return "UsuarioView/usuarioList";
    }

    @GetMapping("/nuevo")
    public String showNewUsuario(Model model){
        model.addAttribute("usuarioForm", new Usuario());
        return "UsuarioView/usuarioNew";
    }

    @GetMapping("/nuevo/submit")
    public String showNewSubmitUsuario(@Valid Usuario usuarioForm, BindingResult bindingResult){

        if(bindingResult.hasErrors())
                    return "redirect:/usuario/nuevo";
                usuarioService.añadir(usuarioForm);
                    return "redirect:/usuario/list";
    }

    @GetMapping("/editar/{id}")
    public String showEditUsuario(PathVariable id, Model model){
        

        usuario = usaurioService.obtenerPorId(id).orElse(null);
        if(usuario != null){
            model.addAttribute("usuarioForm", usuario);
            return "UsuarioView/usuarioEdit";
        }
        else{
            return "redirect:/usuario/list";
        }
    }

    @GetMapping("editar/submit")
    private String showEditSubmitUsuario(@Valid Usuario usuarioForm, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "redirect:/usuario/nuevo";

        usaurioService.editar(usuarioForm);
        return "redirect:/usuario/list";

    }
    
    @GetMapping("/borrar/{id}")
    public String showDeleteUsuario(PathVariable id, Model model){
        usuario = usaurioService.obtenerPorId(id).orElse(null);
        if(usuario != null){
            usaurioService.borrar(usuario);
        }
        else{
        return "redirect:/usuario/list";
        }
    }

    @GetMapping("login/{id}")
    public showLoginUsurio(PathVariable id, Model model){

        Boolean exite = usuarioService.obtenerLogin(id);

        if(usuario){
            return "indexView"; 
        }
        else{
            model.addAttribute("aviso", "Usuario no registrado")
            return "UsuarioView/usuarioNew";
        }
        
        }
        /*Completar login */
}
