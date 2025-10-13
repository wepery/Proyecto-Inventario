package com.example.backend.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Manejador global de excepciones para toda la aplicación.
 * Intercepta errores comunes y devuelve respuestas uniformes.
 */
@ControllerAdvice
public class GlobalErrorHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalErrorHandler.class);

    /**
     * Maneja cualquier excepción no controlada en el sistema.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleInternalServerError(Exception e) {
        logger.error("Error interno en el servidor: {}", e.getMessage(), e);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("⚠️ Ocurrió un error interno en el servidor. Intente nuevamente más tarde.");
    }

    /**
     * Maneja errores específicos de tipo IllegalArgumentException.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleBadRequest(IllegalArgumentException e) {
        logger.warn("Solicitud inválida: {}", e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("❌ Solicitud inválida: " + e.getMessage());
    }

    /**
     * Maneja errores cuando un recurso no es encontrado.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleNotFound(ResourceNotFoundException e) {
        logger.info("Recurso no encontrado: {}", e.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("🚫 Recurso no encontrado: " + e.getMessage());
    }
}
