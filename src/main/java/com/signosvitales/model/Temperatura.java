package com.signosvitales.model;

/**
 * Encapsula la medición de la temperatura corporal.
 */
public class Temperatura {
    private double valor; // Grados Celsius (°C)

    public Temperatura(double valor) {
        this.valor = valor;
    }

    /**
     * revisa si la temperatura se encuentra en el rango normal
     * usado para la clase RegistroSignoVital
     * @return
     */
    public boolean esRangoNormal() {
        return this.valor >= 36.0 && this.valor <= 37.5;
    }
    /**
     * categoriza el valor numerico para saber el estado
     */
    public String evaluarEstado() {
        if (this.valor < 36.0) {
            return "Hipotermia (Baja)";
        } else if (this.valor <= 37.5) {
            return "Estable";
        } else {
            return "Fiebre (Alta)";
        }
    }

    public double getValor() { return valor; }/*para consultar */
    public void setValor(double valor) { this.valor = valor; }/*para actualizar */
}
