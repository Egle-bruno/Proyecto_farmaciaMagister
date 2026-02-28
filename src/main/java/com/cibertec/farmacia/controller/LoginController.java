package com.cibertec.farmacia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    // Carga la pantalla de Login
    @GetMapping("/")
    public String mostrarLogin() {
        return "login"; 
    }

    // Procesa el ingreso
    @PostMapping("/login")
    public String procesarLogin(@RequestParam String username, @RequestParam String password) {
        
        // Validación exacta con tus datos
        if ("bruno".equals(username) && "123456".equals(password)) {
            return "redirect:/home";
        }
        
        // Si falla, te regresa al login y le añade "?error" a la URL
        return "redirect:/?error"; 
    }
}