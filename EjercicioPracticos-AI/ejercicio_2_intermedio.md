# 🟡 Ejercicio 2 (Intermedio): Sistema de Cuenta Bancaria con Encapsulamiento

## 🎯 Objetivo
Crear una clase propia con atributos privados, constructor con validaciones mediante excepciones (`IllegalArgumentException`), métodos de modificación de estado (`ingresar`, `retirar`) y método `toString()`.

---

## 📝 Enunciado
Diseña una clase llamada `CuentaBancaria` para gestionar el dinero de un cliente.

### 1. Atributos de la clase (`private`):
- `numeroCuenta` (`String`): Identificador único de la cuenta.
- `titular` (`String`): Nombre completo del titular.
- `saldo` (`double`): Cantidad de dinero disponible.

### 2. Constructor:
- Firma: `public CuentaBancaria(String numeroCuenta, String titular, double saldoInicial)`
- **Validaciones obligatorias**:
  - `numeroCuenta`: no puede ser nulo ni estar vacío (`isBlank()`). Si falla $\rightarrow$ lanzar `IllegalArgumentException("Número de cuenta obligatorio")`.
  - `titular`: no puede ser nulo ni estar vacío. Si falla $\rightarrow$ lanzar `IllegalArgumentException("El titular es obligatorio")`.
  - `saldoInicial`: no puede ser negativo (`< 0`). Si falla $\rightarrow$ lanzar `IllegalArgumentException("El saldo inicial no puede ser negativo")`.
- Usar `this.` para asignar los atributos.

### 3. Métodos:
- **`void ingresar(double cantidad)`**:
  - Si `cantidad <= 0`, lanzar `IllegalArgumentException("La cantidad a ingresar debe ser positiva")`.
  - Si es válida, suma la cantidad al `saldo`.
- **`boolean retirar(double cantidad)`**:
  - Si `cantidad <= 0`, lanzar `IllegalArgumentException("La cantidad a retirar debe ser positiva")`.
  - Si `cantidad > saldo`, lanzar `IllegalArgumentException("Saldo insuficiente")`.
  - Si todo es correcto, resta la cantidad del saldo y devuelve `true`.
- **`double getSaldo()`**:
  - Devuelve el saldo actual.
- **`@Override public String toString()`**:
  - Devuelve una cadena con el formato: `Cuenta: [numeroCuenta] | Titular: [titular] | Saldo: [saldo] EUR`.

---

## 🖥️ Requisitos Técnicos en el `main`:
1. Crear dos cuentas bancarias:
   - Una para `"Ana Gómez"` con saldo de `1000.0`.
   - Otra para `"Pedro Ruiz"` con saldo de `250.0`.
2. Realizar un ingreso de `300.0` en la cuenta de Ana.
3. Realizar una retirada válida de `50.0` en la cuenta de Pedro.
4. Imprimir ambas cuentas por consola para ver su estado actualizado (comprobando que se llame a `toString()`).
5. Envolver una retirada excesiva (por ejemplo retirar `500.0` de Pedro) dentro de un bloque `try - catch (IllegalArgumentException e)` para mostrar el mensaje de error capturado sin que el programa se rompa.

---

## 📋 Ejemplo de Salida Esperada en Consola
```text
=== ESTADO INICIAL Y OPERACIONES ===
Cuenta creada: Cuenta: ES12-3456 | Titular: Ana Gómez | Saldo: 1000.0 EUR
Cuenta creada: Cuenta: ES98-7654 | Titular: Pedro Ruiz | Saldo: 250.0 EUR

Ingresando 300.0 EUR en la cuenta de Ana...
Retirando 50.0 EUR de la cuenta de Pedro...

=== ESTADO ACTUALIZADO ===
Cuenta: ES12-3456 | Titular: Ana Gómez | Saldo: 1300.0 EUR
Cuenta: ES98-7654 | Titular: Pedro Ruiz | Saldo: 200.0 EUR

=== PRUEBA DE SEGURIDAD (TRY - CATCH) ===
Intentando retirar 500.0 EUR de la cuenta de Pedro...
>> Error controlado: Saldo insuficiente
Saldo final de Pedro sigue siendo: 200.0 EUR
```
