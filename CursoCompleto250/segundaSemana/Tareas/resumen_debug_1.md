# 🕵️‍♂️ Análisis de Fallos: Tarea Debug 1 (Las Del Profe)

> **Resumen rápido de la tarea de depuración**: Lo detectado por ti y las trampas de sintaxis/diseño resueltas.

---

## 🎯 Resumen Ejecutivo

| # | Error / Trampa | Dónde estaba | Qué provocaba | Estado |
|---|---|---|---|:---:|
| **1** | **Falta de llave `}`** | Fin del constructor `Empleado` | Métodos `calcularNomina()` y `toString()` encerrados dentro del constructor. | **Cazado por ti 👏** |
| **2** | **Trampa `void class`** | En las 3 subclases | Error grave de sintaxis (las clases no llevan `void`). | Detectado 🔍 |
| **3** | **`extends Empleados` (plural)** | En las 3 subclases | Intento de heredar de una clase inexistente (la clase padre es `Empleado`). | Detectado 🔍 |
| **4** | **Falta de `;`** | `return` de `EmpleadoFijo` | Error de sintaxis (*Syntax error, insert ";"*). | Detectado 🔍 |
| **5** | **Uso de `:` en vez de `;`** | `return` de `Directivo` | Error de sintaxis al terminar con `:` en vez de `;`. | Detectado 🔍 |
| **6** | **Incongruencia de tipos** | Constructor de `Directivo` | Atributo es `double bonus`, pero recibía `int bonus`. | Detectado 🔍 |
| **7** | **Import innecesario/mal escrito** | Cabecera del archivo | `import java.util.list;` (con minúscula y sin usarse). | Detectado 🔍 |

---

## 💡 Lecciones Clave para tus Clases
1. **El ojo para las llaves:** Descubriste el fallo más difícil de ver a simple vista (la llave faltante del constructor).
2. **Las palabras clave en su sitio:** `void` solo va en métodos sin retorno, nunca delante de `class`.
3. **Plural vs Singular:** Revisa siempre que el nombre tras `extends` coincida exactamente con la clase padre.
4. **Finales de línea en Java:** Todo `return` termina obligatoriamente en punto y coma `;`.
