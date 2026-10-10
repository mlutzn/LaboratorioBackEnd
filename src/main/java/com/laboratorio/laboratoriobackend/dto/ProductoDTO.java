package com.laboratorio.laboratoriobackend.dto;

import java.math.BigDecimal;

public record ProductoDTO (
        Long id,
        String nombre,
        String descripcion,
        BigDecimal precio,
        Integer cantidad,
        Long categoriaId
)
        {

        }
