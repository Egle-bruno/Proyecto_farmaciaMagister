package com.cibertec.farmacia.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.cibertec.farmacia.model.*;
import com.cibertec.farmacia.service.*;

@Controller
@RequestMapping("/ventas")
public class VentaController {

    @Autowired private IVentaService ventaService;
    @Autowired private IClienteService clienteService;
    @Autowired private IProductoService productoService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("lista", ventaService.listarVentas());
        return "ventas/lista";
    }

    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("clientes", clienteService.listarClientes());
        model.addAttribute("productos", productoService.listarProductos());
        return "ventas/form_venta";
    }

    @PostMapping("/guardar")
    public String guardar(@RequestParam("clienteId") Integer clienteId,
                         @RequestParam("productoId") Integer productoId,
                         @RequestParam("cantidad") Integer cantidad,
                         RedirectAttributes flash) {
        try {
            Producto prod = productoService.buscarPorId(productoId);
            
            if (prod == null) {
                flash.addFlashAttribute("error", "El producto no existe.");
                return "redirect:/ventas/nuevo";
            }

            if (cantidad > prod.getStock()) {
                flash.addFlashAttribute("error", "Stock insuficiente. Solo hay " + prod.getStock() + " unidades.");
                return "redirect:/ventas/nuevo";
            }

            // Crear cabecera
            Venta venta = new Venta();
            venta.setCliente(clienteService.buscarPorId(clienteId));
            venta.setFechaVenta(LocalDateTime.now());
            venta.setTotal(prod.getPrecioVenta() * cantidad);

            // Crear detalle
            List<DetalleVenta> listaDetalles = new ArrayList<>();
            DetalleVenta det = new DetalleVenta();
            det.setProducto(prod);
            det.setCantidad(cantidad);
            det.setPrecioUnitario(prod.getPrecioVenta());
            det.setSubtotal(prod.getPrecioVenta() * cantidad);
            // No seteamos la venta aquí, lo hará el Service
            listaDetalles.add(det);

            // Procesar
            ventaService.realizarVenta(venta, listaDetalles);
            flash.addFlashAttribute("success", "Venta registrada con éxito y stock actualizado.");
            
        } catch (Exception e) {
            flash.addFlashAttribute("error", "Error: " + e.getMessage());
            return "redirect:/ventas/nuevo";
        }
        return "redirect:/ventas";
    }

    @GetMapping("/detalle/{id}")
    public String verDetalle(@PathVariable("id") Integer id, Model model) {
        Venta v = ventaService.obtenerVentaConDetalles(id);
        if (v == null) return "redirect:/ventas";
        
        model.addAttribute("venta", v);
        model.addAttribute("detalles", v.getDetalles());
        return "ventas/detalle_boleta";
    }
}