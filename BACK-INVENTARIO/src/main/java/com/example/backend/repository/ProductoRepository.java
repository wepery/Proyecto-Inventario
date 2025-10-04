package com.example.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.entity.Producto;


public interface ProductoRepository extends JpaRepository<Producto, Long>{
	List<Producto> findByEstadoIsTrue();
	
	List<Producto> findByEstadoIsFalse();




}