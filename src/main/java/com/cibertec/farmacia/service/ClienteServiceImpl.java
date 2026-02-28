package com.cibertec.farmacia.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cibertec.farmacia.model.Cliente;
import com.cibertec.farmacia.repository.IClienteRepository;

@Service
public class ClienteServiceImpl implements IClienteService {

    @Autowired
    private IClienteRepository clienteRepo;

    @Override
    public List<Cliente> listarClientes() { return clienteRepo.findAll(); }
    @Override
    public void guardar(Cliente cliente) { clienteRepo.save(cliente); }
    @Override
    public Cliente buscarPorId(Integer id) { return clienteRepo.findById(id).orElse(null); }
    @Override
    public void eliminar(Integer id) { clienteRepo.deleteById(id); }

    @Override
    public List<Cliente> buscarPorDni(String dni) {
        // Le pasamos el mismo parámetro a ambos campos para que busque en los dos
        return clienteRepo.findByNroDocContainingOrNombreContaining(dni, dni);
    }
}