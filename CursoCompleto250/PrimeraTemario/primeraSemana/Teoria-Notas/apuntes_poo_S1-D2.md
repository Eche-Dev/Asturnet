# 📘 Guía de Referencia Rápida: POO en Java (Tema 9 - Día 2)

> **Documento de consulta para ejercicios de Programación Orientada a Objetos.**  
> Basado en los conceptos vistos en clase y en los ejercicios prácticos (`p1_ej_bloques.java` y `empleado.java`).

---

## 📑 Índice de Contenidos
1. [¿Qué es la Programación Orientada a Objetos (POO)?](#1-qué-es-la-programación-orientada-a-objetos-poo)
2. [Los 5 Conceptos Fundamentales](#2-los-5-conceptos-fundamentales)
3. [Tipos de Datos Básicos y Variables](#3-tipos-de-datos-básicos-y-variables)
4. [Anatomía de los Métodos](#4-anatomía-de-los-métodos)
5. [Palabras Clave Esenciales (`this`, `super`, `new`, `final`, etc.)](#5-palabras-clave-esenciales)
6. [Modificadores de Acceso y Encapsulamiento](#6-modificadores-de-acceso-y-encapsulamiento)
7. [Manejo de Errores y Excepciones (`try-catch-throw`)](#7-manejo-de-errores-y-excepciones)
8. [Herencia y Polimorfismo (`extends`, `@Override`)](#8-herencia-y-polimorfismo)
9. [Código de Ejemplo Completo y Explicado Paso a Paso](#9-código-de-ejemplo-completo-y-explicado-paso-a-paso)

---

## 1. ¿Qué es la Programación Orientada a Objetos (POO)?

Es un paradigma o forma de programar que organiza el código imitando el mundo real. En vez de tener funciones sueltas y datos desordenados, agrupamos los **datos** (características) y las **acciones** (comportamiento) en una misma unidad llamada **Objeto**.

Lenguajes populares que usan POO: **Java**, **Python**, **C#**, **C++**.

---

## 2. Los 5 Conceptos Fundamentales

| Concepto | Definición en lenguaje sencillo | Ejemplo cotidiano | En tu código Java |
| :--- | :--- | :--- | :--- |
| **Clase** | El molde, plano o plantilla a partir del cual se fabrican objetos. No ocupa memoria real hasta que creas uno. | El plano de una casa o la receta de una tarta. | `class Producto { ... }` |
| **Objeto / Instancia** | Lo que se construye con el molde. Tiene sus propios valores independientes. | Una casa construida en la calle X con paredes rojas. | `Producto patatas = new Producto(...)` |
| **Atributo** | Variable que guarda una característica o dato del objeto (su estado). | El color, la altura, el precio, el stock. | `private double precio;` |
| **Método** | Función que define una acción o habilidad que sabe hacer el objeto. | Acelerar, frenar, vender, calcular nómina. | `double vender(int unidades) { ... }` |
| **Mensaje** | La orden que le das a un objeto para que ejecute uno de sus métodos. | Pulsar el botón del mando a distancia. | `patatas.vender(3);` |

---

## 3. Tipos de Datos Básicos y Variables

| Tipo | Qué almacena | Rango / Ejemplo | Uso típico |
| :--- | :--- | :--- | :--- |
| `int` | Número entero (sin decimales) | `0`, `5`, `-10`, `300` | Cantidades, unidades, stock, edades. |
| `double` | Número con decimales | `2.50`, `19.99`, `1200.0` | Precios, salarios, medidas precisas. |
| `boolean` | Valor lógico binario | `true` (verdadero) o `false` (falso) | Interruptores, validaciones (`activo`, `disponible`). |
| `String` | Cadena de texto (es una clase de Java) | `"Hola"`, `"Patatas"`, `"A-123"` | Nombres, descripciones, identificadores. |

---

## 4. Anatomía de los Métodos

Un método es un bloque de instrucciones con un nombre. Se clasifican según lo que hacen:

```
[Visibilidad] [TipoDevuelto] nombreMetodo([Parametros]) {
    // Código
    return [Valor]; // Obligatorio si TipoDevuelto no es void
}
```

1. **Constructor**:  
   - Se llama **exactamente igual que la clase** y no lleva tipo de retorno.  
   - Se ejecuta automáticamente al hacer `new`.  
   - Sirve para darle los valores iniciales y comprobar que los datos sean válidos.
   ```java
   public Producto(String nombre, double precio, int stock) { ... }
   ```

2. **Métodos con retorno (devuelven un resultado)**:  
   - En la cabecera pones el tipo de dato que va a devolver (`double`, `int`, `String`, etc.).  
   - Es obligatorio usar la palabra `return` al terminar.
   ```java
   double vender(int unidades) {
       stock -= unidades;
       return unidades * precio; // Devuelve el importe total en euros
   }
   ```

3. **Métodos sin retorno (`void`)**:  
   - La palabra `void` significa "vacío". El método hace una tarea interna pero no devuelve ningún cálculo al que lo llamó.
   ```java
   void reponer(int unidades) {
       stock += unidades; // Modifica el stock pero no devuelve nada
   }
   ```

4. **Getters (Lectores) y Setters (Modificadores)**:
   - `getPrecio()`: Permite a otros ver el precio sin poder alterarlo directamente.
   - `setPrecio(double nuevoPrecio)`: Permite cambiar el precio bajo reglas controladas.

5. **Método `toString()`**:  
   - Método especial de Java que dice cómo debe imprimirse el objeto al hacer `System.out.println(objeto);`.

---

## 5. Palabras Clave Esenciales

- **`new`**: Ordena a Java reservar memoria y crear un nuevo objeto llamando a su constructor.  
  `Producto p = new Producto("Manzana", 1.5, 10);`

- **`this`**: Hace referencia a "este objeto concreto" (el que está ejecutando el código).  
  Evita confusiones cuando el parámetro tiene el mismo nombre que el atributo:
  ```java
  // this.nombre es el atributo del objeto
  // nombre (a secas) es el parámetro que entra al constructor
  this.nombre = nombre; 
  ```

- **`super`**: Hace referencia a la **clase padre** (superclase).  
  Se usa para llamar al constructor del padre (`super(nombre, salarioBase);`) o a métodos del padre.

- **`final`**:  
  - En una variable/atributo: Significa que **es constante**, no se puede cambiar tras asignarse.  
  - En una clase: No permite tener subclases.

- **`static`**:  
  - Pertenece a la **clase en general**, no a un objeto en particular.  
  - Por ejemplo, `main` es `static` porque Java necesita ejecutarlo antes de que existan objetos creados.

---

## 6. Modificadores de Acceso y Encapsulamiento

El **encapsulamiento** consiste en proteger los datos internos de un objeto para que nadie desde fuera pueda romperlos o poner valores incoherentes (como poner un precio negativo o cambiar un saldo a dedo).

| Modificador | Dónde se puede ver / usar | ¿Para qué sirve? |
| :--- | :--- | :--- |
| **`private`** | **Solo dentro de la propia clase**. Nadie de fuera tiene acceso directo. | Ocultar atributos sensibles (`stock`, `precio`, `contraseña`). |
| **`protected`** | La propia clase, sus **hijas (subclases)** y el mismo paquete. | Ideal cuando hay herencia y los hijos necesitan ver el dato. |
| *(sin palabra / default)* | Solo clases dentro del **mismo paquete** (carpeta). | Acceso a nivel de módulo o paquete. |
| **`public`** | **Cualquiera**, desde cualquier archivo del proyecto. | Constructores y métodos que forman la interfaz pública. |

---

## 7. Manejo de Errores y Excepciones

En Java, cuando ocurre algo indebido no debemos imprimir simplemente un texto; debemos **lanzar una excepción** para detener la operación antes de que se corrompan los datos.

### 1. Lanzar una excepción (`throw`)
```java
if (unidades <= 0) {
    throw new IllegalArgumentException("No puedes vender 0 o menos unidades");
}
```

### 2. Capturar y recuperarse del error (`try - catch`)
```java
try {
    // Código que podría fallar
    producto.vender(500); 
} catch (IllegalArgumentException e) {
    // Si falla, el programa no explota; entra aquí
    System.out.println("No se pudo realizar la venta: " + e.getMessage());
} finally {
    // (Opcional) Código que se ejecuta SIEMPRE, haya o no error
}
```

---

## 8. Herencia y Polimorfismo

- **Herencia (`extends`)**: Permite crear una clase hija a partir de una clase padre, reutilizando todo su código y añadiendo características específicas.
  - *Clase Padre (Superclase)*: `Empleado` (tiene `nombre`, `salarioBase`).
  - *Clase Hija (Subclase)*: `Directivo extends Empleado` (hereda nombre y salario, y añade `bonus`).

- **Sobrescritura (`@Override`)**: La clase hija redefine un método del padre para adaptarlo a su forma de funcionar.
  - El padre calcula nómina básica: `salarioBase`.
  - El directivo sobrescribe con `@Override`: `salarioBase + bonus`.

- **Polimorfismo**: Tratar a diferentes clases hijas como si fueran del tipo padre de forma transparente.  
  *Ejemplo*: Puedes meter en un array de tipo `Empleado[]` a directivos, temporales y fijos, y pedirles a todos `.calcularNomina()`; cada uno responderá con su fórmula correspondiente sin necesidad de hacer `if/else`.

---

## 9. Código de Ejemplo Completo y Explicado Paso a Paso

A continuación tienes un ejemplo completo que reúne todos los conceptos explicados. Puedes copiarlo o usarlo como plantilla:

```java
package CursoCompleto250.primeraSemana.practicas;

// 1. CLASE PADRE (MOLDE BASE)
class Vehiculo {
    // ATRIBUTOS PROTECTED: Los hijos pueden verlos directamente
    protected String marca;
    protected double velocidad;

    // CONSTRUCTOR DEL PADRE
    public Vehiculo(String marca) {
        if (marca == null || marca.isBlank()) {
            // Validación: Evitamos crear vehículos sin marca
            throw new IllegalArgumentException("La marca no puede estar vacia.");
        }
        this.marca = marca;     // 'this.marca' es el atributo, 'marca' es el parámetro
        this.velocidad = 0.0;   // Inicia siempre detenido
    }

    // MÉTODO: Acción que modifica el estado interno
    public void acelerar(double incremento) {
        if (incremento <= 0) {
            throw new IllegalArgumentException("El incremento debe ser mayor que cero.");
        }
        this.velocidad += incremento;
    }

    // GETTER: Permite consultar la velocidad sin alterarla directamente
    public double getVelocidad() {
        return this.velocidad;
    }

    // MÉTODO toString: Representación legible en texto del objeto
    @Override
    public String toString() {
        return "Vehiculo " + marca + " [Velocidad: " + velocidad + " km/h]";
    }
}

// 2. CLASE HIJA (HERENCIA CON 'extends')
class CocheElectrico extends Vehiculo {
    // ATRIBUTO PRIVADO PROPIO DEL HIJO
    private int bateria; // Nivel de 0 a 100

    // CONSTRUCTOR DEL HIJO
    public CocheElectrico(String marca, int bateriaInicial) {
        super(marca); // 'super' llama al constructor de Vehiculo (el padre)
        
        if (bateriaInicial < 0 || bateriaInicial > 100) {
            throw new IllegalArgumentException("La bateria debe estar entre 0 y 100.");
        }
        this.bateria = bateriaInicial;
    }

    // SOBRESCRITURA (@Override): Acelerar en un coche eléctrico consume batería
    @Override
    public void acelerar(double incremento) {
        if (bateria <= 5) {
            throw new IllegalStateException("Bateria demasiado baja para acelerar.");
        }
        super.acelerar(incremento); // Llama al acelerar original del padre
        this.bateria -= 5;          // Resta batería adicionalmente
    }

    // GETTER PROPIO DEL HIJO
    public int getBateria() {
        return this.bateria;
    }

    @Override
    public String toString() {
        return "CocheElectrico " + marca + " [Vel: " + velocidad + " km/h, Bateria: " + bateria + "%]";
    }
}

// 3. CLASE PRINCIPAL CON MÉTODO main
public class EjemploPOOExplicado {

    public static void main(String[] args) {
        System.out.println("=== 1. CREACIÓN DE OBJETOS (INSTANCIACIÓN) ===");
        // 'new' crea los objetos en memoria llamando a sus constructores
        Vehiculo furgoneta = new Vehiculo("Ford");
        CocheElectrico tesla = new CocheElectrico("Tesla", 80);

        System.out.println(furgoneta);
        System.out.println(tesla);

        System.out.println("\n=== 2. EJECUCIÓN DE MÉTODOS (ENVIAR MENSAJES) ===");
        furgoneta.acelerar(50);
        tesla.acelerar(60); // Consume 5% de batería y sube velocidad a 60

        System.out.println("Velocidad furgoneta: " + furgoneta.getVelocidad() + " km/h");
        System.out.println(tesla);

        System.out.println("\n=== 3. MANEJO DE EXCEPCIONES (CONTROL DE ERRORES) ===");
        try {
            System.out.println("Intentando acelerar con valor negativo...");
            furgoneta.acelerar(-20); // Esto lanzará un IllegalArgumentException
        } catch (IllegalArgumentException e) {
            // El programa no se interrumpe abruptamente; capturamos el fallo
            System.out.println(">> Error capturado correctamente: " + e.getMessage());
        }

        System.out.println("\n=== 4. POLIMORFISMO EN ACCIÓN ===");
        // Creamos una lista/array de la clase padre (Vehiculo)
        // pero podemos guardar tanto Vehiculos normales como CochesElectricos
        Vehiculo[] flota = { furgoneta, tesla };

        for (Vehiculo v : flota) {
            // Java llama automáticamente al 'toString' adecuado de cada objeto
            System.out.println("Elemento en la flota -> " + v);
        }
    }
}
```

---

### 📌 Resumen Mnemotécnico para tus Prácticas

1. **¿Necesito guardar una característica?** $\rightarrow$ Declara un **atributo** (`private tipo nombre;`).
2. **¿Necesito que el objeto haga algo?** $\rightarrow$ Escribe un **método** (`tipoRetorno nombreMetodo() { ... }`).
3. **¿No debe devolver nada?** $\rightarrow$ Pon **`void`**.
4. **¿Necesito inicializarlo con datos obligatorios?** $\rightarrow$ Crea un **constructor** con esos parámetros.
5. **¿Un dato recibido no tiene sentido?** $\rightarrow$ Lanza error con **`throw new IllegalArgumentException(...)`**.
6. **¿Quiero crear otro objeto parecido pero con cosas extra?** $\rightarrow$ Usa **`extends`** (herencia) y **`super(...)`**.
