# 🏷️ Apuntes de HTML5 — Etiquetas, Atributos y Formularios

Las etiquetas HTML son marcas entre corchetes angulares (`<etiqueta>`) que estructuran y definen el contenido de una página web.
* La mayoría tienen etiqueta de **apertura y cierre**: `<p>Texto</p>`.
* Algunas son **autocontenidas** (no llevan cierre): `<img ...>`, `<br>`, `<meta ...>`, `<input ...>`.

---

## 1. Estructura básica de un documento

| Etiqueta | Función |
| :--- | :--- |
| `<!DOCTYPE html>` | Indica al navegador que el documento usa **HTML5**. |
| `<html>` | Contenedor raíz de toda la página (suele llevar `lang="es"`). |
| `<head>` | Metadatos (`<meta charset="UTF-8">`), `<title>` y enlaces a CSS/scripts (no visible). |
| `<body>` | Contiene todo lo visible de la página. |

---

## 2. Estructura semántica y contenedores

* **`<header>`:** cabecera de la página o de una sección.
* **`<nav>`:** bloque principal de navegación o enlaces.
* **`<main>`:** contenido principal y único del documento.
* **`<section>`:** agrupa contenido temático.
* **`<article>`:** contenido independiente y reutilizable (una noticia, una entrada).
* **`<aside>`:** contenido complementario o barra lateral.
* **`<footer>`:** pie de página o de sección.
* **`<div>` y `<span>`:** contenedores genéricos (sin significado semántico) de **bloque** y de **línea**, usados para aplicar estilos o agrupar elementos.

---

## 3. Texto, enlaces y listas

* **`<h1>` a `<h6>`:** títulos y subtítulos por jerarquía (`<h1>` es el principal).
* **`<p>`:** párrafo de texto.
* **`<a href="...">`:** enlace o hipervínculo.
* **`<strong>` y `<em>`:** énfasis fuerte (negrita semántica) y énfasis (cursiva semántica).
* **`<br>`:** salto de línea.
* **`<ul>` y `<ol>`:** listas desordenadas (viñetas) y ordenadas (numeradas), con elementos **`<li>`** dentro.

---

## 4. Multimedia y tablas

* **`<img src="..." alt="...">`:** inserta una imagen (`alt` es obligatorio para accesibilidad y si falla la carga).
* **`<video>` y `<audio>`:** reproducen vídeo y audio.
* **`<table>`:** tablas de datos (no usar para maquetar), con **`<thead>`**, **`<tbody>`**, **`<tr>`** (fila), **`<th>`** (celda de encabezado) y **`<td>`** (celda de dato).

---

## 5. Formularios y envío al servidor (`GET` vs. `POST`)

> **Nota:** HTML no tiene "métodos" como un lenguaje de programación; en HTML se trabaja con **etiquetas**, **atributos** y el **método HTTP** de envío del formulario (`method`).

* **Etiquetas de formulario:** `<form>`, `<label>`, `<input>`, `<textarea>`, `<select>`, `<button>`.
* **`<form method="...">`:**
  * **`GET`:** envía los datos en la **URL** (visible; ideal para búsquedas y filtros no sensibles).
  * **`POST`:** envía los datos en el **cuerpo** de la petición HTTP (adecuado para altas, contraseñas o archivos).

---

## 6. Atributos comunes

Configuran el comportamiento o identifican los elementos:
* **Globales** (valen en casi cualquier etiqueta): `id`, `class`, `style`, `title`, `lang`, `data-*`.
* **Específicos:** `href` (enlaces), `src` y `alt` (imágenes), `type`, `name`, `value`, `required` (formularios).

---

## 7. Ejemplo rápido

```html
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Ejemplo HTML</title>
</head>
<body>
  <main>
    <h1>Hola mundo</h1>
    <p>Visita <a href="https://example.com">este enlace</a>.</p>
  </main>
</body>
</html>
```
