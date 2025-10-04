package com.example.backend.repositorios;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.backend.entidades.Salidas;

public interface SalidaRepository extends JpaRepository<Salidas, Long> {
	Optional<Salidas> findByFechaSalida(Date fecha);
}
