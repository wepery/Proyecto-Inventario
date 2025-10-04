package com.example.backend.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.entidades.Producto;


public interface ProductoRepository extends JpaRepository<Producto, Long>{
	List<Producto> findByEstadoIsTrue();
	
	List<Producto> findByEstadoIsFalse();




}