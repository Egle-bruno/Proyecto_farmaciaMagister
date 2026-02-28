package com.cibertec.farmacia.model;

import jakarta.persistence.*;

@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Integer idCliente;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "nro_doc")
    private String nroDoc;

    @Column(name = "telefono")
    private String telefono;

    // ESTA ES LA PIEZA QUE FALTA Y CAUSA EL ERROR
    @Column(name = "tipo_doc")
    private String tipoDoc;

    // --- GETTERS Y SETTERS (No borres los que ya tienes, solo agrega estos) ---

    public Integer getIdCliente() { return idCliente; }
    public void setIdCliente(Integer idCliente) { this.idCliente = idCliente; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getNroDoc() { return nroDoc; }
    public void setNroDoc(String nroDoc) { this.nroDoc = nroDoc; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getTipoDoc() { return tipoDoc; }
    public void setTipoDoc(String tipoDoc) { this.tipoDoc = tipoDoc; }
}