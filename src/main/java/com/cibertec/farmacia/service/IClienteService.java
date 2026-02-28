package com.cibertec.farmacia.service;

import java.util.List;
import com.cibertec.farmacia.model.Cliente;

public interface IClienteService {
    public List<Cliente> listarClientes();
    public void guardar(Cliente cliente);
    public Cliente buscarPorId(Integer id);
    public void eliminar(Integer id);
    // Nuevo método para la consulta dinámica
    public List<Cliente> buscarPorDni(String dni);
}