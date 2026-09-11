# Sistema de Gestión de Signos Vitales

Un sistema web en Java diseñado para que el personal médico capture, consulte y evalúe la estabilidad fisiológica de sus pacientes mediante una interfaz gráfica intuitiva y un mecanismo de persistencia ligero sin dependencias de motores de bases de datos externos para la versión prueba del sistema.

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

## Tecnologías Utilizadas

| Componente | Tecnología / Herramienta |
| :--- | :--- |
| **Lenguaje de Programación** | Java 17+, Framework Spring Boot (API REST) con Servidor Web Tomcat Embebido|
| **Pruebas de API & Cliente REST** | Bruno API Client <img src="https://devio2024-media.developers.io/image/upload/f_auto,q_auto,w_3840/v1783834490/user-gen-eyecatch/dwdmcf5jwddw60eykrre.png" alt="Bruno" width="300"/> |
| **Interfaz Gráfica (UI)** | Javascript, CSS3 y HTML5 |
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

Sistema-Gestion-Signos-Vitales/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/signosvitales/
│   │   │       │
│   │   │       ├── controller/                  <-- CONTROLADORES (ENDPOINTS REST)
│   │   │       │   ├── EstabilidadController.java
│   │   │       │   ├── HistorialController.java
│   │   │       │   ├── LoginController.java
│   │   │       │   └── RegistroSignosController.java
│   │   │       │
│   │   │       ├── service/                     <-- LÓGICA DE NEGOCIO Y EVALUACIÓN
│   │   │       │   ├── PacienteService.java
│   │   │       │   ├── PacienteServiceImpl.java
│   │   │       │   ├── RegistroSignoVitalService.java
│   │   │       │   ├── RegistroSignoVitalServiceImpl.java
│   │   │       │   └── EvaluadorFisiologico.java
│   │   │       │
│   │   │       ├── dao/                         <-- PERSISTENCIA EN ARCHIVOS CSV
│   │   │       │   ├── PacienteDAO.java
│   │   │       │   ├── PacienteDAOImpl.java
│   │   │       │   ├── RegistroDAO.java
│   │   │       │   ├── RegistroDAOImpl.java
│   │   │       │   ├── UsuarioDAO.java
│   │   │       │   └── UsuarioDAOImpl.java
│   │   │       │
│   │   │       ├── model/                       <-- ENTIDADES DE DOMINIO
│   │   │       │   ├── CategoriaEtaria.java      <-- Enum de rangos etarios (edad)
│   │   │       │   ├── EntidadPaciente.java
│   │   │       │   ├── FrecuenciaCardiaca.java
│   │   │       │   ├── PresionArterial.java
│   │   │       │   ├── RegistroSignoVital.java
│   │   │       │   ├── Temperatura.java
│   │   │       │   └── Usuario.java
│   │   │       │
│   │   │       ├── dto/                         <-- OBJETOS DE TRANSFERENCIA DE DATOS
│   │   │       │   ├── PacientePeticionDTO.java
│   │   │       │   ├── PacienteRespuestaDTO.java
│   │   │       │   ├── RegistroPeticionDTO.java  <-- Recibe el formulario HTML/JS
│   │   │       │   └── RegistroRespuestaDTO.java <-- Devuelve el veredicto a la UI
│   │   │       │
│   │   │       ├── util/                        <-- UTILIDADES 
│   │   │       │   └── ManejadorArchivosCSV.java
│   │   │       │
│   │   │       └── SignosVitalesApplication.java
│   │   │
│   │   └── resources/
│   │       ├── static/                          <-- FRONTEND (WEB UI)
│   │       │   ├── index.html
│   │       │   ├── css/
│   │       │   │   └── estilos.css
│   │       │   └── js/
│   │       │       └── app.js
│   │       │
│   │       ├── data/                            <-- PERSISTENCIA CSV (Para versión prueba, futuramente evolucionando a BD para ser funcional)
│   │       │   ├── pacientes.csv
│   │       │   ├── registros.csv
│   │       │   └── usuarios.csv
│   │       │
│   │       └── application.properties
│   │
│   └── test/                                    <-- PRUEBAS UNITARIAS
│
├── .gitignore
├── pom.xml
└── README.md
          


