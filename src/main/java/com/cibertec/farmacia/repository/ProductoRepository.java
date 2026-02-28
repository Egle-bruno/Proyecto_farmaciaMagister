package com.cibertec.farmacia.repository;

import org.springframework.data.repository.CrudRepository;
import com.cibertec.farmacia.model.Producto;

public interface ProductoRepository extends CrudRepository<Producto, Integer> {
    // Cuenta los productos con stock crítico para el Dashboard
    long countByStockLessThan(Integer limite);
}