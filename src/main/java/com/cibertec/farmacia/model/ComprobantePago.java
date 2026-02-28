package com.cibertec.farmacia.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "comprobante_pago")
public class ComprobantePago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cp")
    private Integer idCp;

    @Column(name = "fecha_emision")
    private LocalDateTime fechaEmision;

    @Column(name = "sub_total")
    private Double subTotal;

    @Column(name = "impuesto")
    private Double impuesto;

    @Column(name = "total")
    private Double total;

    // --- LLAVE FORÁNEA ÚNICA (1 a 1) ---
    @OneToOne
    @JoinColumn(name = "id_pedido")
    private Pedido pedido;

    public ComprobantePago() {
    }

    // --- GETTERS Y SETTERS ---

    public Integer getIdCp() {
        return idCp;
    }

    public void setIdCp(Integer idCp) {
        this.idCp = idCp;
    }

    public LocalDateTime getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(LocalDateTime fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    public Double getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(Double impuesto) {
        this.impuesto = impuesto;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}