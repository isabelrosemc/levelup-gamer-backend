
package com.levelupgamer.backend.service;

import com.levelupgamer.backend.dto.CrearProductoRequest;
import com.levelupgamer.backend.model.Categoria;
import com.levelupgamer.backend.model.Producto;
import com.levelupgamer.backend.repository.CategoriaRepository;
import com.levelupgamer.backend.repository.ProductoRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProductoService(
            ProductoRepository productoRepository,
            CategoriaRepository categoriaRepository) {

        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    // Obtener todos los productos
    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    // Buscar un producto por su código
    public Optional<Producto> buscarPorCodigo(String codigo) {
        return productoRepository.findByCodigo(codigo);
    }

    // Crear un producto nuevo
    public Producto crearProducto(CrearProductoRequest datos) {

        String codigo = datos.codigo().trim();

        // Comprobar que el código no esté repetido
        if (productoRepository.existsByCodigo(codigo)) {
            throw new ResponseStatusException(
                HttpStatus.CONFLICT,
                "Ya existe un producto con el código " + codigo
            );
        }

        // Buscar la categoría en MariaDB
        Categoria categoria = categoriaRepository
            .findById(datos.categoriaId())
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "La categoría indicada no existe"
            ));

        // Crear el objeto que guardaremos
        Producto producto = new Producto();

        producto.setCodigo(codigo);
        producto.setNombre(datos.nombre().trim());
        producto.setDescripcion(datos.descripcion());
        producto.setPrecio(datos.precio());
        producto.setStock(datos.stock());
        producto.setStockCritico(datos.stockCritico());
        producto.setDescuentoOferta(datos.descuentoOferta());
        producto.setRating(datos.rating());
        producto.setImagen(datos.imagen());

        // Asociar el producto con su categoría
        producto.setCategoria(categoria);

        // Guardar en MariaDB
        return productoRepository.save(producto);
    }

    // Actualizar un producto existente
    public Producto actualizarProducto(
            String codigoActual,
            CrearProductoRequest datos) {

        // Buscar el producto que queremos modificar
        Producto producto = productoRepository
            .findByCodigo(codigoActual)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "El producto no existe"
            ));

        String codigoNuevo = datos.codigo().trim();

        // Evitar usar el código de otro producto
        if (!producto.getCodigo().equals(codigoNuevo)
                && productoRepository.existsByCodigo(codigoNuevo)) {

            throw new ResponseStatusException(
                HttpStatus.CONFLICT,
                "Ya existe otro producto con ese código"
            );
        }

        // Comprobar la categoría
        Categoria categoria = categoriaRepository
            .findById(datos.categoriaId())
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "La categoría indicada no existe"
            ));

        // Actualizar los atributos
        producto.setCodigo(codigoNuevo);
        producto.setNombre(datos.nombre().trim());
        producto.setDescripcion(datos.descripcion());
        producto.setPrecio(datos.precio());
        producto.setStock(datos.stock());
        producto.setStockCritico(datos.stockCritico());
        producto.setDescuentoOferta(datos.descuentoOferta());
        producto.setRating(datos.rating());
        producto.setImagen(datos.imagen());
        producto.setCategoria(categoria);

        // Guardar los cambios en MariaDB
        return productoRepository.save(producto);
    }

    // Eliminar un producto por su código
    public void eliminarProducto(String codigo) {

        // Buscar el producto existente
        Producto producto = productoRepository
            .findByCodigo(codigo)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "El producto no existe"
            ));

        // Eliminarlo de MariaDB
        productoRepository.delete(producto);
    }


}
