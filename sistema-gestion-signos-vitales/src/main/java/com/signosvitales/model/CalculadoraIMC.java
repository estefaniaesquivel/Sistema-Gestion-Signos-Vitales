package com.signosvitales.model;

/**
 * Servicio encargado de calcular y clasificar el IMC (Cumple SRP).
 * Contempla diferenciación por Sexo, Categoría Etaria y Estado de Embarazo.
 */
public class CalculadoraIMC {

    /**
     * Calcula el valor numérico del IMC.
     */
    public static double calcularIMC(double pesoKg, double estaturaMetros) {
        if (estaturaMetros <= 0 || pesoKg <= 0) {
            throw new IllegalArgumentException("El peso y la estatura deben ser mayores a cero.");
        }
        return pesoKg / (estaturaMetros * estaturaMetros);
    }

    /**
     * Clasifica el IMC del paciente tomando en cuenta su Edad, Sexo y si está Embarazada.
     * 
     * @param imc Valor del IMC actual.
     * @param edadAnios Edad en años cumplidos.
     * @param sexo Sexo biológico del paciente.
     * @param esEmbarazada Indica si la paciente se encuentra en estado de gestación.
     * @param imcPregestacional IMC previo al embarazo (requerido si esEmbarazada = true, de lo contrario enviar 0).
     */
    public static String clasificarIMC(double imc, int edadAnios, Sexo sexo, boolean esEmbarazada, double imcPregestacional) {
        if (sexo == null) {
            throw new IllegalArgumentException("El sexo no puede ser nulo.");
        }

        // Validación y cálculo para Embarazo
        if (esEmbarazada) {
            if (sexo == Sexo.MASCULINO) {
                throw new IllegalArgumentException("Un paciente de sexo masculino no puede estar embarazada.");
            }
            if (edadAnios < 7 || edadAnios > 75) {
                throw new IllegalArgumentException("El embarazo solo se contempla para pacientes femeninas entre 7 y 75 años.");
            }
            return clasificarIMCEmbarazo(imcPregestacional);
        }

        // Pediatría y Adolescencia (menores de 18 años)
        if (edadAnios < 18) {
            return clasificarIMCPediatrico(imc, edadAnios, sexo);
        } 
        
        // Adultos y Adultos Mayores
        CategoriaEtaria categoriaEtaria = CategoriaEtaria.obtenerPorEdad(edadAnios);
        return clasificarIMCAdulto(imc, sexo, categoriaEtaria);
    }

    /**
     * Clasificación de IMC para Adultos diferenciada por Sexo y Categoria Etaria.
     */
    private static String clasificarIMCAdulto(double imc, Sexo sexo, CategoriaEtaria categoriaEtaria) {
        if (sexo == Sexo.FEMENINO) {
            // Umbrales para Mujeres Adultas
            if (categoriaEtaria == CategoriaEtaria.ADULTO_MAYOR) {
                if (imc < 23.0) return "Bajo peso";
                if (imc <= 27.9) return "Normal";
                if (imc <= 31.9) return "Sobrepeso";
                return "Obesidad";
            } else {
                if (imc < 18.5) return "Bajo peso";
                if (imc <= 23.9) return "Normal";
                if (imc <= 28.9) return "Sobrepeso";
                return "Obesidad";
            }
        } else {
            // Umbrales para Hombres Adultos
            if (categoriaEtaria == CategoriaEtaria.ADULTO_MAYOR) {
                if (imc < 23.0) return "Bajo peso";
                if (imc <= 27.9) return "Normal";
                if (imc <= 32.9) return "Sobrepeso";
                return "Obesidad";
            } else {
                if (imc < 18.5) return "Bajo peso";
                if (imc <= 24.9) return "Normal";
                if (imc <= 29.9) return "Sobrepeso";
                return "Obesidad";
            }
        }
    }

    /**
     * Clasificación Pediátrica/Adolescente ajustada por Sexo y Edad.
     */
    private static String clasificarIMCPediatrico(double imc, int edadAnios, Sexo sexo) {
        double umbralBajo = (sexo == Sexo.MASCULINO) ? 14.5 : 14.0;
        double factorEdad = (sexo == Sexo.MASCULINO) ? 0.30 : 0.35;
        
        double umbralNormal = 18.0 + (edadAnios * factorEdad);
        double umbralSobrepeso = umbralNormal + 3.5;

        if (imc < umbralBajo) {
            return "Bajo peso";
        } else if (imc <= umbralNormal) {
            return "Normal";
        } else if (imc <= umbralSobrepeso) {
            return "Sobrepeso";
        } else {
            return "Obesidad";
        }
    }

    /**
     * Clasificación y recomendación de ganancia de peso según el IMC pregestacional.
     */
    private static String clasificarIMCEmbarazo(double imcPregestacional) {
        if (imcPregestacional <= 0) {
            throw new IllegalArgumentException("Se requiere el IMC pregestacional válido para evaluar el embarazo.");
        }

        if (imcPregestacional < 18.5) {
            return "Embarazo con Bajo Peso Pregestacional (Ganancia recomendada: 12.5 - 18 kg)";
        } else if (imcPregestacional <= 24.9) {
            return "Embarazo con Peso Normal Pregestacional (Ganancia recomendada: 11.5 - 16 kg)";
        } else if (imcPregestacional <= 29.9) {
            return "Embarazo con Sobrepeso Pregestacional (Ganancia recomendada: 7 - 11.5 kg)";
        } else {
            return "Embarazo con Obesidad Pregestacional (Ganancia recomendada: 5 - 9 kg)";
        }
    }
}