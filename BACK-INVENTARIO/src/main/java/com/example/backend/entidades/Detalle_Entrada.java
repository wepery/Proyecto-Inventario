package com.example.backend.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import com.example.backend.entity.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "detalle_entrada")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Detalle_Entrada {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long detalleEntradaId;
	private int cantidad;
	private String descripcion;

	@ManyToOne
	@JoinColumn(name = "usuarioId", nullable = false)
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "productoId")
	private Producto producto;
	@ManyToOne
	@JoinColumn(name = "entradaId", nullable = false)
	private Entradas entrada;

	public Detalle_Entrada() {
		super();
	}

	public Detalle_Entrada(int cantidad, String descripcion, Usuario usuario, Producto producto, Entradas entrada) {
		super();
		this.cantidad = cantidad;
		this.descripcion = descripcion;
		this.usuario = usuario;
		this.producto = producto;
		this.entrada = entrada;
	}

	public Long getDetalleEntradaId() {
		return detalleEntradaId;
	}

	public void setDetalleEntradaId(Long detalleEntradaId) {
		this.detalleEntradaId = detalleEntradaId;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Entradas getEntrada() {
		return entrada;
	}

	public void setEntrada(Entradas entrada) {
		this.entrada = entrada;
	}

}
