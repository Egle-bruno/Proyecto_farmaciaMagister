package com.cibertec.farmacia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cibertec.farmacia.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
}