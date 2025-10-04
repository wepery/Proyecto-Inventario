package com.example.backend.controladores;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.backend.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.backend.entidades.Proveedor;

import com.example.backend.repositorios.ProveedorRepository;

@RestController
@RequestMapping("/proveedor")

@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE}, allowedHeaders = "*")
public class ProveedorController {

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private ProveedorService proveedorService;

    // Metodo Listar
    @GetMapping
    public List<Proveedor> obtenerProveedor() {
        return proveedorService.listarTodos();
    }

    // Metodo Listar por Id
    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> obtenerProveedorPorId(@PathVariable Long id) {
        return proveedorService.obtenerPorId(id)
                .map(proveedor -> ResponseEntity.ok(proveedor))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    // Metodo Listar desactivadas
    @GetMapping("/desactivadas")
    public List<Proveedor> obtenerProveedorDesactivadas() {
        return proveedorService.findByEstadoIsFalse();
    }

    // Metodo Listar activadas
    @GetMapping("/activadas")
    public List<Proveedor> obtenerProveedorActivadas() {
        return proveedorService.findByEstadoIsTrue();
    }

    // crear proveedor

    @PostMapping("/")
    public ResponseEntity<?>  agregarProveedor(@RequestParam String nombre,
                                      @RequestParam String ruc,
                                      @RequestParam String direccion,
                                      @RequestParam String telefono,
                                      @RequestParam String email,
                                      @RequestParam(value = "estado", required = false) Boolean estado) {
        try {

            return ResponseEntity.ok (proveedorService.crearProveedor(nombre, ruc, direccion, telefono, email, estado));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    @PutMapping("/{id}")
    public ResponseEntity<Proveedor> actualizarProveedor(@PathVariable Long id, @RequestParam String nombre,
                                                         @RequestParam String ruc, @RequestParam String direccion, @RequestParam String telefono,
                                                         @RequestParam String email) {
        // Verificar si el proveedor existe en la base de datos
        Optional<Proveedor> proveedorOptional = proveedorRepository.findById(id);
        if (!proveedorOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        // Obtener el proveedor existente de la base de datos
        Proveedor proveedorExistente = proveedorOptional.get();
        // Actualizar los datos del proveedor
        proveedorExistente.setNombre(nombre);
        proveedorExistente.setRuc(ruc);
        proveedorExistente.setDireccion(direccion);
        proveedorExistente.setTelefono(telefono);
        proveedorExistente.setEmail(email);

        // Guardar los cambios en la base de datos
        Proveedor proveedorActualizada = proveedorRepository.save(proveedorExistente);
        return ResponseEntity.ok(proveedorActualizada);
    }

    // activar categoria

    @PostMapping("/activar/{id}")
    public ResponseEntity<Map<String, String>> activarProveedor(@PathVariable Long id) {
        Optional<Proveedor> proveedorOptional = proveedorRepository.findById(id);
        if (proveedorOptional.isPresent()) {
            Proveedor proveedor = proveedorOptional.get();
            proveedor.setEstado(true);
            proveedorRepository.save(proveedor);

            Map<String, String> response = new HashMap<>();
            response.put("mensaje", "Proveedor activar con éxito");

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // desactivar cateogria
    @PostMapping("/desactivar/{id}")
    public ResponseEntity<Map<String, String>> desactivarProveedor(@PathVariable Long id) {
        Optional<Proveedor> proveedorOptional = proveedorRepository.findById(id);
        if (proveedorOptional.isPresent()) {
            Proveedor proveedor = proveedorOptional.get();
            proveedor.setEstado(false);
            proveedorRepository.save(proveedor);

            Map<String, String> response = new HashMap<>();
            response.put("mensaje", "Proveedor desactivar con éxito");

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
