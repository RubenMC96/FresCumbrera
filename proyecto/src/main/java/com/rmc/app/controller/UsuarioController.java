package com.rmc.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rmc.app.Repositories.UsuarioRepository;
import com.rmc.app.domain.Rol;
import com.rmc.app.domain.Usuario;
import com.rmc.app.service.UsuarioService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;




@Controller
@RequestMapping("/usuario")
public class UsuarioController {
    
    @Autowired
    public UsuarioService usuarioService;

    @Autowired
    public UsuarioRepository usuarioRepository;


    @GetMapping({"/", "/list"})
    public String showList(Model model){
        model.addAttribute("listaUsuarios", usuarioService.obtenerLista());
        return "UsuarioView/ListUsuView";
    }

    @GetMapping("/nuevo")
    public String showNuevo(Model model){
        model.addAttribute("usuarioForm", new Usuario());
        return "UsuarioView/UsuFormNew";
    }

    @PostMapping("/nuevo/submit")
    public String showNuevoSubmit(@Valid Usuario usuarioForm,
        BindingResult bindingResult){
            if(bindingResult.hasErrors())
                return "redirect:/usuario/nuevo";
            usuarioService.añadir(usuarioForm);
                return "redirect:/usuario/list";
    }
    @PostMapping("/autoRegistro/submit")
    public String showAutoRegistroSubmit(@Valid Usuario usuarioForm,
        BindingResult bindingResult){
            if(bindingResult.hasErrors())
                return "redirect:/autoRegistro";
            usuarioService.añadirAutoRegistro(usuarioForm);
                return "redirect:/inicio";
    }

    @GetMapping("/editar/{id}")
    public String ShowEdit(@PathVariable long id, Model model){
        Usuario usuarioConectado = usuarioService.obtenerUsuarioConectado();
        Usuario usuario = usuarioService.obtenerPorId(id);

        
        if(usuarioConectado.getRol() == Rol.ADMIN){
            model.addAttribute("usuarioForm", usuario);
            System.out.println(usuario);
            return "UsuarioView/UsuFormEdit";
        }
        return null;
    }

    @GetMapping("/editarPerfil")
    public String showEditPerfil(Model model){

        Usuario usuario = usuarioService.obtenerUsuarioConectado();

        model.addAttribute("usuarioForm", usuario);

        return("UsuarioView/UsuFormEditPerfil");
    }

    @PostMapping("/editarPerfil/submit")
        public String showPerfilEditSubmit(
            @Valid Usuario usuarioForm,
            BindingResult bindingResult){

            Usuario usuarioConectado = usuarioService.obtenerUsuarioConectado();
            System.out.println("Nombre " + usuarioConectado.getNombre() + "Rol" + usuarioConectado.getRol());
       
                if(bindingResult.hasErrors()){
                    return "redirect:/usuario/editarPerfil";
                }  
                else{
                    usuarioService.añadir(usuarioForm);
                    return "redirect:/inicio";
                }
               
        }
    

    @PostMapping("/editar/submit")
    public String showEditUsuSubmit(
        @Valid Usuario usuarioForm,
    BindingResult bindingResult){

        Usuario usuarioConectado = usuarioService.obtenerUsuarioConectado();
        
        if(usuarioConectado.getRol() == Rol.ADMIN){
            if(bindingResult.hasErrors()){
                return "redirect:/usuario/editar/{id}";
            }
            System.out.println(usuarioForm);

            usuarioService.añadir(usuarioForm);
                return "redirect:/usuario/list";
        }

        return null;
    }


    @GetMapping("/borrar/{id}")
    public String showDelete(@PathVariable long id){
        Usuario usuarioConectado = usuarioService.obtenerUsuarioConectado();
        if(usuarioConectado.getRol() == Rol.ADMIN){

            usuarioService.borrar(id);
            return "redirect:/usuario/list";
        }
        else{
            return "redirect:/usuario/list";
        }
        
    }
    @GetMapping("/borrarPerfil")
    public String showDeletePerfil(HttpServletRequest request, HttpServletResponse response){

        Usuario usuario = usuarioService.obtenerUsuarioConectado();

          if (usuario!= null) {
            // Eliminar el perfil del usuario
            usuarioService.borrar(usuario.getId());
            
            // Obtener el objeto Authentication del usuario actual
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            
            // Validar si el usuario está autenticado
            if (authentication!= null) {
                // Cerrar la sesión
                new SecurityContextLogoutHandler().logout(request, response, authentication);
                
                // Redirigir al usuario a la página principal
                return "redirect:/inicio";
            }
        }

        // Redirigir al usuario a la página principal
        
        return "redirect:/inicio";

    }
}
