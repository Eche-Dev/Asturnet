# 📘 Manual 6: Anatomía de un Método, Clases como Tipos de Datos y Setters con Objetos

> **Archivo:** [`06_anatomia_de_un_metodo_y_tipos_como_objetos.md`](./06_anatomia_de_un_metodo_y_tipos_como_objetos.md)  
> **Ubicación:** `TeoriaExplicadaPor-IA`  
> **Propósito:** Entender qué es cada palabra dentro de la cabecera de un método, por qué tus clases (`SemaforoLed`, `Alumno`) se comportan igual que `int` o `boolean`, y cómo funciona un Setter que conecta dos objetos.

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
3. **`setLed` (Nombre del método):** Es el **verbo o acción**. Representa la orden que le das al objeto: *"Garaje, ejecuta la acción de setLed"*.
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

## 🔄 3. Comparación lado a lado: Método con Primitivo vs Método con Objeto

Fíjate en lo idéntica que es la estructura:

```java
// Ejemplo A: Setter que recibe un número primitivo
public void setCoches(int cantidad) {
    this.cochesDentro = cantidad;
}

// Ejemplo B: Setter que recibe un objeto creado por ti
public void setLed(SemaforoLed led) {
    this.led = led;
}
```

* En el **Ejemplo A**, le pasas un simple número: `miGaraje.setCoches(10);`
* En el **Ejemplo B**, le pasas un objeto completo: `miGaraje.setLed(new SemaforoLed());`

---

## 🔌 4. ¿Qué es un Setter con Lógica? (Conexión de dos objetos)

Un setter no siempre se limita a copiar la variable (`this.led = led;`). En nuestro garaje hicimos un **Setter con Lógica de Asociación**:

```java
public void setLed(SemaforoLed led) { 
    this.led = led; // 1. Guardamos el semáforo dentro del garaje

    // 2. En cuanto lo conectamos, si no es nulo, actualizamos su color:
    if (this.led != null) { 
        this.led.actualizarColor(this.cochesDentro); 
    } 
}
```

### ¿Por qué pusimos el `if (this.led != null)`?
Porque si alguien llama a `miGaraje.setLed(null);` (desconectar el semáforo), evitamos que Java intente cambiarle el color a algo que no existe, protegiéndonos del error fatal `NullPointerException`.

---

## 🧠 Resumen Rápido para Grabar en la Memoria

1. Lo que va **fuera del paréntesis** (`setLed`) es el **NOMBRE DEL MÉTODO**.
2. Lo que va **a la izquierda dentro del paréntesis** (`SemaforoLed`) es el **TIPO DE DATO**.
3. Lo que va **a la derecha dentro del paréntesis** (`led`) es la **VARIABLE**.
