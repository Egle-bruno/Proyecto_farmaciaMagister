package com.cibertec.farmacia.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cibertec.farmacia.model.Cliente;
import com.cibertec.farmacia.service.IClienteService;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private IClienteService clienteService;

    // 1. LISTADO Y BÚSQUEDA (DNI o Nombre)
    @GetMapping
    public String listar(@RequestParam(name = "keyword", required = false) String keyword, Model model) {
        List<Cliente> lista = clienteService.listarClientes();
        
        if (keyword != null && !keyword.trim().isEmpty()) {
            String busca = keyword.trim().toLowerCase();
            lista = lista.stream()
                .filter(c -> (c.getNroDoc() != null && c.getNroDoc().contains(busca)) || 
                             (c.getNombre() != null && c.getNombre().toLowerCase().contains(busca)))
                .collect(Collectors.toList());
            model.addAttribute("keyword", keyword);
        }
        
        model.addAttribute("lista", lista);
        return "clientes/lista"; 
    }

    // 2. MOSTRAR FORMULARIO PARA NUEVO
    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "clientes/formulario"; 
    }

    // 3. MOSTRAR FORMULARIO PARA EDITAR
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Integer id, Model model) {
        Cliente c = clienteService.buscarPorId(id);
        if (c == null) {
            return "redirect:/clientes";
        }
        model.addAttribute("cliente", c);
        return "clientes/formulario";
    }

    // 4. GUARDAR CLIENTE (CORREGIDO PARA EVITAR ERRORES DE BASE DE DATOS)
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("cliente") Cliente cliente, RedirectAttributes flash) {
        try {
            // SEGURIDAD: Si tipoDoc no viene del HTML, le ponemos "DNI" 
            // Esto evita que la base de datos rechace el insert por ser nulo.
            if (cliente.getTipoDoc() == null || cliente.getTipoDoc().isEmpty()) {
                cliente.setTipoDoc("DNI");
            }

            clienteService.guardar(cliente);
            flash.addFlashAttribute("success", "¡Cliente guardado con éxito!");
            System.out.println("LOG: Cliente guardado correctamente -> " + cliente.getNombre());
            
        } catch (Exception e) {
            System.err.println("LOG: Error al guardar cliente: " + e.getMessage());
            flash.addFlashAttribute("error", "Error al guardar: " + e.getMessage());
        }
        return "redirect:/clientes";
    }

    // 5. ELIMINAR CLIENTE
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Integer id, RedirectAttributes flash) {
        try {
            clienteService.eliminar(id);
            flash.addFlashAttribute("success", "Cliente eliminado correctamente.");
        } catch (Exception e) {
            flash.addFlashAttribute("error", "No se puede eliminar el cliente porque tiene registros asociados.");
        }
        return "redirect:/clientes";
    }
}