
package com.levelupgamer.backend.controller;

import com.levelupgamer.backend.dto.CrearProductoRequest;
import com.levelupgamer.backend.model.Producto;
import com.levelupgamer.backend.service.ProductoService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    // GET: Obtener todos los productos
    @GetMapping
    public List<Producto> listarProductos() {
        return productoService.listarProductos();
    }

    // GET: Buscar un producto por código
    @GetMapping("/{codigo}")
    public ResponseEntity<Producto> buscarProducto(
            @PathVariable String codigo) {

        return productoService.buscarPorCodigo(codigo)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST: Crear un producto nuevo
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Producto crearProducto(
            @Valid @RequestBody CrearProductoRequest datos) {

        return productoService.crearProducto(datos);
    }

    // PUT: Actualizar un producto por código
    @PutMapping("/{codigo}")
    public Producto actualizarProducto(
            @PathVariable String codigo,
            @Valid @RequestBody CrearProductoRequest datos) {

        return productoService.actualizarProducto(codigo, datos);
    }

    // DELETE: Eliminar un producto por código
    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarProducto(@PathVariable String codigo) {
        productoService.eliminarProducto(codigo);
    }


}
