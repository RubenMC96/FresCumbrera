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
import com.rmc.app.domain.Rol;
import com.rmc.app.domain.Usuario;
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

    @GetMapping({ "/producto/{idProd}" })
    public String showProducto(@PathVariable long idProd, Model model) {
        Producto producto = productoService.obtenerPorId(idProd);
        model.addAttribute("listaValoracion", valoracionService.obtenerPorProducto(idProd));
        model.addAttribute("producto", producto);
        return "ValoracionView/ListValView";
    }

    // Lista con las valoraciones que ha hecho el usuario
    @GetMapping({ "/usuario/{idUsuario}" })
    public String showUsuario(@PathVariable long idUsuario, Model model) {
        model.addAttribute("listaValoracion", valoracionService.obtenerPorUsuario(idUsuario));
        return "ValoracionView/ListValUsuView";
    }

    @GetMapping("/nuevo/{idProducto}")
    public String showNuevo(@PathVariable long idProducto, Model model) {
        Producto producto = productoService.obtenerPorId(idProducto);
        Usuario usuario = usuarioService.obtenerUsuarioConectado();
        Valoracion valoracion = valoracionService.crearValoracion(producto, usuario);
        model.addAttribute("valoracionForm", valoracion);
        //model.addAttribute("producto", producto);
        return "valoracionView/ValFormNew";
    }

    @PostMapping("/nuevo/submit")
    public String showNuevoSubmit(
            @Valid Valoracion valoracionForm,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "redirect:/valoracion/nuevo";
        valoracionService.añadir(valoracionForm);
        return "redirect:/valoracion/producto/" + valoracionForm.getProducto().getId();
    }

    @GetMapping("/borrar/{idValoracion}")
    public String showDelete(@PathVariable long idValoracion) {
        Usuario usuarioConectado = usuarioService.obtenerUsuarioConectado();
        Valoracion valoracion = valoracionService.obtenerPorId(idValoracion);
        if (usuarioConectado.getRol() == Rol.USER && usuarioConectado.getId() == valoracion.getUsuario().getId()) {
            if (valoracion != null) {
                valoracionService.borrar(idValoracion);

            }
        } else if (usuarioConectado.getRol() == Rol.ADMIN || usuarioConectado.getRol() == Rol.MANAGER) {
            if (valoracion != null) {
                valoracionService.borrar(idValoracion);

            }
        }

        return "redirect:/valoracion/producto/" + valoracion.getProducto().getId();
        // valoracion puede dar null, revisar por que
    }

}