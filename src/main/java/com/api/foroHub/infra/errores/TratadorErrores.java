package com.api.foroHub.infra.errores;

import com.api.foroHub.infra.excepciones.DuplicadoException;
import com.api.foroHub.infra.excepciones.ValidacionIntegridad;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String, Object>> tratarError404(EntityNotFoundException ex) {
        String errorId = logError(ex);
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.NOT_FOUND.value());
        body.put("error", HttpStatus.NOT_FOUND.getReasonPhrase());
        body.put("message", ex.getMessage());
        body.put("errorId", errorId);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> tratarError400(MethodArgumentNotValidException ex) {
        var errores = ex.getFieldErrors().stream().map(DatosErrorValidacion::new).toList();
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("errors", errores);
        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(ValidacionIntegridad.class)
    public ResponseEntity<Map<String, Object>> handleValidacionIntegridad(ValidacionIntegridad ex) {
        String errorId = logError(ex);
        return buildErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), errorId);
    }

    @ExceptionHandler(DuplicadoException.class)
    public ResponseEntity<?> tratarErrorDuplicado(DuplicadoException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new DatosError(e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneralException(Exception ex) {
        String errorId = logError(ex);
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        body.put("error", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        body.put("message", "An unexpected error occurred");
        body.put("errorId", errorId);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
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
        body.put("errorId", errorId);
        return new ResponseEntity<>(body, status);
    }

    private record DatosError(String mensaje) {
        public DatosError(String mensaje) {
            this.mensaje = mensaje;
        }
    }

    private record DatosErrorValidacion(String campo, String error) {
        public DatosErrorValidacion(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
