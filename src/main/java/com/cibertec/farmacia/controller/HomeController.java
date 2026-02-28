package com.cibertec.farmacia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // Borramos el GetMapping("/") de aquí para que no choque con el Login
    @GetMapping("/home")
    public String home() {
        return "home"; // Tu menú de tarjetas
    }
}