package com.laboratorio.laboratoriobackend.repository;

import com.laboratorio.laboratoriobackend.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
