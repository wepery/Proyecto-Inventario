package com.example.backend.service;



import com.example.backend.dto.TokenResponseDTO;
import com.example.backend.dto.LoginRequestDTO;
import com.example.backend.entity.Usuario;

import java.security.Principal;

public interface AuthService {

    TokenResponseDTO login (LoginRequestDTO loginRequestDTO);
    Usuario actualUsuario(Principal principal);
}
