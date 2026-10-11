package com.laboratorio.laboratoriobackend.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record ProductoDTO (
        Long id,
        String nombre,
        String descripcion,
        @NotNull @PositiveOrZero BigDecimal precio,
        @NotNull @PositiveOrZero Integer cantidad,
        @NotNull Long categoriaId
)
        {

        }
