package com.cibertec.farmacia.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.servlet.http.HttpServletResponse;

import com.cibertec.farmacia.model.Producto;
import com.cibertec.farmacia.model.Proveedor;
import com.cibertec.farmacia.service.IProductoService;
import com.cibertec.farmacia.service.IProveedorService;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private IProductoService productoService;

    @Autowired
    private IProveedorService proveedorService;

    @GetMapping
    public String listar(Model model) {
        List<Producto> misProductos = productoService.listarProductos(); 
        model.addAttribute("lista", misProductos); 
        model.addAttribute("productos", misProductos); 
        return "productos/lista"; 
    }

    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        Producto nuevoProducto = new Producto();
        nuevoProducto.setProveedor(new Proveedor()); // Soluciona el Error 500
        
        model.addAttribute("producto", nuevoProducto);
        model.addAttribute("proveedores", proveedorService.listarProveedores());
        return "productos/formulario"; 
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Integer id, Model model) {
        Producto p = productoService.buscarPorId(id);
        if (p == null) {
            return "redirect:/productos";
        }
        if (p.getProveedor() == null) {
            p.setProveedor(new Proveedor());
        }
        model.addAttribute("producto", p);
        model.addAttribute("proveedores", proveedorService.listarProveedores());
        return "productos/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("producto") Producto producto, RedirectAttributes flash) {
        try {
            productoService.guardar(producto);
            flash.addFlashAttribute("success", "Producto guardado correctamente.");
        } catch (Exception e) {
            flash.addFlashAttribute("error", "Error al guardar el producto.");
        }
        return "redirect:/productos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Integer id, RedirectAttributes flash) {
        try {
            productoService.eliminar(id);
            flash.addFlashAttribute("success", "Producto eliminado correctamente.");
        } catch (Exception e) {
            flash.addFlashAttribute("error", "No se puede eliminar: El producto tiene ventas registradas.");
        }
        return "redirect:/productos";
    }

    // EL PDF ESTÁ AQUÍ LISTO Y COMPLETO
    @GetMapping("/exportarPdf")
    public void exportarPdf(HttpServletResponse response) {
        try {
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=Inventario_Productos.pdf");

            com.lowagie.text.Document document = new com.lowagie.text.Document();
            com.lowagie.text.pdf.PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            com.lowagie.text.Font fontTitulo = com.lowagie.text.FontFactory.getFont(com.lowagie.text.FontFactory.HELVETICA_BOLD, 18);
            com.lowagie.text.Paragraph titulo = new com.lowagie.text.Paragraph("Inventario de Productos", fontTitulo);
            titulo.setAlignment(com.lowagie.text.Element.ALIGN_CENTER);
            document.add(titulo);
            document.add(new com.lowagie.text.Paragraph(" "));

            com.lowagie.text.pdf.PdfPTable tabla = new com.lowagie.text.pdf.PdfPTable(4);
            tabla.addCell("ID");
            tabla.addCell("Nombre");
            tabla.addCell("Precio (S/)");
            tabla.addCell("Stock");

            List<Producto> listaProductos = productoService.listarProductos();
            for (Producto p : listaProductos) {
                tabla.addCell(String.valueOf(p.getIdProducto()));
                tabla.addCell(p.getNombre());
                tabla.addCell(String.valueOf(p.getPrecioVenta()));
                tabla.addCell(String.valueOf(p.getStock()));
            }

            document.add(tabla);
            document.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}