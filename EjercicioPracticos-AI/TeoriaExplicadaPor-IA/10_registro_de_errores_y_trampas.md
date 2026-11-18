# ⚠️ 10. Registro de Errores y Trampas Frecuentes (Práctica Real)

> **Propósito:** Lista de errores reales cometidos en los retos prácticos para tenerlos fichados y no volver a tropezar con ellos.  
> Los marcados con **`*`** ya estaban explicados en los manuales anteriores.

---

## 🔁 Errores repetidos (ya estaban en los manuales)

* **`*` Comparar `String` con `==` o `!=` en lugar de `.equals()` / `.equalsIgnoreCase()`** — **Esto lo volviste a repetir como error.** *(Ya estaba en el Manual 01 y Manual 07).*
  * En Java, `==` compara referencias de memoria, no el texto. Usa `texto.equalsIgnoreCase(...)`.
* **`*` El "Salto Fantasma" del buffer (`nextInt()` seguido de `nextLine()`)** — **Esto lo volviste a repetir como error.** *(Ya estaba en el Manual 01).*
  * Después de leer un número con `nextInt()` / `nextDouble()`, pon un `sc.nextLine();` vacío antes de volver a leer texto con `nextLine()`.
* **`*` `NullPointerException` por no comprobar `!= null` antes de usar un método** — **Esto lo volviste a repetir como error.** *(Ya estaba en el Manual 06 y Manual 07).*
  * En arrays con huecos vacíos (`null`), comprueba siempre `array[i] != null && array[i].equalsIgnoreCase(...)` antes de llamar a cualquier método sobre el elemento.

---

## 🆕 Errores nuevos (Retos Parte 1 y Parte 2)

### 1. Arrays y Bucles
* **Tratar un array (`String[]`) como si fuera un solo texto:**
  * Olvidar `.length` en el `for` (`i < estanteria` en lugar de `i < estanteria.length`).
  * Olvidar el índice `[i]` al comparar (`estanteria.equalsIgnoreCase(...)` en vez de `estanteria[i].equalsIgnoreCase(...)`) o al imprimir (`%s` con el array entero).
* **Usar `+=` o `-=` para guardar o borrar en un array:**
  * En arrays de tamaño fijo se asigna con `=`: para guardar `estanteria[i] = producto;`, para vaciar `estanteria[i] = null;`.

### 2. Variables, Condicionales y Ámbito (*Scope*)
* **Redeclarar una variable ya existente o crear otra distinta por error:**
  * Si ya existe `boolean guardado = false;`, para cambiarla luego pon solo `guardado = true;` (sin volver a poner `boolean`).
  * Si el `while` vigila `opcion`, guarda el `nextInt()` en `opcion`, no en una variable nueva (`int opcionElegida`).
* **Confundir `=` (asignar) con `==` (comparar) en un `if`:**
  * `if (!guardado = false)` da error; usa `if (!guardado)` o `if (guardado == false)`.
* **Ámbito (*scope*) en `try-catch-finally` y bucles:**
  * Si creas `Scanner sc` *dentro* del `try { ... }`, el `finally` no podrá verlo para cerrarlo. Créalo *fuera* (justo antes del `try`).
  * No cierres el `Scanner` dentro de un `while` o un `switch`; ciérralo al final, fuera del bucle.
* **Poner código suelto (`for`, `if`) directamente dentro de la `class`:**
  * Todo código ejecutable debe ir dentro de un método o constructor.

### 3. POO, Herencia y Paquetes
* **`super(...)` no es la primera línea del constructor:**
  * En Java, `super(...)` debe ir siempre en la **primera línea** del constructor de la clase hija; las validaciones propias van después.
* **`@Override` con distinto tipo de retorno:**
  * Si el método del padre devuelve `double`, el `@Override` de la hija debe devolver `double` (no `String`).
* **Validar antes de guardar y revisar al copiar/pegar:**
  * Pon los `if (...) throw ...` *antes* de `this.campo = campo;`.
  * Al copiar validaciones entre clases, revisa el nombre de la variable (ej. `especialista.isBlank()`, no `paciente.isBlank()`).
* **Lógica invertida al comprobar `null` y fórmulas de porcentaje:**
  * `citaEncontrada == null` significa que **no** se encontró (ahí va el `throw`), no cuando `!= null`.
  * Un 20% extra es `precioBase + (precioBase * 0.20)` (o `precioBase * 1.20`), no `precioBase * 0.20`.
* **Desajuste entre `package` y carpetas:**
  * Si metes archivos dentro de una subcarpeta (ej. `parte2reto/parteMODULAR/`), el `package` debe reflejar la ruta completa desde la raíz (`package parte2reto.parteMODULAR;`).
