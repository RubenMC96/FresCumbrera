package com.rmc.app.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rmc.app.domain.Valoracion;
import com.rmc.app.service.ProductoService;
import com.rmc.app.service.ValoracionService;

import jakarta.validation.Valid;




@Controller
@RequestMapping("/valoracion") 
public class ValoracionController {

    @Autowired
    public ValoracionService valoracionService;
    @Autowired
    public ProductoService productoService;

        @GetMapping({"/", "/valoracion/producto/{idProd}"})
        public String showProducto(@PathVariable long id, Model model){
            model.addAttribute("listaValoracion", valoracionService.obtenerPorProducto(id));
            return "valoracionView/ListValView";
        }

        @GetMapping({"/", "/valoracion/usuario/{idUsuario}"})
        public String showUsuario(@PathVariable long id, Model model){
            model.addAttribute("listaValoracion", valoracionService.obtenerPorUsuario(id));
            return "valoracionView/ListValView";
        }
        @GetMapping("/nuevo")
        public String showNuevo(Model model){
            model.addAttribute("valoracionForm", new Valoracion());
            return "valoracionView/ValFormNew";
        }
        @PostMapping("/nuevo/submit")
        public String showNuevoSubmit (
            @Valid Valoracion valoracionForm,
            BindingResult bindingResult){
                if(bindingResult.hasErrors())
                    return "redirect:/valoracion/nuevo";
                valoracionService.añadir(valoracionForm);
                    return "redirect:/valoracion/list";
        }
        @PostMapping("/editar/submit")
        public String showEditSubmit (
            @Valid Valoracion valoracionForm,
            BindingResult bindingResult){
                if(bindingResult.hasErrors())
                    return "redirect:/valoracion/editar/{id}";
                valoracionService.editar(valoracionForm);
                    return "redirect:/valoracion/list";
        }

        @GetMapping("/editar/{id}")
        public String showEditForm(@PathVariable long id, Model model) {
            Valoracion valoracion = valoracionService.obtenerPorId(id);
            // el commandobject del formulario es el empleado con el id solicitado
            if (valoracion != null) {
                model.addAttribute("ValoracionForm", valoracion);
                return "valoracionView/ValFormEdit";
            }
            // si no lo encuentra vuelve a la página de inicio.
            return "redirect:/valoracion/list";
        }

        @GetMapping("/borrar/{id}")
        public String showDelete(@PathVariable long id) {
            if(productoService.findByCategory(id).size() == 0){
                valoracionService.borrar(id);
                return "redirect:/valoracion/list";
            }
            return "redirect:/valoracion/";
            
        }


    
}