package com.example.backend.service.impl;

import com.example.backend.entity.Detalle_Salida;
import com.example.backend.entity.Producto;
import com.example.backend.entity.Salidas;
import com.example.backend.repository.Detalle_SalidaRepository;
import com.example.backend.repository.ProductoRepository;
import com.example.backend.repository.SalidaRepository;
import com.example.backend.service.DetalleSalidaService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DetalleSalidaServiceImpl implements DetalleSalidaService {

    private final Detalle_SalidaRepository detalle_SalidaRepository;
    private  final ProductoRepository   productoRepository;
    private final SalidaRepository salidaRepository;

    public DetalleSalidaServiceImpl(Detalle_SalidaRepository detalle_SalidaRepository, ProductoRepository productoRepository, SalidaRepository salidaRepository) {
        this.detalle_SalidaRepository = detalle_SalidaRepository;
        this.productoRepository = productoRepository;
        this.salidaRepository = salidaRepository;
    }

    @Override
    public Detalle_Salida obtenerPorId(Long id) {
        return detalle_SalidaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));
    }

    @Override
    public List<Detalle_Salida> obtenerTodas() {
        return detalle_SalidaRepository.findAll();
    }

    @Override
    public Map<String, Boolean> actualizarDetalleSalida(Long detalleSalidaId, Detalle_Salida detalleEntrada) {
        Map<String, Boolean> response = new HashMap<>();
        response.put("success", false);

        Optional<Detalle_Salida> detalleExistenteOpt = detalle_SalidaRepository.findById(detalleSalidaId);
        if (detalleExistenteOpt.isEmpty()) {
            return response; // No existe el detalle
        }

        Detalle_Salida detalleExistente = detalleExistenteOpt.get();

        // Ajuste del stock si cambia la cantidad
        int cantidadAntigua = detalleExistente.getCantidad();
        int cantidadNueva = detalleEntrada.getCantidad();

        if (cantidadNueva != cantidadAntigua) {
            Producto producto = detalleEntrada.getProducto();
            Producto productoActualizado = productoRepository.findById(producto.getProductoId()).orElse(null);

            if (productoActualizado == null) {
                return response; // Producto no encontrado
            }

            int diferenciaCantidad = cantidadNueva - cantidadAntigua;
            int nuevoStock = productoActualizado.getStock() - diferenciaCantidad;
            productoActualizado.setStock(nuevoStock);
            productoRepository.save(productoActualizado);
        }

        // Actualizar los campos del detalle
        detalleExistente.setProducto(detalleEntrada.getProducto());
        detalleExistente.setDescripcion(detalleEntrada.getDescripcion());
        detalleExistente.setCantidad(detalleEntrada.getCantidad());

        detalle_SalidaRepository.save(detalleExistente);
        response.put("success", true);

        return response;
    }

    @Override
    public List<Detalle_Salida> crearDetalleSalida(List<Detalle_Salida> listaDetalleSalida) {
        List<Detalle_Salida> guardados = new ArrayList<>();

        for (Detalle_Salida detalle : listaDetalleSalida) {
            // Buscar salida existente por fecha
            Optional<Salidas> salidaExistenteOpt = salidaRepository.findByFechaSalida(detalle.getSalida().getFechaSalida());
            Salidas salidaGuardada;

            if (salidaExistenteOpt.isPresent()) {
                salidaGuardada = salidaExistenteOpt.get();
            } else {
                Salidas nuevaSalida = new Salidas();
                nuevaSalida.setFechaSalida(detalle.getSalida().getFechaSalida());
                salidaGuardada = salidaRepository.save(nuevaSalida);
            }

            detalle.setSalida(salidaGuardada);

            // Actualizar stock del producto
            Producto producto = productoRepository.findById(detalle.getProducto().getProductoId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            int nuevoStock = producto.getStock() - detalle.getCantidad();
            producto.setStock(nuevoStock);
            productoRepository.save(producto);

            // Guardar detalle
            guardados.add(detalle_SalidaRepository.save(detalle));
        }

        return guardados;
    }
}
