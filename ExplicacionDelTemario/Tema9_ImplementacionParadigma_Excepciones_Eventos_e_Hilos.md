# 📘 Tema 9: Implementación Completa, Excepciones, Eventos y Concurrencia (Hilos)

> **Correspondencia con el Manual Oficial UF2404:** Páginas 47 a 55.  
> **Objetivo:** Poner en práctica todo lo aprendido en los 8 temas anteriores: la receta exacta para programar clases empresariales desde un diseño UML, dominar el tratamiento de excepciones (propias, checked y unchecked), implementar el patrón Observador para eventos reactivos, entender la ejecución multihilo concurrente y dar los primeros pasos con bases de datos vía JDBC.

---

## 👨‍🍳 1. La Receta Oficial en 8 Pasos para Implementar una Clase

Cuando te dan un diseño de un ejercicio o un diagrama de clases UML, sigue **siempre este orden estricto** para no olvidar nada:

1. **Declarar el Paquete y la Clase:** Elegir el nombre y el nivel de visibilidad (`public`, `abstract` o `final`).
2. **Atributos Privados (`private`):** Elegir el tipo o la colección adecuada (`List`, `Map`, etc.).
3. **Constructores:** Asignar los valores iniciales y **validar datos obligatorios** (que no vengan vacíos ni nulos).
4. **Métodos de la Interfaz Pública:** La lógica de negocio (`ingresar()`, `entrar()`, `calcular()`).
5. **Relaciones:** Herencia (`extends`), interfaces (`implements`) o composición con otros objetos.
6. **Tratamiento de Errores:** Lanzar excepciones si los datos rompen las reglas.
7. **Métodos Estándar:** Sobrescribir `toString()`, y si hace falta `equals()` y `hashCode()`.
8. **Documentar y Probar:** Comentarios Javadoc y método `main` o test unitario.

---

## 📁 2. Estructura Estándar de un Proyecto Empresarial

En el mundo laboral, las clases no se dejan sueltas; se organizan en paquetes usando el **dominio web invertido de la empresa**:

```
mi-proyecto/
└── src/main/java/es/academia/gestion/
    ├── App.java                <-- Punto de entrada principal con el método main()
    ├── modelo/                 <-- Las entidades de datos (Coche, Cuenta, Alumno)
    │   ├── Vehiculo.java
    │   └── Coche.java
    ├── servicio/               <-- La lógica del negocio (Garaje, Banco, Matrícula)
    │   └── GarajeService.java
    └── excepcion/              <-- Excepciones propias del programa
        └── GarajeLlenoException.java
```

---

## 🏷️ 3. Construcciones Modernas: `enum` y `record`

Java incluye dos herramientas que ahorran cientos de líneas de código repetitivo:

### A) El Tipo Enumerado (`enum`)
Se usa cuando una variable solo puede tomar un **conjunto cerrado y fijo de valores posibles**:
```java
public enum EstadoVehiculo { 
    DENTRO, 
    FUERA, 
    EN_MANTENIMIENTO 
}
```

### B) Los Registros (`record`) (Java 16+)
Son clases pensadas exclusivamente para **transportar datos inmutables**. El compilador de Java crea automáticamente los atributos privados, el constructor, los getters, `toString()`, `equals()` y `hashCode()`:

```java
// ¡En una sola línea tienes una clase completa e inmutable!
public record DatosConductor(String dni, String nombre, int edad) {
    // Constructor compacto opcional para validar reglas:
    public DatosConductor {
        if (edad < 18) throw new IllegalArgumentException("El conductor debe ser mayor de edad");
    }
}
```

---

## 🚨 4. Jerarquía y Tratamiento de Excepciones

Una excepción es un **objeto** que se crea cuando algo sale mal durante la ejecución de un programa.

```
                         ┌─────────────┐
                         │  Throwable  │
                         └──────┬──────┘
                   ┌────────────┴────────────┐
                   ▼                         ▼
             ┌───────────┐             ┌───────────┐
             │   Error   │             │ Exception │
             └───────────┘             └─────┬─────┘
          (Fallos fatales JVM:         ┌─────┴─────────────────────┐
           OutOfMemoryError)           ▼                           ▼
                              ┌───────────────────┐     ┌──────────────────────┐
                              │ RuntimeException  │     │ Excepciones Checked  │
                              │ (Unchecked)       │     │ (Comprobadas)        │
                              └───────────────────┘     └──────────────────────┘
                              NullPointerException      IOException, SQLException
                              IllegalArgumentException  Excepciones Propias
```

### Tabla de Tipos de Excepciones:

| Tipo | ¿Qué representa? | ¿Obliga el compilador a tratarla? | Ejemplos |
| :--- | :--- | :---: | :--- |
| **Comprobadas (`Checked`)** | Situaciones previsibles fuera del control del programa (fallo de red, archivo no encontrado). Heredan de `Exception`. | **SÍ** (`try-catch` o `throws`) | `IOException`, `SQLException`, `GarajeLlenoException`. |
| **No Comprobadas (`Unchecked`)** | Errores lógicos del programador (despistes de código). Heredan de `RuntimeException`. | **NO** (se deben corregir programando bien) | `NullPointerException`, `IndexOutOfBoundsException`, `IllegalArgumentException`. |
| **Errores Graves (`Error`)** | Fallos catastróficos de la máquina virtual donde el programa no puede recuperarse. | **NO** (no se deben capturar) | `OutOfMemoryError`, `StackOverflowError`. |

---

## 🛡️ 5. Crear Excepciones Propias y `try-with-resources`

### Creando tu propia excepción de negocio:
```java
// Al heredar de Exception, obligas a quien use tu método a gestionarla con try-catch
public class GarajeLlenoException extends Exception {
    private final int plazasTotales;

    public GarajeLlenoException(int plazasTotales) {
        super("No se puede entrar: El garaje ha alcanzado su capacidad máxima de " + plazasTotales);
        this.plazasTotales = plazasTotales;
    }

    public int getPlazasTotales() {
        return plazasTotales;
    }
}
```

### Capturando con `try - catch - finally`:
```java
try {
    garaje.entrar(coche);
} catch (GarajeLlenoException e) {
    System.err.println("Aviso: " + e.getMessage());
} finally {
    // Se ejecuta SIEMPRE, tanto si hubo fallo como si todo salió bien
    System.out.println("Fin de la operación de acceso");
}
```

### Cierre Automático: `try-with-resources`
Cualquier clase que implemente `AutoCloseable` (conexiones, ficheros, scanners) se cierra automáticamente sin necesidad de escribir un bloque `finally`:

```java
try (Scanner sc = new Scanner(System.in)) {
    // Al salir de la llave }, el Scanner se cierra solo
}
```

---

## 🔔 6. Gestión de Eventos: El Patrón Observador (Observer)

En sistemas reactivos e interactivos (interfaces visuales, sensores, carritos de compra), los objetos no se consultan unos a otros continuamente; **se avisan cuando pasa algo**:

1. **Fuente (Sujeto):** El objeto que cambia de estado (ej: un `Pedido` que pasa a "Enviado").
2. **Evento:** Un objeto que contiene los datos de lo que ha sucedido (`EventoPedido`).
3. **Oyente (`Listener`):** Una interfaz con el método que reacciona.

```java
// 1. Interfaz funcional del oyente
@FunctionalInterface
public interface EstadoListener {
    void alCambiar(String nuevoEstado);
}

// 2. La clase que emite el evento
public class Pedido {
    private final List<EstadoListener> oyentes = new ArrayList<>();

    public void suscribir(EstadoListener oyente) {
        oyentes.add(oyente);
    }

    public void setEstado(String estado) {
        // Notificamos a todos los oyentes registrados usando lambdas:
        for (EstadoListener o : oyentes) {
            o.alCambiar(estado);
        }
    }
}

// 3. Suscribirse usando una expresión Lambda en una línea:
pedido.suscribir(estado -> System.out.println("Enviando SMS al cliente: Pedido " + estado));
```

---

## 🧵 7. Hilos y Concurrencia (Threads)

Un **hilo (`Thread`)** es un camino de ejecución independiente. Permite hacer varias tareas en paralelo (por ejemplo: descargar un archivo mientras la interfaz del garaje sigue respondiendo).

### Ciclo de Vida de un Hilo:
* **Nuevo:** Creado con `new Thread(...)` pero aún parado.
* **Ejecutable (`Runnable`):** Se llama a `start()` y espera turno en la CPU.
* **En Ejecución:** La CPU está procesando sus líneas de código.
* **Bloqueado / Esperando:** Esperando por un `sleep()`, un recurso ocupado o un `join()`.
* **Terminado:** Su método `run()` ha terminado.

---

## 💥 8. Peligro Crítico: Condiciones de Carrera y `synchronized`

> [!CAUTION]
> ### ⚠️ `valor++` NO es una operación atómica
> La operación `valor++` en realidad son 3 pasos de procesador:
> 1. Leer el valor actual de memoria.
> 2. Sumarle 1 en el registro.
> 3. Guardar el nuevo valor en memoria.
> 
> Si dos hilos ejecutan `valor++` a la vez sobre la misma variable, **se pisan el uno al otro** y se pierden datos (Condición de Carrera).

### La Solución:
```java
// Opción A: Métodos sincronizados (solo un hilo puede entrar a la vez)
public synchronized void incrementar() {
    this.contador++;
}

// Opción B: Clases concurrentes nativas atómicas (más rápidas)
private AtomicInteger contador = new AtomicInteger(0);
contador.incrementAndGet(); // Atómico y seguro sin bloqueos
```

---

## 🏊 9. Pools de Hilos (`ExecutorService`)

En aplicaciones reales **nunca se crean hilos a mano** con `new Thread()` porque son caros para el sistema operativo. Se utiliza un **Pool de hilos reutilizables**:

```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

// Creamos un grupo de 3 trabajadores disponibles
ExecutorService pool = Executors.newFixedThreadPool(3);

// Enviamos una tarea pesada en segundo plano
Future<Integer> tarea = pool.submit(() -> {
    Thread.sleep(1000);
    return 42; // Resultado tras calcular
});

// Obtenemos el resultado cuando haya terminado
System.out.println("Resultado: " + tarea.get());

pool.shutdown(); // Cerramos el pool al salir
```

---

## 🗄️ 10. Introducción a Bases de Datos con JDBC

Para conectar una aplicación Java a una base de datos relacional (MySQL, PostgreSQL, Oracle) se utiliza la API oficial **JDBC**:

```
[ Clase Java ] ──> [ PreparedStatement (SQL) ] ──> [ Driver JDBC ] ──> [ Base de Datos ]
```

### Reglas Sagradas de JDBC:
1. **Conexión:** `DriverManager.getConnection(url, user, pass)`.
2. **Consultas Seguras:** Usa siempre `PreparedStatement` con interrogaciones `?`.  
   **JAMÁS concatenes texto del usuario dentro del SQL** (evita el ataque de Inyección SQL).
3. **Lectura de Resultados:** `ResultSet rs = ps.executeQuery();` recorre las filas con `while (rs.next())`.
4. **Arquitectura Limpia:** La lógica de base de datos se aísla en clases **DAO (Data Access Object)** o Repositorios; nunca se mezcla código SQL dentro de la clase `Vehiculo` o `Coche`.

---

## 📋 Resumen Rápido del Tema 9

* Seguir la receta en 8 pasos garantiza una implementación OO impecable.
* Las **Excepciones Checked** obligan a gestionarlas (`try-catch`), mientras que las **Unchecked** son fallos lógicos evitables.
* El patrón **Observador** desacopla a los emisores de los receptores mediante eventos y listeners.
* Para evitar que varios hilos rompan variables compartidas, se usa `synchronized` o `AtomicInteger`.
* En entornos profesionales, la concurrencia se gestiona con **`ExecutorService`** y las bases de datos mediante **JDBC y DAOs**.
