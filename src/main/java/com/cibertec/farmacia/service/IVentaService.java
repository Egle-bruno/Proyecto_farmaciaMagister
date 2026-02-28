package com.cibertec.farmacia.service;

import java.util.List;
import com.cibertec.farmacia.model.Venta;
import com.cibertec.farmacia.model.DetalleVenta;

public interface IVentaService {
    List<Venta> listarVentas();
    void realizarVenta(Venta venta, List<DetalleVenta> detalles);
    Venta buscarPorId(Integer id);
    // Método indispensable para el reporte de boleta
    Venta obtenerVentaConDetalles(Integer idVenta);
}