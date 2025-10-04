package com.example.backend.controller;


import com.example.backend.security.dto.UsuarioRequestDTO;
import com.example.backend.security.model.Usuario;
import com.example.backend.security.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
        RequestMethod.DELETE}, allowedHeaders = "*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    // registrar usuarios normales
    @PostMapping("/guardar-normal")
    public ResponseEntity<?> guardarNormal(@RequestBody UsuarioRequestDTO admin) {
        try {
            // asignamos ADMIN de forma fija
            admin.setRol("NORMAL");

            return ResponseEntity.ok(usuarioService.registrarUsuario(admin));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    // registrar usuarios admin
    @PostMapping("/guardar-admin")
    public ResponseEntity<?> guardarAdmin(@RequestBody UsuarioRequestDTO admin) {
        try {

            return ResponseEntity.ok(usuarioService.registrarUsuario(admin));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    // 🔹 Administradores ACTIVADOS
    @GetMapping("/admin/activadas")
    public ResponseEntity<List<Usuario>> obtenerAdminActivadas() {
        List<Usuario> usuariosAdminActivados = usuarioService.listarUsuarioAdminActivado();
        return ResponseEntity.ok(usuariosAdminActivados);
    }

    // 🔹 Administradores DESACTIVADOS
    @GetMapping("/admin/desactivadas")
    public ResponseEntity<List<Usuario>> obtenerAdminDesactivadas() {
        List<Usuario> usuariosAdminDesactivados = usuarioService.listarUsuarioAdminDesactivado();
        return ResponseEntity.ok(usuariosAdminDesactivados);
    }

    // 🔹 Usuarios normales ACTIVADOS
    @GetMapping("/normal/activadas")
    public ResponseEntity<List<Usuario>> obtenerNormalActivadas() {
        List<Usuario> usuariosNormalesActivados = usuarioService.listarUsuarioNormalActivado();
        return ResponseEntity.ok(usuariosNormalesActivados);
    }

    // 🔹 Usuarios normales DESACTIVADOS
    @GetMapping("/normal/desactivadas")
    public ResponseEntity<List<Usuario>> obtenerNormalDesactivadas() {
        List<Usuario> usuariosNormalesDesactivados = usuarioService.listarUsuarioNormalDesactivado();
        return ResponseEntity.ok(usuariosNormalesDesactivados);
    }

    //LISTAR POR ID
    @GetMapping("/listarId/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable Long id) {
        Usuario usuario = usuarioService.listarPorId(id);
        return ResponseEntity.ok(usuario);
    }
    // 🔹 Desactivar usuario (estado = false)
    @PutMapping("/desactivar/{id}")
    public ResponseEntity<String> desactivarUsuario(@PathVariable("id") Long  id) {


            Usuario usuarioActualizado = usuarioService.eliminarUsuario(id);
            return ResponseEntity.ok("SE DESACTIVO CORRECTAMENTE");


    }

    // 🔹 Activar usuario (estado = true)
    @PutMapping("/activar/{id}")
    public ResponseEntity<Usuario> activarUsuario(@PathVariable("id") Long  id) {
        Usuario usuarioActualizado = usuarioService.activarUsuario(id);
        return ResponseEntity.ok(usuarioActualizado);
    }
}
