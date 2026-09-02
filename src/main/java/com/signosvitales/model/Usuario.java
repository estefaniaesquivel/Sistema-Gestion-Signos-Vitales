package com.signosvitales.model;
/**
 * Clase que representa a un usuario (médico) en el sistema
 * 
 */
public class Usuario {

    /** Identificador único del usuario. */
    private final String idUsuario;
    /** Nombre completo del médico. */
    private String nombreMedico;
    /** Nombre de usuario para iniciar sesión. */
    private final String usuario;
    /** Hash de la contraseña almacenada de forma segura. */
    private String passwordHash;
    

    /**
     * Constructor de la clase Usuario.
    * @param idUsuario el ID del usuario
    * @param nombreMedico el nombre del médico
    * @param usuario el nombre de usuario
    * @param passwordHash el hash de la contraseña
    * 
    */
    public Usuario(String idUsuario, String nombreMedico, String usuario, String passwordHash) {
        this.idUsuario = idUsuario;
        this.nombreMedico = nombreMedico;
        this.usuario = usuario;
        this.passwordHash = passwordHash;
    }

    /** Getters y Setters */

    /**
     * Obtiene el ID del usuario.
     * @return El identificador del usuario.
     */
    public String getIdUsuario() { 
        return idUsuario; 
    }

    /**
     * Obtiene el nombre del médico.
     * @return El nombre completo del médico.
     */
    public String getNombreMedico() { 
        return nombreMedico; 
    }

    /**
     * Obtiene el nombre de usuario de inicio de sesión.
     * @return El username registrado.
     */
    public String getUsuario() { 
        return usuario; 
    }

    /**
     * Obtiene el hash de la contraseña.
     * @return El string del hash.
     */
    public String getPasswordHash() { 
        return passwordHash; 
    }

    /**
     * Actualiza el nombre del médico.
     * @param nombreMedico El nuevo nombre del médico.
     */
    public void setNombreMedico(String nombreMedico) {
        this.nombreMedico = nombreMedico;
    }

    /**
     * Actualiza el hash de la contraseña.
     * @param passwordHash El nuevo hash de la contraseña.
     */
    public void setPasswordHash(String passwordHash) { 
        this.passwordHash = passwordHash; 
    }
}