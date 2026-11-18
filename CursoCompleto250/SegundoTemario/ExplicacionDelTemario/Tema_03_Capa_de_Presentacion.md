# Tema 3. La capa de presentación

- **Duración orientativa:** 12 horas
- **Criterios de evaluación:** CE1.5 (HTML), CE1.6 (scripting en cliente: JavaScript), CE1.8 (lenguajes orientados a objetos en servidor: JSP, ASP, Servlets, PHP).

---

## Análisis del tema

Este tema cubre todo lo que ocurre entre el navegador y la parte del servidor encargada de preparar las vistas. Combina tecnologías de **cliente** (HTML5, CSS, JavaScript/DOM, `fetch` + JSON) con generación dinámica en **servidor** (Servlets y motores de plantillas). En la práctica y evaluación es muy importante recordar las reglas de seguridad de esta capa: **la validación del cliente es solo comodidad, nunca seguridad**, y **toda salida debe escaparse para evitar XSS**.

---

## Resumen de lo más importante

### 1. Las dos mitades de la capa de presentación
- **Lado cliente (navegador):** HTML (estructura), CSS (aspecto y disposición), JavaScript (comportamiento, validación, peticiones asíncronas).
- **Lado servidor:** Servlets / JSP / Thymeleaf (Java), PHP, ASP.NET, Node.js para generar HTML dinámico o responder JSON.

### 2. HTML5 y formularios
- **Etiquetas semánticas:** `<header>`, `<nav>`, `<main>`, `<section>`, `<article>`, `<footer>` (mejoran accesibilidad y SEO).
- **Accesibilidad:** atributo `alt` en imágenes, `<label>` asociado a cada campo, jerarquía correcta de títulos (`h1`…`h6`). Tablas (`<table>`) solo para datos, no para maquetar.
- **Formularios (`<form>`):**
  - `method="GET"`: datos en la URL (búsquedas).
  - `method="POST"`: datos en el cuerpo de la petición (altas, contraseñas).
  - Validación nativa HTML5: `required`, `min`, `max`, `pattern`, `type="email|number|date"`.

### 3. CSS básico
- **Conceptos clave:** modelo de caja (contenido, `padding`, `border`, `margin`), especificidad de selectores, **Flexbox** y **Grid** para maquetación, y **`@media` queries** para diseño adaptable (*responsive*).

### 4. JavaScript en el cliente, DOM y AJAX (`fetch`)
- **DOM (*Document Object Model*):** representación en árbol de objetos del HTML.
- **Eventos:** `addEventListener("submit", ...)` + `evento.preventDefault()` para interceptar formularios (mismo patrón observador visto en UF2404).
- **Peticiones asíncronas (`fetch` + `async/await` + JSON):** actualizan partes de la página sin recargarla completa.
- **Precaución:** usar `textContent` en lugar de `innerHTML` al insertar datos de usuario para evitar inyección de scripts.

### 5. Generación dinámica en servidor
- **Servlet (Java):** clase que hereda de `HttpServlet` y sobrescribe `doGet`/`doPost` usando `HttpServletRequest` y `HttpServletResponse`. Ciclo de vida gestionado por el contenedor (`init`, `service`, `destroy`).
- **Plantillas:** mezclar HTML dentro de Java es incómodo, por eso se usan plantillas HTML con interpolación:
  - **JSP / JSTL** (`${producto.nombre}`), **PHP** (`<?php ... ?>`), **ASP.NET Razor** (`@Model.Nombre`), **Thymeleaf / Jinja** (`{{ producto.nombre }}`).

### 6. Seguridad básica en la presentación
- **XSS (*Cross-Site Scripting*):** inyección de JavaScript malicioso en páginas vistas por otros. **Defensa:** escapar siempre toda salida (`textContent`, `htmlspecialchars`, `th:text`).
- **CSRF:** web maliciosa que provoca acciones del usuario autenticado. **Defensa:** tokens anti-CSRF y cookies `SameSite`.
- **Regla de oro:** *Valida la entrada (siempre en el servidor) y escapa la salida.* Usar HTTPS y cabeceras como `Content-Security-Policy`.
