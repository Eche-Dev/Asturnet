# 🟢 Ejercicio 1 (Fácil): Simulador de Factura de Supermercado

## 🎯 Objetivo
Practicar la entrada de datos por teclado con `Scanner`, evitar el error del buffer tras leer números, realizar cálculos aritméticos básicos y mostrar la salida formateada con `printf`.

---

## 📝 Enunciado
Una tienda necesita un programa en consola que permita registrar la compra de un producto y calcular su total con impuestos.

El programa debe pedir al usuario por teclado:
1. **Nombre del cliente** (`String`, línea completa).
2. **Nombre del producto** (`String`, línea completa).
3. **Precio por unidad en euros** (`double`).
4. **Cantidad de unidades compradas** (`int`).
5. **Tipo de cliente o ciudad de entrega** (`String`, para comprobar que limpias bien el buffer tras leer la cantidad).

---

## ⚙️ Reglas de Negocio y Cálculos
1. **Subtotal** = `precioUnidad * cantidad`.
2. **IVA (21%)** = `subtotal * 0.21`.
3. **Descuento**:
   - Si el subtotal supera los `50.00 EUR`, se aplica un descuento del `5%` sobre el subtotal.
   - Si no supera los `50.00 EUR`, el descuento es `0.00 EUR`.
4. **Total a pagar** = `subtotal + IVA - descuento`.

---

## 🖥️ Requisitos Técnicos
- Utilizar `System.out.print(...)` para solicitar cada dato en la misma línea.
- Limpiar el buffer con `scanner.nextLine();` justo después de leer el número entero de la cantidad.
- Mostrar el ticket final con `System.out.printf(...)`:
  - Los importes en dinero deben formatearse obligatoriamente con `%.2f` y salto de línea `%n`.
  - Los nombres deben mostrarse con `%s`.
- Cerrar el scanner al finalizar con `scanner.close();`.

---

## 📋 Ejemplo de Salida Esperada en Consola
```text
====================================
      SUPERMERCADO ASTURNET         
====================================
Ingrese nombre del cliente: Carlos Pérez
Ingrese nombre del producto: Café molido
Ingrese precio por unidad: 4.50
Ingrese cantidad: 12
Ingrese ciudad de entrega: Oviedo

=========== FACTURA SIMPLIFICADA ===========
Cliente: Carlos Pérez | Destino: Oviedo
Artículo: Café molido (12 uds x 4.50 EUR)
--------------------------------------------
Subtotal:              54.00 EUR
IVA (21%):             11.34 EUR
Descuento aplicado:     2.70 EUR
--------------------------------------------
TOTAL A PAGAR:         62.64 EUR
============================================
```
