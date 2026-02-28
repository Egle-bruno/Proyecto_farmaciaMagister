package com.cibertec.farmacia.service;

import java.util.List;
import com.cibertec.farmacia.model.Venta;

public interface VentaService {
    
    // EL CAMBIO CLAVE: Cambiamos 'void' por 'Venta'
    // Esto es lo que quita las líneas rojas del Controller
    Venta guardar(Venta venta);
    
    // Este método es necesario para que el Controller muestre la boleta
    Venta buscarPorId(Integer id);

    // MANTÉN LOS SIGUIENTES SI YA LOS TENÍAS (No los borres):
    List<Venta> listarTodas();
    // void eliminar(Integer id); (Si lo tenías, déjalo)
}