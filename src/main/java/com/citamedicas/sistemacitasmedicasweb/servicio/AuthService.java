
package com.citamedicas.sistemacitasmedicasweb.servicio;

import com.citamedicas.sistemacitasmedicasweb.modelo.Usuario;
import com.citamedicas.sistemacitasmedicasweb.repository.UsuarioRepository;
import java.util.Optional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Servicio encargado del registro y autenticación de usuarios.
 * Las contraseñas se almacenan protegidas mediante BCrypt.
 */
@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    // Inyección de dependencias del repositorio y BCrypt.
    public AuthService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    /**
     * Registra un usuario nuevo después de validar sus datos.
     */
    public String registrar(String nombreUsuario, String contrasena) {

        if (nombreUsuario == null || nombreUsuario.isBlank()
                || contrasena == null || contrasena.isBlank()) {
            throw new IllegalArgumentException(
                    "El usuario y la contraseña son obligatorios.");
        }

        if (usuarioRepository.existsByUsuario(nombreUsuario)) {
            throw new IllegalArgumentException(
                    "El nombre de usuario ya está registrado.");
        }

        // Se cifra la contraseña antes de guardarla.
        String contrasenaEncriptada =
                passwordEncoder.encode(contrasena);

        Usuario nuevoUsuario =
                new Usuario(nombreUsuario, contrasenaEncriptada);

        usuarioRepository.save(nuevoUsuario);

        return "Usuario registrado correctamente.";
    }

    /**
     * Comprueba si el usuario y la contraseña son correctos.
     */
    public String autenticar(String nombreUsuario, String contrasena) {

        Optional<Usuario> usuarioEncontrado =
                usuarioRepository.findByUsuario(nombreUsuario);

        if (usuarioEncontrado.isEmpty()
                || contrasena == null
                || !passwordEncoder.matches(
                        contrasena,
                        usuarioEncontrado.get().getContrasena())) {

            throw new IllegalArgumentException(
                    "Error en la autenticación.");
        }

        return "Autenticación satisfactoria.";
    }
}
