# Sistema de Gestión de Signos Vitales

Un sistema web PWA en Java diseñado para que el personal médico capture, consulte y evalúe la estabilidad fisiológica de sus pacientes mediante una interfaz gráfica intuitiva. 

---

¿Por qué un PWA (Progressive Web App)?
Para el personal clínico o usuarios no experimentados en tecnología, implementar una PWA es la opción perfecta porque elimina la barrera de instalar software tradicional: no requiere descargar ejecutables .exe ni enfrentarse a confusas advertencias de virus o permisos de administrador. Con solo un clic desde el navegador, el sistema se integra en el equipo como cualquier programa nativo (con su propio ícono en el escritorio y la barra de tareas) y se actualiza de forma transparente (de manera invisible, automática y sin interrumpir al usuario), garantizando que el personal médico trabaje siempre con la versión más reciente sin realizar configuraciones ni mantenimientos manuales.

---

## Índice

* [Descripción General](#descripción-general)
* [Características Principales](#características-principales)
* [Arquitectura del Sistema](#arquitectura-del-sistema)
* [Tecnologías Utilizadas](#tecnologías-utilizadas)
* [Estructura del Proyecto](#estructura-del-proyecto)
* [Módulos del Sistema](#módulos-del-sistema)
* [Configuración de Dockerfile](#configuración-de-dockerfile)
* [Despliegue DevOps en Render con Docker](#despliegue-devops-en-render-con-docker)
* [Requisitos e Instalación](#requisitos-e-instalación)
* [Equipo de Desarrollo](#equipo-de-desarrollo)

---

## Descripción General

El **Sistema de Gestión de Signos Vitales** permite la captura manual de constantes fisiológicas (temperatura corporal, presión arterial y frecuencia cardíaca) por parte del médico. La aplicación valida las mediciones contra rangos clínicos normativos, calcula la Presión Arterial Media (PAM), evalúa el nivel de estabilidad general y mantiene un registro histórico del paciente.

---

## Características Principales

* **Autenticación de Usuarios:** Control de acceso mediante roles para garantizar la privacidad y seguridad de la información médica.
* **Evaluación Fisiológica Automática:** Clasificación inmediata de lectura de signos vitales (Normal, Riesgo Moderado, Crítico) respaldada por validaciones en el dominio.
* **Persistencia Relacional y ORM:** Gestión de datos con PostgreSQL en Supabase abstraído mediante Spring Data JPA repositories.
* **Contenedorización en DevOps:** Empaquetado ligero multi-etapa con Docker para despliegues reproducibles sin dependencias locales en el servidor.
* **Interfaz Orientada al Usuario:** Flujo visual PWA ligero desacoplado en JavaScript, CSS3 y HTML5.

---

## Tecnologías Utilizadas

| Componente | Tecnología / Herramienta |
| :--- | :--- |
| **Lenguaje de Programación** | Java 17+, Framework Spring Boot (API REST) con Servidor Web Tomcat Embebido <br> <img src="https://miro.medium.com/v2/resize:fit:1200/1*gxXLMIuJDHCH7fwIgEP1cg.png" alt="springboot" width="300"/> |
| **Pruebas de API & Cliente REST** | Bruno API Client <br> <img src="https://devio2024-media.developers.io/image/upload/f_auto,q_auto,w_3840/v1783834490/user-gen-eyecatch/dwdmcf5jwddw60eykrre.png" alt="Bruno" width="300"/> |
| **Interfaz Gráfica (UI)** | JavaScript, CSS3 y HTML5 (PWA) |
| **Contenedorización & DevOps** | Docker (Multi-stage build con Java 17 Temurin) <br> <img src="https://www.docker.com/wp-content/uploads/2022/03/horizontal-logo-monochromatic-white.png" alt="docker" width="300"/> |
| **Alojamiento Cloud (Hosting)** | **Frontend & Backend:** Render (Web Service desplegado con Docker) <br> <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRznRcHFwGdWnxV_iB2TAzQMqbfKiGPw0uP00-npuHSDuVtn1BALmYExnMt&s=10" alt="render" width="300"/> <br><br> **Base de Datos (Host BD):** Supabase <br> <img src="https://miro.medium.com/1*qIupaLEYPaVvP6M2nKcp5Q.png" alt="supabase" width="300"/> |
| **Base de Datos & Persistencia** | PostgreSQL en Supabase con Spring Data JPA (`JpaRepository`) |
| **Entorno de Desarrollo** | Visual Studio Code |
| **Gestión de Versiones** | Git & GitHub |

---

### ¿Por qué la combinación de Render, Docker y Supabase es la elección ideal?

<img src="https://miro.medium.com/1*1YMpP_3xodeGI8nSlTG4ig.png" alt="Arquitectura Render y Supabase" width="600"/>

La arquitectura desacoplada y contenedorizada entre **Render**, **Docker** y **Supabase** es la estrategia óptima para publicar una API en producción sin incurrir en costos de infraestructura ni lidiar con problemas de entorno:

* **Estandarización de entorno (Docker):** Garantiza que la aplicación compile y corra con Java 17 exactamente igual en la máquina local que en la nube de Render, optimizando la RAM a un máximo de 384 MB para no exceder los límites del plan gratuito.
* **Persistencia sin vencimiento:** Render borra los datos locales al entrar en reposo por su sistema de archivos efímero. Al conectar la API desplegada en Docker hacia PostgreSQL en Supabase, los datos clínicos permanecen intactos de manera permanente.
* **Separación de responsabilidades:** Render ejecuta el contenedor Docker con Spring Boot y sirve la PWA, mientras que Supabase administra la capa de base de datos relacional y sus copias de seguridad.
* **Flujo DevOps automatizado:** Cada push a la rama `main` en GitHub activa un pipeline en Render que reconstruye la imagen Docker y despliega la nueva versión sin tiempo de inactividad visible.

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
│   │   │       ├── controller/                     <-- ENDPOINTS REST
│   │   │       │   ├── EstabilidadController.java
│   │   │       │   ├── HistorialController.java
│   │   │       │   ├── LoginController.java
│   │   │       │   └── RegistroSignosController.java
│   │   │       │
│   │   │       ├── service/                        <-- LÓGICA DE NEGOCIO Y REGLAS CLÍNICAS
│   │   │       │   ├── PacienteService.java
│   │   │       │   ├── PacienteServiceImpl.java
│   │   │       │   ├── RegistroSignoVitalService.java
│   │   │       │   ├── RegistroSignoVitalServiceImpl.java
│   │   │       │   └── EvaluadorFisiologico.java
│   │   │       │
│   │   │       ├── repository/                     <-- INTERFACES JPA PARA POSTGRESQL
│   │   │       │   ├── PacienteRepository.java     <-- extends JpaRepository<EntidadPaciente, Long>
│   │   │       │   ├── RegistroSignoVitalRepository.java <-- extends JpaRepository<RegistroSignoVital, Long>
│   │   │       │   └── UsuarioRepository.java      <-- extends JpaRepository<Usuario, Long>
│   │   │       │
│   │   │       ├── model/                          <-- ENTIDADES JPA MAPEADAS A TABLAS DE SUPABASE
│   │   │       │   ├── CategoriaEtaria.java        <-- Enum
│   │   │       │   ├── EntidadPaciente.java        <-- @Entity @Table(name = "pacientes")
│   │   │       │   ├── FrecuenciaCardiaca.java     <-- @Embeddable o @Entity
│   │   │       │   ├── PresionArterial.java        <-- @Embeddable o @Entity
│   │   │       │   ├── RegistroSignoVital.java     <-- @Entity @Table(name = "registros_signos_vitales")
│   │   │       │   ├── Temperatura.java            <-- @Embeddable o @Entity
│   │   │       │   └── Usuario.java                <-- @Entity @Table(name = "usuarios")
│   │   │       │
│   │   │       ├── dto/                            <-- DTOs PARA COMUNICACIÓN HTTP/JSON
│   │   │       │   ├── PacientePeticionDTO.java
│   │   │       │   ├── PacienteRespuestaDTO.java
│   │   │       │   ├── RegistroPeticionDTO.java
│   │   │       │   └── RegistroRespuestaDTO.java
│   │   │       │
│   │   │       └── SignosVitalesApplication.java
│   │   │
│   │   └── resources/
│   │       ├── static/                             <-- FRONTEND (PWA / WEB UI)
│   │       │   ├── index.html
│   │       │   ├── manifest.json                   <-- Requerido para PWA
│   │       │   ├── sw.js                           <-- Service Worker para PWA
│   │       │   ├── css/
│   │       │   │   └── estilos.css
│   │       │   └── js/
│   │       │       └── app.js
│   │       │
│   │       └── application.properties              <-- CONFIGURACIÓN CONEXIÓN POSTGRESQL / SUPABASE
│   │
│   └── test/                                       <-- PRUEBAS DE INTEGRACIÓN CON BASE DE DATOS
│
├── .gitignore
├── Dockerfile                                      <-- CONFIGURACIÓN DE CONTENEDORIZACIÓN DEVOPS
├── pom.xml
└── README.md
