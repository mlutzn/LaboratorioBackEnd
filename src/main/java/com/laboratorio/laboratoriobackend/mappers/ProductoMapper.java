package com.laboratorio.laboratoriobackend.mappers;

import com.laboratorio.laboratoriobackend.dto.ProductoDTO;
import com.laboratorio.laboratoriobackend.model.Producto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductoMapper {

    @Mapping(source = "categoria.id", target = "categoriaId")
    ProductoDTO toDto(Producto producto);

    @Mapping(target = "categoria", ignore = true)
    Producto toEntity(ProductoDTO productoDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "categoria", ignore = true)
    void updateEntity(ProductoDTO productoDTO, @MappingTarget Producto producto);
}
