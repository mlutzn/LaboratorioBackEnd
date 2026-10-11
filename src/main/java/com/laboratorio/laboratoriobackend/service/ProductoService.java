package com.laboratorio.laboratoriobackend.service;

import com.laboratorio.laboratoriobackend.model.Producto;
import com.laboratorio.laboratoriobackend.repository.CategoriaRepository;
import com.laboratorio.laboratoriobackend.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProductoService(ProductoRepository productoRepository, CategoriaRepository categoriaRepository) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    public Optional<Producto> findById(
            Long id
    ) {
        return productoRepository.findById(id);
    }

    public Optional<Producto> create(Producto producto, Long categoriaId) {
        return categoriaRepository.findById(categoriaId).map(categoria -> {
            producto.setId(null);
            producto.setCategoria(categoria);
            return productoRepository.save(producto);
        });
    }

    @Transactional
    public Optional<Producto> update(Long id, Producto datos, Long categoriaId) {
        return productoRepository.findById(id).flatMap(producto ->
                categoriaRepository.findById(categoriaId).map(categoria -> {
                    producto.setNombre(datos.getNombre());
                    producto.setDescripcion(datos.getDescripcion());
                    producto.setPrecio(datos.getPrecio());
                    producto.setCantidad(datos.getCantidad());
                    producto.setCategoria(categoria);
                    return productoRepository.save(producto);
                }));
    }

    @Transactional
    public boolean delete(Long id) {
        if (!productoRepository.existsById(id)) {
            return false;
        }
        productoRepository.deleteById(id);
        return true;
    }
}
