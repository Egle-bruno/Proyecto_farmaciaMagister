package com.cibertec.farmacia.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cibertec.farmacia.model.Proveedor;
import com.cibertec.farmacia.repository.ProveedorRepository;

@Service
public class ProveedorServiceImpl implements IProveedorService {

    @Autowired
    private ProveedorRepository repo;

    @Override
    public List<Proveedor> listarProveedores() {
        return (List<Proveedor>) repo.findAll();
    }

    @Override
    public void guardar(Proveedor proveedor) {
        repo.save(proveedor);
    }

    @Override
    public Proveedor buscarPorId(Integer id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public void eliminar(Integer id) {
        repo.deleteById(id);
    }
}