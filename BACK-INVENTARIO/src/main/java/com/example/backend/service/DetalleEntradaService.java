package com.example.backend.service;

import com.example.backend.entity.Detalle_Entrada;

import java.util.List;

public interface DetalleEntradaService {
    List<Detalle_Entrada> crearDetalleEntrada(List<Detalle_Entrada> listaDetalleEntrada) ;

    Detalle_Entrada obtenerPorId(Long id);

    List<Detalle_Entrada> obtenerTodos();

    Detalle_Entrada actualizarDetalleEntrada(Long detalleEntradaId, Detalle_Entrada detalleEntrada);
}
