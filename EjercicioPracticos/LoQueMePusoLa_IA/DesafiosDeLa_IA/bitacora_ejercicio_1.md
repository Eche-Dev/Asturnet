# 📝 Bitácora de Aprendizaje: Ejercicio 1 (Supermercado)

> **Documento de consulta rápida**: Errores comunes reales, cómo se solucionaron y el truco de alineación profesional con `printf`.

---

## 1. 🎯 Lo Nuevo Aprendido: Alineación de Columnas con `printf`

En lugar de contar espacios a mano con la barra espaciadora (que siempre se descuadran), Java permite fijar el ancho exacto de cada columna.

### La fórmula que usamos:
```java
System.out.printf("%-22s %8.2f EUR%n", "Subtotal:", subTotal);
```

### ¿Qué significa cada parte?
- **`%-22s`**: 
  - `%s`: Recibe un texto (ej: `"Subtotal:"`, `"IVA (21%):"`).
  - `22`: Reserva un espacio fijo de 22 caracteres de ancho.
  - `-` (signo menos): Alinea el texto a la **izquierda**.
- **`%8.2f`**:
  - `%f`: Recibe un número con decimales (`double`).
  - `.2`: Redondea y muestra siempre **2 decimales exactos**.
  - `8`: Reserva 8 huecos y alinea el número a la **derecha** (como en los recibos bancarios).
- **`EUR%n`**: Escribe la palabra `EUR` fija al final y da un salto de línea limpio (`%n`).

---

## 2. ⚠️ Los Errores Reales que Tuviste y Cómo se Solucionaron

### Error 1: `Incorrect Package`
- **Qué pasaba:** Línea 1 marcada en rojo por VS Code.
- **Por qué:** El nombre del `package` no coincidía exactamente con la carpeta donde estaba abierto el proyecto.
- **Solución:** Ajustar la ruta exacta del paquete o usar la bombilla de VS Code (*Add package declaration*).

---

### Error 2: `Duplicate local variable ciudad`
- **Qué pasaba:** La consola arrojó `Unresolved compilation problem: Duplicate local variable ciudad`.
- **Por qué:** Se escribió dos veces `String ciudad = ...`. En Java no puedes declarar dos variables con el mismo nombre en el mismo método.
- **Solución:** Declarar el tipo `String` solo la primera vez.

---

### Error 3: El Scanner "fantasma" que se saltaba preguntas (Buffer)
- **Qué pasaba:** El programa pedía la cantidad y de inmediato se saltaba la ciudad sin dejar escribir.
- **Por qué:** `nextInt()` y `nextDouble()` leen el número pero dejan el **Enter (`\n`)** flotando en la memoria. El siguiente `nextLine()` se tragaba ese Enter vacío.
- **Solución:** Poner una línea de limpieza justo después del número:
  ```java
  int cantidad = supermercado.nextInt();
  supermercado.nextLine(); // 🧹 Limpieza del Enter pendiente
  String ciudad = supermercado.nextLine(); // Ahora sí espera al usuario
  ```

---

### Error 4: `InputMismatchException`
- **Qué pasaba:** Al escribir el precio o cantidad, el programa se rompía de golpe con error de Scanner.
- **Por qué:** Ocurre cuando el método espera un número y el usuario introduce letras (ej: `as22`), o cuando se escribe punto `.` en lugar de coma `,` según el idioma configurado en tu Windows.
- **Solución:** Introducir siempre datos del tipo correcto (`int` para enteros, `double` para decimales).

---

### Error 5: `MissingFormatArgumentException`
- **Qué pasaba:** La consola fallaba con `Format specifier '%.2f'`.
- **Por qué:** En el texto pusiste un marcador (como `%.2f` o `%s`) pero se te olvidó pasarle la variable al final de la línea.
- **Regla de oro:** **Por cada `%` que pongas dentro de las comillas, debes poner una variable separada por coma.**

---

### Error 6: Método dentro de otro método (`main`)
- **Qué pasaba:** Intentar poner `double aplicable(double total) { ... }` dentro de `public static void main`.
- **Por qué:** En Java no se pueden crear métodos dentro de métodos.
- **Solución:** Hacer la lógica directamente con un bloque `if / else` y una variable normal:
  ```java
  double descuento = 0.0;
  if (subTotal > 50) {
      descuento = subTotal * 0.05;
  } else {
      descuento = 0.0;
  }
  ```

---

## 3. 🏆 Resultado Final Logrado

Gracias a todas las pruebas y correcciones, la salida final quedó perfectamente encuadrada:

```text
===============FACTURA SIMPLIFICADA===============
Cliente: DAVID VILLANUEVA | Destino: GIJON BEGOÑA
Articulo: MANZANA (14 usd x 50.0 EUR)
--------------------------------------------------
Subtotal:                700,00 EUR
IVA (21%):               147,00 EUR
Descuento aplicado:       35,00 EUR
--------------------------------------------------
Total a pagar:           812,00 EUR
==================================================
```
