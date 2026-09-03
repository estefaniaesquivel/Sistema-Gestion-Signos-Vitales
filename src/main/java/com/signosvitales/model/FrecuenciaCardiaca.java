package com.signosvitales.model;

/**
 * Representa y encapsula la medición de la frecuencia cardíaca de un paciente.
 * Proporciona métodos para validar si la lectura se encuentra dentro del rango
 * fisiológico normal y para obtener un diagnóstico cualitativo básico (Bradicardia,
 * Estable o Taquicardia).
 */
public class FrecuenciaCardiaca {

    /**
     * El atributo bpm se declara como private para aplicar el principio
     *  de encapsulamiento. Esto impide que otras clases modifiquen la variable 
     * directamente con valores no válidos (como números negativos o lecturas imposibles), 
     * obligando a que cualquier consulta o cambio se realice de forma controlada 
     * mediante getBpm() y setBpm(). Protege la integridad de los datos médicos y 
     * permite agregar reglas de validación sin alterar el resto del sistema.
     */
    private int bpm; /**latidos por minuto (del inglés beats per minute)*/

    public FrecuenciaCardiaca(int bpm) {
        this.bpm = bpm; /**Crea el objeto bpm */

        /**
         * bpm es el número entero que el médico observa en su baumanómetro digital u 
         * oxímetro, y luego escribe en el campo de texto correspondiente dentro de
         * la interfaz gráfica.
         */
    }

    /**
     * Evalúa si la frecuencia cardíaca se encuentra dentro del rango fisiológico normal para un adulto.
     *
     * @return {@code true} si la frecuencia cardíaca está entre 60 y 100 bpm (inclusive);
     *         {@code false} en caso contrario.
     */
    public boolean esRangoNormal() {
        return this.bpm >= 60 && this.bpm <= 100;
    }


    /**
     * Este método, evaluarEstado(), toma el valor entero de bpm (ingresado por el médico) y 
     * devuelve un diagnóstico textual cualitativo evaluando la velocidad del pulso 
     * contra los límites clínicos estándar de un adulto.
     * 
     * Bradicardia (<60 bpm): El ritmo cardíaco es más lento de lo normal. En reposo, esto puede 
     * significar que el corazón no está bombeando suficiente sangre oxigenada al cuerpo 
     * con la frecuencia adecuada (salvo en atletas de alto rendimiento, donde es normal).
     * 
     * 
     * Estable (60 a 100 bpm): Es el rango fisiológico normocárdico en un adulto. El corazón bombea
     * a un ritmo óptimo para mantener la perfusión de los tejidos sin un esfuerzo excesivo.
     * 
     * 
     * Taquicardia (>100 bpm): El ritmo cardíaco es acelerado. En reposo, indica un estado
     *  de estrés fisiológico, fiebre, deshidratación, dolor agudo o alteración cardíaca que 
     * requiere atención.
     * 
     * 
     */
    public String evaluarEstado() {
        if (this.bpm < 60) {
            return "Bradicardia (Baja)";
        } else if (this.bpm <= 100) {
            return "Estable";
        } else {
            return "Taquicardia (Alta)";
        }
    }


                            /** Getters y Setters */

    /**
     * Lee y devuelve el valor que tiene guardado la variable privada bpm
     * @return int, porque indica que el método va a retornar un número entero 
     * como respuesta al lugar donde lo llamen. No existen los "medios latidos"
     */
    public int getBpm() { 
        return bpm; 
    }

    /**
     * Recibe un nuevo valor por parámetro y actualiza la variable interna bpm.
     * Tiene void porque no devuelve ningún resultado; solo realiza la acción 
     * de guardar/actualizar la variable.
     */
    public void setBpm(int bpm) { 
        this.bpm = bpm; 
    }
}
