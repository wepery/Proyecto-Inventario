package com.example.backend.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.entity.Entradas;

public interface EntradaRepository extends JpaRepository<Entradas, Long> {

	Optional<Entradas> findByFechaEntrada(Date fecha);

}
