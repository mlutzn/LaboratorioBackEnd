package com.laboratorio.laboratoriobackend.controllers;

import com.laboratorio.laboratoriobackend.dto.CategoriaDTO;
import com.laboratorio.laboratoriobackend.mappers.CategoriaMapper;
import com.laboratorio.laboratoriobackend.model.Categoria;
import com.laboratorio.laboratoriobackend.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/categoria")
public class CategoriaController {

    private final CategoriaService categoriaService;
    private final CategoriaMapper categoriaMapper;

    public CategoriaController(CategoriaService categoriaService, CategoriaMapper categoriaMapper) {
        this.categoriaService = categoriaService;
        this.categoriaMapper = categoriaMapper;
    }

    @PreAuthorize("hasAuthority('categoria:leer')")
    @GetMapping
    public List<CategoriaDTO> findAll() {
        return categoriaMapper.toDtoList(categoriaService.findAll());
    }

    @PreAuthorize("hasAuthority('categoria:leer')")
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> findById(@PathVariable Long id) {
        return ResponseEntity.of(categoriaService.findById(id).map(categoriaMapper::toDto));
    }
    @PreAuthorize("hasAuthority('categoria:escribir')")
    @PostMapping()
    public ResponseEntity<CategoriaDTO> create(@Valid @RequestBody CategoriaDTO categoriaDTO) {
        Categoria creado = categoriaService.create(categoriaMapper.toEntity(categoriaDTO));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(creado.getId())
                .toUri();
        return ResponseEntity.created(location).body(categoriaMapper.toDto(creado));
    }

    @PreAuthorize("hasAuthority('categoria:escribir')")
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> update(@PathVariable Long id, @Valid @RequestBody CategoriaDTO categoriaDTO) {
        return ResponseEntity.of(categoriaService.update(id, categoriaMapper.toEntity(categoriaDTO)).map(categoriaMapper::toDto));
    }

    @PreAuthorize("hasAuthority('categoria:eliminar')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (categoriaService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();          // 404
        }
        if (categoriaService.tieneProductos(id)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();  // 409
        }
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();             // 204
    }
}
