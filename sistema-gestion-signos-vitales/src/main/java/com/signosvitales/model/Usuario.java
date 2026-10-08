package com.signosvitales.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Clase que representa a un usuario (médico) en el sistema.
 * Mapeada como Entidad JPA para la persistencia de datos.
 */
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(name = "nombre_medico", nullable = false)
    private String nombreMedico;

    @Column(name = "usuario", nullable = false, unique = true, length = 50)
    private String usuario;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    /**
     * Constructor vacio obligatorio para el funcionamiento interno de JPA/Hibernate.
     */
    public Usuario() {
    }

    /**
     * Constructor completo para instanciar la entidad manualmente.
     */
    public Usuario(Long idUsuario, String nombreMedico, String usuario, String passwordHash) {
        this.idUsuario = idUsuario;
        this.nombreMedico = nombreMedico;
        this.usuario = usuario;
        this.passwordHash = passwordHash;
    }

    /* --- Getters y Setters --- */

    public Long getIdUsuario() { 
        return idUsuario; 
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreMedico() { 
        return nombreMedico; 
    }

    public void setNombreMedico(String nombreMedico) {
        this.nombreMedico = nombreMedico;
    }

    public String getUsuario() { 
        return usuario; 
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPasswordHash() { 
        return passwordHash; 
    }

    public void setPasswordHash(String passwordHash) { 
        this.passwordHash = passwordHash; 
    }
}