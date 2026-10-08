package com.signosvitales.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.signosvitales.model.Usuario;

/**
 * Repositorio de Spring Data JPA para la entidad Usuario.
 * Proporciona métodos CRUD automáticos y consultas personalizadas.
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    /**
     * Busca un usuario por su nombre de usuario de inicio de sesión.
     * Útil para autenticación y login.
     * 
     * @param usuario El nombre de usuario a buscar.
     * @return Un Optional que contiene el usuario si se encuentra.
     */
    Optional<Usuario> findByUsuario(String usuario);

    /**
     * Verifica si ya existe un usuario registrado con el username dado.
     * Útil para validaciones antes de registrar un nuevo médico.
     * 
     * @param usuario El nombre de usuario a verificar.
     * @return true si ya existe, false en caso contrario.
     */
    boolean existsByUsuario(String usuario);
}