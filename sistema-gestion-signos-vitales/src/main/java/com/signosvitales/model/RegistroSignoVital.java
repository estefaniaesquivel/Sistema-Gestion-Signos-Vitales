package com.signosvitales.model;

import java.time.LocalDateTime;

//IMPORTANTEE, FALTA REFACTORIZACION EN LA CLASE!!!!!!!!!
/**
 * Guarda la toma de signos vitales de un paciente con fecha y hora actual.
 * Junta las mediciones de temperatura, presión arterial y frecuencia cardíaca
 * y evalúa el estado general del paciente.
 */
public class RegistroSignoVital {

    private String idRegistro;
    private String idPaciente;
    private LocalDateTime fechaHora;
    private Temperatura temperatura;
    private PresionArterial presionArterial;
    private FrecuenciaCardiaca frecuenciaCardiaca;

    /* Constructor principal */
    public RegistroSignoVital(String idRegistro, String idPaciente,
                               Temperatura temperatura,
                               PresionArterial presionArterial,
                               FrecuenciaCardiaca frecuenciaCardiaca) {
        this.idRegistro = idRegistro;
        this.idPaciente = idPaciente;
        this.fechaHora = LocalDateTime.now();
        this.temperatura = temperatura;
        this.presionArterial = presionArterial;
        this.frecuenciaCardiaca = frecuenciaCardiaca;
    }

    /* Constructor sobrecargado enfocado en presión arterial */
    public RegistroSignoVital(String idRegistro, String idPaciente, PresionArterial presionArterial) {
        this(idRegistro, idPaciente, null, presionArterial, null);
    }

    /**
     * Evalúa la estabilidad basándose en la categoría etaria del paciente.
     */
    public String determinarNivelEstabilidad(CategoriaEtaria categoria) {
        if (presionArterial == null) {
            return "Sin datos";
        }

        // Se pasa la categoría etaria requerida por PresionArterial
        boolean presionNormal = presionArterial.esRangoNormal(categoria);
        boolean tempNormal = (temperatura == null) || temperatura.esRangoNormal();
        boolean fcNormal = (frecuenciaCardiaca == null) || frecuenciaCardiaca.esRangoNormal();

        if (presionNormal && tempNormal && fcNormal) {
            return "Normal";
        }

        double pam = presionArterial.calcularPAM();
        if (pam < 65.0 || pam > 115.0) {
            return "Crítico";
        }

        return "Riesgo Moderado";
    }

    /**
     * Cuenta cuántos signos están en rango normal para determinar el resultado general.
     */
    public String calcularNivelEstabilidad(CategoriaEtaria categoria) {
        boolean tempEstable = (temperatura != null) && temperatura.esRangoNormal();
        boolean presionEstable = (presionArterial != null) && presionArterial.esRangoNormal(categoria);
        boolean fcEstable = (frecuenciaCardiaca != null) && frecuenciaCardiaca.esRangoNormal();

        int signosNormales = 0;
        if (tempEstable) signosNormales++;
        if (presionEstable) signosNormales++;
        if (fcEstable) signosNormales++;

        return switch (signosNormales) {
            case 3 -> "Estable";           // Los tres signos están normales
            case 1, 2 -> "Riesgo Moderado"; // Uno o dos signos están fuera de rango
            default -> "Crítico";          // Ningún signo está bien
        };
    }

    // --- Getters y Setters ---

    public String getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(String idRegistro) {
        this.idRegistro = idRegistro;
    }

    public String getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(String idPaciente) {
        this.idPaciente = idPaciente;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Temperatura getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Temperatura temperatura) {
        this.temperatura = temperatura;
    }

    public PresionArterial getPresionArterial() {
        return presionArterial;
    }

    public void setPresionArterial(PresionArterial presionArterial) {
        this.presionArterial = presionArterial;
    }

    public FrecuenciaCardiaca getFrecuenciaCardiaca() {
        return frecuenciaCardiaca;
    }

    public void setFrecuenciaCardiaca(FrecuenciaCardiaca frecuenciaCardiaca) {
        this.frecuenciaCardiaca = frecuenciaCardiaca;
    }
}