package com.laboratorio.laboratoriobackend.mappers;

import com.laboratorio.laboratoriobackend.dto.CategoriaDTO;
import com.laboratorio.laboratoriobackend.model.Categoria;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoriaMapper {

    CategoriaDTO toDto(Categoria categoria);
    Categoria toEntity(CategoriaDTO categoriaDTO);

    void updateEntity(CategoriaDTO categoriaDTO, @MappingTarget Categoria categoria);

}
