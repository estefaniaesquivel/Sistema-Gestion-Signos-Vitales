package com.signosvitales.model;

/**
 * Encapsula la medición de la frecuencia cardíaca.
 */
public class FrecuenciaCardiaca {
    private int bpm; // Latidos por minuto

    public FrecuenciaCardiaca(int bpm) {
        this.bpm = bpm;
    }

    public boolean esRangoNormal() {
        return this.bpm >= 60 && this.bpm <= 100;
    }

    public String evaluarEstado() {
        if (this.bpm < 60) {
            return "Bradicardia (Baja)";
        } else if (this.bpm <= 100) {
            return "Estable";
        } else {
            return "Taquicardia (Alta)";
        }
    }

    public int getBpm() { return bpm; }
    public void setBpm(int bpm) { this.bpm = bpm; }
}
