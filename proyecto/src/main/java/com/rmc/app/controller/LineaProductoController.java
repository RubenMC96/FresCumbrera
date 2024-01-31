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
import com.rmc.app.domain.LineaProducto;
import com.rmc.app.domain.Producto;
import com.rmc.app.service.CompraService;
import com.rmc.app.service.LineaProductoService;
import com.rmc.app.service.ProductoService;
import com.rmc.app.service.UsuarioService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/lineaProducto")
public class LineaProductoController {

    @Autowired
    public CompraService compraService;
    @Autowired
    public ProductoService productoService;
    @Autowired
    public LineaProductoService lineaProductoService;
    @Autowired
    public UsuarioService usuarioService;

    @GetMapping({ "/list/{id}" })
    public String showList(@PathVariable long id, Model model) {
        Compra compra = compraService.obtenerPorId(id);
        LineaProducto lineaProducto = lineaProductoService.obtenerPorCompra(compra);
        model.addAttribute("listaLineaProducto", lineaProducto);
        return "LineaProductoView/ListLineaProductoView";
    }

    @GetMapping("/annadir/{id}")
    public String showAñadir(@PathVariable long productoId, @PathVariable Integer cantidadProductos, Model model) {
        Producto producto = productoService.obtenerPorId(productoId);
        lineaProductoService.annadir(productoId, cantidadProductos);
        model.addAttribute("lineaProductoForm", producto);
        model.addAttribute("cantidad", cantidadProductos);
        return "redirect:/lineaProducto/list";
    }

    @PostMapping("/editar/submit")
    public String showEditSubmit(
            @Valid LineaProducto lineaProductoForm,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "redirect:/lineaProducto/editar/{id}";
        lineaProductoService.editar(lineaProductoForm);
        return "redirect:/lineaProducto/list";
    }

    @GetMapping("/editar/{id}")
    public String showEditForm(@PathVariable long id, Model model) {
        LineaProducto lineaProducto = lineaProductoService.obtenerPorId(id);
        // el commandobject del formulario es el empleado con el id solicitado
        if (lineaProducto != null) {
            model.addAttribute("lineaProductoForm", lineaProducto);
            return "lineaProductoView/lineaProductoFormEdit";
        }
        // si no lo encuentra vuelve a la página de inicio.
        return "redirect:/lineaProducto/list";
    }

    @GetMapping("/borrar/{id}")
    public String showDelete(@PathVariable long id) {
        lineaProductoService.borrar(id);
        return "redirect:/lineaProducto/list";

    }

    @GetMapping("/borrar")
    public String showDeleteAll(Model model) {
        lineaProductoService.borrarTodo();
        model.addAttribute("mensaje", "La lineaProducto está vacía");
        return "redirect:/lineaProducto/list";

    }

}