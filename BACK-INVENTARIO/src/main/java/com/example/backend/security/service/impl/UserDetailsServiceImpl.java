package com.example.backend.security.service.impl;

import com.example.backend.security.model.Usuario;
import com.example.backend.security.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String input) throws UsernameNotFoundException {
        return usuarioRepository.findByUsername(input)
                .orElseThrow(() ->
                        new UsernameNotFoundException("USUARIO NO ENCONTRADO"));
    }
}
