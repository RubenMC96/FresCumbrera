package com.rmc.app.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rmc.app.domain.Compra;
import com.rmc.app.service.CompraService;
import jakarta.validation.Valid;




@Controller
@RequestMapping("/compra") 
public class CompraController {

    @Autowired
    public CompraService compraService;

        @GetMapping({"/", "/list"})
        public String showList(Model model){
            model.addAttribute("listacompra", compraService.obtenerLista());
            return "compraView/ListCatView";
        }
        @GetMapping("/nuevo")
        public String showNuevo(Model model){
            model.addAttribute("compraForm", new Compra());
            return "compraView/CatFormNew";
        }
        @PostMapping("/nuevo/submit")
        public String showNuevoSubmit (
            @Valid Compra compraForm,
            BindingResult bindingResult){
                if(bindingResult.hasErrors())
                    return "redirect:/compra/nuevo";
                compraService.añadir(compraForm);
                    return "redirect:/compra/list";
        }
        @PostMapping("/editar/submit")
        public String showEditSubmit (
            @Valid Compra compraForm,
            BindingResult bindingResult){
                if(bindingResult.hasErrors())
                    return "redirect:/compra/editar/{id}";
                compraService.editar(compraForm);
                    return "redirect:/compra/list";
        }

        @GetMapping("/editar/{id}")
        public String showEditForm(@PathVariable long id, Model model) {
            Compra compra = compraService.obtenerPorId(id);
            // el commandobject del formulario es el empleado con el id solicitado
            if (compra != null) {
                model.addAttribute("compraForm", compra);
                return "compraView/CatFormEdit";
            }
            // si no lo encuentra vuelve a la página de inicio.
            return "redirect:/compra/list";
        }

    
}