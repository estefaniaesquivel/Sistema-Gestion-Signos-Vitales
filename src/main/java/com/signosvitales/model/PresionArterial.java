package com.signosvitales.model;

public class PresionArterial {
    private double sistolica;  // PAS (presion arterial sistolica)
    private double diastolica; // PAD (presion arterial diastolica)

    public PresionArterial(double sistolica, double diastolica) {
        if (sistolica <= 0 || diastolica <= 0 || sistolica <= diastolica) {
            throw new IllegalArgumentException("PAS y PAD deben ser > 0 y PAS > PAD.");
        }
        this.sistolica = sistolica;
        this.diastolica = diastolica;
    }

    // Cálculo pam = [(pad * 2) + pas] / 3
    public double calcularPAM() {
        return ((diastolica * 2) + sistolica) / 3.0;
    }

    // Evaluación directa del diagrama para saber si está en rango normal (Estable)
    public boolean esRangoNormal() {
        double pam = calcularPAM();
        return pam >= 70.0 && pam <= 100.0;
    }

    // Retorna el diagnóstico del diagrama
    public String evaluarEstado() {
        double pam = calcularPAM();
        if (pam < 70.0) {
            return "Hipotensión (Baja)";
        } else if (pam >= 70.0 && pam <= 100.0) {
            return "Estable";
        } else {
            return "Hipertensión (Alta)";
        }
    }

    public double getSistolica() { return sistolica; }
    public double getDiastolica() { return diastolica; }
}
