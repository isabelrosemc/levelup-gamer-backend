package com.levelupgamer.backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "boletas")
public class Boleta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fecha = LocalDateTime.now();

    private Integer subtotal;

    private Integer descuentoAplicado;

    private Integer total;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "boleta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleBoleta> detalles = new ArrayList<>();

    public Boleta() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }

    public Integer getSubtotal() { return subtotal; }
    public void setSubtotal(Integer subtotal) { this.subtotal = subtotal; }

    public Integer getDescuentoAplicado() { return descuentoAplicado; }
    public void setDescuentoAplicado(Integer descuentoAplicado) { this.descuentoAplicado = descuentoAplicado; }

    public Integer getTotal() { return total; }
    public void setTotal(Integer total) { this.total = total; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public List<DetalleBoleta> getDetalles() { return detalles; }
    public void setDetalles(List<DetalleBoleta> detalles) { this.detalles = detalles; }
}