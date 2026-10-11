package com.laboratorio.laboratoriobackend.dto;

import jakarta.validation.constraints.NotNull;

public record CategoriaDTO (
        Long id,
        @NotNull String nombre,
        String descripcion
){
}
