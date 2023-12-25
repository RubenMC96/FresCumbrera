package com.rmc.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rmc.app.Repositories.UsuarioRepository;
import com.rmc.app.domain.Usuario;
import com.rmc.app.service.UsuarioService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
    
    @Autowired
    Usuario usuario;
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/list")
    public String showUsuarios(Model model){
        model.addAttribute("listaUsuarios", usuarioService.obtenerLista());//Fallos porque aun no exite
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
                    usuarioService.crearUsuario(usuarioForm.getNombreUsuario(), usuarioForm.getContrasena());
                    usuarioService.añadir(usuarioForm);
                
                    return "redirect:/usuario/list";
    }

    @GetMapping("/editar/{id}")
    public String showEditUsuario(@PathVariable long id, Model model){
        

        usuario = usuarioService.obtenerPorId(id);
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

        usuarioService.editar(usuarioForm);
        return "redirect:/usuario/list";

    }
    
    @GetMapping("/borrar/{id}")
    public String showDeleteUsuario(@PathVariable long id){
        
        usuarioService.borrar(id);
        return "redirect:/usuario/list";

    }

    @PostMapping("/login/{email}")
    public String showLoginUsurio(@RequestParam("nombreUsuario") String nombreUsuario, @RequestParam("contrasena") String contrasena, @PathVariable String email , Model model){

        Boolean existe = usuarioService.existeUsuario(email);

        if(existe){
            Usuario usuario = usuarioService.obtenerPorEmail(email);

           String contrasenaCifrada = usuario.getContrasena();

           if(passwordEncoder.matches(contrasena, contrasenaCifrada)){
            
            model.addAttribute("login", usuario);

            return "indexView";
           }
            model.addAttribute("aviso", "Contraseña no válida");
            return "UsuarioView/usuarioNew";
        }
        else{
            model.addAttribute("aviso", "Usuario no registrado");
            return "UsuarioView/usuarioNew";
        }
        
        }

    @GetMapping("/cuenta/{id}")
    public String showCuenta(@PathVariable Long id, Model model){

        Usuario usuario = usuarioService.obtenerPorId(id);
        if(usuario != null){
            model.addAttribute("usuario", usuario);
            return "UsuarioView/cuenta";
        }
        else 
        return "redirect:/usuario/list";
        
    }
    
}
