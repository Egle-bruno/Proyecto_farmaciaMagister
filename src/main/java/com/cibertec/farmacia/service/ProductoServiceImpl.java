package com.cibertec.farmacia.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cibertec.farmacia.model.Producto;
import com.cibertec.farmacia.repository.ProductoRepository;

@Service
public class ProductoServiceImpl implements IProductoService {

    @Autowired
    private ProductoRepository repo;

    @Override
    public List<Producto> listarProductos() {
        return (List<Producto>) repo.findAll();
    }

    @Override
    public void guardar(Producto producto) {
        repo.save(producto);
    }

    @Override
    public Producto buscarPorId(Integer id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Integer id) {
        repo.deleteById(id);
    }
}