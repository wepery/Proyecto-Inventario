package com.example.backend.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.backend.entidades.Reclamos;

public interface ReclamoRepository extends JpaRepository<Reclamos, Long> {
	List<Reclamos> findByEstadoIsTrue();

	List<Reclamos> findByEstadoIsFalse();
}