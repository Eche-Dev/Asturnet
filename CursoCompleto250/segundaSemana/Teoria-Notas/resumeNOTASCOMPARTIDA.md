# 📝 Resumen de Notas Compartidas en Clase (Semana 2)

> **Archivo:** `resumeNOTASCOMPARTIDA.md`  
> **Propósito:** Apuntes estructurados y limpios de la pizarra y notas compartidas con el profesor y compañeros.

---

## 📑 Índice Rápido
1. [Los 4 Pilares de la POO (AOC)](#1-los-4-pilares-de-la-poo)
2. [Modelado de Clases: Cuentas y Biblioteca](#2-modelado-de-clases-en-el-mundo-real)
3. [Modificadores de Acceso (Visibilidad)](#3-modificadores-de-acceso-visibilidad)
4. [Tipos de Métodos según su Función](#4-tipos-de-métodos-según-su-función)
5. [Variables y Métodos Estáticos (`static`)](#5-variables-y-métodos-estáticos-static)
6. [Clase `Cuenta` Completa y Comentada](#6-código-ejemplo-clase-cuenta-bancaria)
7. [Clase `Producto` con Validaciones](#7-código-ejemplo-clase-producto-tienda)
8. [Manejo de Errores (`try`, `catch`, `finally`)](#8-manejo-de-errores-y-excepciones)
9. [Herencia: `Vehiculo` y `Coche` (`extends`, `super`, `@Override`)](#9-herencia-vehiculo-y-coche)
10. [Métodos Clave Heredados de `Object`](#10-métodos-fundamentales-de-object)
11. [Interfaces en Java (`interface`, `implements`, `default`)](#11-interfaces-múltiples)

---

## 1. Los 4 Pilares de la POO

| Pilar | ¿Qué significa en lenguaje sencillo? | Ejemplo cotidiano |
| :--- | :--- | :--- |
| **Abstracción** | Quedarse únicamente con las características esenciales para el problema, ignorando detalles irrelevantes. | Para un banco, de una persona solo importa su DNI, nombre y nómina; no su color de pelo. |
| **Encapsulación** | Ocultar los datos internos (`private`) y ofrecer una forma controlada y segura de modificarlos mediante métodos. | No dejas que nadie cambie tu saldo bancario a mano; solo mediante `ingresar()` o `retirar()`. |
| **Herencia** | Crear una clase nueva que reutiliza y amplía a otra ya existente (`extends`). | `Coche` hereda la matrícula y velocidad de `Vehículo`, y le añade `puertas`. |
| **Polimorfismo** | Enviar un mismo mensaje a distintos objetos y que cada uno responda según su propia naturaleza. | Ordenar `.calcularNomina()` y que el fijo sume trienios y el directivo sume su bonus. |

---

## 2. Modelado de Clases en el Mundo Real

### A) Sistema Bancario (Cuentas)
* **Clase:** `Cuenta`
* **Objetos / Instancias:** `cuentaAna`, `cuentaGabriel`, `cuentaJonatan`.
* **Atributos:** `saldo`, `titular`, `deuda`.
* **Métodos:** `ingresar()`, `retirar()`, `darAlta()`, `darBaja()`.
* **Mensaje:** `cuentaGabriel.ingresar(1000);`

### B) Sistema de Biblioteca
* **Clase:** `Biblioteca`
* **Entidades asociadas:**
  * `Socio`: Atributos (`nombre`, `numeroSocio`), Método (`prestar(Libro)`).
  * `Prestamo`: Atributos (`fechaDevolucion`, `activo`), Método (`devolver()`).
  * `Libro`: Atributos (`titulo`, `sku`), Método (`disponible()`).

---

## 3. Modificadores de Acceso (Visibilidad)

| Modificador | Dónde es visible | Uso recomendado |
| :--- | :--- | :--- |
| **`public`** | En cualquier clase de todo el proyecto. | Métodos y constructores públicos. |
| **`protected`** | En la propia clase, en subclases hijas (`extends`) y en el mismo paquete. | Atributos del padre que las hijas necesitan usar. |
| *(Por defecto)* | Solamente dentro de la misma carpeta / paquete. | Clases auxiliares locales. |
| **`private`** | **Únicamente dentro de la propia clase**. | Atributos (para cumplir la encapsulación). |

---

## 4. Tipos de Métodos según su Función

1. **Constructores:** Reservan memoria e inicializan el estado inicial del objeto (`Cuenta(String titular, double saldoInicial)`).
2. **Métodos de Consulta (*Getters*):** Leen información sin modificar el estado (`getSaldo()`, `getTitular()`).
3. **Métodos Modificadores (*Setters* / Comandos):** Cambian el estado interno con validaciones (`ingresar(importe)`, `retirar(importe)`).
4. **Métodos de Utilidad:** Conversiones o cálculos auxiliares (`toString()`, `getSaldoEnDolares()`).
5. **Métodos de Cierre / Destrucción:** Liberan recursos o conexiones abiertas (`close()`).

---

## 5. Variables y Métodos Estáticos (`static`)

* **Variable de instancia:** Cada cuenta tiene su propio saldo independiente.
* **Variable `static`:** Existe **una sola variable compartida por todas las cuentas**. Pertenece a la clase entera.

```java
// Ejemplo de uso:
Cuenta a = new Cuenta("Manuel", 500);
Cuenta b = new Cuenta("Matilde", 300);

// Se llama directamente usando el NOMBRE DE LA CLASE:
System.out.println("Total de cuentas creadas en el banco: " + Cuenta.getTotalCuentas());
```

---

## 6. Código Ejemplo: Clase `Cuenta` Bancaria

```java
public class Cuenta {

    // Variable static: compartida por todos los objetos Cuenta
    private static int totalCuentas = 0;

    // Atributos de instancia (propios de cada cuenta)
    private final String titular;
    private double saldo;

    // Constructor completo
    public Cuenta(String titular, double saldoInicial) {
        if (titular == null || titular.trim().isEmpty()) {
            throw new IllegalArgumentException("El titular es obligatorio.");
        }
        if (saldoInicial < 0) {
            throw new IllegalArgumentException("El saldo inicial no puede ser negativo.");
        }
        this.titular = titular.trim();
        this.saldo = saldoInicial;
        totalCuentas++; // Incrementa el contador global de cuentas
    }

    // Constructor sobrecargado (inicia con saldo 0)
    public Cuenta(String titular) {
        this(titular, 0.0);
    }

    // Método para ingresar dinero
    public void ingresar(double importe) {
        if (importe <= 0) {
            throw new IllegalArgumentException("El importe a ingresar debe ser positivo.");
        }
        this.saldo += importe;
    }

    // Método para retirar dinero
    public void retirar(double importe) {
        if (importe <= 0) {
            throw new IllegalArgumentException("Importe no válido: " + importe);
        }
        if (importe > this.saldo) {
            throw new IllegalArgumentException("Saldo insuficiente. Saldo actual: " + this.saldo);
        }
        this.saldo -= importe;
    }

    // Getters
    public double getSaldo() { return this.saldo; }
    public String getTitular() { return this.titular; }
    
    // Método static para consultar el total global
    public static int getTotalCuentas() { return totalCuentas; }

    @Override
    public String toString() {
        return titular + ": " + saldo + " EUR";
    }
}
```

---

## 7. Código Ejemplo: Clase `Producto` (Tienda)

```java
public class Producto {

    private final String nombre;
    private final double precio;
    private int stock;

    public Producto(String nombre, double precio, int stock) {
        if (precio <= 0) {
            throw new IllegalArgumentException("Precio negativo o cero.");
        }
        if (stock < 0) {
            throw new IllegalArgumentException("Stock negativo.");
        }
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("Nombre obligatorio.");
        }
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public double vender(int unidades) {
        if (unidades <= 0) {
            throw new IllegalArgumentException("Unidades no válidas para venta.");
        }
        if (unidades > stock) {
            throw new IllegalArgumentException("Stock insuficiente de: " + nombre);
        }
        stock -= unidades;
        return unidades * precio;
    }

    public void reponer(int unidades) {
        if (unidades <= 0) {
            throw new IllegalArgumentException("Las unidades a reponer deben ser mayores a cero.");
        }
        if (unidades > 300) {
            throw new IllegalArgumentException("Avisa a un supervisor para reposiciones superiores a 300.");
        }
        stock += unidades;
    }

    @Override
    public String toString() {
        return nombre + " (" + precio + " EUR, stock " + stock + ")";
    }
}
```

---

## 8. Manejo de Errores y Excepciones

```java
try {
    // 1. Código que intentamos ejecutar (puede provocar error)
    patatas.vender(50); 
} catch (IllegalArgumentException e) {
    // 2. Bloque donde atrapamos el error para que el programa no caiga
    System.out.println("No se pudo completar la venta: " + e.getMessage());
} finally {
    // 3. Bloque que siempre se ejecuta al final (haya habido error o no)
    System.out.println("Fin del proceso de venta.");
}
```

---

## 9. Herencia: `Vehiculo` y `Coche`

```java
// Clase base (Padre)
class Vehiculo {
    protected String matricula;
    protected int velocidad;

    public Vehiculo(String matricula) {
        this.matricula = matricula;
        this.velocidad = 0;
    }

    public void acelerar(int incremento) {
        this.velocidad += incremento;
    }

    public String describir() {
        return "Vehículo [" + matricula + "] a " + velocidad + " km/h";
    }
}

// Subclase (Hija)
class Coche extends Vehiculo {
    private int puertas;

    public Coche(String matricula, int puertas) {
        super(matricula); // Llama al constructor del padre (Vehiculo)
        this.puertas = puertas;
    }

    @Override
    public String describir() {
        // Reutiliza la descripción del padre y añade las puertas
        return "Coche de " + puertas + " puertas. " + super.describir();
    }
}
```

---

## 10. Métodos Fundamentales de `Object`

Toda clase en Java hereda automáticamente de la clase `Object` estos 4 métodos clave:

1. **`toString()`**: Representación en texto legible del objeto al hacer `System.out.println(obj)`.
2. **`equals(Object obj)`**: Compara el contenido de dos objetos para ver si son iguales (no sus direcciones de memoria).
3. **`hashCode()`**: Devuelve un código numérico para almacenar el objeto en colecciones hash (`HashSet`, `HashMap`).
4. **`getClass()`**: Devuelve el tipo real de la clase en tiempo de ejecución (`getClass().getSimpleName()`).

---

## 11. Interfaces Múltiples

Una interfaz define qué habilidades sabe hacer una clase (un contrato). En Java una clase puede implementar múltiples interfaces:

```java
interface Volador {
    default String volar() { return "Volando por los aires"; }
}

interface Nadador {
    default String nadar() { return "Nadando bajo el agua"; }
}

interface Caminador {
    default String caminar() { return "Caminando sobre tierra"; }
}

// El pato sabe volar, nadar y caminar a la vez:
class Pato implements Volador, Nadador, Caminador {
    private String nombre;

    public Pato(String nombre) {
        this.nombre = nombre;
    }
}
```
