package com.cibertec.farmacia.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cibertec.farmacia.model.*;
import com.cibertec.farmacia.repository.*;

@Service
public class VentaServiceImpl implements IVentaService {

    @Autowired private VentaRepository ventaRepo;
    @Autowired private ProductoRepository productoRepo;

    @Override
    public List<Venta> listarVentas() {
        return (List<Venta>) ventaRepo.findAll();
    }

    @Override
    @Transactional
    public void realizarVenta(Venta venta, List<DetalleVenta> detalles) {
        // 1. Vinculamos los detalles con la venta y actualizamos stock
        for (DetalleVenta det : detalles) {
            det.setVenta(venta); // Asociación bidireccional
            
            Producto prod = productoRepo.findById(det.getProducto().getIdProducto())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            if (prod.getStock() >= det.getCantidad()) {
                prod.setStock(prod.getStock() - det.getCantidad());
                productoRepo.save(prod); // Actualiza stock en DB
            } else {
                throw new RuntimeException("Stock insuficiente para: " + prod.getNombre());
            }
        }

        // 2. Metemos la lista de detalles dentro del objeto venta
        venta.setDetalles(detalles);

        // 3. Guardamos la venta (esto guardará automáticamente los detalles por el CASCADE)
        ventaRepo.save(venta);
    }

    @Override
    public Venta buscarPorId(Integer id) {
        return ventaRepo.findById(id).orElse(null);
    }

    @Override
    public Venta obtenerVentaConDetalles(Integer idVenta) {
        return ventaRepo.findById(idVenta).orElse(null);
    }
}