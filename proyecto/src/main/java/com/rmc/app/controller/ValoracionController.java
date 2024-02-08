package com.rmc.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rmc.app.domain.Producto;
import com.rmc.app.domain.Valoracion;
import com.rmc.app.service.ProductoService;
import com.rmc.app.service.UsuarioService;
import com.rmc.app.service.ValoracionService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/valoracion")
public class ValoracionController {

    @Autowired
    public ValoracionService valoracionService;
    @Autowired
    public ProductoService productoService;
    @Autowired
    UsuarioService usuarioService;

    @GetMapping({"/producto/{idProd}" })
    public String showProducto(@PathVariable long idProd, Model model) {
        model.addAttribute("listaValoracion", valoracionService.obtenerPorProducto(idProd));
        return "valoracionView/ListValView";
    }

    // Lista con las valoraciones que ha hecho el usuario
    @GetMapping({"/usuario/{idUsuario}" })
    public String showUsuario(@PathVariable long idUsuario, Model model) {
        model.addAttribute("listaValoracion", valoracionService.obtenerPorUsuario(idUsuario));
        return "ValoracionView/ListValView";
    }

    @GetMapping("/nuevo/{idProducto}")
    public String showNuevo(@PathVariable  long idProducto, Model model) {
        Producto producto = productoService.obtenerPorId(idProducto);
        //Usuario usuario = usuarioService.obtenerUsuarioConectado();
        model.addAttribute("valoracionForm", new Valoracion(0L, null,null,null,null,producto));
        model.addAttribute("producto", producto);
        return "valoracionView/ValFormNew";
    }

    @PostMapping("/nuevo/submit")
    public String showNuevoSubmit(
            @Valid Valoracion valoracionForm,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "redirect:/valoracion/nuevo";
        valoracionService.añadir(valoracionForm);
        return "redirect:/valoracion/producto/" +valoracionForm.getProducto().getId();
    }

    // @PostMapping("/editar/submit")
    // public String showEditSubmit(
    //         @Valid Valoracion valoracionForm,
    //         BindingResult bindingResult) {
    //     if (bindingResult.hasErrors())
    //         return "redirect:/valoracion/editar/{id}";
    //     valoracionService.editar(valoracionForm);
    //     return "redirect:/valoracion/list";
    // }

    // @GetMapping("/editar/{id}")
    // public String showEditForm(@PathVariable long id, Model model) {
    //     Valoracion valoracion = valoracionService.obtenerPorId(id);
    //     // el commandobject del formulario es el empleado con el id solicitado
    //     if (valoracion != null) {
    //         model.addAttribute("ValoracionForm", valoracion);
    //         return "valoracionView/ValFormEdit";
    //     }
    //     // si no lo encuentra vuelve a la página de inicio.
    //     return "redirect:/valoracion/list";
    // }

    @GetMapping("/borrar/{idValoracion}")
    public String showDelete(@PathVariable long idValoracion) {
        Valoracion valoracion = valoracionService.obtenerPorId(idValoracion);
        if (valoracion != null) {
            valoracionService.borrar(idValoracion);
            
        }
        return "redirect:/valoracion/producto/"+ valoracion.getProducto().getId();
        //valoracion puede dar null, revisar por que
    }

}