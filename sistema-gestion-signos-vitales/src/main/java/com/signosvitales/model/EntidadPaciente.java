package com.signosvitales.model;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * EntidadPaciente
 * representa basicamente, la entidad de dominio de un paciente y su historial de signos vitales
 */
public class EntidadPaciente {
    //variables iniciales para info del paciente
    private String idPaciente;
    private String nombre;
    private int edad;
    private List<RegistroSignoVital> historial;

    /**
     * crea un nuevo paciente con su historial en blanco.
     * @param idPaciente Identificador único del paciente.
     * @param nombre     Nombre completo del paciente.
     * @param edad       Edad en años.
     */
    public EntidadPaciente(String idPaciente, String nombre, int edad) {
        this.idPaciente = idPaciente;
        this.nombre = nombre;
        this.edad = edad;
        this.historial = new ArrayList<>();
    }

    /**
     * Método principal, se conecta con RegistroSignoVital
     * agrega signos vitales al historial del paciente.
     * @param registro
     */
    public void agregarRegistro(RegistroSignoVital registro) {
        this.historial.add(registro);
    }

    // Getters y Setters
    public String getIdPaciente() { return idPaciente; }
    public void setIdPaciente(String idPaciente) { this.idPaciente = idPaciente; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }
    public List<RegistroSignoVital> getHistorial() { return historial; }
    public void setHistorial(List<RegistroSignoVital> historial) { this.historial = historial; }
}
