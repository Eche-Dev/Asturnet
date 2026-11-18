# 🧠 Manual 5: Cómo Empezar desde Cero sin Bloquearse (Superando el Lienzo en Blanco)

> **Archivo:** [`05_como_empezar_desde_cero_sin_bloquearse.md`](./05_como_empezar_desde_cero_sin_bloquearse.md)  
> **Ubicación:** `TeoriaExplicadaPor-IA`  
> **Propósito:** La guía definitiva para cuando abras un archivo `.java` vacío y no sepas por dónde empezar ni dónde va cada cosa.

---

## 🎯 1. La Parálisis del Lienzo en Blanco

Lo que te está pasando es **lo más normal del mundo en programación**, y tiene un nombre: **la parálisis del lienzo en blanco**.

El 100% de los programadores pasan por ahí. Existen dos tipos de conocimiento:

1. **Conocimiento pasivo (Reconocer):** Lees el código y lo entiendes todo. Sabes por qué está cada línea. Tu cabeza ya razona como programador.
2. **Conocimiento activo (Crear de la nada):** Te ponen una pantalla negra en blanco y el cerebro se agobia porque no sabe qué poner primero: *¿un `double`? ¿un `void`? ¿el constructor? ¿el método?*

Cuando ves la solución y dices: *"¡Joder, si era justo lo que había pensado!"*, confirma que **la lógica ya la tienes**. Solo te falta una plantilla fija (**una receta de cocina que nunca cambia**) para que tu mano no dude.

---

## 🏛️ 2. La Regla de los 4 Pisos (El Esqueleto Universal)

A partir de hoy, cuando tengas que crear cualquier ejercicio desde cero (sea un garaje, un alumno, un coche o una cuenta bancaria), **nunca improvises**. Toda clase en Java es como un **edificio de 4 pisos**, siempre en el mismo orden:

```java
// ========================================================
// PISO 0: EL CASCARÓN (La fachada del edificio)
// ========================================================
public class NombreDeLaClase {

    // ====================================================
    // PISO 1: LOS ATRIBUTOS (La memoria del objeto)
    // Pregunta: ¿Qué datos necesita RECORDAR este objeto?
    // Regla: Siempre 'private', tipo y nombre;
    // ====================================================
    private int cochesDentro;
    private int cochesQueSalieron;

    // ====================================================
    // PISO 2: EL CONSTRUCTOR (El momento del nacimiento)
    // Pregunta: ¿Con qué valores debe arrancar al hacer 'new'?
    // Regla: Se llama IGUAL a la clase. NUNCA lleva 'void' ni tipo.
    // ====================================================
    public NombreDeLaClase() {
        this.cochesDentro = 0;
        this.cochesQueSalieron = 0;
    }

    // ====================================================
    // PISO 3: LOS MÉTODOS (Las habilidades o acciones)
    // Pregunta: ¿Qué sabe HACER?
    // Regla: ¿Devuelve algo? Pon el tipo. ¿No devuelve nada? Pon 'void'.
    // ====================================================
    public void entrarCoche() {
        // Hace la acción
        this.cochesDentro++;
    }

    public int cuantosCochesHay() {
        // Devuelve el dato
        return this.cochesDentro;
    }

    // ====================================================
    // PISO 4: EL MAIN (La zona de pruebas)
    // Pregunta: ¿Cómo pruebo que funciona?
    // ====================================================
    public static void main(String[] args) {
        NombreDeLaClase objeto = new NombreDeLaClase();
        objeto.entrarCoche();
    }
}
```

---

## 🚫 3. Despejando las Dudas que te Bloquean

### 1. ¿`class void` existe?
**¡NUNCA!** `class` y `void` jamás van juntos.
* `class` sirve únicamente para nombrar el molde: `public class Garaje`.
* `void` solo se usa en los métodos que hacen una tarea y **no devuelven ningún dato**: `public void salirCoche()`.

---

### 2. ¿Esto debe ir en el constructor o en un método?
Hazte esta pregunta trampa:
* ¿Es algo que ocurre **una sola vez al nacer el objeto**? (Darle nombre, poner saldo inicial a 0, validar que no empiece roto).  
  👉 **Va en el CONSTRUCTOR**.
* ¿Es algo que ocurre **a lo largo de la vida del objeto muchas veces**? (Entrar un coche, sacar dinero, matricular, calificar, consultar).  
  👉 **Va en un MÉTODO**.

---

### 3. ¿`void` o `double / int`?
Mira lo que quieres que haga el método:
* **Si el método es para preguntar un dato:**  
  *"Oye garaje, ¿cuántos coches hay?"* ➔ Te tiene que responder con un número entero:  
  ```java
  public int cuantosCochesHay() {
      return this.cochesDentro;
  }
  ```
* **Si el método es una orden o acción:**  
  *"Garaje, abre la barrera y entra un coche"* ➔ No te tiene que responder ningún número, solo hacer el trabajo:  
  ```java
  public void entrarCoche() {
      this.cochesDentro++;
  }
  ```

---

## 🧙‍♂️ 4. El Truco Secreto: Programa en Español Primero

La próxima vez que abras un archivo vacío, **no escribas Java al principio**. Haz esto en 2 minutos:

### Paso 1: Escribe la clase vacía
```java
public class Garaje {

}
```

### Paso 2: Escribe en español con comentarios `//` lo que tiene que tener
```java
public class Garaje {
    // 1. Necesito guardar los coches dentro y los que salen

    // 2. Método para meter un coche: si hay 50 error, si no sumo 1

    // 3. Método para sacar coche: resto 1 y sumo a los salidos
}
```

### Paso 3: Traduce cada comentario a 2 o 3 líneas de Java justo debajo
* *"Guardar los coches"* ➔ `private int cochesDentro = 0;`
* *"Si hay 50 error"* ➔ `if (this.cochesDentro >= 50) throw new IllegalStateException("Lleno");`
* *"Sumo 1"* ➔ `this.cochesDentro++;`

Al descomponer el problema en pequeñas frases en español, tu mente no se agobia y ya no te quedas pegado.
