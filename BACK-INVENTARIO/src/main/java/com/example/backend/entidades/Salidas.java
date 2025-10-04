package com.example.backend.entidades;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
@Table(name = "salidas")
public class Salidas {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long salidaId;

	private Date fechaSalida;

	public Salidas(Date fechaSalida) {
		super();
		this.fechaSalida = fechaSalida;
	}

	public Salidas() {
		super();
	}



	public Long getSalidaId() {
		return salidaId;
	}

	public void setSalidaId(Long salidaId) {
		this.salidaId = salidaId;
	}
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
	public Date getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	
	
}
