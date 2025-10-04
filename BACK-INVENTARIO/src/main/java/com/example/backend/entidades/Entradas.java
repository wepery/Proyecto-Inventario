package com.example.backend.entidades;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "entradas")
public class Entradas {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long entradaId;
	@JsonIgnore
	private Date fechaEntrada;



	public Entradas() {
		super();
	}

	public Entradas(Date fechaEntrada) {
		super();
		this.fechaEntrada = fechaEntrada;

	}

	public Long getEntradaId() {
		return entradaId;
	}

	public void setEntradaId(Long entradaId) {
		this.entradaId = entradaId;
	}
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
	public Date getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}


}
