package com.laboratorio.laboratoriobackend.repository;

import com.laboratorio.laboratoriobackend.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long>{
}
