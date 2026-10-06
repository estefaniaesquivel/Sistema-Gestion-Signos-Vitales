package com.signosvitales.model;

public enum CategoriaEtaria {
    NEONATO_LACTANTE(0, 0),
    PREESCOLAR(1, 5),
    ESCOLAR(6, 12),
    ADOLESCENTE(13, 17),
    ADULTO_JOVEN(18, 39),
    ADULTO_MEDIO(40, 59),
    ADULTO_MAYOR(60, 120);

    private final int edadMinima;
    private final int edadMaxima;

    CategoriaEtaria(int edadMinima, int edadMaxima) {
        this.edadMinima = edadMinima;
        this.edadMaxima = edadMaxima;
    }

    public static CategoriaEtaria obtenerPorEdad(int edadAnios) {
        for (CategoriaEtaria cat : values()) {
            if (edadAnios >= cat.edadMinima && edadAnios <= cat.edadMaxima) {
                return cat;
            }
        }
        return ADULTO_MAYOR;
    }

    public int getEdadMinima() { return edadMinima; }
    public int getEdadMaxima() { return edadMaxima; }
}