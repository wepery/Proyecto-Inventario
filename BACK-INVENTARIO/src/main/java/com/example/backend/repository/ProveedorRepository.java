package com.example.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.example.backend.entity.Proveedor;


@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {
	List<Proveedor> findByEstadoIsTrue();

	List<Proveedor> findByEstadoIsFalse();
	Optional<Proveedor> findByRuc(String ruc);

}
