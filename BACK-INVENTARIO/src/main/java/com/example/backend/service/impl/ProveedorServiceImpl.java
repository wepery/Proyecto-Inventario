package com.example.backend.service.impl;

import com.example.backend.entity.Proveedor;
import com.example.backend.repository.ProveedorRepository;
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
    public Proveedor crearProveedor(String nombre, String ruc, String direccion,
                                    String telefono, String email, Boolean estado) {
        if (estado == null) {
            estado = true; // por defecto activo
        }

        Proveedor proveedor = new Proveedor();
        proveedor.setNombre(nombre);
        proveedor.setRuc(ruc);
        proveedor.setDireccion(direccion);
        proveedor.setTelefono(telefono);
        proveedor.setEmail(email);
        proveedor.setEstado(estado);

        return proveedorRepository.save(proveedor);
    }

    @Override
    public Proveedor actualizarProveedor(Long id, String nombre, String ruc,
                                         String direccion, String telefono, String email) {
        Optional<Proveedor> proveedorOpt = proveedorRepository.findById(id);
        if (proveedorOpt.isEmpty()) {
            return null;
        }

        Proveedor proveedor = proveedorOpt.get();
        proveedor.setNombre(nombre);
        proveedor.setRuc(ruc);
        proveedor.setDireccion(direccion);
        proveedor.setTelefono(telefono);
        proveedor.setEmail(email);

        return proveedorRepository.save(proveedor);
    }

    @Override
    public boolean activarProveedor(Long id) {
        Optional<Proveedor> proveedorOpt = proveedorRepository.findById(id);
        if (proveedorOpt.isPresent()) {
            Proveedor proveedor = proveedorOpt.get();
            proveedor.setEstado(true);
            proveedorRepository.save(proveedor);
            return true;
        }
        return false;
    }

    @Override
    public boolean desactivarProveedor(Long id) {
        Optional<Proveedor> proveedorOpt = proveedorRepository.findById(id);
        if (proveedorOpt.isPresent()) {
            Proveedor proveedor = proveedorOpt.get();
            proveedor.setEstado(false);
            proveedorRepository.save(proveedor);
            return true;
        }
        return false;
    }


}
