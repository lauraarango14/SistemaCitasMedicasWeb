
package com.citamedicas.sistemacitasmedicasweb.controller;

import com.citamedicas.sistemacitasmedicasweb.servicio.AuthService;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador REST encargado de recibir las solicitudes
 * de registro e inicio de sesión de los usuarios.
 */
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    private final AuthService authService;

    // Inyección del servicio de autenticación.
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Servicio web para registrar un usuario nuevo.
     */
    @PostMapping("/registro")
    public ResponseEntity<Map<String, String>> registrar(
            @RequestBody AuthRequest solicitud) {

        try {
            String mensaje = authService.registrar(
                    solicitud.usuario(),
                    solicitud.contrasena());

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Map.of("mensaje", mensaje));

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", e.getMessage()));
        }
    }

    /**
     * Servicio web para validar las credenciales de acceso.
     */
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> autenticar(
            @RequestBody AuthRequest solicitud) {

        try {
            String mensaje = authService.autenticar(
                    solicitud.usuario(),
                    solicitud.contrasena());

            return ResponseEntity.ok(
                    Map.of("mensaje", mensaje));

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    /**
     * Estructura de los datos recibidos en las solicitudes.
     */
    public record AuthRequest(
            String usuario,
            String contrasena) {
    }
}
