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
import org.springframework.web.bind.annotation.RequestParam;

import com.rmc.app.Repositories.UsuarioRepository;
import com.rmc.app.domain.Rol;
import com.rmc.app.domain.Usuario;
import com.rmc.app.domain.DTO.UsuarioDTO;
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
        model.addAttribute("usuarioForm", new UsuarioDTO());
        return "UsuarioView/UsuFormNew";
    }

    @PostMapping("/nuevo/submit")
    public String showNuevoSubmit(@Valid UsuarioDTO usuarioForm,
        BindingResult bindingResult){
            if(bindingResult.hasErrors())
                return "redirect:/usuario/nuevo";
                else{
                    Usuario usuario;
                    Rol rol = Rol.USER;
                    if(usuarioForm.getRol() == 0){
                        rol = Rol.USER;
                    }else if (usuarioForm.getRol() == 1) {
                        rol = Rol.ADMIN;

                    }else if (usuarioForm.getRol() == 2) {
                        rol = Rol.USER;
                    }

                    usuario = new Usuario(usuarioForm.getId(), usuarioForm.getNombre(), usuarioForm.getApellidos(), usuarioForm.getEmail(), usuarioForm.getNombreUsuario(), usuarioForm.getPassword(), usuarioForm.getTelefono(), rol);

                    usuarioService.añadir(usuario);

                    return "redirect:/usuario/list";
                }
            
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
        UsuarioDTO usuarioDTO;
        Integer rol = 2;
        if(usuario.getRol() == Rol.ADMIN){
            rol = 0;
        }else if (usuario.getRol() == Rol.MANAGER) {
            rol = 1;

        }else if (usuario.getRol() == Rol.USER) {
            rol = 2;
        }

        usuarioDTO = new UsuarioDTO(usuario.getId(), usuario.getNombre(), usuario.getApellidos(), usuario.getEmail(), usuario.getNombreUsuario(), usuario.getPassword(), usuario.getTelefono(), rol);
        
        if(usuarioConectado.getRol() == Rol.ADMIN){
            model.addAttribute("usuarioForm", usuarioDTO);
            System.out.println(usuario);
            return "UsuarioView/UsuFormEdit";
        }
        return null;
    }

    @PostMapping("/editar/submit")
    public String showEditUsuSubmit(
        @Valid UsuarioDTO usuarioForm,
    BindingResult bindingResult){

        Usuario usuario;
        Usuario usuarioConectado = usuarioService.obtenerUsuarioConectado();
        
        if(usuarioConectado.getRol() == Rol.ADMIN){
            if(bindingResult.hasErrors()){
                return "redirect:/usuario/editar/{id}";
            }else{
                Rol rol = Rol.USER;
                if(usuarioForm.getRol() == 0){
                    rol = Rol.ADMIN;}
                    else if (usuarioForm.getRol() == 1) {
                    rol = Rol.MANAGER;}
                    else if (usuarioForm.getRol() == 2) {
                    rol = Rol.USER;
                    }
                    usuario = new Usuario(usuarioForm.getId(), usuarioForm.getNombre(), usuarioForm.getApellidos(), usuarioForm.getEmail(), usuarioForm.getNombreUsuario(), usuarioForm.getPassword(), usuarioForm.getTelefono(), rol);
                    usuarioService.editar(usuario);
                return "redirect:/usuario/list";
                }
            
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
                    usuarioService.editarUsuario(usuarioForm);
                    return "redirect:/inicio";
                }
               
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
