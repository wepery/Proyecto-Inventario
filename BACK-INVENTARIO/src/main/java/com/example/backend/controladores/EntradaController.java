package com.example.backend.controladores;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

import com.example.backend.entidades.Detalle_Entrada;
import com.example.backend.entidades.Entradas;
import com.example.backend.entidades.Producto;

import com.example.backend.repositorios.Detalle_EntradaRepository;
import com.example.backend.repositorios.EntradaRepository;
import com.example.backend.repositorios.ProductoRepository;

@RestController
@RequestMapping("/entradas")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE }, allowedHeaders = "*")
public class EntradaController {

	@Autowired
	private EntradaRepository entradaRepository;
	@Autowired
	private ProductoRepository productoRepository;

	@Autowired
	private Detalle_EntradaRepository detalle_EntradaRepository;

	@PostMapping("/")
	public ResponseEntity<?> crearEntradaConDetalles(@RequestBody List<Detalle_Entrada> listaDetalleEntrada) {
		try {
			// SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
			// Date fecha = formatter1.parse(Fecha);

			// Buscar una entrada existente con la misma fecha
			for (Detalle_Entrada detalleEntrada : listaDetalleEntrada) {
				Optional<Entradas> entradaExistente = entradaRepository
						.findByFechaEntrada(detalleEntrada.getEntrada().getFechaEntrada());

				if (entradaExistente.isPresent()) {
					// Si existe una entrada con la misma fecha, asocia el detalleEntrada a esa
					// entrada
					Entradas entradaGuardada = entradaExistente.get();
					detalleEntrada.setEntrada(entradaGuardada);

					// Actualizar el stock del producto
					Producto producto = detalleEntrada.getProducto();
					System.out.print("Datos encontrados" + producto.toString());

					Producto updateProducto = productoRepository.findById(producto.getProductoId()).orElse(null);
					int cantidadIngresada = detalleEntrada.getCantidad();

					// Verifica que los campos obligatorios del producto tengan valores vÃ¡lidos
					/*
					 * if (producto.getNombre() == null || producto.getPrecio() == null ||
					 * producto.getDescripcion() == null || producto.getUbicacion() == null ||
					 * producto.getProveedor() == null) { return ResponseEntity.badRequest().
					 * body("Los campos obligatorios del producto no pueden ser nulos."); }
					 */

					if (updateProducto == null) {
						return ResponseEntity.badRequest()
								.body("Los campos obligatorios del producto no pueden ser nulos.");
					}

					System.out.print("Datos ingresados:" + producto.toString());
					int nuevoStock = updateProducto.getStock() + cantidadIngresada;
					updateProducto.setStock(nuevoStock);
					productoRepository.save(updateProducto);

					detalle_EntradaRepository.save(detalleEntrada);
				} else {
					// Si no existe una entrada con la misma fecha, crea una nueva entrada
					Entradas entrada = new Entradas();
					entrada.setFechaEntrada(detalleEntrada.getEntrada().getFechaEntrada());
					Entradas entradaGuardada = entradaRepository.save(entrada);

					detalleEntrada.setEntrada(entradaGuardada);

					// Actualizar el stock del producto
					Producto producto = detalleEntrada.getProducto();
					Producto updateProducto = productoRepository.findById(producto.getProductoId()).orElse(null);
					if (updateProducto == null) {
						return ResponseEntity.badRequest()
								.body("Los campos obligatorios del producto no pueden ser nulos.");
					}

					int cantidadIngresada = detalleEntrada.getCantidad();
					int nuevoStock = updateProducto.getStock() + cantidadIngresada;
					updateProducto.setStock(nuevoStock);
					productoRepository.save(updateProducto);

					detalle_EntradaRepository.save(detalleEntrada);
				}
			}
			Map<String, Boolean> mapa = new HashMap<>();
			mapa.put("success", true);

			return ResponseEntity.ok(mapa);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/{id}")
	public Detalle_Entrada obtenerDetalle(@PathVariable Long id) {
		return detalle_EntradaRepository.findById(id).orElseThrow(() -> new RuntimeException("Compra no encontrada"));
	}

	@GetMapping
	public List<Detalle_Entrada> obtenerVenta() {
		return detalle_EntradaRepository.findAll();
	}


	
	@PutMapping("/{detalleEntradaId}")
	public ResponseEntity<?> actualizarEntradaConDetalles(@PathVariable Long detalleEntradaId,
	        @RequestBody Detalle_Entrada detalleEntrada) {
	    try {
	        Map<String, Boolean> mapa = new HashMap<>();
	        mapa.put("success", false);

	        Optional<Detalle_Entrada> detalleEntradaExistente = detalle_EntradaRepository.findById(detalleEntradaId);

	        if (detalleEntradaExistente.isPresent()) {
	            Detalle_Entrada detalleEntradaGuardado = detalleEntradaExistente.get();

	            // Obtener la cantidad antigua
	            int cantidadAntigua = detalleEntradaGuardado.getCantidad();
	            int cantidadNueva = detalleEntrada.getCantidad();

	            // Si la cantidad nueva es diferente de la antigua, ajustar el stock
	            if (cantidadNueva != cantidadAntigua) {
	                Producto productoExistente = detalleEntradaGuardado.getProducto();
	                
	                // Asegurarse de que el producto existente no sea nulo
	                if (productoExistente == null) {
	                    return ResponseEntity.badRequest()
	                            .body("El producto no puede ser nulo.");
	                }

	                // Restar la cantidad antigua y agregar la cantidad nueva al stock
	                int diferenciaCantidad = cantidadNueva - cantidadAntigua;
	                int nuevoStock = productoExistente.getStock() + diferenciaCantidad;
	                productoExistente.setStock(nuevoStock);
	                productoRepository.save(productoExistente);
	            }

	            // Actualizar otros campos según sea necesario
	            detalleEntradaGuardado.setDescripcion(detalleEntrada.getDescripcion());
	            detalleEntradaGuardado.setCantidad(detalleEntrada.getCantidad());
	            // No es necesario actualizar el objeto Producto en este punto

	            detalle_EntradaRepository.save(detalleEntradaGuardado);
	            mapa.put("success", true); // Actualización exitosa
	            return ResponseEntity.ok(mapa);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.badRequest().build();
	    }
	}


}
