package com.example.backend.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.entidades.Reclamos;
import com.example.backend.servicio.ReclamoService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CorreoController {
	@Autowired
	private ReclamoService reclamoService;

	@PostMapping("/reclamo/{id}/enviar-disculpas")
	public ResponseEntity<String> enviarDisculpas(@PathVariable("id") Long id, @RequestBody String mensaje) {
		Reclamos reclamo = reclamoService.obtenerReclamoPorId(id);

		if (reclamo != null) {
			String destinatario = reclamo.getUsuario().getEmail();
			String asunto = "Respuesta de disculpas para el reclamo #" + id;
			String contenido = "Estimado/a " + reclamo.getUsuario().getNombre()+ reclamo.getUsuario().getApellido()+",\n\n"
					+ "Lamentamos profundamente los inconvenientes ocasionados por su reclamo. Queremos ofrecerle nuestras más sinceras disculpas y asegurarle que estamos trabajando para resolver la situación lo antes posible.\n\n"
					+ "Mensaje de disculpas: " + mensaje + "\n\n" +
					"--------------------------,\n" + 
					"Atentamente,\n" + "Equipo de Soporte";

			reclamoService.enviarCorreoDisculpas(destinatario, asunto, contenido);
		    reclamo.setEstado(false);
	        reclamoService.actualizarReclamo(reclamo);
			return ResponseEntity.ok("Correo de disculpas enviado correctamente");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reclamo no encontrado");
		}
	}

}
