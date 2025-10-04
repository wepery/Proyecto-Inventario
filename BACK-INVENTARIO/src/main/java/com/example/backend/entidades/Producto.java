package com.example.backend.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "producto")
public class Producto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productoId;
	private String nombre;
	private String precio;
	private String descripcion;
	private String ubicacion;
	private int stock;
	private boolean estado;


	@ManyToOne
	@JoinColumn(name = "proveedorId")
	private Proveedor proveedor;



	


	@Override
	public String toString() {
		return "Producto [productoId=" + productoId + ", nombre=" + nombre + ", precio=" + precio + ", descripcion="
				+ descripcion + ", ubicacion=" + ubicacion + ", stock=" + stock + ", estado=" + estado + ", proveedor="
				+ proveedor + "]";
	}

	public Producto(String nombre, String precio, String descripcion, String ubicacion, int stock, boolean estado,
			Proveedor proveedor) {
		super();
		this.nombre = nombre;
		this.precio = precio;
		this.descripcion = descripcion;
		this.ubicacion = ubicacion;
		this.stock = stock;
		this.estado = estado;
		this.proveedor = proveedor;
	}

	public Producto() {
		super();
	}

	public Long getProductoId() {
		return productoId;
	}

	public void setProductoId(Long productoId) {
		this.productoId = productoId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPrecio() {
		return precio;
	}

	public void setPrecio(String precio) {
		this.precio = precio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}


	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}


	

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}



	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	
	
	

}
