package com.example.backend.entidades;

import com.example.backend.security.model.Usuario;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "detalle_salida")
public class Detalle_Salida {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long detalleSalidaId;
	private int cantidad;
	private String descripcion;

	@ManyToOne
	@JoinColumn(name = "usuarioId", nullable = false)
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "productoId", nullable = false)
	private Producto producto;

	@ManyToOne
	@JoinColumn(name = "salidaId", nullable = false)
	private Salidas salida;

	public Detalle_Salida(Long detalleSalidaId, int cantidad, String descripcion, Usuario usuario, Producto producto,
			Salidas salida) {
		super();
		this.detalleSalidaId = detalleSalidaId;
		this.cantidad = cantidad;
		this.descripcion = descripcion;
		this.usuario = usuario;
		this.producto = producto;
		this.salida = salida;
	}

	public Detalle_Salida() {
		super();
	}

	public Long getDetalleSalidaId() {
		return detalleSalidaId;
	}

	public void setDetalleSalidaId(Long detalleSalidaId) {
		this.detalleSalidaId = detalleSalidaId;
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

	public Salidas getSalida() {
		return salida;
	}

	public void setSalida(Salidas salida) {
		this.salida = salida;
	}

}
