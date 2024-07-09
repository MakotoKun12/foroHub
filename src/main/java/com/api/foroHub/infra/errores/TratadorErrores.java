package com.api.foroHub.infra.errores;

import com.api.foroHub.infra.excepciones.DuplicadoException;
import com.api.foroHub.infra.excepciones.ValidacionIntegridad;
import jakarta.validation.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestControllerAdvice
public class TratadorErrores {

    private static final Logger logger = LoggerFactory.getLogger(TratadorErrores.class);

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Map<String, Object>> handleBadCredentialsException(BadCredentialsException ex) {
        return buildErrorResponse(HttpStatus.UNAUTHORIZED, "Email o password invalidos", null);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleEntityNotFoundException(EntityNotFoundException ex) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage(), logError(ex));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        var errores = ex.getFieldErrors().stream().map(DatosErrorValidacion::new).toList();
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("errors", errores);
        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(ValidacionIntegridad.class)
    public ResponseEntity<Map<String, Object>> handleValidacionIntegridad(ValidacionIntegridad ex) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), logError(ex));
    }

    @ExceptionHandler(DuplicadoException.class)
    public ResponseEntity<Map<String, Object>> handleDuplicadoException(DuplicadoException ex) {
        return buildErrorResponse(HttpStatus.CONFLICT, ex.getMessage(), null);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(ValidationException ex) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), logError(ex));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneralException(Exception ex) {
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred", logError(ex));
    }

    private String logError(Exception ex) {
        String errorId = UUID.randomUUID().toString();
        logger.error("Error ID: " + errorId, ex);
        return errorId;
    }

    private ResponseEntity<Map<String, Object>> buildErrorResponse(HttpStatus status, String message, String errorId) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", message);
        if (errorId != null) {
            body.put("errorId", errorId);
        }
        return ResponseEntity.status(status).body(body);
    }

    private record DatosErrorValidacion(String campo, String error) {
        public DatosErrorValidacion(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
