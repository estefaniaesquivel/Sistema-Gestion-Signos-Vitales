package com.signosvitales.model;

import java.util.ArrayList;
import java.util.List;

public class EntidadPaciente {
    //variables iniciales para info del paciente
    private String idPaciente;
    private String nombre;
    private int edad;
    private List<RegistroSignoVital> historial;

    public EntidadPaciente(String idPaciente, String nombre, int edad) {
        this.idPaciente = idPaciente;
        this.nombre = nombre;
        this.edad = edad;
        this.historial = new ArrayList<>();
    }

    // Método principal, se conecta con RegistroSignoVital
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
