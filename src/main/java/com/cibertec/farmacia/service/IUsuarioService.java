package com.cibertec.farmacia.service;
import java.util.List;
import com.cibertec.farmacia.model.Usuario;
public interface IUsuarioService {
    List<Usuario> listarUsuarios();
    void guardar(Usuario usuario);
}