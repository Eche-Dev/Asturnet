# 📘 Guía de Referencia Rápida: Fundamentos de Java, Entrada de Datos y Formato (Día 1)

> **Documento de consulta para ejercicios de entrada por consola, salida formateada y tipos básicos.**  
> Basado en las prácticas `P1_ejemplo.java`, `P2_ejemplo.java`, `P3_ejemplo.java` y `P4_ejemplo.java`.

---

## 📑 Índice de Contenidos
1. [Estructura Mínima de un Programa en Java](#1-estructura-mínima-de-un-programa-en-java)
2. [Salida por Consola: Diferencias entre `print`, `println` y `printf`](#2-salida-por-consola-print-println-y-printf)
3. [Formato con `printf`: El misterio de `%s`, `%.2f` y `%n`](#3-formato-con-printf-el-misterio-de-s-2f-y-n)
4. [Lectura de Datos con `Scanner`](#4-lectura-de-datos-con-scanner)
5. [⚠️ El Problema del Buffer del Scanner (Salto Fantasma)](#5-⚠️-el-problema-del-buffer-del-scanner-salto-fantasma)
6. [Estructuras Condicionales Básicas (`if / else`)](#6-estructuras-condicionales-básicas-if--else)
7. [Buenas Prácticas y Cierre de Recursos (`close`)](#7-buenas-prácticas-y-cierre-de-recursos-close)
8. [Código de Ejemplo Completo y Explicado Paso a Paso](#8-código-de-ejemplo-completo-y-explicado-paso-a-paso)

---

## 1. Estructura Mínima de un Programa en Java

En Java **todo debe pertenecer a una clase** y el programa siempre arranca por el método especial `main`:

```java
package miCarpeta; // Paquete o ubicación del archivo (opcional según la carpeta)
import java.util.Scanner; // Importamos herramientas externas que necesitemos

public class MiPrograma {
    // Punto de entrada obligatorio:
    public static void main(String[] args) {
        // Aquí va tu código ejecutable
    }
}
```

> **¡Ojo!** Como vimos con `P4_ejemplo.java`, no puedes llamar a `println(...)` suelto. Las instrucciones de impresión pertenecen al objeto de salida estándar del sistema: `System.out.println(...)`.

---

## 2. Salida por Consola: `print`, `println` y `printf`

| Método | ¿Qué hace? | ¿Pone salto de línea al final? | Ejemplo |
| :--- | :--- | :---: | :--- |
| `System.out.print(...)` | Imprime texto plano de corrido. | ❌ No | Ideal para pedir datos: `System.out.print("Escribe tu edad: ");` |
| `System.out.println(...)` | Imprime texto y salta automáticamente a la siguiente línea (*ln = line*). | ✅ Sí | `System.out.println("Bienvenido!");` |
| `System.out.printf(...)` | Imprime texto **formateado** sustituyendo marcadores (`%s`, `%f`, etc.). | ❌ No (requiere `%n` para saltar) | `System.out.printf("Total: %.2f EUR%n", 19.99);` |

---

## 3. Formato con `printf`: El misterio de `%s`, `%.2f` y `%n`

`printf` significa *print formatted* (imprimir con formato). Te permite escribir una plantilla con "huecos" (llamados **especificadores de formato**) y luego rellenarlos con variables en el orden exacto.

### La regla de oro de `printf`:
> **Por cada especificador (`%`) que pongas en el texto, debes pasar exactamente una variable correspondiente.**  
> Si pones 2 marcadores (`%s` y `%.2f`), debes pasar 2 variables: primero un String y luego un número decimal. Si quitas una variable o sobran marcadores, Java lanzará un error (`MissingFormatArgumentException`).

### Especificadores más utilizados:

| Marcador | ¿Qué tipo de dato espera? | Ejemplo de uso | Resultado en consola |
| :---: | :--- | :--- | :--- |
| `%s` | Cadena de texto (`String`) | `printf("Hola %s", "Juan")` | `Hola Juan` |
| `%d` | Número entero (`int`, `long`) | `printf("Tienes %d años", 25)` | `Tienes 25 años` |
| `%f` | Número decimal (`double`, `float`) sin limitar | `printf("Precio: %f", 12.34567)` | `Precio: 12.345670` |
| `%.2f` | Número decimal redondeado a **2 decimales** | `printf("Total: $%.2f", 15.6789)` | `Total: $15.68` |
| `%.0f` | Número decimal sin decimales (redondeo entero) | `printf("Aprox: %.0f", 9.8)` | `Aprox: 10` |
| `%n` | Salto de línea independiente del sistema operativo | `printf("Fila 1%nFila 2%n")` | Salta de renglón |
| `%%` | Imprime el símbolo literal de porcentaje `%` | `printf("Descuento: 10%%")` | `Descuento: 10%` |

#### Desglose de tu duda en `P3_ejemplo.java`:
```java
// Dos marcadores -> Requiere DOS variables a la derecha (en ese mismo orden)
//  1: %s (String)      2: %.2f (double con 2 decimales)
System.out.printf("El salario anual de %s es: $%.2f%n", nombreTrabajador, salarioAnual);
//                                       ↑          ↑            ↑               ↑
//                                    Hueco 1    Hueco 2     Variable 1      Variable 2
```
Si quitabas `nombreTrabajador` y dejabas solo `salarioAnual`, Java encontraba `%s` primero e intentaba meter un `double` en un hueco de texto o faltaba el segundo argumento, causando un error.

---

## 4. Lectura de Datos con `Scanner`

Para recibir información escrita por el usuario a través de la terminal usamos la clase `Scanner`:

```java
import java.util.Scanner; // 1. Importar arriba del todo

Scanner entrada = new Scanner(System.in); // 2. Crear el lector vinculado al teclado (System.in)
```

### Métodos de lectura según el tipo de dato:

| Método | Tipo que lee | ¿Cómo funciona? |
| :--- | :--- | :--- |
| `scanner.nextLine()` | `String` | Lee **toda la línea completa** hasta que el usuario pulsa Enter. |
| `scanner.next()` | `String` | Lee solo una palabra (se detiene en el primer espacio). |
| `scanner.nextInt()` | `int` | Lee un número entero. |
| `scanner.nextDouble()`| `double` | Lee un número decimal (usa coma `,` o punto `.` según el idioma del sistema). |
| `scanner.nextBoolean()`| `boolean` | Lee `true` o `false`. |

---

## 5. ⚠️ El Problema del Buffer del Scanner (Salto Fantasma)

Este es el fallo más típico al empezar en Java con consola.

### ¿Por qué ocurre?
Cuando escribes `25` y pulsas **ENTER**:
1. En el teclado envías dos cosas: el número `25` y el salto de línea `\n` (Enter).
2. `nextInt()` o `nextDouble()` leen el número `25` y lo consumen, **pero dejan el `\n` flotando en el buffer (la memoria de entrada)**.
3. Si inmediatamente después ejecutas un `nextLine()`, este método ve el `\n` pendiente, piensa *"¡el usuario ya pulsó Enter!"* y termina de inmediato sin dejar escribir al usuario, devolviendo un texto vacío `""`.

```
Teclado escribe: [ 2 5 ] [ \n ]
nextInt() toma:  [ 2 5 ] 
Queda esperando en el buffer: [ \n ]
nextLine() entra, se traga el [ \n ] y se salta la pregunta.
```

### ¿Cómo se soluciona? (Limpiar el Buffer)
Siempre que vayas a pedir un texto (`nextLine()`) **después de haber pedido un número** (`nextInt()` o `nextDouble()`), debes poner un `scanner.nextLine();` de limpieza:

```java
System.out.print("Ingrese su edad: ");
int edad = scanner.nextInt(); 

scanner.nextLine(); // 🧹 LIMPIEZA DEL BUFFER: Consume el Enter que quedó flotando

System.out.print("Ingrese su ciudad: ");
String ciudad = scanner.nextLine(); // Ahora sí espera a que el usuario escriba
```

---

## 6. Estructuras Condicionales Básicas (`if / else`)

Sirven para tomar decisiones lógicas en base a condiciones que devuelven `true` (verdadero) o `false` (falso):

```java
if (edad >= 18) {
    System.out.println("Es mayor de edad");
} else if (edad >= 13) {
    System.out.println("Es adolescente");
} else {
    System.out.println("Es un niño");
}
```

### Operadores de comparación y lógicos:
- `==` igual a (para números o primitivos). Para textos (`String`) se usa `texto1.equals(texto2)`.
- `!=` diferente de.
- `>`, `<`, `>=`, `<=` mayor, menor, mayor o igual, menor o igual.
- `&&` operador **Y** (ambas condiciones deben cumplirse).
- `||` operador **O** (basta con que una se cumpla).
- `!` negación (**NO**).

---

## 7. Buenas Prácticas y Cierre de Recursos (`close`)

Al terminar de usar un `Scanner` que lee de consola o archivos, es una buena práctica cerrarlo para liberar recursos de la memoria:

```java
scanner.close(); // Al final de tu main
```

---

## 8. Código de Ejemplo Completo y Explicado Paso a Paso

A continuación tienes un programa completo que demuestra:
1. `print` para preguntar en la misma línea.
2. Lectura de números con `nextDouble()`.
3. Limpieza correcta del buffer.
4. Lectura de textos con `nextLine()`.
5. Cálculos matemáticos y toma de decisiones con `if / else`.
6. Salida formateada con `printf` usando `%s`, `%.2f` y `%n`.

```java
package CursoCompleto250.primeraSemana.practicas.PrimerDia;

import java.util.Scanner;

public class EjemploDia1Completo {

    public static void main(String[] args) {
        // 1. CREACIÓN DEL SCANNER
        Scanner lector = new Scanner(System.in);

        System.out.println("==========================================");
        System.out.println("   SISTEMA DE FACTURACIÓN Y NÓMINA (DÍA 1) ");
        System.out.println("==========================================");

        // 2. LECTURA DE TEXTO INICIAL (No hay problema de buffer aquí porque es la primera)
        System.out.print("Ingrese el nombre del empleado: ");
        String empleado = lector.nextLine();

        // 3. LECTURA DE VALORES NUMÉRICOS
        System.out.print("Ingrese las horas trabajadas en el mes: ");
        double horas = lector.nextDouble();

        System.out.print("Ingrese el precio pactado por hora: ");
        double tarifaHora = lector.nextDouble();

        // 4. LIMPIEZA OBLIGATORIA DEL BUFFER
        // Tras leer números (tarifaHora), el Enter queda flotando.
        // Si no hacemos esto, la siguiente pregunta de texto se la saltará.
        lector.nextLine(); // 🧹 Limpiamos el buffer

        System.out.print("Ingrese el departamento o sede: ");
        String departamento = lector.nextLine(); // Ahora sí lee correctamente

        // 5. CÁLCULOS
        double salarioBruto = horas * tarifaHora;
        double porcentajeRetencion;

        // 6. ESTRUCTURA DE CONTROL IF / ELSE
        if (salarioBruto > 2000.0) {
            porcentajeRetencion = 15.0; // 15% de IRPF si gana más de 2000
        } else {
            porcentajeRetencion = 8.0;  // 8% de IRPF si gana 2000 o menos
        }

        double retencion = salarioBruto * (porcentajeRetencion / 100);
        double salarioNeto = salarioBruto - retencion;

        // 7. PRESENTACIÓN DE RESULTADOS CON PRINTF
        System.out.println("\n---------------- RESUMEN -----------------");
        // %s se sustituye por 'empleado'
        // %s se sustituye por 'departamento'
        System.out.printf("Empleado: %s | Sede: %s%n", empleado, departamento);

        // %.2f muestra solo dos decimales y redondea
        // %% se usa para imprimir el símbolo '%' en pantalla
        System.out.printf("Salario Bruto:       $%.2f%n", salarioBruto);
        System.out.printf("Retención aplicada:  $%.2f (%.0f%%)%n", retencion, porcentajeRetencion);
        System.out.printf("Salario Neto Final:  $%.2f%n", salarioNeto);
        System.out.println("------------------------------------------");

        // 8. CIERRE DEL SCANNER
        lector.close();
    }
}
```

---

### 📌 Resumen Mnemotécnico para el Día 1

1. **¿Voy a pedir datos por teclado?** $\rightarrow$ `Scanner teclado = new Scanner(System.in);`.
2. **¿Pido un número y luego un texto?** $\rightarrow$ Pon siempre `teclado.nextLine();` de limpieza antes de leer el texto.
3. **¿Quiero dinero o números bonitos con 2 decimales?** $\rightarrow$ Usa `System.out.printf("%.2f EUR%n", variable);`.
4. **¿Cuántos argumentos van en `printf`?** $\rightarrow$ Exactamente **uno por cada `%`** que pongas en el texto.
5. **¿Quiero que el cursor no salte de línea para que escriba al lado?** $\rightarrow$ Usa `System.out.print(...)`.
