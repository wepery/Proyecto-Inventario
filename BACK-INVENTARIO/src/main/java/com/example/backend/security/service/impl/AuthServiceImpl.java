package com.example.backend.security.service.impl;


import com.example.backend.security.dto.LoginRequestDTO;
import com.example.backend.security.dto.TokenResponseDTO;
import com.example.backend.security.model.Usuario;
import com.example.backend.security.service.AuthService;
import com.example.backend.security.service.UsuarioService;
import com.example.backend.security.utils.JwtUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    public TokenResponseDTO login(LoginRequestDTO loginRequestDTO) {
        String identificador= loginRequestDTO.getLogin();
        validarIdentificador(identificador);
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequestDTO.getLogin(), loginRequestDTO.getPassword()));
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(loginRequestDTO.getLogin());
            String token = this.jwtUtils.generateToken(userDetails);

            return new TokenResponseDTO(token);
        }catch (BadCredentialsException ex) {
            // Usuario o contraseña incorrectos
            throw new RuntimeException("Usuario o contraseña incorrectos", ex);
        } catch (Exception ex) {
            // Otros errores de autenticación
            throw new RuntimeException("Error al iniciar sesión", ex);
        }

    }
    @Override
    public Usuario actualUsuario(Principal principal) {
        if (principal == null || principal.getName() == null) {
            throw new RuntimeException("Usuario no autorizado");
        }
        Usuario usuario = (Usuario) this.userDetailsService.loadUserByUsername(principal.getName());
        return usuario;
    }
    public void validarIdentificador(String identificador) {
        boolean existe;
        existe = usuarioService.usuarioExistePorUsername(identificador);

        if (!existe) {
            throw new IllegalArgumentException("Usuario no encontrado");
        }
    }


}
