package com.example.backend.service;

import com.example.backend.entity.Detalle_Salida;

import java.util.List;
import java.util.Map;

public interface DetalleSalidaService {

    Detalle_Salida obtenerPorId(Long id);

    List<Detalle_Salida> obtenerTodas();
    Map<String, Boolean> actualizarDetalleSalida(Long detalleSalidaId, Detalle_Salida detalleEntrada);
    List<Detalle_Salida> crearDetalleSalida(List<Detalle_Salida> listaDetalleSalida);
}
