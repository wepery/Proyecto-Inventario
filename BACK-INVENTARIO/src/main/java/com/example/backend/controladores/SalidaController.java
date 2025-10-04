package com.example.backend.controladores;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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



import com.example.backend.entidades.Detalle_Salida;
import com.example.backend.entidades.Producto;
import com.example.backend.entidades.Salidas;
import com.example.backend.repositorios.Detalle_SalidaRepository;
import com.example.backend.repositorios.ProductoRepository;
import com.example.backend.repositorios.SalidaRepository;

@RestController
@RequestMapping("/salidas")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE }, allowedHeaders = "*")
public class SalidaController {
	@Autowired
	private SalidaRepository salidaRepository;
	@Autowired
	private ProductoRepository productoRepository;

	@Autowired
	private Detalle_SalidaRepository detalle_SalidaRepository;
	
	@PostMapping("/")
	public ResponseEntity<?> crearEntradaConDetalles(@RequestBody List<Detalle_Salida> listaDetalleSalida) {
		try {


			// Buscar una entrada existente con la misma fecha
			for (Detalle_Salida detalle_Salida : listaDetalleSalida) {
				Optional<Salidas> salidaexistente = salidaRepository.findByFechaSalida(detalle_Salida.getSalida().getFechaSalida());

				if (salidaexistente.isPresent()) {
					// Si existe una entrada con la misma fecha, asocia el detalleEntrada a esa
					// entrada
					Salidas salidaGuardada = salidaexistente.get();
					detalle_Salida.setSalida(salidaGuardada);

					// Actualizar el stock del producto
					Producto producto = detalle_Salida.getProducto();
					System.out.print("Datos encontrados" + producto.toString());

					Producto updateProducto = productoRepository.findById(producto.getProductoId()).orElse(null);
					int cantidadIngresada = detalle_Salida.getCantidad();

					if (updateProducto == null) {
						return ResponseEntity.badRequest()
								.body("Los campos obligatorios del producto no pueden ser nulos.");
					}

					System.out.print("Datos ingresados:" + producto.toString());
					int nuevoStock = updateProducto.getStock() - cantidadIngresada;
					updateProducto.setStock(nuevoStock);
					productoRepository.save(updateProducto);

					detalle_SalidaRepository.save(detalle_Salida);
				} else {
					// Si no existe una entrada con la misma fecha, crea una nueva entrada
					Salidas salidas = new Salidas();
					salidas.setFechaSalida(detalle_Salida.getSalida().getFechaSalida());
				
					Salidas salidaGuardada = salidaRepository.save(salidas);

					detalle_Salida.setSalida(salidaGuardada);

					// Actualizar el stock del producto
					Producto producto = detalle_Salida.getProducto();
					Producto updateProducto = productoRepository.findById(producto.getProductoId()).orElse(null);
					if (updateProducto == null) {
						return ResponseEntity.badRequest()
								.body("Los campos obligatorios del producto no pueden ser nulos.");
					}

					int cantidadIngresada = detalle_Salida.getCantidad();
					int nuevoStock = updateProducto.getStock() - cantidadIngresada;
					updateProducto.setStock(nuevoStock);
					productoRepository.save(updateProducto);

					detalle_SalidaRepository.save(detalle_Salida);
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
	public Detalle_Salida obtenerDetalle(@PathVariable Long id) {
		return detalle_SalidaRepository.findById(id).orElseThrow(() -> new RuntimeException("Compra no encontrada"));
	}
	@GetMapping
	public List<Detalle_Salida> obtenerVenta() {
		return detalle_SalidaRepository.findAll();
	}

	@PutMapping("/{detalleSalidaId}")
	public ResponseEntity<?> actualizarEntradaConDetalles(@PathVariable Long detalleSalidaId,
			@RequestBody Detalle_Salida detalleEntrada) {
		try {
			Map<String, Boolean> mapa = new HashMap<>();
			mapa.put("success", false);

			Optional<Detalle_Salida> detalleEntradaExistente = detalle_SalidaRepository.findById(detalleSalidaId);

			if (detalleEntradaExistente.isPresent()) {
				Detalle_Salida detalleEntradaGuardado = detalleEntradaExistente.get();

				// Obtener la cantidad antigua
				int cantidadAntigua = detalleEntradaGuardado.getCantidad();
				int cantidadNueva = detalleEntrada.getCantidad();

				// Si la cantidad nueva es diferente de la antigua, ajustar el stock
				if (cantidadNueva != cantidadAntigua) {
					Producto producto = detalleEntrada.getProducto();
					Producto updateProducto = productoRepository.findById(producto.getProductoId()).orElse(null);

					if (updateProducto == null) {
						return ResponseEntity.badRequest()
								.body("Los campos obligatorios del producto no pueden ser nulos.");
					}

					// Restar la cantidad antigua y agregar la cantidad nueva al stock
					int diferenciaCantidad = cantidadNueva + cantidadAntigua;
					int nuevoStock = updateProducto.getStock() - diferenciaCantidad;
					updateProducto.setStock(nuevoStock);
					productoRepository.save(updateProducto);
				}

				// Actualizar otros campos según sea necesario
				
				detalleEntradaGuardado.setProducto(detalleEntrada.getProducto());
				detalleEntradaGuardado.setDescripcion(detalleEntrada.getDescripcion());
				detalleEntradaGuardado.setCantidad(detalleEntrada.getCantidad());
				detalle_SalidaRepository.save(detalleEntradaGuardado);
				System.out.print("actualizo esto " + detalleEntradaGuardado);
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
