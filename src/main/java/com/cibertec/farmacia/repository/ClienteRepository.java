package com.cibertec.farmacia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cibertec.farmacia.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}