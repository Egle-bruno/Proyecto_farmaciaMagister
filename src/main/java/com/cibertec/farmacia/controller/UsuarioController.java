package com.cibertec.farmacia.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cibertec.farmacia.model.Usuario;
import com.cibertec.farmacia.service.IUsuarioService;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping
    public String listar(Model model) {
        List<Usuario> lista = usuarioService.listarUsuarios();
        model.addAttribute("lista", lista);
        return "usuarios/lista";
    }

    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "usuarios/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("usuario") Usuario usuario, RedirectAttributes flash) {
        try {
            usuarioService.guardar(usuario);
            flash.addFlashAttribute("success", "Usuario registrado con éxito.");
        } catch (Exception e) {
            flash.addFlashAttribute("error", "Error al registrar el usuario.");
        }
        return "redirect:/usuarios";
    }
}