# Tema 1. Arquitectura web y protocolos

- **Duración orientativa:** 8 horas
- **Criterios de evaluación:** CE1.1 (componentes del modelo multicapa), CE1.2 (TCP/IP, HTTP, HTML, XML, XSL, SOAP), CE1.3 (J2EE y .NET), CE1.7 (sesión de aplicación).

---

## Análisis del tema

Este tema sienta las bases de cómo funciona cualquier aplicación web moderna por debajo: la comunicación cliente–servidor sobre HTTP, qué papel cumple cada pieza en el servidor y cómo se mantiene el estado (sesiones) sobre un protocolo que por diseño es sin estado. Dominar los métodos HTTP, los códigos de estado y la diferencia entre cookie, sesión y token es imprescindible tanto para entender los temas siguientes como para preguntas teóricas de examen.

---

## Resumen de lo más importante

### 1. Arquitectura cliente–servidor (4 componentes)
El navegador actúa como **cliente ligero** (apenas tiene lógica de negocio; presenta interfaz y recoge acciones).

| Componente | Función principal | Ejemplos |
| :--- | :--- | :--- |
| **Cliente ligero** | Mostrar interfaz (HTML/CSS) y ejecutar scripts de presentación | Chrome, Firefox, Safari |
| **Servidor web** | Atender peticiones HTTP, servir ficheros estáticos y delegar lo dinámico | Apache HTTPD, Nginx, IIS |
| **Servidor de aplicaciones** | Ejecutar la lógica de negocio (servlets, componentes, servicios) | Tomcat, Jetty, WildFly, Spring Boot |
| **Servidor de bases de datos** | Almacenar y consultar datos de forma persistente | PostgreSQL, MySQL, Oracle, SQL Server |

> En la práctica, servidor web y servidor de aplicaciones suelen ir juntos (p. ej. Tomcat), pero conviene separar responsabilidades.

### 2. El protocolo HTTP
- Funciona sobre **TCP/IP** con esquema **petición–respuesta** y es **sin estado (*stateless*)**: cada petición es independiente.
- **Petición:** método + URL + cabeceras + cuerpo (opcional).
- **Respuesta:** código de estado + cabeceras + cuerpo (HTML, JSON, etc.).
- **HTTPS** = HTTP sobre **TLS** (cifra el tráfico y autentica al servidor).

| Método | Uso | ¿Tiene cuerpo? | ¿Idempotente? |
| :--- | :--- | :--- | :--- |
| `GET` | Obtener un recurso | No | Sí |
| `POST` | Crear un recurso / enviar formulario | Sí | No |
| `PUT` | Reemplazar un recurso completo | Sí | Sí |
| `PATCH` | Modificar parcialmente un recurso | Sí | No necesariamente |
| `DELETE` | Eliminar un recurso | Normalmente no | Sí |

*Idempotente = repetir la operación produce el mismo efecto que ejecutarla una sola vez.*

### 3. Códigos de estado y cabeceras clave
- **1xx:** Informativos (`100 Continue`).
- **2xx:** Éxito (`200 OK`, `201 Created`, `204 No Content`).
- **3xx:** Redirección (`301 Moved Permanently`, `302 Found`, `304 Not Modified`).
- **4xx:** Error del cliente (`400 Bad Request`, `401 Unauthorized`, `403 Forbidden`, `404 Not Found`).
- **5xx:** Error del servidor (`500 Internal Server Error`, `503 Service Unavailable`).
- **Cabeceras frecuentes:** petición (`Host`, `User-Agent`, `Accept`, `Cookie`, `Authorization`); respuesta (`Content-Type`, `Content-Length`, `Set-Cookie`, `Cache-Control`).

### 4. Tecnologías y protocolos habituales
- **Red/transporte y aplicación:** TCP/IP, HTTP/HTTPS.
- **Presentación:** HTML (estructura), CSS (aspecto), JavaScript (comportamiento en cliente).
- **Datos e integración:** XML, XSL(T) (transformar XML a otros formatos), **SOAP** (servicios XML con contrato WSDL) y **REST / JSON** (servicios ligeros sobre HTTP).

### 5. Sesiones y estado
- **Cookie:** pequeño dato guardado por el navegador y reenviado en cada petición (`Set-Cookie` / `Cookie`). Nunca guardar datos sensibles en ellas.
- **Sesión:** datos del usuario guardados en el **servidor**, asociados a un identificador enviado en una cookie (p. ej. `JSESSIONID`).
- **Alternativa moderna:** tokens **JWT** en la cabecera `Authorization`.
- **Seguridad:** flags `HttpOnly`, `Secure`, caducidad de sesión y regenerar el ID tras el login.

### 6. Modelos de programación estándar
- **Java / Jakarta EE** (antes J2EE, JVM): Servlets, JSP, JSF, Jakarta REST; persistencia con JPA/Hibernate y JDBC; servidores Tomcat, WildFly, Payara; frameworks como Spring Boot.
- **.NET** (.NET runtime): C#, ASP.NET (MVC, Razor, Web API); persistencia con Entity Framework y ADO.NET; servidores Kestrel, IIS.
