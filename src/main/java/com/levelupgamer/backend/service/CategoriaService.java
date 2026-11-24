
package com.levelupgamer.backend.service;

import com.levelupgamer.backend.model.Categoria;
import com.levelupgamer.backend.repository.CategoriaRepository;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    // Obtener todas las categorías
    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }

    // Registrar una nueva categoría
    public Categoria crearCategoria(Categoria categoria) {

        // Verificar que el nombre no esté vacío
        if (categoria == null ||
            categoria.getNombre() == null ||
            categoria.getNombre().isBlank()) {

            throw new IllegalArgumentException(
                "El nombre de la categoría es obligatorio"
            );
        }

        String nombre = categoria.getNombre().trim();

        // Evitar nombres duplicados
        if (categoriaRepository.existsByNombre(nombre)) {
            throw new IllegalArgumentException(
                "Ya existe una categoría con ese nombre"
            );
        }

        // La base de datos genera el ID automáticamente
        categoria.setId(null);
        categoria.setNombre(nombre);

        return categoriaRepository.save(categoria);
    }
}
