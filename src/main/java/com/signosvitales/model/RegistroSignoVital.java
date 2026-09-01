package com.signosvitales.model;

import java.time.LocalDateTime;

/**
 * Representa una captura puntual de los signos vitales de un paciente.
 * Almacena la fecha y hora generadas automáticamente, junto con las
 * tres mediciones fisiológicas validadas por sus respectivas clases.
 * 
 */
public class RegistroSignoVital {

    /** Identificador único del registro de signos vitales. */
    private String idRegistro;

    /** Identificador del paciente al que pertenece la medición (RF-SV-01). */
    private String idPaciente;

    /** Fecha y hora exacta de la captura generada por el sistema (RF-SV-02). */
    private LocalDateTime fechaHora;

    /** Objeto que encapsula y valida la temperatura corporal (RF-SV-03). */
    private Temperatura temperatura;

    /** Objeto que encapsula y valida la presión arterial (RF-SV-03). */
    private PresionArterial presionArterial;

    /** Objeto que encapsula y valida la frecuencia cardíaca (RF-SV-03). */
    private FrecuenciaCardiaca frecuenciaCardiaca;

    /**
     * Construye un nuevo registro asociando las mediciones al paciente 
     * y asignando automáticamente la fecha/hora actual del sistema.
     * 
     * @param idRegistro Identificador único del registro.
     * @param idPaciente Identificador del paciente existente.
     * @param temperatura Instancia previamente validada de Temperatura.
     * @param presionArterial Instancia previamente validada de PresionArterial.
     * @param frecuenciaCardiaca Instancia previamente validada de FrecuenciaCardiaca.
     */
    public RegistroSignoVital(String idRegistro, String idPaciente, 
                              Temperatura temperatura, 
                              PresionArterial presionArterial, 
                              FrecuenciaCardiaca frecuenciaCardiaca) {
        this.idRegistro = idRegistro;
        this.idPaciente = idPaciente; // RF-SV-01
        this.fechaHora = LocalDateTime.now(); // RF-SV-02
        this.temperatura = temperatura; // RF-SV-03
        this.presionArterial = presionArterial; // RF-SV-03
        this.frecuenciaCardiaca = frecuenciaCardiaca; // RF-SV-03
    }

    /**
     * Constructor sobrecargado enfocado en la medición de presión arterial.
     * 
     * @param idRegistro Identificador único del registro.
     * @param idPaciente Identificador del paciente.
     * @param presionArterial Instancia de PresionArterial.
     */
    public RegistroSignoVital(String idRegistro, String idPaciente, PresionArterial presionArterial) {
        this(idRegistro, idPaciente, null, presionArterial, null);
    }

    /**
     * Determina el nivel de estabilidad del paciente con base en el resultado
     * del cálculo de la presión arterial y las mediciones disponibles.
     * 
     * @return "Normal", "Riesgo Moderado" o "Crítico".
     */
    public String determinarNivelEstabilidad() {
        if (presionArterial == null) {
            return "Sin datos";
        }

        // 1. Caso Normal: Presión arterial y demás signos presentes en rango normal
        boolean presionNormal = presionArterial.esRangoNormal();
        boolean tempNormal = (temperatura == null) || temperatura.esRangoNormal();
        boolean fcNormal = (frecuenciaCardiaca == null) || frecuenciaCardiaca.esRangoNormal();

        if (presionNormal && tempNormal && fcNormal) {
            return "Normal";
        }

        // 2. Caso Crítico: PAM en rango extremo de riesgo (<65 o >115 mmHg)
        double pam = presionArterial.calcularPAM();
        if (pam < 65.0 || pam > 115.0) {
            return "Crítico";
        }

        // 3. Caso Alteración Leve / Moderada
        return "Riesgo Moderado";
    }

    /** Getters y Setters */
    public String getIdRegistro() { 
        return idRegistro; 
    }

    /** Actualiza el identificador único del registro. */
    public void setIdRegistro(String idRegistro) { 
        this.idRegistro = idRegistro; 
    }

    /** Obtiene el identificador del paciente al que pertenece la medición. */
    public String getIdPaciente() { 
        return idPaciente; 
    }

    /** Actualiza el identificador del paciente. */
    public void setIdPaciente(String idPaciente) { 
        this.idPaciente = idPaciente; 
    }

    /** Obtiene la fecha y hora exacta de la captura generada por el sistema. */
    public LocalDateTime getFechaHora() { 
        return fechaHora; 
    }

    /** Actualiza la fecha y hora de la captura. */
    public void setFechaHora(LocalDateTime fechaHora) { 
        this.fechaHora = fechaHora; 
    }

    /** Obtiene la instancia de Temperatura asociada al registro. */
    public Temperatura getTemperatura() { 
        return temperatura; 
    }

    /** Actualiza la instancia de Temperatura asociada al registro. */
    public void setTemperatura(Temperatura temperatura) { 
        this.temperatura = temperatura; 
    }

    /** Obtiene la instancia de PresionArterial asociada al registro. */
    public PresionArterial getPresionArterial() { 
        return presionArterial; 
    }

    /** Actualiza la instancia de PresionArterial asociada al registro. */
    public void setPresionArterial(PresionArterial presionArterial) { 
        this.presionArterial = presionArterial; 
    }

    /** Obtiene la instancia de FrecuenciaCardiaca asociada al registro. */
    public FrecuenciaCardiaca getFrecuenciaCardiaca() { 
        return frecuenciaCardiaca; 
    }

    /** Actualiza la instancia de FrecuenciaCardiaca asociada al registro. */
    public void setFrecuenciaCardiaca(FrecuenciaCardiaca frecuenciaCardiaca) { 
        this.frecuenciaCardiaca = frecuenciaCardiaca; 
    }
}