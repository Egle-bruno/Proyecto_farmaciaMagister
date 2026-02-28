package com.cibertec.farmacia.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cibertec.farmacia.model.Proveedor;
import com.cibertec.farmacia.service.IProveedorService;

@Controller
@RequestMapping("/proveedores")
public class ProveedorController {

    @Autowired
    private IProveedorService proveedorService;

    @GetMapping
    public String listar(Model model) {
        List<Proveedor> lista = proveedorService.listarProveedores();
        model.addAttribute("lista", lista);
        return "proveedores/lista";
    }

    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("proveedor", new Proveedor());
        return "proveedores/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("proveedor") Proveedor proveedor, RedirectAttributes flash) {
        try {
            proveedorService.guardar(proveedor);
            flash.addFlashAttribute("success", "Proveedor guardado correctamente.");
        } catch (Exception e) {
            flash.addFlashAttribute("error", "Error al guardar el proveedor.");
        }
        return "redirect:/proveedores";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Integer id, Model model) {
        Proveedor p = proveedorService.buscarPorId(id);
        model.addAttribute("proveedor", p);
        return "proveedores/formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Integer id, RedirectAttributes flash) {
        try {
            proveedorService.eliminar(id);
            flash.addFlashAttribute("success", "Proveedor eliminado con éxito.");
        } catch (Exception e) {
            flash.addFlashAttribute("error", "No se puede eliminar: El proveedor tiene registros asociados.");
        }
        return "redirect:/proveedores";
    }
}