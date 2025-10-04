package com.example.backend.controller;

import com.example.backend.security.dto.LoginRequestDTO;

import com.example.backend.security.service.AuthService;
import com.example.backend.security.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.security.Principal;
import java.util.List;

@Controller
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthService authService;

    @PostMapping("/generate-token")
    public ResponseEntity<?> generarToken(@RequestBody LoginRequestDTO jwtRequest) throws Exception {
        try {
            return ResponseEntity.ok(authService.login(jwtRequest));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("ERRRO SOLICITUD");
        }
    }


    @GetMapping("/actual-usuario")
    public ResponseEntity<?> obtenerUsuarioActual(Principal principal) {
        try {
            return ResponseEntity.ok(authService.actualUsuario(principal));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("ERROR USUARIO ACTUAL");
        }
    }




}
