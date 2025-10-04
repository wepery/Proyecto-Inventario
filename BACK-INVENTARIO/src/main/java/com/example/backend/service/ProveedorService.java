package com.example.backend.service;

import com.example.backend.entidades.Proveedor;

import java.util.List;
import java.util.Optional;


public interface ProveedorService {
    List<Proveedor> listarTodos();
    List<Proveedor> findByEstadoIsTrue();
    List<Proveedor> findByEstadoIsFalse();
    Optional<Proveedor> obtenerPorId(Long id);
    Proveedor crearProveedor(String nombre, String ruc, String direccion, String telefono, String email, Boolean estado);
}
