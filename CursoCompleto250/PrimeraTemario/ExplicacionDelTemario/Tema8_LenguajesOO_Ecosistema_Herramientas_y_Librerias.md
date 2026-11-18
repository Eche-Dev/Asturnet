# 📘 Tema 8: Lenguajes Orientados a Objetos, Ecosistema y Librerías Estándar

> **Correspondencia con el Manual Oficial UF2404:** Páginas 42 a 46.  
> **Objetivo:** Conocer el panorama comparativo de lenguajes OO (Java vs Python, C#, C++, JS), comprender la cadena de herramientas profesionales (IDE, Maven/Gradle, Git, JUnit), dominar las librerías estándar básicas de Java (`java.lang`, `java.util`, `java.time`), aprender a usar `StringBuilder` y dar los primeros pasos con `Streams` y `Optional`.

---

## 🌍 1. Panorama de Lenguajes Orientados a Objetos

El paradigma de objetos se aplica de formas muy distintas según el lenguaje:

* **Lenguajes Puros OO:** Todo absolutamente es un objeto (ej: Smalltalk, Ruby).
* **Lenguajes Híbridos / Multiparadigma:** Combinan objetos con programación imperativa o funcional (ej: Java, C#, C++, Python, JavaScript).

### Tabla Comparativa Oficial del Módulo:

| Lenguaje | Tipado | Ejecución | Gestión de Memoria | Herencia de Clases | Uso Habitual en la Industria |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **Java** | **Estático** | Compilado a Bytecode $\rightarrow$ JVM | Garbage Collector | Simple + Interfaces | Empresa, grandes backends, Android |
| **C#** | **Estático** | Compilado a IL $\rightarrow$ .NET Runtime | Garbage Collector | Simple + Interfaces | Aplicaciones empresariales, escritorio Windows, videojuegos (Unity) |
| **C++** | **Estático** | Compilado a código máquina nativo | Manual (RAII / Punteros) | **Múltiple** | Sistemas operativos, motores gráficos, alto rendimiento |
| **Python** | **Dinámico** | Interpretado (Bytecode en CPython) | Conteo de referencias + GC | **Múltiple** | Inteligencia Artificial, Ciencia de Datos, scripting, APIs web |
| **TypeScript / JS** | Opcional / Dinámico | Motor de navegador (V8) o Node.js | Garbage Collector | Basada en prototipos / Clases | Frontend web, Fullstack Node |

---

## 🎯 2. Tipos de Aplicaciones y Tecnologías en Java

Java abarca todo el espectro de desarrollo en la industria tecnológica:

1. **Consola / Scripts:** Interacción por texto con `Scanner` y `System.out`.
2. **Escritorio (GUI):** Interfaces gráficas locales con **JavaFX** o **Swing**.
3. **Web & Microservicios:** La joya de la corona de Java en el mundo laboral: **Spring Boot** y **Jakarta EE** (APIs REST).
4. **Móvil:** Aplicaciones nativas de **Android** (Java / Kotlin).
5. **Procesamiento por Lotes (Batch):** Tareas masivas programadas en segundo plano con **Spring Batch** y ejecutores concurrentes.

---

## 🧰 3. La Cadena de Herramientas del Desarrollador Profesional

Un programador profesional no programa en el bloc de notas; utiliza un ecosistema integrado:

```
[ IDE ] ──> [ Herramienta de Construcción ] ──> [ Control de Versiones ] ──> [ Pruebas ]
VS Code        Maven (pom.xml)                    Git / GitHub                  JUnit 5
IntelliJ       Gradle (build.gradle)              GitLab
Eclipse
```

* **IDE (Entorno de Desarrollo Integrado):** Reúne editor con autocompletado inteligente, detección de errores en vivo y depurador paso a paso.
* **Herramientas de Construcción (Maven / Gradle):** Descargan librerías automáticamente de internet, compilan el proyecto, pasan los tests y generan el archivo ejecutable final (`.jar`).
* **Depurador (Debugger):** Permite pausar la ejecución en una línea concreta (*Breakpoint*) e inspeccionar qué vale cada variable en ese milisegundo.
* **Pruebas Automatizadas (JUnit):** Código que prueba tu propio código para garantizar que no se rompe nada al actualizar.

---

## 📚 4. Las Grandes Librerías Estándar de Java (`java.*`)

Para no reinventar la rueda, Java incluye miles de clases listas para usar:

| Paquete | ¿Qué contiene? | Clases más famosas |
| :--- | :--- | :--- |
| **`java.lang`** | El núcleo del lenguaje (se importa solo, sin `import`). | `String`, `Math`, `Object`, `Integer`, `System`. |
| **`java.util`** | Colecciones, utilidades y estructuras de datos. | `List`, `ArrayList`, `Map`, `Set`, `Scanner`. |
| **`java.time`** | Manejo moderno y seguro de fechas y horas (Java 8+). | `LocalDate`, `LocalDateTime`, `Duration`. |
| **`java.io` / `java.nio.file`** | Entrada y salida: lectura y escritura de ficheros. | `File`, `Path`, `Files`, `BufferedReader`. |
| **`java.net` / `java.net.http`** | Conexiones de red y clientes web. | `HttpClient`, `HttpRequest`, `URI`. |
| **`java.sql`** | Conexión con bases de datos relacionales (JDBC). | `Connection`, `PreparedStatement`, `ResultSet`. |
| **`java.util.concurrent`** | Programación multihilo y concurrencia avanzada. | `ExecutorService`, `Future`, `AtomicInteger`. |

---

## 🔤 5. Cadenas: Inmutabilidad de `String` vs `StringBuilder`

En Java, los objetos `String` son **inmutables**: una vez creados en memoria, jamás se pueden modificar. Si haces `texto += " hola"`, Java destruye el texto viejo y crea un objeto nuevo entero.

> [!WARNING]
> ### 🛑 NUNCA concatenes Strings con `+` dentro de un bucle
> Concatenar dentro de un bucle de 10.000 vueltas con `+` crea 10.000 objetos temporales en memoria y satura la CPU.  
> **Para concatenar en bucles, usa siempre `StringBuilder`:**

```java
// ✅ FORMA EFICIENTE Y PROFESIONAL:
StringBuilder sb = new StringBuilder();
for (int i = 1; i <= 5; i++) {
    sb.append("Vehiculo_").append(i).append(" ");
}
String resultado = sb.toString(); // Se convierte a String solo al final
```

---

## ⏱️ 6. Fechas Modernas con `java.time`

Desde Java 8, la gestión de fechas se hace con la API inmutable y segura de `java.time`:

```java
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

LocalDate hoy = LocalDate.now();
LocalDate examen = LocalDate.of(2026, 12, 1);

// Calcular días de diferencia entre dos fechas:
long diasRestantes = ChronoUnit.DAYS.between(hoy, examen);
```

---

## 🌊 7. Procesar Datos con `Streams` y erradicar errores con `Optional`

En la programación moderna se utiliza el estilo **declarativo** (decir *qué* quieres en vez de escribir bucles `for` con *cómo* recorrer):

```java
List<String> nombres = List.of("Ana", "Luis", "Álvaro", "Beatriz", "Ángel");

// STREAMS: Filtrar, ordenar y recolectar en una sola línea fluida
List<String> conA = nombres.stream()
    .filter(n -> n.startsWith("A") || n.startsWith("Á"))
    .sorted()
    .toList();

// OPTIONAL: Evita el temido NullPointerException cuando algo puede no existir
Optional<String> encontrado = nombres.stream()
    .filter(n -> n.length() > 6)
    .findFirst();

// Si existe lo imprime, si no existe usa un valor por defecto:
System.out.println(encontrado.orElse("No hay ninguno"));
```

---

## 📖 8. Cómo Leer la Documentación Oficial (Javadoc)

Para saber usar cualquier clase desconocida en la documentación de Oracle:
1. **Localiza el Paquete:** Mira si necesitas importar la clase (`java.time.*`).
2. **Revisa los Constructores:** Mira qué parámetros te pide para crearla con `new`.
3. **Comprueba los Métodos:** Fíjate en el **tipo de retorno** y en qué excepciones lanza (`@throws`).
4. **Fíjate en los modificadores `static`:** Si un método es `static` (como `Math.sqrt()` o `LocalDate.now()`), no necesitas instanciarlo con `new`; se llama directamente por el nombre de la clase.

---

## 📋 Resumen Rápido del Tema 8

* **Java es estático, fuertemente tipado y corre sobre la JVM**, ideal para backend corporativo robusto.
* Las herramientas oficiales del oficio son: **IDE + Maven/Gradle + Git + JUnit**.
* Las librerías de `java.*` contienen todas las herramientas esenciales ya probadas y optimizadas.
* `String` es inmutable; usa `StringBuilder` para bucles y concatenaciones masivas.
* `Streams` permite filtrar y transformar listas de forma limpia y legible.
