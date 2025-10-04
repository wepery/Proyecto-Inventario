package com.example.backend.service.impl;

import com.example.backend.entity.Producto;
import com.example.backend.entity.Proveedor;
import com.example.backend.repository.ProductoRepository;
import com.example.backend.repository.ProveedorRepository;
import com.example.backend.service.ProductoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {
    private final ProductoRepository productoRepository;
    private final ProveedorRepository proveedorRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository, ProveedorRepository proveedorRepository) {
        this.productoRepository = productoRepository;
        this.proveedorRepository = proveedorRepository;
    }

    @Override
    public List<Producto> obtenerTodosLosProductos() {
        return productoRepository.findAll();
    }

    @Override
    public Producto obtenerProductoPorId(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    @Override
    public List<Producto> obtenerProductosActivados() {
        return productoRepository.findByEstadoIsTrue();
    }

    @Override
    public List<Producto> obtenerProductosDesactivados() {
        return productoRepository.findByEstadoIsFalse();
    }

    @Override
    public boolean activarProducto(Long id) {
        Producto producto = obtenerProductoPorId(id);
        if (producto != null) {
            producto.setEstado(true);
            productoRepository.save(producto);
            return true;
        }
        return false;
    }

    @Override
    public boolean desactivarProducto(Long id) {
        Producto producto = obtenerProductoPorId(id);
        if (producto != null) {
            producto.setEstado(false);
            productoRepository.save(producto);
            return true;
        }
        return false;
    }

    @Override
    public Producto actualizarProducto(Long id, String nombre, String precio, String descripcion,
                                       int stock, String ubicacion, Long proveedorId) {
        Producto producto = obtenerProductoPorId(id);
        if (producto == null) {
            return null; // Producto no encontrado
        }

        Proveedor proveedor = proveedorRepository.findById(proveedorId).orElse(null);
        if (proveedor == null) {
            return null; // Proveedor no encontrado
        }

        // Actualizar atributos
        producto.setNombre(nombre);
        producto.setPrecio(precio);
        producto.setDescripcion(descripcion);
        producto.setStock(stock);
        producto.setUbicacion(ubicacion);
        producto.setProveedor(proveedor);

        return productoRepository.save(producto);
    }

    @Override
    public Producto agregarProducto(String nombre, String precio, String descripcion,
                                    int stock, String ubicacion, Boolean estado, Long proveedorId) {

        if (estado == null) {
            estado = true;
        }

        Proveedor proveedor = proveedorRepository.findById(proveedorId).orElse(null);
        if (proveedor == null) {
            return null;
        }

        Producto producto = new Producto(nombre, precio, descripcion, ubicacion, stock, estado, proveedor);

        return productoRepository.save(producto);
    }

}
