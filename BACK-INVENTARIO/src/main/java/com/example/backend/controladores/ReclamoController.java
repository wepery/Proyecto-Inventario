package com.example.backend.controladores;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.backend.entidades.Reclamos;

import com.example.backend.repositorios.ReclamoRepository;

import org.springframework.web.bind.annotation.RequestMethod;
@RestController
@RequestMapping("/reclamo")

@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE }, allowedHeaders = "*")
public class ReclamoController {
// el crud de reclamo no s epodra actuarliza tendra un controlador que tengas los metodos de  crear reclamo , listar los reclamos que esten enviado y los que esten  ahi recien respondidos y otro para mandar mensajes al correo
	@Autowired
	private ReclamoRepository reclamoRepository;
	

	// Metodo Listar
	@GetMapping
	public List<Reclamos> obtenerProveedor() {
		return reclamoRepository.findAll();
	}

	// Metodo Listar por Id
	@GetMapping("/{id}")
	public ResponseEntity<Reclamos> obtenerReclamoPorId(@PathVariable Long id) {
		return reclamoRepository.findById(id)
				.map(ResponseEntity::ok)                // 200 OK con el reclamo
				.orElseGet(() -> ResponseEntity.notFound().build()); // 404 si no existe
	}

	// Metodo Listar desactivadas
	// listaremos el los reclamo desactivados a que serian los enviadps al correo pidiendo las serias disculpas del caso
	// lo cual tendriamos que hacer un metodo para que se desactive a la hora de enviar el  correo
	
	@GetMapping("/desactivadas")
	public List<Reclamos> obtenerReclamoDesactivadas() {
		return reclamoRepository.findByEstadoIsFalse();
	}

	// Metodo Listar activadas
	// los activados seran los mensajes que nos han enviado los usuarios o clientes 
	@GetMapping("/activadas")
	public List<Reclamos> obtenerReclamoActivadas() {
		return reclamoRepository.findByEstadoIsTrue();
	}

	@PostMapping("/")
	public ResponseEntity<Reclamos> agregarReclamo(@RequestBody Reclamos reclamo) {
	    reclamo.setEstado(true); // Establecer estado en true por defecto
	    
	    Reclamos reclamoGuardado = reclamoRepository.save(reclamo);
	    return ResponseEntity.status(HttpStatus.CREATED).body(reclamoGuardado);
	}
	

	// activar categoria

	@PostMapping("/activar/{id}")
	public ResponseEntity<Map<String, String>> activarReclamo(@PathVariable Long id) {
		Optional<Reclamos> reclamoOptional = reclamoRepository.findById(id);
		if (reclamoOptional.isPresent()) {
			Reclamos reclamo = reclamoOptional.get();
			reclamo.setEstado(true);
			reclamoRepository.save(reclamo);

			Map<String, String> response = new HashMap<>();
			response.put("mensaje", "Reclamo activar con éxito");

			return ResponseEntity.ok(response);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	// desactivar cateogria
	@PostMapping("/desactivar/{id}")
	public ResponseEntity<Map<String, String>> desactivarReclamo(@PathVariable Long id) {
		Optional<Reclamos> reclamoOptional = reclamoRepository.findById(id);
		if (reclamoOptional.isPresent()) {
			Reclamos reclamo = reclamoOptional.get();
			reclamo.setEstado(false);
			reclamoRepository.save(reclamo);

			Map<String, String> response = new HashMap<>();
			response.put("mensaje", "Reclamo desactivar con éxito");

			return ResponseEntity.ok(response);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	

}