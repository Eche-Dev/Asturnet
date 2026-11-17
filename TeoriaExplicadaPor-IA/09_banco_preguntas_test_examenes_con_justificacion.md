# 📝 Manual 09: Banco de Preguntas de Examen / Test con Justificación Oficial

> **Propósito:** Recopilación de todas las preguntas de los cuestionarios interactivos de clase (a partir del 13 de noviembre), con su respuesta correcta, la justificación oficial del porqué y la explicación de por qué fallan las otras opciones.  
> Ideal para repasar rápido antes de exámenes teóricos del certificado UF2404.

---

## 🗂️ Índice por Bloques Temáticos

1. [POO, Clases y Objetos](#1-poo-clases-y-objetos)
2. [Herencia, Polimorfismo y Métodos](#2-herencia-polimorfismo-y-métodos)
3. [Relaciones entre Clases (Composición vs Asociación)](#3-relaciones-entre-clases)
4. [Gestión de Memoria y Tipos de Datos (Stack, Heap, Strings, Referencias)](#4-gestión-de-memoria-y-tipos-de-datos)
5. [Estructuras de Información y Colecciones (Set, Map, Tree)](#5-estructuras-de-información-y-colecciones)
6. [Manejo de Errores, Recursos y Bases de Datos (try-with-resources, JDBC)](#6-manejo-de-errores-recursos-y-bases-de-datos)
7. [Concurrencia e Hilos (Threads, Sincronización y Condiciones de Carrera)](#7-concurrencia-e-hilos-threads)

---

## 🏛️ 1. POO, Clases y Objetos

### ❓ P1. ¿Cuál es la relación correcta entre clase y objeto?
* ❌ *No tienen relación*
* ✅ **Un objeto es una instancia de una clase**
* ❌ *Un objeto es una plantilla de clases*
* ❌ *Una clase es una instancia de un objeto*

> **💡 Justificación:**  
> La **clase** es el plano arquitectónico o el molde de galletas (la plantilla). El **objeto** es la casa ya construida o la galleta real horneada: por eso en POO se define formalmente que el objeto es una **instancia** concreta de una clase.

---

### ❓ P2. Un objeto se caracteriza por:
* ❌ *Su nombre de clase*
* ❌ *Solo sus métodos*
* ❌ *Solo sus atributos*
* ✅ **Identidad, estado y comportamiento**

> **💡 Justificación:**  
> Son los tres pilares universales de cualquier objeto:
> 1. **Identidad:** Es único en la memoria física (su dirección/referencia en el Heap).
> 2. **Estado:** Los valores actuales de sus atributos en cada momento (ej: `saldo = 500`).
> 3. **Comportamiento:** Lo que sabe hacer a través de sus métodos (ej: `ingresar()`, `vender()`).

---

### ❓ P3. La encapsulación consiste en:
* ✅ **Ocultar el estado y exponer una interfaz controlada**
* ❌ *Crear muchos objetos*
* ❌ *Heredar de varias clases*
* ❌ *Convertir tipos*

> **💡 Justificación:**  
> Encapsular (como una cápsula de medicina o una caja fuerte) significa poner los atributos en privado (`private`) para que nadie los altere directamente desde fuera, y ofrecer métodos públicos (`getters`, `setters`, operaciones) que validen y controlen cualquier acceso.

---

### ❓ P4. ¿Cuál es la clase raíz en Java?
* ✅ **Object**
* ❌ *Root*
* ❌ *Class*
* ❌ *Base*

> **💡 Justificación:**  
> En Java, absolutamente todas las clases (incluso las que creas tú como `tpv` o `Garaje`) heredan directa o indirectamente de `java.lang.Object`. Es la raíz de la que proceden métodos como `toString()`, `equals()` y `hashCode()`.

---

### ❓ P5. Un atributo `static` se caracteriza por:
* ❌ *Ser siempre constante*
* ❌ *No poder leerse*
* ✅ **Existir una sola vez, compartido por la clase**
* ❌ *Existir una vez por objeto*

> **💡 Justificación:**  
> Un atributo normal existe una vez por cada objeto (100 coches tienen 100 matrículas distintas). Un atributo **`static`** pertenece a la **clase**: solo existe **una única copia física en memoria** compartida por todos los objetos creados.  
> *(Si fuera constante llevaría la palabra `final`)*.

---

### ❓ P6. En Python, el primer parámetro de un método de instancia suele llamarse:
* ✅ **self**
* ❌ *obj*
* ❌ *cls*
* ❌ *this*

> **💡 Justificación:**  
> En Python es obligatorio que el método reciba explícitamente la referencia al propio objeto, y por convenio oficial se llama siempre `self` (ej: `def __init__(self, nombre):`). En Java su equivalente es la palabra clave `this`.  
> *(`cls` se reserva para métodos de clase con `@classmethod`)*.

---

### ❓ P47. ¿Qué indica la palabra `this`?
* ❌ *La clase padre*
* ✅ **El objeto actual**
* ❌ *Un método estático*
* ❌ *Una variable global*

> **💡 Justificación:**  
> En Java, **`this`** es una autoreferencia que apunta a la **instancia u objeto actual** sobre el que se está ejecutando el código (muy usado para diferenciar atributos de parámetros: `this.nombre = nombre`).  
> *(Para referirse a la clase padre se utiliza la palabra reservada `super`)*.

---

### ❓ P41. Un buen diseño orientado a objetos busca:
* ❌ *Evitar la ocultación de información*
* ❌ *Máximo número de clases*
* ❌ *Alto acoplamiento y baja cohesión*
* ✅ **Bajo acoplamiento y alta cohesión**

> **💡 Justificación:**  
> Es la regla de oro de la arquitectura del software:
> * **Bajo acoplamiento:** Las clases deben depender lo menos posible unas de otras para que cambiar una no rompa las demás.
> * **Alta cohesión:** Cada clase debe tener una única responsabilidad bien definida (hacer una sola cosa y hacerla bien).

---

### ❓ P42. ¿Cuál NO es uno de los cuatro pilares de la POO?
* ✅ **Compilación**
* ❌ *Polimorfismo*
* ❌ *Herencia*
* ❌ *Encapsulación*

> **💡 Justificación:**  
> Los **4 pilares fundamentales de la POO** son:  
> 1. **Abstracción**  
> 2. **Encapsulación**  
> 3. **Herencia**  
> 4. **Polimorfismo**  
> *(La **compilación** es el proceso de traducir código fuente a bytecode/binario, no tiene nada que ver con los pilares del paradigma de objetos)*.

---

## 🧬 2. Herencia, Polimorfismo y Métodos

### ❓ P40. ¿Qué relación expresa la herencia?
* ❌ *«tiene un»*
* ❌ *«crea un»*
* ✅ **«es un»**
* ❌ *«usa un»*

> **💡 Justificación:**  
> En programación orientada a objetos (POO), la **herencia** modela siempre una relación **«es un»** (*IS-A* en inglés). Por ejemplo: un `Coche` **es un** `Vehiculo`, un `Gato` **es un** `Animal`.  
> * Por qué fallan las otras:  
>   * **«tiene un»** (*HAS-A*): Modela **composición** o **asociación** (ej: un `Coche` tiene un `Motor`).  
>   * **«usa un»**: Modela **dependencia** (ej: un método recibe un parámetro de otra clase para hacer un cálculo).  
>   * **«crea un»**: Modela instanciación/factoría.

---

### ❓ P45. Un procedimiento se diferencia de una función en que:
* ❌ *No tiene parámetros*
* ✅ **No devuelve valor**
* ❌ *Siempre es privado*
* ❌ *Siempre es recursivo*

> **💡 Justificación:**  
> En la teoría clásica de programación y algoritmos:  
> * **Función:** Realiza un cálculo y **devuelve un resultado** (ej: `int suma(int a, int b) { return a + b; }`).  
> * **Procedimiento:** Ejecuta una serie de instrucciones/acciones pero **NO devuelve ningún valor** (en Java se corresponde con los métodos de retorno **`void`**, como `void imprimir()`). Ambos pueden recibir parámetros y tener cualquier modificador de acceso.

---

### ❓ P7. La sobrecarga de métodos se resuelve:
* ❌ *En ejecución*
* ✅ **En compilación**
* ❌ *Al enlazar*
* ❌ *Nunca*

> **💡 Justificación:**  
> * **Sobrecarga (*Overloading*):** Mismo nombre con distintos parámetros (ej: `sumar(int, int)` y `sumar(double, double)`). El compilador analiza los tipos de argumentos y decide a qué método llamar **antes de arrancar el programa (en compilación)**.
> * *(La que se resuelve **en ejecución** es la **sobrescritura con `@Override`**, que es polimorfismo dinámico)*.

---

### ❓ P8. Un constructor sobrecargado es aquel que:
* ❌ *Hereda de otro constructor*
* ❌ *Es privado*
* ❌ *Devuelve un valor*
* ✅ **Tiene el mismo nombre y distintos parámetros**

> **💡 Justificación:**  
> Tener constructores sobrecargados permite crear objetos de varias formas (ej: un constructor `Vehiculo(matricula)` y otro `Vehiculo(matricula, modelo)`). Tienen el mismo nombre (el de la clase) pero diferente lista de parámetros.

---

### ❓ P9. En Java, ¿qué palabra clave se usa para invocar el constructor de la superclase?
* ❌ *parent*
* ❌ *this*
* ❌ *base*
* ✅ **super**

> **💡 Justificación:**  
> `super(...)` es la instrucción oficial en Java para llamar al constructor del padre desde el constructor hijo.  
> *(`this(...)` llama a otro constructor de la misma clase; `base` es de C#; `parent` no existe en Java)*.

---

### ❓ P10. ¿Se puede instanciar con `new` una clase abstracta?
* ❌ *Sí, siempre*
* ✅ **No**
* ❌ *Solo si tiene constructor*
* ❌ *Solo en Python*

> **💡 Justificación:**  
> Las clases abstractas son moldes incompletos con métodos sin cuerpo (como `abstract class Vehiculo`). Java **prohíbe tajantemente** hacer `new Vehiculo()`. Solo se pueden instanciar sus clases hijas concretas que hayan rellenado los métodos obligatorios (`new Coche()`).

---

### ❓ P11. Una clase Java puede implementar:
* ❌ *Una sola interfaz*
* ✅ **Varias interfaces**
* ❌ *Ninguna*
* ❌ *Solo si es abstracta*

> **💡 Justificación:**  
> Java no admite herencia múltiple de clases (`extends` solo permite un único padre para evitar el problema del diamante), pero permite implementar **todas las interfaces que necesites** separadas por comas (`implements Conducible, Asegurable, Vendible`).

---

### ❓ P12. ¿Qué evita un `ClassCastException` al bajar de tipo?
* ✅ **instanceof antes de convertir**
* ❌ *Nada*
* ❌ *static*
* ❌ *final*

> **💡 Justificación:**  
> Al hacer un *downcasting* (forzar la conversión de un padre a un hijo, ej: `(Coche) v`), si el objeto en realidad era una `Moto`, el programa colapsa con `ClassCastException`. Para hacerlo de forma 100% segura, se comprueba primero con `if (v instanceof Coche)`.

---

### ❓ P38. Al crear un objeto de una subclase, ¿qué constructor se ejecuta primero?
* ❌ *El de Object solo al final*
* ❌ *El de la subclase*
* ✅ **El de la superclase**
* ❌ *Ninguno*

> **💡 Justificación:**  
> En Java, antes de construir la casa hay que poner los cimientos: la primera instrucción dentro del constructor de un hijo siempre es la llamada al constructor del padre (`super(...)`). Por tanto, la cadena de construcción va de arriba hacia abajo (`Object` $\rightarrow$ superclase $\rightarrow$ subclase), ejecutándose **primero el constructor de la superclase**.

---

## 🔗 3. Relaciones entre Clases

### ❓ P13. Una línea de factura respecto a su factura se considera que es:
* ✅ **Composición**
* ❌ *Dependencia*
* ❌ *Asociación*
* ❌ *Herencia*

> **💡 Justificación:**  
> Es el ejemplo canónico de **composición** (relación fuerte todo-parte). Una línea de factura no tiene sentido ni razón de existir por sí sola fuera de su factura correspondiente.

---

### ❓ P14. En una composición, si se elimina EL TODO:
* ❌ *Se genera una excepción*
* ✅ **Las partes dejan de existir**
* ❌ *Las partes siguen existiendo*
* ❌ *Nada cambia en la memoria*

> **💡 Justificación:**  
> En la composición, el ciclo de vida de las partes está ligado al 100% al del contenedor (el todo). Si destruyes la Factura o la Casa, sus líneas de factura o sus habitaciones **se destruyen y dejan de existir con ella**.  
> *(En la agregación, en cambio, las partes sí sobreviven)*.

---

### ❓ P15. ¿Qué relación es más débil: asociación o composición?
* ❌ *Son iguales*
* ❌ *Ninguna*
* ✅ **Asociación**
* ❌ *Composición*

> **💡 Justificación:**  
> En la **asociación**, dos objetos colaboran pero son independientes (ej: un `Profesor` y un `Curso`: si el curso termina, el profesor sigue existiendo). La **composición** es mucho más fuerte e indivisible.

---

### ❓ P16. «Favorece la composición sobre la herencia» busca principalmente:
* ❌ *Evitar interfaces*
* ✅ **Menor acoplamiento y mayor flexibilidad**
* ❌ *Más clases*
* ❌ *Menos objetos*

> **💡 Justificación:**  
> Principio de diseño de software fundamental (*Gang of Four*). La herencia genera un acoplamiento muy rígido (cualquier cambio en el padre puede romper a todos los hijos). La composición (usar objetos dentro de objetos) permite intercambiar componentes en caliente con **menor dependencia y máxima flexibilidad**.

---

### ❓ P17. Al analizar un enunciado, los verbos suelen indicar candidatos a:
* ✅ **Métodos**
* ❌ *Paquetes*
* ❌ *Clases*

> **💡 Justificación:**  
> En la técnica de análisis gramatical de Abbott:
> * **Sustantivos** (cosas/entidades) $\rightarrow$ Clases (`Cliente`, `Factura`).
> * **Verbos** (acciones/comportamiento) $\rightarrow$ **Métodos** (`vender()`, `ingresar()`).
> * **Adjetivos** (estados/cualidades) $\rightarrow$ Atributos (`saldo`, `activo`).

---

### ❓ P18. ¿Qué fase del ciclo de desarrollo corresponde a decidir qué clases tendrá el sistema y cómo se relacionan?
* ❌ *Pruebas de aceptación*
* ❌ *Mantenimiento*
* ✅ **Diseño**
* ❌ *Análisis de requisitos*

> **💡 Justificación:**  
> El **Análisis** define *qué* necesita el cliente. El **Diseño** define *cómo* construirlo: dibuja los diagramas UML, decide **qué clases habrá, qué atributos/métodos tendrán y cómo se relacionarán**. La **Implementación** es cuando se escribe el código.

---

## 💾 4. Gestión de Memoria y Tipos de Datos

### ❓ P19. String en Java es:
* ❌ *Mutable*
* ✅ **Inmutable**
* ❌ *Un tipo primitivo*
* ❌ *Un array de enteros*

> **💡 Justificación:**  
> En Java, `String` es un objeto **inmutable**. Una vez creado en el montón (Heap), su texto original jamás se puede modificar. Métodos como `.toUpperCase()` o el operador `+` no modifican la cadena original: crean un objeto `String` nuevo en memoria.

---

### ❓ P20. Si a y b son referencias al mismo objeto, `a == b` es:
* ❌ *Depende del contenido*
* ✅ **Verdadero**
* ❌ *Error de compilación*
* ❌ *Falso*

> **💡 Justificación:**  
> El operador `==` en objetos **compara direcciones de memoria física** (dónde apuntan en el Heap). Si `a` y `b` apuntan exactamente al mismo objeto, sus direcciones de memoria son idénticas, por lo que `a == b` siempre devuelve **`true`**.  
> *(Para comparar contenidos de objetos distintos se usa `.equals()`)*.

---

### ❓ P21. ¿Qué guarda la Pila (Stack) y qué guarda el Montón (Heap)?
* **La Pila (*Stack*):**
  * Variables locales primitivas (`int`, `double`, `boolean`).
  * Las **referencias/punteros** (las direcciones que apuntan a los objetos).
  * Control de llamadas a métodos (se libera automáticamente al terminar el método).
* **El Montón (*Heap*):**
  * **Todos los objetos creados con `new`** (`new Coche()`, `new tpv()`, `ArrayList`, `String`).
  * Los arrays (`new int[10]`).
  * Los atributos internos que forman parte de los objetos.
  * Son limpiados por el Garbage Collector cuando quedan huérfanos.

---

### ❓ P46. Los objetos de un programa Java se almacenan en:
* ❌ *La pila (stack)*
* ❌ *El disco*
* ❌ *El hilo*
* ✅ **El montón (heap)**

> **💡 Justificación:**  
> En la arquitectura de memoria de la JVM:  
> * **El montón (*Heap*):** Es el área dinámica de memoria donde residen **todos los objetos** instanciados con `new` (y sus atributos).  
> * **La pila (*Stack*):** Solo almacena variables locales primitivas y las referencias (punteros/direcciones) que apuntan hacia los objetos del Heap.

---

### ❓ P22. ¿Qué hace el recolector de basura (*Garbage Collector*)?
* ✅ **Libera la memoria de objetos no alcanzables**
* ❌ *Elimina clases sin uso del disco*
* ❌ *Cierra ficheros abiertos*
* ❌ *Compila el código*

> **💡 Justificación:**  
> El **Garbage Collector** analiza el montón (Heap) en segundo plano: en cuanto detecta que un objeto ha quedado huérfano (ninguna variable del programa apunta hacia él), destruye el objeto y libera su espacio en la memoria RAM automáticamente.

---

### ❓ P44. Java pasa los objetos a los métodos:
* ❌ *Copiando el objeto entero*
* ❌ *Por puntero explícito*
* ❌ *Siempre por referencia de variable*
* ✅ **Por valor de la referencia**

> **💡 Justificación:**  
> En Java **TODO se pasa estrictamente por valor** (*pass-by-value*):  
> * Para tipos primitivos (`int`, `double`): se pasa una copia directa del valor.  
> * Para objetos: la variable contiene la **referencia** (la dirección de memoria en el Heap), y lo que se pasa al método es una **copia del valor de esa referencia**.  
> Por eso, si modificas los atributos del objeto dentro del método, los cambios se reflejan fuera (porque ambas referencias apuntan al mismo objeto en el Heap); pero si reasignas el parámetro (`obj = new Otro()`), la variable original fuera del método no cambia.

---

## 📦 5. Estructuras de Información y Colecciones

### ❓ P39. El paquete de Java con las colecciones es:
* ❌ *java.net*
* ❌ *java.io*
* ✅ **java.util**
* ❌ *java.sql*

> **💡 Justificación:**  
> En Java, todo el framework de colecciones (*Java Collections Framework*: `List`, `ArrayList`, `LinkedList`, `Set`, `HashSet`, `Map`, `HashMap`, `Queue`, `Deque`, etc.) se encuentra dentro del paquete **`java.util`**.  
> * **`java.net`**: Paquete para conexiones de red, sockets y URLs/HTTP.  
> * **`java.io`**: Paquete para entrada y salida de datos y ficheros (`File`, `InputStream`, `BufferedReader`).  
> * **`java.sql`**: Paquete JDBC para conexión y consultas a bases de datos relacionales (`Connection`, `Statement`, `ResultSet`).

---

### ❓ P43. ¿Qué conviene devolver desde un getter de una colección interna?
* ❌ *El tamaño solo*
* ❌ *null*
* ❌ *La lista original*
* ✅ **Una copia o vista inmutable**

> **💡 Justificación:**  
> Devolver la lista original rompe por completo la **encapsulación**: cualquier código externo que llame a `objeto.getLista()` podría hacer `.clear()` o `.add(...)` modificando el estado interno del objeto sin su consentimiento.  
> Por seguridad y buenas prácticas, se debe devolver una copia defensiva (ej: `new ArrayList<>(this.lista)`) o una vista de solo lectura (ej: `Collections.unmodifiableList(this.lista)`).

---

### ❓ P23. ¿Qué estructura elimina duplicados automáticamente?
* ❌ *List*
* ✅ **Set**
* ❌ *Array*
* ❌ *Deque*

> **💡 Justificación:**  
> La interfaz **`Set`** (`HashSet`, `TreeSet`) representa el concepto matemático de conjunto: **jamás permite elementos repetidos**. Si intentas añadir un elemento que ya existe, lo rechaza automáticamente.  
> *(Las listas, arrays y deques sí admiten duplicados)*.

---

### ❓ P24. ¿El `TreeMap` va con clave? ¿Qué diferencia tiene con `TreeSet` y `HashMap`?
* **¿Lleva clave?:** **SÍ, absolutamente.** Todo lo que termine en `Map` almacena pares de **Clave $\rightarrow$ Valor** (`Map<K, V>`).
* **`TreeSet` vs `TreeMap`:**
  * `TreeSet`: Guarda elementos individuales (una columna), sin duplicados y ordenados de menor a mayor.
  * `TreeMap`: Guarda parejas de `Clave -> Valor` (dos columnas), ordenadas automáticamente por su **Clave**.
* **`HashMap` vs `TreeMap`:**
  * `HashMap`: Es más rápido, pero desordena las claves por completo.
  * `TreeMap`: Mantiene las claves siempre perfectamente ordenadas (alfabéticamente o numéricamente).

---

### ❓ P25. Una función recursiva debe tener siempre:
* ❌ *Un bucle for*
* ✅ **Un caso base**
* ❌ *Variables globales*
* ❌ *Parámetros opcionales*

> **💡 Justificación:**  
> Una función recursiva se llama a sí misma. El **caso base** es la condición de parada de emergencia: si no existiera, se llamaría infinitamente hasta agotar la memoria de la pila y provocar un error catastrófico `StackOverflowError`.

---

## 🛡️ 6. Manejo de Errores, Recursos y Bases de Datos

### ❓ P26. `try-with-resources` sirve para:
* ✅ **Cerrar automáticamente recurso AutoCloseable**
* ❌ *Definir enums*
* ❌ *Capturar todas las excepciones*
* ❌ *Crear hilos*

> **💡 Justificación:**  
> Cualquier objeto colocado dentro del paréntesis de `try (...)` que implemente la interfaz `AutoCloseable` (como un `Scanner`, un fichero o una conexión a BD) llamará a su método `.close()` de forma automática al terminar, ahorrándonos tener que escribir bloques `finally` manuales.

---

### ❓ P27. Para consultas con datos del usuario es más seguro:
* ❌ *Concatenar el texto SQL*
* ✅ **PreparedStatement con parámetros**
* ❌ *Guardar la consulta en un fichero*
* ❌ *Statement sin parámetros*

> **💡 Justificación:**  
> Concatenar cadenas en SQL (`"WHERE user = '" + input + "'"` ) abre la puerta a ataques de **Inyección SQL** (pueden inyectar comandos para robar o borrar la base de datos). **`PreparedStatement`** utiliza interrogaciones `?` para desinfectar y parametrizar los datos de forma 100% segura.

---

### ❓ P28. En el patrón observador, la fuente del evento:
* ❌ *No puede tener más de un oyente*
* ❌ *Ejecuta el código de los oyentes directamente*
* ❌ *Hereda de los oyentes*
* ✅ **Mantiene una lista de oyentes y los notifica**

> **💡 Justificación:**  
> En el patrón **Observador** (*Observer*), el emisor de los eventos mantiene internamente una colección (como un `List<Oyente>`), y cuando sucede una acción importante, recorre la lista notificando a todos los suscriptores.

---

## 🧵 7. Concurrencia e Hilos (*Threads*)

### ❓ P29. ¿Qué es un hilo (*Thread*) en Java?
* ❌ *Un tipo de variable*
* ✅ **Una unidad de ejecución dentro de un proceso**
* ❌ *Una colección de objetos*
* ❌ *Un método especial de una clase*

> **💡 Justificación:**  
> Un hilo es el flujo de ejecución más pequeño que la CPU puede programar. Un mismo programa en Java puede lanzar varios hilos para hacer tareas simultáneas compartiendo la memoria.

---

### ❓ P48. ¿Qué dos formas hay de crear un hilo en Java?
* ✅ **Heredar de Thread o implementar Runnable**
* ❌ *Usar static o final*
* ❌ *Heredar de Object o de String*
* ❌ *Usar try o catch*

> **💡 Justificación:**  
> En Java estándar existen dos maneras fundamentales de definir la lógica de un hilo:  
> 1. **Heredar de la clase `Thread`:** `class MiHilo extends Thread { public void run() { ... } }` y luego `new MiHilo().start();`  
> 2. **Implementar la interfaz `Runnable`:** `class MiTarea implements Runnable { public void run() { ... } }` y luego `new Thread(new MiTarea()).start();` *(forma recomendada porque permite a la clase heredar de otra clase distinta)*.

---

### ❓ P51. ¿En qué estado está un hilo creado con `new` pero sin llamar a `start()`?
* ❌ *RUNNABLE*
* ❌ *TERMINATED*
* ❌ *BLOCKED*
* ✅ **NEW**

> **💡 Justificación:**  
> En el ciclo de vida de un hilo en Java (`Thread.State`):  
> * **`NEW`**: El objeto `Thread` ha sido instanciado con `new Thread()`, pero **aún no se ha invocado `start()`**.  
> * **`RUNNABLE`**: Se ha llamado a `start()` y el hilo está corriendo o listo para que la CPU le dé turno.  
> * **`BLOCKED` / `WAITING` / `TIMED_WAITING`**: Esperando un monitor de bloqueo (`synchronized`), un `wait()`, `join()` o `sleep()`.  
> * **`TERMINATED`**: El método `run()` ha finalizado y el hilo ha muerto.

---

### ❓ P52. ¿Qué es un hilo demonio (*daemon*)?
* ❌ *Un hilo que bloquea el programa*
* ❌ *Un hilo con prioridad máxima*
* ✅ **Un hilo de servicio en segundo plano que no impide que la JVM termine**
* ❌ *Un hilo que nunca se puede crear*

> **💡 Justificación:**  
> Un **hilo demonio** (*daemon thread*, configurado con `hilo.setDaemon(true)`) es un proceso auxiliar de fondo (como el Garbage Collector de Java):  
> * Da servicio o soporte a los hilos de usuario.  
> * **No retiene a la JVM:** Si todos los hilos normales (de usuario) terminan su trabajo, la máquina virtual Java finaliza y cierra el programa de golpe sin esperar a que los hilos demonio concluyan.

---

### ❓ P30. ¿Qué método contiene el código que ejecutará un hilo?
* ❌ *start()*
* ✅ **run()**
* ❌ *sleep()*
* ❌ *boot()*

> **💡 Justificación:**  
> * **`run()`**: Es el método donde se **programa el trabajo real** que debe hacer el hilo.
> * *(Cuidado con la trampa: para **arrancar** el hilo se llama a `start()`, pero el que **contiene el código** es `run()`)*.

---

### ❓ P49. ¿Qué ocurre si llamas a `run()` directamente en vez de a `start()`?
* ❌ *Se crea un hilo nuevo igualmente*
* ❌ *Da error de compilación*
* ✅ **El código se ejecuta en el hilo actual, sin crear un hilo nuevo**
* ❌ *El programa termina*

> **💡 Justificación:**  
> Es una de las trampas más típicas de examen en Java:  
> * **`start()`**: Registra el hilo en el sistema operativo, crea una nueva pila (*call stack*) y arranca la ejecución en paralelo.  
> * **`run()`**: Es un método Java ordinario. Si lo llamas tú manualmente (`miHilo.run()`), el código se ejecutará secuencialmente dentro del **mismo hilo actual** (por ejemplo, el hilo `main`), sin ninguna concurrencia ni nuevo hilo.

---

### ❓ P31. ¿Para qué sirve el método `sleep()`?
* ❌ *Para eliminar un hilo*
* ✅ **Pausar temporalmente la ejecución del hilo**
* ❌ *Para crear un objeto*
* ❌ *Para finalizar todos los hilos*

> **💡 Justificación:**  
> `Thread.sleep(milisegundos)` congela la ejecución del hilo durante el tiempo indicado sin consumir procesador, pasando el hilo a estado de espera/bloqueado.

---

### ❓ P32. ¿Qué significa que dos hilos se ejecutan concurrentemente?
* ❌ *Que siempre se ejecutan exactamente al mismo tiempo*
* ❌ *Que comparten obligatoriamente la misma variable*
* ✅ **Que sus ejecuciones pueden solaparse en el tiempo**
* ❌ *Que uno debe terminar antes de comenzar el otro*

> **💡 Justificación:**  
> Concurrencia significa que dos o más tareas están vivas y progresando en el mismo periodo, pudiendo **solaparse** (intercalándose en un único procesador o en paralelo en varios núcleos).

---

### ❓ P33. ¿Para qué sirve la palabra `synchronized` en Java?
* ❌ *Para ordenar alfabéticamente los objetos*
* ✅ **Para controlar el acceso concurrente a recursos**
* ❌ *Para crear nuevos hilos*
* ❌ *Para detener la JVM*

> **💡 Justificación:**  
> `synchronized` actúa como un cerrojo: garantiza la **exclusión mutua**, impidiendo que dos o más hilos modifiquen la misma variable o método a la vez y corrompan los datos.

---

### ❓ P34. ¿Qué evita que dos hilos modifiquen a la vez un contador compartido?
* ❌ *final* (congela el valor para siempre, no permitiría sumar)
* ❌ *private* (solo oculta la visibilidad, no protege de hilos)
* ❌ *static* (empeora el problema al ser compartida por todos)
* ✅ **synchronized**

---

### ❓ P35. ¿Qué método permite esperar a que otro hilo termine su ejecución?
* ❌ *sleep()*
* ❌ *wait()*
* ✅ **join()**
* ❌ *interrupt()*

> **💡 Justificación:**  
> `hilo.join()` pausa al hilo que hace la llamada hasta que el hilo indicado finalice todo su trabajo y muera de forma natural.

---

### ❓ P36. Una condición de carrera ocurre cuando:
* ❌ *Un hilo termina antes que otro*
* ❌ *Hay una excepción*
* ❌ *Se usa synchronized*
* ✅ **El resultado depende del orden de ejecución**

> **💡 Justificación:**  
> Una **condición de carrera** (*race condition*) es un fallo grave de concurrencia: varios hilos leen y escriben en una variable compartida sin sincronizar, haciendo que el resultado final del programa sea erróneo e impredecible según la velocidad o el orden de la CPU.

---

### ❓ P50. ¿Qué es un interbloqueo (*deadlock*)?
* ❌ *Un hilo que termina antes de tiempo*
* ❌ *Un hilo con prioridad máxima*
* ❌ *Un error de sintaxis*
* ✅ **Dos o más hilos esperándose mutuamente... (para liberar un recurso)**

> **💡 Justificación:**  
> Un **interbloqueo** (*deadlock* o "abrazo mortal") ocurre en programación concurrente cuando dos o más hilos se quedan congelados indefinidamente porque cada uno está esperando a que el otro libere un recurso bloqueado:  
> * El **Hilo 1** tiene bloqueado el Recurso A y espera el Recurso B.  
> * El **Hilo 2** tiene bloqueado el Recurso B y espera el Recurso A.  
> Como ninguno puede continuar sin el otro, el programa se queda colgado para siempre.

---

### ❓ P37. ¿Se usa `poll` para matar un Thread? ¿Cómo se detiene?
* **NO.** `poll()` es un método de colas (`Queue.poll()`) para extraer el primer elemento.
* Los hilos **no se matan a la fuerza** (el viejo `thread.stop()` está prohibido/obsoleto).
* **Forma correcta de detenerlos:**
  * En un hilo individual: se le avisa con **`hilo.interrupt()`** y el hilo comprueba en su bucle `isInterrupted()`.
  * En una piscina de hilos (*Pool*): se usa **`pool.shutdown()`** o **`pool.shutdownNow()`**.
