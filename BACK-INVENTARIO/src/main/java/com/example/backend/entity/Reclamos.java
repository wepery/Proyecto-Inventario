package com.example.backend.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "reclamos")
public class Reclamos {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long reclamoId;

	private String asunto;
	@ManyToOne
	@JoinColumn(name = "usuarioId", nullable = false)
	private Usuario usuario;
	private boolean estado;
	public Reclamos(String asunto, Usuario usuario, boolean estado) {
		super();
		this.asunto = asunto;
		this.usuario = usuario;
		this.estado = estado;
	}
	public Reclamos() {
		super();
	}
	public Long getReclamoId() {
		return reclamoId;
	}
	public void setReclamoId(Long reclamoId) {
		this.reclamoId = reclamoId;
	}
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	
	
	
}
