# Sistema de Gestión de Signos Vitales

Un sistema local en Java diseñado para que el personal médico capture, consulte y evalúe la estabilidad fisiológica de sus pacientes mediante una interfaz gráfica intuitiva y un mecanismo de persistencia ligero sin dependencias de motores de bases de datos externos.

---

## Índice

* [Descripción General](#descripción-general)
* [Características Principales](#características-principales)
* [Arquitectura del Sistema](#arquitectura-del-sistema)
* [Tecnologías Utilizadas](#tecnologías-utilizadas)
* [Estructura del Proyecto](#estructura-del-proyecto)
* [Módulos del Sistema](#módulos-del-sistema)
* [Requisitos e Instalación](#requisitos-e-instalación)
* [Equipo de Desarrollo](#equipo-de-desarrollo)

---

## Descripción General

El **Sistema de Gestión de Signos Vitales** permite la captura manual de constantes fisiológicas (temperatura corporal, presión arterial y frecuencia cardíaca) por parte del médico. La aplicación valida las mediciones contra rangos clínicos normativos, calcula la Presión Arterial Media (PAM), evalúa el nivel de estabilidad general y mantiene un registro histórico del paciente.

---

## Características Principales

* **Autenticación de Usuarios:** Control de acceso mediante roles para garantizar la privacidad y seguridad de la información médica.
* **Evaluación Fisiológica Automática:** Clasificación inmediata de lectura de signos vitales (Normal, Riesgo Moderado, Crítico) respaldada por validaciones en el dominio.
* **Persistencia Ligera:** Manejo de datos mediante el patrón DAO sobre archivos planos en formato CSV.
* **Interfaz Orientada al Usuario:** Flujo visual en JavaFX desacoplado mediante vistas FXML.

---

## Arquitectura del Sistema

El sistema implementa una arquitectura en tres capas para garantizar una clara separación de responsabilidades y mantenibilidad:

+-------------------------------------------------------+
|                    CAPA DE PRESENTACIÓN               |
|  vistas FXML (Scene Builder) + Controladores JavaFX   |
+---------------------------+---------------------------+
|
v
+-------------------------------------------------------+
|                    CAPA DE LÓGICA                     |
|  Entidades de Dominio (Paciente, Registro, Signos)    |
+---------------------------+---------------------------+
|
v
+-------------------------------------------------------+
|                  CAPA DE PERSISTENCIA                 |
|      Patrón DAO (UsuarioDAO, PacienteDAO, etc.)       |
+---------------------------+---------------------------+
|
v
+-------------------------------------------------------+
|                    ALMACENAMIENTO                     |
|            Archivos Planos de Texto (.csv)            |
+-------------------------------------------------------+

---

## Tecnologías Utilizadas

| Componente | Tecnología / Herramienta |
| :--- | :--- |
| **Lenguaje de Programación** | Java 17+ |
| **Interfaz Gráfica (UI)** | JavaFX SDK & Scene Builder |
| **Entorno de Desarrollo** | Visual Studio Code |
| **Gestión de Versiones** | Git & GitHub |
| **Mecanismo de Persistencia** | I/O Streams con archivos CSV |

---


## Módulos del Sistema

### 1. Autenticación
Restringe el acceso exclusivamente a usuarios autorizados con rol de médico. Valida credenciales almacenadas y gestiona los mensajes de error en pantalla.

### 2. Gestión de Pacientes
Administra la información general de los pacientes (ID, nombre, edad) y los vincula directamente con sus registros clínicos.

### 3. Registro de Signos Vitales
Permite al médico ingresar la lectura de signos vitales asociada a un paciente. La fecha y hora se capturan de forma automática al momento del registro.

### 4. Evaluación de Estabilidad
* **Temperatura:** Evalúa lecturas en grados Celsius frente a rangos normativos (hipotermia, normal, fiebre).
* **Presión Arterial:** Mide Presión Sistólica (PAS) y Diastólica (PAD), calculando la Presión Arterial Media (PAM) con la fórmula médica estándar:

  $$\text{PAM} = \frac{(2 \times \text{PAD}) + \text{PAS}}{3}$$

* **Frecuencia Cardíaca:** Clasifica las pulsaciones en latidos por minuto (bradicardia, normal, taquicardia).


---

## Estructura del Proyecto

```text
src/
└── com/
    └── signosvitales/
        ├── app/
        │   └── Main.java
        ├── controller/
        │   ├── HistorialController.java
        │   ├── LoginController.java
        │   └── RegistroSignosController.java
        ├── dao/
        │   ├── PacienteDAO.java
        │   ├── RegistroDAO.java
        │   └── UsuarioDAO.java
        ├── model/
        │   ├── EntidadPaciente.java
        │   ├── FrecuenciaCardiaca.java
        │   ├── PresionArterial.java
        │   ├── RegistroSignoVital.java
        │   └── Temperatura.java
        └── view/
            ├── EstabilidadView.fxml
            ├── HistorialPacienteView.fxml
            ├── LoginView.fxml
            └── RegistroSignosView.fxml




