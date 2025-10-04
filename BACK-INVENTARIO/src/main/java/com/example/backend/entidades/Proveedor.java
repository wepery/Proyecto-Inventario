package com.example.backend.entidades;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "proveedor")
public class Proveedor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Long proveedorId;
	private String nombre;
	private String ruc;
	private String direccion;
	private String telefono;
	private String email;
	private boolean estado;


	public Proveedor(String nombre, String ruc, String direccion, String telefono, String email, boolean estado) {
		super();
		this.nombre = nombre;
		this.ruc = ruc;
		this.direccion = direccion;
		this.telefono = telefono;
		this.email = email;
		this.estado = estado;
	}
	public Proveedor() {
		super();
	}
	public Long getProveedorId() {
		return proveedorId;
	}
	public void setProveedorId(Long proveedorId) {
		this.proveedorId = proveedorId;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getRuc() {
		return ruc;
	}
	public void setRuc(String ruc) {
		this.ruc = ruc;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
}
