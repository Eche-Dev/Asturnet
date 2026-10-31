# 📝 Bitácora de Aprendizaje: Ejercicio 2 (Cuenta Bancaria - Intermedio)

> **Documento de consulta rápida**: Qué hiciste bien de forma intuitiva, qué fallos tuviste y cómo se solucionaron.

---

## 1. 👏 Lo que hiciste muy bien (¡A mantenerlo!)

1. **Estructura de clase limpia**: Creaste correctamente la clase y entendiste el concepto de encapsulamiento con `private`.
2. **Validaciones defensivas impecables**: Las condiciones para evitar datos nulos o vacíos (`numeroCuenta == null || numeroCuenta.isBlank()`) y el uso de `throw new IllegalArgumentException(...)` las dominaste a la primera.
3. **Uso de `this`**: Supiste diferenciar el objeto actual frente a los parámetros en `this.numeroCuenta = numeroCuenta;`.

---

## 2. ⚠️ Los 4 Fallos que Tuviste y Cómo se Solucionaron

### Fallo 1: Confundir Atributos (Datos) con Métodos (Acciones)
- **El error:** Pusiste `private double ingresar;` y `private double retirar;` arriba como variables.
- **Por qué pasa:** Una clase guarda **características** (número, titular, saldo). Ingresar y retirar no son datos a guardar, son **acciones (métodos)** que se ejecutan cuando se necesitan.
- **Solución:** Se borraron esas dos líneas de arriba.

---

### Fallo 2: Asignar a un atributo que no existía en el Constructor
- **El error:** Escribiste `this.saldoInicial = saldoInicial;`.
- **Por qué falló:** Tu variable arriba se llama `saldo`. Al poner `this.saldoInicial`, Java buscaba una variable que no existía.
- **Solución:** Asignar el valor del parámetro al atributo real:
  ```java
  this.saldo = saldoInicial;
  ```

---

### Fallo 3: Parámetros innecesarios y suma al revés en `ingresar`
- **El error:** Declaraste `void deposito(double ingresar, double saldo)` y adentro hiciste `ingresar += saldo;`.
- **Por qué falló:** 
  1. El método no necesita recibir el `saldo` por fuera; el objeto ya conoce su propio saldo.
  2. Estabas sumándole el saldo a la variable temporal en vez de sumar el dinero a la cuenta real.
- **Solución:**
  ```java
  void ingresar(double cantidad) {
      if (cantidad <= 0) throw new IllegalArgumentException(...);
      this.saldo += cantidad; // ✅ Suma directa a la cuenta
  }
  ```

---

### Fallo 4: Falta de resta y el error de compilación del `return` en `retirar`
- **El error:** 
  ```java
  if (retirar < saldo)
      return true;
  ```
- **Por qué falló:**
  1. No estabas restando el dinero de la cuenta (`saldo -= cantidad`).
  2. Si retirabas exactamente todo el dinero (`retirar == saldo`), la condición daba `false` y el método no llegaba a ningún `return`. Java te arrojaba error obligatorio: *"Missing return statement"*.
- **Solución:** Restar el dinero y devolver `true` siempre que pase las comprobaciones:
  ```java
  this.saldo -= cantidad; // 1. Restamos
  return true;            // 2. Respondemos true
  ```

---

## 3. 📌 Resumen Mnemotécnico para POO

- **¿Es una cualidad que el objeto debe recordar?** $\rightarrow$ Es un **atributo** (`saldo`, `titular`).
- **¿Es algo que el objeto sabe hacer?** $\rightarrow$ Es un **método** (`ingresar()`, `retirar()`).
- **Los métodos ya pueden ver los atributos de su clase**: No hace falta pasarle `saldo` como parámetro a un método de la propia cuenta bancaria.
