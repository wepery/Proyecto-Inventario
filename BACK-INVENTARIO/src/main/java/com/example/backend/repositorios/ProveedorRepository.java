package com.example.backend.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.example.backend.entidades.Proveedor;


@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {
	List<Proveedor> findByEstadoIsTrue();

	List<Proveedor> findByEstadoIsFalse();


}
