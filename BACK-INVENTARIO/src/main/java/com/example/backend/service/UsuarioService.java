package com.example.backend.service;

import com.example.backend.dto.UsuarioRequestDTO;
import com.example.backend.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    Usuario registrarUsuario(UsuarioRequestDTO usuario);

    Usuario eliminarUsuario(Long  usuarioId);

    Usuario activarUsuario(Long  usuarioId);

    Optional<Usuario> listarCodigo(String codigo);

    boolean usuarioExistePorUsername(String username);

    boolean existsByUsernameAndPassword(String username, String password);

    Optional<Usuario> findByUsername(String username);


    boolean usuarioExistePorCorreo(String correo);

    boolean usuarioExistePorTelefono(String telefono);

    boolean usuarioExistePorDni(String dni);


    List<Usuario> obtenerUsuariosPorRol(Long codigo, Boolean estado);

    List<Usuario> listarUsuarioAdminActivado();

    List<Usuario> listarUsuarioAdminDesactivado();

    List<Usuario> listarUsuarioNormalActivado();

    List<Usuario> listarUsuarioNormalDesactivado();
    Usuario listarPorId(Long id);
}



