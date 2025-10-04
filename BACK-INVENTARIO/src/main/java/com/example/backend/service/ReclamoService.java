package com.example.backend.service;

import com.example.backend.entity.Reclamos;

import java.util.List;

public interface ReclamoService {

    List<Reclamos> obtenerTodosLosReclamos();

    Reclamos enviarDisculpasReclamo(Long id, String mensaje); // Para enviar correo + actualizar estado

    Reclamos obtenerReclamoPorId(Long id);                  // Para obtener un reclamo por ID

    Reclamos actualizarReclamo(Reclamos reclamo);

    boolean desactivarReclamo(Long id);

    boolean activarReclamo(Long id);

    List<Reclamos> obtenerReclamosDesactivados();

    List<Reclamos> obtenerReclamosActivados();

    Reclamos agregarReclamo(Reclamos reclamo);
}
