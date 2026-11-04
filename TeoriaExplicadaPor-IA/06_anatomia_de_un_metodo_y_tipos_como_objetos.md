# 📘 Manual 6: Anatomía de un Método, Clases como Tipos y Preguntas Frecuentes Resueltas

> **Archivo:** [`06_anatomia_de_un_metodo_y_tipos_como_objetos.md`](./06_anatomia_de_un_metodo_y_tipos_como_objetos.md)  
> **Ubicación:** `TeoriaExplicadaPor-IA`  
> **Propósito:** Recopilatorio de dudas reales sobre la cabecera de métodos, clases como tipos de datos, qué significa `!= null`, cómo viaja el mensaje en `e.getMessage()`, y buenas prácticas con `Scanner`.

---

## 🔬 1. La Radiografía de un Método en Java

Fíjate en esta línea que tantas dudas genera al principio:

```java
public void setLed(SemaforoLed led) {
```

Si la miramos al microscopio palabra por palabra:

```text
  public        void          setLed        (   SemaforoLed          led   )
    ▲            ▲              ▲                    ▲                ▲
    │            │              │                    │                │
1. Visibilidad 2. Retorno  3. Nombre del       4. TIPO DE DATO   5. Nombre de la
  (Quién entra) (Qué sale)    MÉTODO              (El Molde)        VARIABLE
```

### ¿Qué hace cada pieza?
1. **`public` (Visibilidad):** Dice quién tiene permiso para llamar a este método. `public` significa que se puede llamar desde cualquier parte del programa (por ejemplo, desde el `main`).
2. **`void` (Tipo de retorno):** Dice qué dato te devuelve el método al terminar. `void` significa que hace un trabajo interno pero **no te devuelve ningún dato a la mano** (no hay un `return valor;`).
3. **`setLed` (Nombre del método):** Es el **verbo o acción**. Representa la orden que le das al objeto: *"Garaje, ejecuta la acción de setLed"*. **NO es una variable**.
4. **`SemaforoLed` (Tipo de dato del parámetro):** Especifica **qué clase de dato** tiene permiso para entrar por el paréntesis.
5. **`led` (Nombre de la variable / Parámetro):** Es el nombre con el que bautizamos al objeto que entra para poder usarlo dentro de las llaves `{ ... }`.

---

## 🎨 2. ¿Por qué `SemaforoLed` tiene el mismo color que `int` o `boolean` en VS Code?

Porque en Java **TODA CLASE QUE CREAS ES UN NUEVO TIPO DE DATO**.

VS Code pinta del mismo color (azul o verde según el tema) todo aquello que sea un **Tipo de Dato**:

| Tipo Primitivo de Java | Clase que creas tú | ¿Qué significa en la práctica? |
| :--- | :--- | :--- |
| `int numero` | `SemaforoLed led` | Ambos son el **molde / tipo** y el segundo es la **variable**. |
| `boolean activo` | `Alumno estudiante` | `boolean` acepta true/false; `Alumno` acepta un objeto Alumno. |
| `double precio` | `Curso cursoJava` | `double` acepta números con coma; `Curso` acepta un objeto Curso. |
| `String texto` | `garaje miGaraje` | `String` es una clase de Java; `garaje` es una clase tuya. |

> 💡 **La magia de la Programación Orientada a Objetos:**  
> Cuando creas `class SemaforoLed { ... }`, le estás enseñando a Java una **nueva palabra en su vocabulario**. A partir de ese segundo, puedes usar `SemaforoLed` en cualquier sitio donde antes ponías `int`, `String` o `double`.

---

## 🔌 3. ¿Qué es un Setter con Lógica y por qué ponemos `if (this.led != null)`?

En nuestro garaje escribimos:

```java
public void setLed(SemaforoLed led) { 
    this.led = led; // 1. Guardamos el semáforo dentro del garaje

    // 2. En cuanto lo conectamos, si no es nulo, actualizamos su color:
    if (this.led != null) { 
        this.led.actualizarColor(this.cochesDentro); 
    } 
}
```

### ❓ Duda: ¿Por qué ponemos `if (this.led != null)`? ¿Lo estamos dejando en null?
**¡NO!** El símbolo **`!=`** significa **"ES DISTINTO DE"** (o "NO ES").

Por lo tanto:
```java
if (this.led != null)
```
Significa literalmente:  
👉 **"¿Es `this.led` DISTINTO de null? (es decir: ¿SÍ que hay un LED conectado?)"**

#### 💥 ¿Qué pasaría si lo quitamos?
Si un usuario crea un garaje sin semáforo (`garaje g = new garaje();`) e intenta entrar un coche, `this.led` valdría `null` (vacío).  
Al intentar ejecutar `null.actualizarColor(...)`, Java explota inmediatamente con:
```
java.lang.NullPointerException
```
El `if (this.led != null)` actúa como un **escudo protector**: solo intenta cambiar el color si de verdad hay una bombilla enchufada.

---

## 📦 4. ¿Cómo sabe qué mensaje mostrar `e.getMessage()` si parece que no lo especificamos?

### ❓ Duda: Al hacer `System.out.println(e.getMessage());`, ¿de dónde sale ese texto?
**¡SÍ lo especificaste tú!** Viaja empaquetado dentro del `throw`.

Míralo como una carta con remitente y destinatario:

#### 1. Cuando lanzas el error en `entrarCoche()`:
```java
if (this.cochesDentro >= 50) {
    throw new IllegalStateException("Error: El garaje está lleno (50 coches). Entrada denegada.");
    //                              ▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲
    //                              TÚ MISMO ESCRIBISTE AQUÍ EL MENSAJE
}
```

#### 2. Cuando el `catch` atrapa el error:
```java
} catch (IllegalStateException e) {
    System.out.println(e.getMessage()); 
}
```
* **`e`** es la variable que atrapa el objeto de la excepción.
* **`e.getMessage()`** es un *getter* interno de Java que abre la excepción y saca **exactamente el texto que tú pusiste entre comillas al hacer el `throw`**.

---

## ⌨️ 5. ¿Puedo meter un `Scanner` dentro de un Getter como `NombreProfesor()`?

### ❓ Duda: Si tengo este método, ¿puedo meter un Scanner dentro?
```java
public String NombreProfesor() {
    if (nombreProfesor == null) {
        return "No hay profesor asignado";
    } else {
        return nombreProfesor;
    }
}
```

### Respuesta:
* **Técnicamente:** Java te deja compilarlo.
* **Profesionalmente:** **NO debes hacerlo jamás.**

#### ¿Por qué?
Porque `NombreProfesor()` es un método de **CONSULTA (un Getter)**. Su único cometido es responder a una pregunta en milisegundos: *"¿Quién es el profesor?"*.  
Si metes un `Scanner` dentro, cada vez que tu programa quiera consultar o imprimir el nombre, **se congelará la pantalla pidiendo al usuario que vuelva a teclear**.

#### La separación correcta:
* **Método para capturar datos (con `Scanner`):** `public void pedirDatos()`
* **Método para consultar (silencioso y rápido):** `public String getNombreProfesor()`

---

## 🔀 6. ¿Qué más existe en Java además de `if / else`?

1. **`switch / case / default`:** Para comparar una misma variable contra muchas opciones fijas (menús, días de la semana, colores).
   ```java
   switch (color) {
       case "VERDE" -> System.out.println("Pase");
       case "ROJO"  -> System.out.println("Alto");
       default      -> System.out.println("Averiado");
   }
   ```
2. **Operador Ternario (`? :`):** El `if-else` comprimido en una sola línea.
   ```java
   String estado = (nota >= 5) ? "Aprobado" : "Suspenso";
   ```
3. **Bucles de repetición:**
   * `for`: Cuando sabes cuántas vueltas dar (`for (int i = 0; i < 55; i++)`).
   * `for-each`: Para recorrer listas elemento a elemento (`for (Empleado e : lista)`).
   * `while`: Repite mientras se cumpla una condición.
   * `do-while`: Se ejecuta al menos una vez obligatoriamente antes de comprobar.

---

## 📄 7. ¿El código funciona igual en una sola línea que formateado?

### ❓ Duda: ¿Esta línea funcionaría igual si no la formateo?
```java
@Override
public String toString() {
    return getClass().getSimpleName() + " " + nombre;
}
```
**SÍ, 100% IGUAL.**  
Tanto si lo escribes en 4 líneas como si lo dejas en una:
```java
@Override public String toString() { return getClass().getSimpleName() + " " + nombre; }
```
Java ignora los saltos de línea y los espacios en blanco. Solo lo formateamos en varias líneas para que los ojos humanos lo lean y entiendan mejor.

---

## 🧠 Resumen Rápido

| Concepto | Qué significa |
| :--- | :--- |
| `setLed` | Nombre del método (la acción). |
| `SemaforoLed` | Tipo de dato (el molde del objeto). |
| `!= null` | Comprueba que la variable **NO** esté vacía antes de usarla. |
| `e.getMessage()` | Lee el texto que tú mismo guardaste dentro de `throw new Exception("texto")`. |
| `Scanner` | Va en el `main` o en métodos de entrada (`pedirDatos()`), **nunca** dentro de un getter. |
