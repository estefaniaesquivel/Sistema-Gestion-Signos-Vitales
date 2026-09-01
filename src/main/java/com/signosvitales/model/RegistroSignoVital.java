package com.signosvitales.model;

import java.time.LocalDateTime;

/**
 * Representa una captura puntual de los signos vitales de un paciente.
 * Almacena la fecha y hora generadas automáticamente, junto con las
 * tres mediciones fisiológicas validadas por sus respectivas clases.
 * 
 * RegistroSignoVital recibe la instancia de PresionArterial (junto a Temperatura y FrecuenciaCardiaca) y ejecuta su propio método calcularNivelEstabilidad()
 * para determinar el veredicto global de estabilidad del paciente(Estable, Riesgo Moderado, Crítico).
 * Por el momento 
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


    /**
     * Evalúa las mediciones fisiológicas para determinar el veredicto general.
     * Consulta el estado individual mediante esRangoNormal() de cada signo vital (Temperatura, PresionArterial, FrecuenciaCardiaca).
     * 
     * @return "Estable", "Riesgo Moderado" o "Crítico".
     */
    public String calcularNivelEstabilidad() {
        boolean tempEstable = (temperatura != null) && temperatura.esRangoNormal();
        boolean presionEstable = (presionArterial != null) && presionArterial.esRangoNormal();
        boolean fcEstable = (frecuenciaCardiaca != null) && frecuenciaCardiaca.esRangoNormal();

        int signosNormales = 0;
        if (tempEstable) signosNormales++;
        if (presionEstable) signosNormales++;
        if (fcEstable) signosNormales++;

        /**Clasificación del veredicto final*/ 
        if (signosNormales == 3) {
            return "Estable";          // Todos los signos normales (Semáforo Verde)
        } else if (signosNormales == 1 || signosNormales == 2) {
            return "Riesgo Moderado"; // 1oó 2 signos alterados (Semáforo Amarillo)
        } else {
            return "Crítico";         // Todos los signos alterados (Semáforo Rojo)
        }
    }
}