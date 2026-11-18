# Tema 2. Arquitectura multicapa

- **Duración orientativa:** 8 horas
- **Criterios de evaluación:** CE1.1 (modelo multicapa), CE1.4 (capas lógicas: presentación, aplicación y datos), CE2.2 (elección de tecnologías de acceso a datos).

---

## Análisis del tema

Este tema explica cómo estructurar el código para que la aplicación no sea un bloque monolítico donde se mezclan HTML, reglas de negocio y SQL. Es un tema clave de diseño: introduce el **modelo de tres capas**, la **inyección de dependencias** mediante interfaces (conectando directamente con lo visto en UF2404) y aclara una confusión habitual de examen: **capas lógicas (*layers*) frente a niveles físicos (*tiers*)**, así como el lugar exacto que ocupa el patrón **MVC**.

---

## Resumen de lo más importante

### 1. El modelo de tres capas
Cada capa tiene una única responsabilidad y **solo conoce a la capa inmediatamente inferior** (dependencias de arriba hacia abajo, nunca al revés ni circulares).

| Capa | Responsabilidad | Contiene | No debe contener |
| :--- | :--- | :--- | :--- |
| **Presentación** | Interactuar con el usuario o cliente | Páginas, controladores, formularios, validación de formato | Reglas de negocio, SQL |
| **Aplicación / negocio** | Reglas y procesos del dominio | Servicios, entidades del dominio, cálculos, validaciones de negocio | HTML, detalles de la BD |
| **Datos / persistencia** | Guardar y recuperar información | Repositorios / DAO, consultas SQL, conexión | Reglas de negocio, interfaz |

**Beneficios:** separación de responsabilidades, bajo acoplamiento y alta cohesión, mantenibilidad, facilidad de pruebas aisladas y reutilización de la lógica desde distintos clientes (web, móvil, API).

### 2. Capas lógicas (*layers*) vs. niveles físicos (*tiers*)
- **Capas:** organización lógica del código.
- **Niveles:** reparto físico de esas capas en máquinas o procesos.
  - *1 nivel:* todo en una máquina (desarrollo, apps pequeñas).
  - *2 niveles:* cliente con lógica + servidor de BD (apps de escritorio clásicas).
  - *3 niveles:* cliente, servidor de aplicaciones y servidor de BD (apps web corporativas).
  - *n niveles:* balanceadores, caché, microservicios, colas (alta disponibilidad y escalado).

### 3. Implementación típica: Controlador → Servicio → Repositorio
- **Repositorio / DAO (`ProductoRepositorio`):** oculta el almacenamiento detrás de una interfaz (`buscar`, `guardar`). Permite cambiar de BD (memoria, fichero, MySQL) sin tocar la lógica.
- **Servicio (`TiendaServicio`):** implementa casos de uso y **reglas de negocio** (p. ej. comprobar stock). Recibe el repositorio por el constructor (**inyección de dependencias**), no hace `new` de la implementación concreta, facilitando las pruebas con dobles/falsos.
- **Controlador (`TiendaControlador`):** recibe la petición, extrae parámetros, llama al servicio y traduce excepciones de negocio en respuestas/mensajes para el usuario.

### 4. Patrón MVC y su relación con las capas
- **MVC (Modelo–Vista–Controlador)** NO sustituye a las tres capas: **vive dentro de la capa de presentación** y se apoya en la capa de negocio.
  - **Modelo:** datos y estado que se muestran (objetos de dominio, **DTO**).
  - **Vista:** presenta el modelo al usuario (HTML, JSP, Thymeleaf, Jinja).
  - **Controlador:** recibe la acción, llama al servicio y elige la vista (Servlet, `@Controller`, vista de Django).

### 5. Errores frecuentes a evitar
- Poner lógica de negocio en el controlador o en la plantilla.
- Repartir SQL por toda la aplicación.
- Crear dependencias circulares entre capas.
- Crear capas «pasamanos» que solo delegan sin aportar valor.
- Exponer entidades de BD directamente al cliente en lugar de usar **DTO** (*Data Transfer Object*).
