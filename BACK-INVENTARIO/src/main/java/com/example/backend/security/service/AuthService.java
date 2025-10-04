package com.example.backend.security.service;



import com.example.backend.security.dto.TokenResponseDTO;
import com.example.backend.security.dto.LoginRequestDTO;
import com.example.backend.security.model.Usuario;

import java.security.Principal;

public interface AuthService {

    TokenResponseDTO login (LoginRequestDTO loginRequestDTO);
    Usuario actualUsuario(Principal principal);
}
