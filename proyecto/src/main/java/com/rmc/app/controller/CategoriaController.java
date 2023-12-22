package com.rmc.app.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rmc.app.domain.Categoria;
import com.rmc.app.service.CategoriaService;
import com.rmc.app.service.ProductoService;

import jakarta.validation.Valid;




@Controller
@RequestMapping("/categoria") 
public class CategoriaController {

    @Autowired
    public CategoriaService categoriaService;
    @Autowired
    public ProductoService productoService;

        @GetMapping({"/", "/list"})
        public String showList(Model model){
            model.addAttribute("listaCategoria", categoriaService.obteberLista());
            return "CategoriaView/ListCatView";
        }
        @GetMapping("/nuevo")
        public String showNuevo(Model model){
            model.addAttribute("categoriaForm", new Categoria());
            return "CategoriaView/CatFormNew";
        }
        @PostMapping("/nuevo/submit")
        public String showNuevoSubmit (
            @Valid Categoria categoriaForm,
            BindingResult bindingResult){
                if(bindingResult.hasErrors())
                    return "redirect:/categoria/nuevo";
                categoriaService.añadir(categoriaForm);
                    return "redirect:/categoria/list";
        }
        @PostMapping("/editar/submit")
        public String showEditSubmit (
            @Valid Categoria categoriaForm,
            BindingResult bindingResult){
                if(bindingResult.hasErrors())
                    return "redirect:/categoria/editar/{id}";
                categoriaService.editar(categoriaForm);
                    return "redirect:/categoria/list";
        }

        @GetMapping("/editar/{id}")
        public String showEditForm(@PathVariable long id, Model model) {
            Categoria categoria = categoriaService.obtenerPorId(id);
            // el commandobject del formulario es el empleado con el id solicitado
            if (categoria != null) {
                model.addAttribute("categoriaForm", categoria);
                return "CategoriaView/CatFormEdit";
            }
            // si no lo encuentra vuelve a la página de inicio.
            return "redirect:/categoria/list";
        }

        @GetMapping("/borrar/{id}")
        public String showDelete(@PathVariable long id) {
            if(productoService.findByCategory(id).size() == 0){
                categoriaService.borrar(id);
                return "redirect:/categoria/list";
            }
            return "redirect:/categoria/";
            
        }


    
}