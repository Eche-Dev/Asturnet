# 🔴 Ejercicio 3 (Avanzado): Gestión de Vehículos con Herencia y Polimorfismo

## 🎯 Objetivo
Aplicar los principios de herencia (`extends`), llamada al constructor padre (`super`), sobrescritura de métodos (`@Override`), modificadores `protected` y procesamiento polimórfico mediante un array de la clase base.

---

## 📝 Enunciado
Una empresa de logística dispone de varios tipos de vehículos en su flota y necesita calcular el coste de mantenimiento mensual de cada uno de ellos.

---

## 🏗️ Estructura de Clases

### 1. Clase Base: `VehiculoFlota` (Padre)
- **Atributos (`protected final` o `protected`)**:
  - `matricula` (`String`): Matrícula del vehículo.
  - `marca` (`String`): Marca / modelo.
  - `costeFijoBase` (`double`): Coste mensual mínimo fijo (por ejemplo, seguro base y aparcamiento).
- **Constructor**:
  - Recibe los 3 parámetros con validaciones (matrícula y marca no vacías, costeFijoBase > 0).
- **Método**:
  - `double calcularMantenimiento()`: Devuelve el `costeFijoBase`.
  - `@Override public String toString()`: Devuelve `[Matricula: X, Marca: Y, Base: Z EUR]`.

---

### 2. Clase Hija 1: `CamionPesado extends VehiculoFlota`
- **Atributo propio (`private final`)**:
  - `numEjes` (`int`): Número de ejes del camión (mínimo 2).
- **Constructor**:
  - Recibe `matricula`, `marca`, `costeFijoBase` y `numEjes`.
  - Llama al constructor del padre usando `super(matricula, marca, costeFijoBase)`.
  - Valida que `numEjes >= 2`, si no lanza `IllegalArgumentException`.
- **Método (`@Override`)**:
  - `double calcularMantenimiento()`: El mantenimiento de un camión pesado es el `costeFijoBase` más `120.0 EUR` extra por cada eje (`costeFijoBase + (120 * numEjes)`).

---

### 3. Clase Hija 2: `FurgonetaReparto extends VehiculoFlota`
- **Atributo propio (`private final`)**:
  - `kilometrosMes` (`double`): Kilómetros recorridos durante el mes.
- **Constructor**:
  - Recibe `matricula`, `marca`, `costeFijoBase` y `kilometrosMes`.
  - Llama a `super(...)` y valida que los kilómetros no sean negativos.
- **Método (`@Override`)**:
  - `double calcularMantenimiento()`: Añade `0.08 EUR` de desgaste por cada kilómetro recorrido (`costeFijoBase + (kilometrosMes * 0.08)`).

---

## 🖥️ Requisitos Técnicos en el `main`:
1. Crear un array de tipo base:
   ```java
   VehiculoFlota[] flota = {
       new CamionPesado("1234-BBB", "Volvo FH", 400.0, 4),
       new FurgonetaReparto("5678-CCC", "Renault Master", 150.0, 3500.0),
       new CamionPesado("9012-DDD", "Scania R", 450.0, 3)
   };
   ```
2. Recorrer el array con un bucle `for-each` (`for (VehiculoFlota v : flota)`).
3. Dentro del bucle:
   - Imprimir cada vehículo y su coste total de mantenimiento calculado mediante polimorfismo.
   - Ir sumando el coste total acumulado de toda la flota.
4. Al terminar el bucle, imprimir el gasto total de mantenimiento de la empresa formateado a dos decimales con `printf`.

---

## 📋 Ejemplo de Salida Esperada en Consola
```text
============= REPORTE MENSUAL DE FLOTA =============
CamionPesado [1234-BBB - Volvo FH] -> Mantenimiento: 880.00 EUR
FurgonetaReparto [5678-CCC - Renault Master] -> Mantenimiento: 430.00 EUR
CamionPesado [9012-DDD - Scania R] -> Mantenimiento: 810.00 EUR
----------------------------------------------------
Coste total acumulado de mantenimiento: 2120.00 EUR
====================================================
```
