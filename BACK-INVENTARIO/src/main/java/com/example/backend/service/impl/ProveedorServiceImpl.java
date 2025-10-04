package com.example.backend.service.impl;

import com.example.backend.entidades.Proveedor;
import com.example.backend.repositorios.ProveedorRepository;
import com.example.backend.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProveedorServiceImpl implements ProveedorService {
    @Autowired
    private ProveedorRepository proveedorRepository;


    @Override
    public List<Proveedor> listarTodos() {
        return proveedorRepository.findAll();
    }

    @Override
    public List<Proveedor> findByEstadoIsTrue() {
        return proveedorRepository.findByEstadoIsTrue();
    }

    @Override
    public List<Proveedor> findByEstadoIsFalse() {
        return proveedorRepository.findByEstadoIsFalse();
    }

    @Override
    public Optional<Proveedor> obtenerPorId(Long id) {
        return proveedorRepository.findById(id);
    }

    @Override
    public Proveedor crearProveedor(String nombre, String ruc, String direccion, String telefono, String email, Boolean estado) {
        if (estado == null) {
            estado = true; // lógica de negocio: valor por defecto
        }
        Proveedor proveedor = new Proveedor(nombre, ruc, direccion, telefono, email, estado);
        return proveedorRepository.save(proveedor);
    }

}
