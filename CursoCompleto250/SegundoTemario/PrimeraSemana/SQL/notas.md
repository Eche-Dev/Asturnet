# 📝 Apuntes de Clase — Arquitectura Web, HTTP y Protocolos (UF2405)

## 1. Flujo de una aplicación web

```text
Cliente ligero (Navegador) ──HTTP──▶ Servidor Web ──▶ Servidor de Aplicaciones ──SQL──▶ Servidor de Datos (BD)
```

* **Cliente ligero:** el navegador (Chrome, Firefox…). Solo presenta la interfaz y envía peticiones.
* **Servidor Web:** atiende peticiones HTTP y sirve archivos estáticos (HTML, CSS, imágenes).
* **Servidor de Aplicaciones:** ejecuta la lógica de negocio (Java, Python, etc.).
* **Servidor de Datos:** almacena y consulta la información de forma persistente (MySQL, PostgreSQL…).

---

## 2. Cabeceras HTTP más utilizadas

### 📤 De petición (`Cliente ──▶ Servidor`)

| Cabecera | Qué indica | Ejemplo |
| :--- | :--- | :--- |
| **`Host`** | Dominio del servidor al que va la petición | `developer.mozilla.org` |
| **`User-Agent`** | Navegador o programa que realiza la petición | `Mozilla/5.0 ... Firefox` |
| **`Accept`** | Formatos de respuesta que admite el cliente | `text/html`, `application/json` |
| **`Accept-Language`** | Idioma preferido por el cliente | `es-ES` |
| **`Accept-Encoding`** | Compresiones que admite | `gzip` |
| **`Authorization`** | Credenciales o token del cliente | `Bearer <token>` |
| **`Cookie`** | Cookies que el navegador devuelve al servidor | `SESSIONID=abc123` |
| **`Referer`** | Página desde la que llegó el usuario | `https://google.com` |
| **`If-Modified-Since` / `If-None-Match`** | Pide el recurso solo si ha cambiado desde la última copia | Fecha o *ETag* |

### 📥 De respuesta (`Servidor ──▶ Cliente`)

| Cabecera | Qué indica | Ejemplo / Detalle |
| :--- | :--- | :--- |
| **`Content-Type`** | Tipo de contenido devuelto | `text/html; charset=UTF-8`, `application/json` |
| **`Content-Length`** | Tamaño en bytes del cuerpo | `99` |
| **`Server`** | Software que utiliza el servidor | `Apache`, `Nginx` |
| **`Set-Cookie`** | Crea o actualiza una cookie en el navegador | `Set-Cookie: SESSIONID=...; HttpOnly; Secure` |
| **`Location`** | URL de destino en redirecciones (`301`, `302`) | `https://ejemplo.es/nuevo` |
| **`Cache-Control`** | Cómo y cuánto tiempo guardar en caché | `max-age=3600` (1 h), `no-cache` (revalida), `no-store` (no guarda, ej. bancos) |
| **`Expires`** | Fecha límite en la que caduca el contenido | Fecha HTTP |
| **`Last-Modified`** | Fecha de la última modificación del recurso | Fecha HTTP |
| **`Access-Control-Allow-Origin`** | Qué orígenes externos pueden acceder (CORS) | `*` o `https://miweb.es` |

### 🔄 Comunes (petición y respuesta)

| Cabecera | Qué indica |
| :--- | :--- |
| **`Connection`** | Si la conexión TCP se mantiene abierta (`keep-alive`) o se cierra (`close`) |
| **`Date`** | Fecha y hora en que se generó el mensaje |

---

## 3. Conceptos clave de red y web

### 💾 Caché y dirección de pruebas
* **Caché:** memoria donde se guarda una copia temporal de un recurso para no volver a descargarlo.
* **URL de desarrollo:** `http://localhost:8080/`
  * Estructura: `Protocolo://IP_o_Dominio:Puerto/ruta`
  * `localhost` (`127.0.0.1`) apunta siempre a tu propia máquina.

### 🔌 Puertos más conocidos
Un **puerto** (del `0` al `65535`) identifica a qué servicio o programa va dirigida la conexión dentro de una máquina (`IP` = máquina, `Puerto` = servicio).

| Puerto | Protocolo / Servicio | Para qué sirve |
| :---: | :--- | :--- |
| **20 / 21** | **FTP** | Transferencia de archivos |
| **22** | **SSH** | Acceso remoto seguro a una terminal |
| **25** | **SMTP** | Envío de correos electrónicos entre servidores |
| **53** | **DNS** | Traduce dominio ↔ IP (ej. `google.com` ↔ `142.250.27.102`) |
| **67 / 68** | **DHCP** | Asigna IPs automáticamente en una red |
| **80** | **HTTP** | Web sin cifrar |
| **443** | **HTTPS** | Web segura (cifrada con TLS) |
| **3306** | **MySQL / MariaDB** | Base de datos relacional |
| **8080** | **HTTP alternativo** | Servidores de desarrollo (Tomcat, Java…) |

### 🔤 Codificación `UTF-8`
El *charset* indica cómo se convierten las letras en bytes. Si cliente y servidor usan distinto juego de caracteres aparecen fallos como `Método` → `MÃ©todo`. **`UTF-8`** es el estándar web actual:
* **HTML:** `<meta charset="UTF-8">`
* **Cabecera HTTP:** `Content-Type: text/html; charset=UTF-8`
* **Java:** `cuerpo.getBytes(StandardCharsets.UTF_8)`
* **MySQL:** `utf8mb4`

---

## 4. Protocolos y tecnologías (siglas esenciales)

* **TCP/IP** (*Transmission Control Protocol / Internet Protocol*): **IP** asegura que el paquete llega a la máquina correcta; **TCP** asegura que llega completo y en orden.
* **HTTP / HTTPS** (*HyperText Transfer Protocol*): protocolo de transferencia de recursos web (sin estado).
* **HTML** (*HyperText Markup Language*): define la **estructura y contenido** de la página.
* **CSS** (*Cascading Style Sheets*): define el **aspecto visual** (colores, tamaños, disposición).
* **XML** (*eXtensible Markup Language*): formato de intercambio de datos con etiquetas propias.
* **XSL** (*eXtensible Stylesheet Language*): transforma documentos XML a otros formatos como HTML.
* **SOAP** (*Simple Object Access Protocol*): servicios web formales que intercambian mensajes en XML.
* **REST** (*REpresentational State Transfer*): estilo de arquitectura de servicios web que usa los métodos HTTP (`GET`, `POST`, `PUT`, `DELETE`) sobre URLs.
* **JSON** (*JavaScript Object Notation*): formato ligero de intercambio de datos clave–valor:
  ```json
  {"hora": "12:00"}
  ```

---

## 5. Ejemplos prácticos de mensajes HTTP

### 🍪 Sesión con Cookie (el estado vive en el servidor)
1. El servidor crea la sesión y manda el identificador en `Set-Cookie`:
   ```http
   HTTP/1.1 200 OK
   Set-Cookie: SESSIONID=oj43ht8g9239hghujhs
   ```
2. En la siguiente petición, el navegador lo devuelve automáticamente:
   ```http
   GET /perfil HTTP/1.1
   Cookie: SESSIONID=oj43ht8g9239hghujhs
   ```

### 🔑 Autenticación con Token JWT (el estado viaja firmado en el cliente)
El cliente envía el token (`cabecera.datos.firma`) y el servidor solo verifica la firma:
```http
GET /api/perfil HTTP/1.1
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjoiQW5hIn0.firma
```

### 📨 Petición `POST` creando un recurso en una API REST
Petición del cliente enviando JSON:
```http
POST /api/nombreapi HTTP/1.1
Host: www.dicampus.es
Content-Type: application/json
Content-Length: 43

{"nombre": "Ana", "identificacion": "Alumna1"}
```
Respuesta del servidor al crear el recurso:
```http
HTTP/1.1 201 Created
```
