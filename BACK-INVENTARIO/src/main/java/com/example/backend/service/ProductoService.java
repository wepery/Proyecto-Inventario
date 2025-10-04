package com.example.backend.service;

import com.example.backend.entity.Producto;

import java.util.List;

public interface ProductoService {

    List<Producto> obtenerTodosLosProductos();

    Producto obtenerProductoPorId(Long id);

    List<Producto> obtenerProductosActivados();

    List<Producto> obtenerProductosDesactivados();

    boolean activarProducto(Long id);

    boolean desactivarProducto(Long id);

    Producto actualizarProducto(Long id, String nombre, String precio, String descripcion,
                                int stock, String ubicacion, Long proveedorId);

    Producto agregarProducto(String nombre, String precio, String descripcion,
                             int stock, String ubicacion, Boolean estado, Long proveedorId);
}
