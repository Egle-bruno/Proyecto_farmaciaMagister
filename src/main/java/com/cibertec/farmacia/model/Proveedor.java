package com.cibertec.farmacia.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "proveedor")
public class Proveedor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_proveedor")
	private Integer idProveedor;

	@Column(name = "razon_social", length = 100)
	private String razonSocial;

	@Column(name = "ruc", length = 11)
	private String ruc;

	@Column(name = "direccion", length = 150)
	private String direccion;

	@Column(name = "telefono", length = 15)
	private String telefono;

	// Getters y Setters
	public Integer getIdProveedor() { return idProveedor; }
	public void setIdProveedor(Integer idProveedor) { this.idProveedor = idProveedor; }
	public String getRazonSocial() { return razonSocial; }
	public void setRazonSocial(String razonSocial) { this.razonSocial = razonSocial; }
	public String getRuc() { return ruc; }
	public void setRuc(String ruc) { this.ruc = ruc; }
	public String getDireccion() { return direccion; }
	public void setDireccion(String direccion) { this.direccion = direccion; }
	public String getTelefono() { return telefono; }
	public void setTelefono(String telefono) { this.telefono = telefono; }
}