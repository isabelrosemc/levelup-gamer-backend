
package com.levelupgamer.backend.dto;

import jakarta.validation.constraints.*;

public record CrearProductoRequest(

    @NotBlank
    @Size(max = 20)
    String codigo,

    @NotBlank
    @Size(max = 100)
    String nombre,

    @Size(max = 1000)
    String descripcion,

    @NotNull
    @Min(1)
    Integer precio,

    @NotNull
    @Min(0)
    Integer stock,

    @NotNull
    @Min(0)
    Integer stockCritico,

    @NotNull
    @Min(0)
    @Max(90)
    Integer descuentoOferta,

    @NotNull
    @Min(1)
    @Max(5)
    Integer rating,

    @Size(max = 255)
    String imagen,

    @NotNull
    @Positive
    Long categoriaId

) {}
