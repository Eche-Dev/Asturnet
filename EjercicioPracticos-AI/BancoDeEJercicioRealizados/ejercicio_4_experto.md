# 🔥 Ejercicio 4 (Experto): Terminal de Punto de Venta (TPV) con Inventario, Herencia y Manejo Robusto de Excepciones

## 🎯 Objetivo
Integrar **todo** lo aprendido en los dos primeros días en un único ejercicio profesional:
- Entrada de datos por consola (`Scanner`) con control de buffer.
- Herencia entre clases (`extends`, `super`).
- Encapsulamiento con atributos `private` y `protected`.
- Lógica de inventario con control de stock.
- Sobrescritura (`@Override`) y Polimorfismo.
- Excepciones defensivas (`throw`) y capturadas en `try-catch`.
- Formato monetario estricto con `printf`.

---

## 📝 Enunciado
Desarrolla el motor de ventas para una tienda de electrónica y alimentación que vende dos tipos de artículos especiales.

---

## 🏗️ Jerarquía de Clases

### 1. Clase Padre: `Articulo`
- **Atributos (`protected`)**:
  - `codigo` (`String`)
  - `nombre` (`String`)
  - `precioBase` (`double`)
  - `stock` (`int`)
- **Constructor**:
  - Inicializa los 4 atributos.
  - Valida que `precioBase > 0`, `stock >= 0`, `codigo` y `nombre` no estén vacíos. Si falla alguno, lanza `IllegalArgumentException`.
- **Métodos**:
  - `double getPrecioFinal()`: Por defecto devuelve `precioBase * 1.21` (precio con 21% IVA estándar).
  - `void reponer(int cantidad)`: Suma existencias si `cantidad > 0`.
  - `double vender(int unidades)`:
    - Valida que `unidades > 0`.
    - Valida que `unidades <= stock` (si no, lanza `IllegalArgumentException("Stock insuficiente en " + nombre)`).
    - Resta `unidades` al `stock`.
    - Devuelve el importe total cobrado: `unidades * getPrecioFinal()`.
  - `@Override public String toString()`: Representación en texto con código, nombre, stock y precio final.

---

### 2. Clase Hija: `ArticuloOferta extends Articulo`
- **Atributo propio (`private`)**:
  - `porcentajeDescuento` (`double`): Por ejemplo, `15.0` para un 15% de descuento.
- **Constructor**:
  - Recibe los datos del padre más el `porcentajeDescuento`.
  - Llama a `super(...)`.
  - Valida que el descuento esté entre `1` y `50%`.
- **Método (`@Override`)**:
  - `double getPrecioFinal()`: Calcula el precio con IVA del padre, y a ese resultado le descuenta el porcentaje de oferta.

---

### 3. Clase Hija: `ArticuloPerecedero extends Articulo`
- **Atributo propio (`private`)**:
  - `diasParaCaducar` (`int`): Días restantes de consumo preferente.
- **Constructor**:
  - Recibe datos del padre más los días. Llama a `super(...)`.
- **Método (`@Override`)**:
  - `double getPrecioFinal()`:
    - Si le quedan `1` o `2` días para caducar, se rebaja un **40%** sobre el precio con IVA para liquidarlo rápido.
    - Si le quedan más de 2 días, cobra el precio normal con IVA.

---

## 🖥️ Requisitos del Programa Principal (`main`):

1. **Catálogo Inicial (Array Polimórfico)**:
   Crea un array de artículos en la tienda:
   ```java
   Articulo[] catalogo = {
       new Articulo("A01", "Teclado USB", 20.0, 10),
       new ArticuloOferta("O02", "Auriculares Bluetooth", 40.0, 5, 20.0), // 20% descuento
       new ArticuloPerecedero("P03", "Yogures Artesanales", 2.50, 8, 2)    // 2 días para caducar -> liquidación 40%
   };
   ```

2. **Mostrar Catálogo**:
   Recorrer el array y listar los artículos con su stock y su precio final por unidad.

3. **Venta Interactiva por Consola (`Scanner`)**:
   - Preguntar al usuario qué artículo desea comprar (introduciendo el código: `A01`, `O02`, etc.).
   - Preguntar cuántas unidades desea.
   - Usar un bucle para buscar el artículo en el catálogo.
   - Si no existe el código, informar al usuario.
   - Si existe, ejecutar `.vender(unidades)` dentro de un bloque **`try - catch`**.
     - Si la venta tiene éxito: Mostrar el ticket de la venta formateado con `printf` (`%.2f EUR`).
     - Si la venta falla (por ejemplo, pide más unidades del stock que hay): Capturar `IllegalArgumentException` y mostrar el error por consola con `e.getMessage()`.

4. **Comprobación Final**:
   Imprimir el catálogo de nuevo para verificar que el stock del producto vendido ha disminuido correctamente y que los productos que dieron error no alteraron su stock.

---

## 📋 Ejemplo de Salida Esperada en Consola
```text
================ CATÁLOGO DE LA TIENDA ================
[A01] Teclado USB | Stock: 10 | Precio Final: 24.20 EUR
[O02] Auriculares Bluetooth (OFERTA) | Stock: 5 | Precio Final: 38.72 EUR
[P03] Yogures Artesanales (LIQUIDACIÓN) | Stock: 8 | Precio Final: 1.82 EUR
=======================================================

--- PROCESO DE VENTA ---
Ingrese el código del producto que desea comprar: O02
Ingrese la cantidad de unidades: 7

>> Error en la venta: Stock insuficiente en Auriculares Bluetooth

--- SEGUNDO INTENTO DE VENTA ---
Ingrese el código del producto que desea comprar: O02
Ingrese la cantidad de unidades: 3

>>> VENTA EXITOSA <<<
Producto: Auriculares Bluetooth
Unidades vendidas: 3
Total a pagar: 116.16 EUR

================ INVENTARIO ACTUALIZADO ================
[A01] Teclado USB | Stock: 10 | Precio Final: 24.20 EUR
[O02] Auriculares Bluetooth (OFERTA) | Stock: 2 | Precio Final: 38.72 EUR
[P03] Yogures Artesanales (LIQUIDACIÓN) | Stock: 8 | Precio Final: 1.82 EUR
=======================================================
```
