package com.laboratorio.laboratoriobackend.controllers;

import com.laboratorio.laboratoriobackend.dto.ProductoDTO;
import com.laboratorio.laboratoriobackend.mappers.ProductoMapper;
import com.laboratorio.laboratoriobackend.model.Producto;
import com.laboratorio.laboratoriobackend.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/producto")
public class ProductoController {

   private final ProductoService productoService;
   private final ProductoMapper productoMapper;

   public ProductoController(ProductoService productoService, ProductoMapper productoMapper) {
       this.productoService = productoService;
       this.productoMapper = productoMapper;
   }

        @PreAuthorize("hasAuthority('producto:leer')")
        @GetMapping
        public List<ProductoDTO> findAll() {
            return productoMapper.toDtoList(productoService.findAll());
        }


        @PreAuthorize("hasAuthority('producto:leer')")
        @GetMapping("/{id}")
        public ResponseEntity<ProductoDTO> findById(@PathVariable Long id) {
            return ResponseEntity.of(productoService.findById(id).map(productoMapper::toDto));
        }
    @PreAuthorize("hasAuthority('producto:escribir')")
    @PostMapping
    public ResponseEntity<ProductoDTO> create(@Valid @RequestBody ProductoDTO productoDTO) {
        return productoService.create(productoMapper.toEntity(productoDTO), productoDTO.categoriaId())
                .map(creado -> {
                    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                            .path("/{id}")
                            .buildAndExpand(creado.getId())
                            .toUri();
                    return ResponseEntity.created(location).body(productoMapper.toDto(creado));
                })
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

        @PreAuthorize("hasAuthority('producto:escribir')")
        @PutMapping("/{id}")
        public ResponseEntity<ProductoDTO> update(@PathVariable Long id, @Valid @RequestBody ProductoDTO productoDTO) {
            return ResponseEntity.of(
                    productoService.update(id, productoMapper.toEntity(productoDTO), productoDTO.categoriaId())
                            .map(productoMapper::toDto));
        }

        @PreAuthorize("hasAuthority('producto:eliminar')")
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> delete(@PathVariable Long id) {
            return productoService.delete(id)
                    ? ResponseEntity.noContent().build()
                    : ResponseEntity.notFound().build();
        }
}
