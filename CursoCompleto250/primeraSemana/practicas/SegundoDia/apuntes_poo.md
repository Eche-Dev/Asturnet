# Apuntes POO - Tema 9

Resumen breve vinculado al ejercicio `Producto` (`p1_ej.java`).

---

## Lenguajes POO

- **Java** (el que usamos en este curso)
- **Python**
- **C#**

---

## Conceptos base

| Concepto | Qué es | Ejemplo en tu código |
|----------|--------|----------------------|
| **Clase** | Molde o plano para crear objetos. Define atributos y métodos. | `class Producto { ... }` |
| **Objeto** | Entidad concreta creada desde la clase, con identidad y estado propios. | `patatas`, `peras` |
| **Atributo** | Dato que guarda el estado del objeto. | `nombre`, `precio`, `stock` |
| **Método** | Operación que define el comportamiento del objeto. | `vender(int)`, `reponer(int)` |
| **Mensaje** | Invocación de un método sobre un objeto. | `patatas.vender(3)` |

---

## Tipos de datos usados

| Tipo | Qué guarda | Ejemplo tuyo |
|------|------------|--------------|
| `int` | Número entero | `stock` |
| `double` | Número con decimales | `precio` |
| `String` | Texto | `nombre` |

---

## Tipos de métodos según su función

| Tipo | Qué hace | Ejemplo |
|------|----------|---------|
| **Constructor** | Reserva e inicializa el estado al crear el objeto. | `public Producto(String, double, int)` |
| **Consulta (getter)** | Lee el estado sin modificarlo. | (aún no tienes uno; sería `getPrecio()`) |
| **Modificador (setter / comando)** | Cambia el estado del objeto. | `reponer(25)` cambia el `stock` |
| **Utilidad** | Cálculos auxiliares o conversión. | `toString()` convierte el objeto en texto |
| **Destrucción / cierre** | Libera recursos externos. | `close()` (no aplica en este ejercicio simple) |

---

## Palabras clave importantes

- **`this`**: referencia a la instancia actual.  
  Ejemplo: `this.nombre = nombre;` guarda el parámetro en el atributo del objeto que se está creando.

- **`void`**: indica que un método no devuelve ningún valor.  
  Ejemplo: `void reponer(int unidades)`.

- **Visibilidad / Encapsulamiento**:

  | Modificador | Quién puede verlo |
  |-------------|-------------------|
  | `public` | Cualquiera |
  | `protected` | Mismo paquete y subclases |
  | (sin modificador) | Solo el mismo paquete |
  | `private` | Solo la propia clase |

  En tu código los atributos son `private` para ocultar el estado interno y ofrecer una interfaz controlada (métodos como `vender` o `reponer`). Eso es **encapsulamiento**.

---

## Manejo de excepciones

```java
try {
    // Código que puede lanzar una excepción
    peras.vender(50);
} catch (IllegalArgumentException e) {
    // Código para reaccionar a la excepción
    System.out.println("Error " + e.getMessage());
}
// finally { } // Código que siempre se ejecuta (opcional)
```

---

## Herencia

- **Super-clase**: clase padre o base, contiene lo común.
- **Sub-clase**: hereda lo de la clase anterior.
- COMO SE DECLARA UNA SUB CLASE: `extends`.
- Herencia simple: una sola super-clase.
- Herencia múltiple: varias super-clases (en Java aplica a interfaces, no a clases directamente).

---

> Cuando tengas el cronograma o más contenido del curso, pásamelo y lo integramos con tu código.