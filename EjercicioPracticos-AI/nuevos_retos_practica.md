# 🚀 Nuevos Retos Prácticos (Temas 1 al 9 de POO)

Guía completa de ejercicios para afianzar conceptos, arrays y dar el salto a interfaces gráficas.

---

## 🎯 PARTE 1: Dos Ejercicios de ARRAYS PUROS (Tamaño fijo)

*(Aquí no uses `ArrayList`; practica con arrays tradicionales de tamaño fijo `int[]` y `double[]` para dominar los índices `[i]`, longitudes `.length` y bucles `for`).*

### 🔹 Reto Array 1: El Radar Meteorológico (Estadísticas con números)
* **Objetivo:** Trabajar búsquedas de máximos, mínimos y medias en un array.
* **Enunciado:**
  1. Crea un array de 7 posiciones con las temperaturas registradas durante una semana:  
     `double[] temperaturas = { 12.5, 18.0, 9.4, 22.1, 15.6, 8.0, 19.5 };`
  2. Mediante bucles `for` calcula y muestra por consola:
     * La **temperatura media** de la semana.
     * El **día más caluroso** (la temperatura máxima) y el **más frío** (la mínima).
     * Cuántos días superaron los `15.0 °C`.
  3. **Extra:** Pide por `Scanner` una temperatura al usuario y dile si ese valor exacto se registró algún día de la semana o no.

---

### 🔹 Reto Array 2: La Estantería del Almacén (Array de Textos y Búsqueda)
* **Objetivo:** Recorrer arrays de texto `String[]`, controlar posiciones vacías (`null`) y modificar elementos por índice.
* **Enunciado:**
  1. Tienes una estantería fija con 5 huecos:  
     `String[] estanteria = new String[5];`
  2. Coloca inicialmente 3 productos en posiciones concretas:
     * Posición 0: `"Caja Tornillos"`
     * Posición 1: `"Martillo"`
     * Posición 3: `"Destornillador"` *(la 2 y la 4 quedan vacías / `null`)*.
  3. Haz un menú sencillo por consola con `Scanner`:
     * **1. Ver estantería:** Muestra qué hay en cada hueco (si está en `null`, que imprima `"[Vacío]"`).
     * **2. Guardar producto:** Pide al usuario el nombre del producto y mételo en el **primer hueco libre** que encuentres. Si la estantería está llena (no hay ningún `null`), avísale de que no cabe nada más.
     * **3. Retirar producto:** Pide el nombre de un producto, búscalo con `.equalsIgnoreCase()`, y si lo encuentras, bórralo dejando ese hueco en `null`.

---

## 🚀 PARTE 2: Dos Ejercicios de POO (Temas 1 al 9 a tu nivel)

### 🟢 Reto POO 1: Sistema de Citas Médicas (Herencia, Validaciones y Colecciones)
* **Objetivo:** Dominar herencia, cálculo de importes y listas dinámicas `List<Consulta>`.
* **Clase Padre `Consulta`:**
  * Atributos `protected`: `codigo`, `paciente`, `precioBase`.
  * Constructor con validación (`precioBase > 0`, textos no nulos ni vacíos con `throw`).
  * Método `double calcularCoste()`: Devuelve `precioBase`.
  * `toString()` para mostrar los datos.
* **Clase Hija `ConsultaUrgencias extends Consulta`:**
  * Atributo propio: `boolean nocturna`.
  * Constructor con `super(...)`.
  * `@Override calcularCoste()`: Si es nocturna, se le suma un suplemento fijo de 30 € al precio base; si no, cobra el precio base normal.
* **Clase Hija `ConsultaEspecialista extends Consulta`:**
  * Atributo propio: `String especialidad` (ej: "Traumatología", "Cardiología").
  * Constructor con `super(...)`.
  * `@Override calcularCoste()`: Aplica un 20% extra al precio base por honorarios de especialista.
* **Clase `MainClinica`:**
  * Catálogo de citas en una lista o array polimórfico.
  * Buscar una cita por código con `Scanner` y cobrarla dentro de un `try-catch`.

---

### 🟢 Reto POO 2: Plataforma de Cursos Online (Excepciones Propias y Cupos)
* **Objetivo:** Crear tu propia clase de excepción y gestionar reservas con cupo limitado.
* **Excepción propia:** Crea una clase `CursoLlenoException extends Exception`.
* **Clase `Curso`:**
  * Atributos: `nombreCurso`, `cupoMaximo`, `List<String> alumnosMatriculados`.
  * Constructor: Valida que el cupo sea mayor que 0.
  * Método `void matricular(String nombreAlumno) throws CursoLlenoException`:
    * Si la lista de alumnos ya llegó al cupo máximo, lanza `throw new CursoLlenoException("No quedan plazas en " + nombreCurso)`.
    * Si hay hueco, añade el alumno a la lista y confirma la matrícula.
  * Método `void darDeBaja(String nombreAlumno)`: Si el alumno está en la lista, lo elimina.
* **Clase `MainCursos`:**
  * Matricula alumnos por consola hasta llenar el curso y captura tu propia excepción con `try-catch`.

---

## 👑 PARTE 3: El Proyecto Avanzado — Calculadora Gráfica con Interfaz (Swing)

*(Para hacer poco a poco, paso a paso, cuando quieras).*

* **Tecnología:** Java **Swing** (`javax.swing.*`), la librería oficial de ventanas de escritorio de Java.
* **Qué tendrá la interfaz:**
  1. Una ventana (`JFrame`) con título *"Mi Calculadora Java"*.
  2. Una pantalla de texto arriba (`JTextField`) donde se ven los números y el resultado.
  3. Una cuadrícula de botones (`JButton`):
     * Números del `0` al `9`.
     * Operaciones: `+`, `-`, `*`, `/`.
     * Botón de igual `=` para calcular.
     * Botón `C` (*Clear*) para borrar y reiniciar la pantalla.
  4. **Lógica con POO y control de errores:**
     * Conectar los clics de los botones con Eventos y Lambdas (`btn.addActionListener(e -> ...)`).
     * Controlar con `try-catch` que si alguien divide entre cero (`/ 0`), la pantalla no reviente y muestre el texto `"Error: División por 0"`.
