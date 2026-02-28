package com.cibertec.farmacia.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cibertec.farmacia.model.Cliente;

@Repository
public interface IClienteRepository extends JpaRepository<Cliente, Integer> {
    
    // Al usar este nombre, Spring construye la consulta solo.
    // No lleva @Query, por lo tanto NO puede haber error rojo en Eclipse.
    List<Cliente> findByNroDocContainingOrNombreContaining(String nroDoc, String nombre);
}