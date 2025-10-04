package com.example.backend.utils;

import java.util.regex.Pattern;

public final class ValidacionUtil {

    private static final Pattern TELEFONO_PATTERN = Pattern.compile("\\d{9}");
    private static final Pattern CORREO_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    private static final Pattern NOMBRE_PATTERN = Pattern.compile("^[A-Za-zÁÉÍÓÚáéíóúÑñ\\s]+$");
    private static final Pattern DNI_PATTERN = Pattern.compile("\\d{8}");

    public ValidacionUtil() {
    }

    public static boolean TelefonoValido(String telefono) {
        return telefono != null && TELEFONO_PATTERN.matcher(telefono).matches();
    }

    public static boolean CorreoValido(String correo) {
        return correo != null && CORREO_PATTERN.matcher(correo).matches();
    }

    public static boolean NombreValido(String nombre) {
        return nombre != null && NOMBRE_PATTERN.matcher(nombre).matches();
    }

    public static boolean DNIValido(String dni) {
        return dni != null && DNI_PATTERN.matcher(dni).matches();
    }
}
