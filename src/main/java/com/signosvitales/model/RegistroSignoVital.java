package com.signosvitales.model;

import java.time.LocalDateTime;

/*
 * Guarda la toma de signos vitales de un paciente con fecha y hora actual
 * Junta las mediciones de temperatura, presion arterial y frecuencia cardiaca
 * y evalua el estado general del paciente
 */
public class RegistroSignoVital {

    private String idRegistro;
    private String idPaciente;
    private LocalDateTime fechaHora;
    private Temperatura temperatura;
    private PresionArterial presionArterial;
    private FrecuenciaCardiaca frecuenciaCardiaca;

    /* Constructor principal con todos los signos vitales */
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

    /* Constructor para cuando solo se registra la presion */
    public RegistroSignoVital(String idRegistro, String idPaciente, PresionArterial presionArterial) {
        this(idRegistro, idPaciente, null, presionArterial, null);
    }

    /* Evalua la estabilidad basandose principalmente en la presion arterial */
    public String determinarNivelEstabilidad() {
        if (presionArterial == null) {
            return "Sin datos";
        }

        // Si todos los signos que hay estan en rango normal
        boolean presionNormal = presionArterial.esRangoNormal();
        boolean tempNormal = (temperatura == null) || temperatura.esRangoNormal();
        boolean fcNormal = (frecuenciaCardiaca == null) || frecuenciaCardiaca.esRangoNormal();

        if (presionNormal && tempNormal && fcNormal) {
            return "Normal";
        }

        // Si la presion media es muy baja o muy alta es peligroso
        double pam = presionArterial.calcularPAM();
        if (pam < 65.0 || pam > 115.0) {
            return "Crítico";
        }

        // Si esta alterada pero no en un extremo
        return "Riesgo Moderado";
    }

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

    /* Cuenta cuantos signos estan bien para dar el resultado general */
    public String calcularNivelEstabilidad() {
        boolean tempEstable = (temperatura != null) && temperatura.esRangoNormal();
        boolean presionEstable = (presionArterial != null) && presionArterial.esRangoNormal();
        boolean fcEstable = (frecuenciaCardiaca != null) && frecuenciaCardiaca.esRangoNormal();

        int signosNormales = 0;
        if (tempEstable) signosNormales++;
        if (presionEstable) signosNormales++;
        if (fcEstable) signosNormales++;

        return switch (signosNormales) {
            case 3 -> "Estable";          // Los tres signos estan normales
            case 1, 2 -> "Riesgo Moderado"; // Uno o dos signos estan fuera de rango
            default -> "Crítico";         // Ningun signo esta bien
        };
    }
}
