package com.cibertec.farmacia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cibertec.farmacia.model.Usuario;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    
    // Método para buscar un usuario por su nombre (útil para el login)
    Optional<Usuario> findByUsername(String username);
}