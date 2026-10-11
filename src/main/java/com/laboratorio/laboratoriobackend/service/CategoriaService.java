package com.laboratorio.laboratoriobackend.service;

import com.laboratorio.laboratoriobackend.model.Categoria;
import com.laboratorio.laboratoriobackend.repository.CategoriaRepository;
import com.laboratorio.laboratoriobackend.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final ProductoRepository productoRepository;

    public CategoriaService(CategoriaRepository categoriaRepository, ProductoRepository productoRepository) {
        this.categoriaRepository = categoriaRepository;
        this.productoRepository = productoRepository;
    }

    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    public Optional<Categoria> findById(
            Long id
    ) {
        return categoriaRepository.findById(id);
    }
    public Categoria create(
            Categoria categoria
    ) {
        categoria.setId(null);
        return categoriaRepository.save(categoria);
    }

    @Transactional
    public Optional<Categoria> update(Long id, Categoria datos) {
        return categoriaRepository.findById(id).map(categoria -> {
            categoria.setNombre(datos.getNombre());
            categoria.setDescripcion(datos.getDescripcion());
            return categoriaRepository.save(categoria);
        });
    }

    @Transactional
    public boolean delete(Long id) {
        if (!categoriaRepository.existsById(id)) {
            return false;
        }
        categoriaRepository.deleteById(id);
        return true;
    }
    public boolean tieneProductos(Long id) {
        return productoRepository.existsByCategoriaId(id);
    }
}
