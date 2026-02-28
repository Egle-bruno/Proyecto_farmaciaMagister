package com.cibertec.farmacia.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cibertec.farmacia.model.Usuario;
import com.cibertec.farmacia.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private UsuarioRepository repo;

    @Override
    public List<Usuario> listarUsuarios() {
        return (List<Usuario>) repo.findAll();
    }

    @Override
    public void guardar(Usuario usuario) {
        repo.save(usuario);
    }
}