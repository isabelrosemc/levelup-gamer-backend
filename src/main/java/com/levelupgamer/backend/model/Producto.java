
    
  
package com.levelupgamer.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Codigo visible en la tienda, por ejemplo: JM001 o AC001.
    @NotBlank
    @Size(max = 20)
    @Column(nullable = false, unique = true, length = 20)
    private String codigo;

    @NotBlank
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String nombre;

    @Size(max = 1000)
    @Column(length = 1000)
    private String descripcion;

    // Precios en pesos chilenos, sin decimales.
    @NotNull
    @Min(1)
    @Column(nullable = false)
    private Integer precio;

    @NotNull
    @Min(0)
    @Column(nullable = false)
    private Integer stock;

    @NotNull
    @Min(0)
    @Column(name = "stock_critico", nullable = false)
    private Integer stockCritico = 0;

    @NotNull
    @Min(0)
    @Max(90)
    @Column(name = "descuento_oferta", nullable = false)
    private Integer descuentoOferta = 0;

    @NotNull
    @Min(1)
    @Max(5)
    @Column(nullable = false)
    private Integer rating = 5;

    @Size(max = 255)
    private String imagen;

    // Conservamos la relacion de JPA creada por tu companera.
    @NotNull
    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    public Producto() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Integer getPrecio() { return precio; }
    public void setPrecio(Integer precio) { this.precio = precio; }

    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }

    public Integer getStockCritico() { return stockCritico; }
    public void setStockCritico(Integer stockCritico) { this.stockCritico = stockCritico; }

    public Integer getDescuentoOferta() { return descuentoOferta; }
    public void setDescuentoOferta(Integer descuentoOferta) { this.descuentoOferta = descuentoOferta; }

    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }

    public String getImagen() { return imagen; }
    public void setImagen(String imagen) { this.imagen = imagen; }

    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }
}

