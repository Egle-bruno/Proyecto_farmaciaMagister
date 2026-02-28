package com.cibertec.farmacia.service;
import java.util.List;
import com.cibertec.farmacia.model.Proveedor;

public interface IProveedorService {
    // Asegúrate de que se llame así exactamente
    List<Proveedor> listarProveedores(); 
    void guardar(Proveedor proveedor);
    Proveedor buscarPorId(Integer id);
    void eliminar(Integer id);
}