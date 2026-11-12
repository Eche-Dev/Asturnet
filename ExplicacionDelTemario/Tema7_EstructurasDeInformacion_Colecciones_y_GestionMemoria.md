# 📘 Tema 7: Estructuras de Información, Colecciones y Gestión de Memoria

> **Correspondencia con el Manual Oficial UF2404:** Páginas 37 a 41.  
> **Objetivo:** Dominar los tipos de datos en memoria, saber elegir la colección adecuada para cada problema (List, Set, Map, Pila, Cola), entender la diferencia crítica entre Stack y Heap, y comprender cómo el Garbage Collector gestiona los objetos.

---

## 🔢 1. Tipos de Datos Simples vs Objetos de Referencia

En Java la memoria se divide entre **valores directos** y **punteros/referencias**:

| Categoría | Java | Características clave |
| :--- | :--- | :--- |
| **Enteros** | `byte`, `short`, `int`, `long` | Tamaño y rango fijo en memoria (primitivos rápidos). |
| **Reales (Decimales)** | `float`, `double` | Coma flotante IEEE 754. **Tienen error de redondeo**. |
| **Lógico** | `boolean` | Solo `true` o `false`. |
| **Carácter** | `char` | Carácter Unicode de 16 bits entre comillas simples `'a'`. |
| **Cadena** | `String` | **Es un Objeto inmutable**, no un primitivo. Vive en el montón. |
| **Cualquier Clase** | `Vehiculo`, `Coche`, etc. | **Variable de referencia**: Guarda la dirección de memoria donde vive el objeto. |

> [!CAUTION]
> ### 💣 El Peligro del Dinero en Coma Flotante
> En Java y la mayoría de lenguajes: `0.1 + 0.2 != 0.3` (da `0.30000000000000004` por la precisión binaria).  
> **Regla de oro profesional:** Para dinero, contabilidad o facturación en Java **NUNCA uses `double` ni `float`**; usa siempre `BigDecimal`.

---

## 📦 2. Arrays Fijos vs Colecciones Dinámicas

* **Array tradicional (`int[]`, `String[]`):**
  * Tamaño **fijo e inmutable** desde que nace (`new int[10]`).
  * Acceso directo ultra rápido por índice numérico (`tabla[0]`).
  * Si intentas acceder a una posición fuera de su tamaño, lanza `ArrayIndexOutOfBoundsException`.
* **Colecciones (`java.util.*`):**
  * Tamaño **dinámico**: Crecen y menguan automáticamente según añades o quitas elementos.
  * Solo guardan **Objetos** (usan tipos envoltorio como `Integer`, `Double`, `Boolean` en vez de primitivos).

---

## 🧭 3. El Cuadro de Decisión: ¿Qué Colección Elegir?

Elegir la estructura de datos correcta es la decisión de arquitectura más importante para que un programa sea rápido y limpio:

| Estructura | Clase en Java | ¿Admite duplicados? | ¿Mantiene orden? | Cuándo usarla |
| :--- | :--- | :---: | :---: | :--- |
| **Lista (`List`)** | `ArrayList`, `LinkedList` | **SÍ** | **SÍ** (orden de inserción) | Cuando importa el orden cronológico o se repiten datos (ej: historial de entradas al garaje). |
| **Conjunto (`Set`)** | `HashSet`, `TreeSet` | **NO** | `HashSet`: No.<br>`TreeSet`: Sí (orden natural). | Cuando necesitas **garantizar que no haya duplicados** y buscar rápidamente si alguien ya existe (ej: matrículas activas). |
| **Mapa (`Map`)** | `HashMap`, `TreeMap` | Claves únicas (valores sí se repiten) | `HashMap`: No.<br>`TreeMap`: Claves ordenadas. | Cuando buscas por una **clave identificadora** (ej: buscar un Cliente por su DNI o un Vehículo por su Matrícula). |
| **Pila (`Deque`)** | `ArrayDeque` | **SÍ** | **LIFO** (Last In, First Out) | El último en entrar es el primero en salir (ej: botón "Deshacer" en Word, historial atrás del navegador). |
| **Cola (`Queue`)** | `LinkedList`, `ArrayDeque` | **SÍ** | **FIFO** (First In, First Out) | El primero en llegar es el primero en ser atendido (ej: cola de impresión, turnos de espera). |

### Ejemplo rápido en Java:
```java
// 1. LISTA: se permiten repetidos
List<String> nombres = new ArrayList<>();
nombres.add("Ana");
nombres.add("Luis");
nombres.add("Ana"); // Se añade sin problemas

// 2. CONJUNTO: elimina duplicados automáticamente
Set<String> unicos = new HashSet<>(nombres); // Quedan solo "Ana" y "Luis"

// 3. MAPA: pares Clave -> Valor
Map<String, Integer> edades = new HashMap<>();
edades.put("Ana", 30);
edades.put("Luis", 25);
int edadAna = edades.get("Ana"); // Devuelve 30 al instante sin recorrer un bucle
```

---

## 🌲 4. Estructuras No Lineales: Árboles y Grafos

No toda la información viaja en fila india:
1. **Árboles (Jerarquías):** Cada elemento (nodo) tiene un padre y cero o más hijos (como las carpetas de tu disco duro).
   * **Árbol Binario de Búsqueda (BST):** Los menores a la izquierda, los mayores a la derecha. Permite buscar en tiempo logarítmico $\mathcal{O}(\log n)$.
   * En Java ya vienen listos en `TreeSet` y `TreeMap` (internamente usan árboles rojo-negro autobalanceados).
2. **Grafos (Redes):** Nodos conectados por aristas en cualquier dirección (redes de metro, amigos en Facebook, rutas de carreteras).
   * En Java se suelen modelar mediante mapas de adyacencia: `Map<Nodo, List<Nodo>>`.

---

## 🛠️ 5. Cómo Crear una Estructura Propia: Pila Enlazada con Nodos

Para entender cómo se construyen las colecciones por dentro, se suele programar una estructura enlazada mediante referencias:

```java
public class MiPila<T> {

    // Clase interna privada: el cliente no sabe cómo se guardan los datos (Encapsulación)
    private static class Nodo<T> {
        final T dato;
        final Nodo<T> siguiente;

        Nodo(T dato, Nodo<T> siguiente) {
            this.dato = dato;
            this.siguiente = siguiente;
        }
    }

    private Nodo<T> cima = null; // Puntero al elemento de arriba
    private int tamano = 0;

    // Apilar: pone el nuevo elemento arriba apuntando a la antigua cima
    public void apilar(T elemento) {
        cima = new Nodo<>(elemento, cima);
        tamano++;
    }

    // Desapilar: extrae el elemento superior
    public T desapilar() {
        if (cima == null) throw new IllegalStateException("La pila está vacía");
        T valor = cima.dato;
        cima = cima.siguiente; // La cima baja un escalón
        tamano--;
        return valor;
    }

    public boolean estaVacia() {
        return cima == null;
    }
}
```

---

## 🧠 6. Gestión de Memoria: Pila (Stack) vs Montón (Heap)

En Java la memoria del programa se divide en dos grandes regiones:

```
          MEMORIA STACK (Pila)                MEMORIA HEAP (Montón)
      ┌───────────────────────────┐        ┌───────────────────────────┐
      │ Variables locales rápidas │        │  Todos los Objetos (new)  │
      │   int x = 10;             │        │                           │
      │   Cuenta c1 ──────────────┼───────>│  [ Cuenta @0x84A         │
      │   Cuenta c2 ──────────────┼───┐    │     titular: "Ana"        │
      │   (Se borran solas al     │   └───>│     saldo: 500.0 ]        │
      │    salir del método)      │        │                           │
      └───────────────────────────┘        └───────────────────────────┘
```

1. **Stack (Pila de llamadas):**
   * Guarda los métodos que se están ejecutando y sus variables locales (números primitivos y direcciones de punteros).
   * Es rapidísima y de tamaño pequeño.
   * **Limpieza automática:** En cuanto un método termina con `return`, su trozo de pila se destruye de inmediato.
2. **Heap (Montón):**
   * Es el gran almacén donde viven **todos los objetos** creados con la palabra clave `new`.
   * Los objetos viven allí independientemente de los métodos, mientras alguien mantenga una referencia hacia ellos.

---

## 🔍 7. Referencias, Copia Superficial e Igualdad

Cuando asignas una variable de objeto a otra, **NO estás clonando el objeto**, solo estás copiando el puntero:

```java
int[] a = {1, 2, 3};
int[] b = a;         // ⚠️ Copia la REFERENCIA: 'a' y 'b' apuntan a la MISMA caja en el Heap
b[0] = 999;
System.out.println(a[0]); // ¡Imprime 999! Modificar 'b' modificó a 'a'.

int[] c = a.clone(); // ✅ Copia superficial del CONTENIDO (crea un nuevo objeto independiente)
c[0] = 50;
System.out.println(a[0]); // Sigue siendo 999.
```

### La diferencia entre `==` y `.equals()`:
* **`==`:** Compara si dos variables tienen la **misma dirección de memoria** (si apuntan al mismo sitio del Heap).
* **`.equals()`:** Compara si el **contenido interior** de los objetos es equivalente.

---

## 🧹 8. El Garbage Collector (Recolector de Basura) y Fugas de Memoria

En Java no existe el comando `free()` o `delete` de C++. El **Garbage Collector (GC)** vigila constantemente el Heap en segundo plano:

1. **¿Cuándo se borra un objeto?** Cuando un objeto se queda **inalcanzable** (ninguna variable viva en la pila apunta hacia él).
2. **`System.gc()`:** Es solo una sugerencia a la máquina virtual, **no una orden obligatoria**.
3. **¿Puede haber fugas de memoria en Java?**  
   **SÍ (fugas lógicas).** Si dejas objetos guardados en una lista `static` que nunca se vacía, o no das de baja un listener o una caché sin límite, el Garbage Collector creerá que los sigues necesitando y nunca los liberará, provocando `OutOfMemoryError`.

---

## 📋 Resumen Rápido del Tema 7

* Los tipos simples (`int`, `double`) guardan valores; las variables de objeto guardan **referencias**.
* Las colecciones crecen dinámicamente; los arrays tienen tamaño fijo.
* **Pila = LIFO**; **Cola = FIFO**; **Set = Sin repetidos**; **Map = Clave/Valor**.
* Las variables locales viven en la **Stack**; los objetos instanciados con `new` viven en el **Heap**.
* El Garbage Collector limpia la memoria huérfana de forma autónoma.
