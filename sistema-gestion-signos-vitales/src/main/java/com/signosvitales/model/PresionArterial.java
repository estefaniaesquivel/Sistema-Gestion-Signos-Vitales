package com.signosvitales.model;

/**
 * Representa las mediciones de Presión Arterial y su evaluación clínica
 * adaptada a la CategoriaEtaria e impacto del IMC.
 */
public class PresionArterial {
    private double sistolica;  // PAS (Presión Arterial Sistólica)
    private double diastolica; // PAD (Presión Arterial Diastólica)

    public PresionArterial(double sistolica, double diastolica) {
        if (sistolica <= 0 || diastolica <= 0 || sistolica <= diastolica) {
            throw new IllegalArgumentException("PAS y PAD deben ser > 0 y PAS > PAD.");
        }
        this.sistolica = sistolica;
        this.diastolica = diastolica;
    }

    /**
     * Presión Arterial Media (PAM): PAM = [(PAD * 2) + PAS] / 3
     */
    public double calcularPAM() {
        return ((diastolica * 2) + sistolica) / 3.0;
    }

    /**
     * Evalúa si la presión arterial está en rango normal considerando la CategoriaEtaria.
     */
    public boolean esRangoNormal(CategoriaEtaria categoria) {
        if (categoria == null) {
            throw new IllegalArgumentException("La categoría etaria no puede ser nula.");
        }

        return switch (categoria) {
            case NEONATO_LACTANTE -> (sistolica >= 60 && sistolica <= 90) && (diastolica >= 30 && diastolica <= 60);
            case PREESCOLAR       -> (sistolica >= 75 && sistolica <= 110) && (diastolica >= 45 && diastolica <= 75);
            case ESCOLAR          -> (sistolica >= 80 && sistolica <= 115) && (diastolica >= 50 && diastolica <= 80);
            case ADOLESCENTE      -> (sistolica >= 90 && sistolica <= 120) && (diastolica >= 60 && diastolica <= 80);
            case ADULTO_JOVEN, 
                 ADULTO_MEDIO     -> (sistolica >= 90 && sistolica < 120)  && (diastolica >= 60 && diastolica < 80);
            case ADULTO_MAYOR     -> (sistolica >= 90 && sistolica <= 130) && (diastolica >= 60 && diastolica <= 85);
        };
    }

    /**
     * Evalúa el rango normal integrando la categoría etaria y el resultado de la CalculadoraIMC.
     */
    public boolean esRangoNormal(CategoriaEtaria categoria, double imc, int edadAnios, Sexo sexo, boolean esEmbarazada, double imcPregestacional) {
        boolean normaBase = esRangoNormal(categoria);
        String clasificacionIMC = CalculadoraIMC.clasificarIMC(imc, edadAnios, sexo, esEmbarazada, imcPregestacional);

        // Si la persona presenta Obesidad, los umbrales de alerta de presión arterial son más estrictos
        if (clasificacionIMC.contains("Obesidad") && sistolica >= 120) {
            return false; 
        }

        return normaBase;
    }

    /**
     * Retorna el diagnóstico ajustado por la CategoriaEtaria.
     */
    public String evaluarEstado(CategoriaEtaria categoria) {
        if (categoria == null) {
            throw new IllegalArgumentException("La categoría etaria no puede ser nula.");
        }

        // Definición de límites de hipertensión según etapa etaria (AHA/OMS)
        double pasMaxNormal = switch (categoria) {
            case NEONATO_LACTANTE -> 90;
            case PREESCOLAR       -> 110;
            case ESCOLAR          -> 115;
            case ADOLESCENTE,
                 ADULTO_JOVEN,
                 ADULTO_MEDIO     -> 120;
            case ADULTO_MAYOR     -> 130;
        };

        if (!esRangoNormal(categoria)) {
            if (sistolica < 90 && categoria.ordinal() >= CategoriaEtaria.ADOLESCENTE.ordinal()) {
                return "Hipotensión (Baja)";
            } else if (sistolica > pasMaxNormal) {
                return "Hipertensión (Alta)";
            }
        }

        return "Estable";
    }

    public double getSistolica() { return sistolica; }
    public double getDiastolica() { return diastolica; }
}