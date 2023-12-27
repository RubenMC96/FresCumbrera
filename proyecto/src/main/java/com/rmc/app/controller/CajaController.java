package com.rmc.app.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rmc.app.domain.Caja;
import com.rmc.app.domain.Producto;
import com.rmc.app.domain.Usuario;
import com.rmc.app.service.CajaService;
import com.rmc.app.service.CompraService;
import com.rmc.app.service.ProductoService;
import com.rmc.app.service.UsuarioService;

import jakarta.validation.Valid;




@Controller
@RequestMapping("/caja") 
public class CajaController {

    @Autowired
    public CompraService compraService;
    @Autowired
    public ProductoService productoService;
    @Autowired
    public CajaService cajaService;
    @Autowired
    public UsuarioService usuarioService;

        @GetMapping({"/list/{id}"})
        public String showList(@PathVariable long id , Model model){
            Usuario usuario = usuarioService.obtenerPorId(id);
            Caja caja = cajaService.obtenerPorUsuario(usuario);
            model.addAttribute("listaCaja", caja);
            return "cajaView/ListCajaView";
        }
        @GetMapping("/annadir/{id}")
        public String showAñadir(@PathVariable long usuarioId, @PathVariable long productoId ,Model model){
            Producto producto = productoService.obtenerPorId(productoId);
            cajaService.annadir(usuarioId, productoId);
            model.addAttribute("cajaForm", producto);
            return "redirect:/caja/list";
        }
        @PostMapping("/editar/submit")
        public String showEditSubmit (
            @Valid Caja cajaForm,
            BindingResult bindingResult){
                if(bindingResult.hasErrors())
                    return "redirect:/caja/editar/{id}";
                cajaService.editar(cajaForm);
                    return "redirect:/caja/list";
        }

        @GetMapping("/editar/{id}")
        public String showEditForm(@PathVariable long id, Model model) {
            Caja caja = cajaService.obtenerPorId(id);
            // el commandobject del formulario es el empleado con el id solicitado
            if (caja != null) {
                model.addAttribute("cajaForm", caja);
                return "cajaView/cajaFormEdit";
            }
            // si no lo encuentra vuelve a la página de inicio.
            return "redirect:/caja/list";
        }

        @GetMapping("/borrar/{id}")
        public String showDelete(@PathVariable long id) {
                cajaService.borrar(id);
                return "redirect:/caja/list";
            
        }
        @GetMapping("/borrar")
        public String showDeleteAll(Model model) {
                cajaService.borrarTodo();
                model.addAttribute("mensaje", "La caja está vacía");
                return "redirect:/caja/list";
            
        }
        


    
}