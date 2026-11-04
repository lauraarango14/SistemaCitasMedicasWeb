package com.citamedicas.sistemacitasmedicasweb.repository;

import com.citamedicas.sistemacitasmedicasweb.modelo.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio para consultar y administrar los usuarios.
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Busca un usuario por su nombre de usuario.
    Optional<Usuario> findByUsuario(String usuario);

    // Verifica si el nombre de usuario ya está registrado.
    boolean existsByUsuario(String usuario);
}