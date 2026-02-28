package com.cibertec.farmacia.service;
import java.util.List;
import com.cibertec.farmacia.model.Producto;
public interface IProductoService {
    List<Producto> listarProductos();
    void guardar(Producto producto);
    Producto buscarPorId(Integer id);
    void eliminar(Integer id);
}