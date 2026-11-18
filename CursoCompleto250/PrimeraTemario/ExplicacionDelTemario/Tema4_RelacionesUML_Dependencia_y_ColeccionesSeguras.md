# 📘 Tema 4: Las 5 Relaciones de Clases, Diagramas UML y Colecciones Seguras

> **Correspondencia con el Manual Oficial UF2404:** Páginas 23 a 27.  
> **Objetivo:** Diferenciar con exactitud Dependencia, Asociación, Agregación y Composición, dibujar diagramas UML con sus símbolos oficiales y aprender a proteger colecciones con copias inmutables.

---

## 🧭 1. El Cuadro de las 5 Relaciones entre Clases

Los objetos casi nunca trabajan aislados. Para conectar clases existen exactamente 5 relaciones fundamentales:

| Relación | Significado en español | Pregunta mágica para detectarla | Ciclo de vida |
| :--- | :--- | :--- | :--- |
| **1. Dependencia** | *Usa temporalmente* | ¿La clase B solo entra como parámetro o variable local dentro de un método? | **Independiente** |
| **2. Asociación** | *Conoce a* | ¿La clase A guarda un atributo de B para comunicarse habitualmente? | **Independiente** |
| **3. Agregación** | *Tiene-un* (Todo-Parte Débil) | ¿El contenedor tiene elementos que seguirían existiendo si el contenedor se destruye? | **Independiente** |
| **4. Composición** | *Tiene-un* (Todo-Parte Fuerte) | ¿Las partes dejan de tener sentido y mueren si se destruye el contenedor? | **Ligado al todo** |
| **5. Generalización** | *Es-un* (Herencia) | ¿Es una versión especializada de una categoría superior? | Jerárquico |

---

## 🔍 2. La Diferencia Clave: ¿Dependencia o Asociación?

* **Dependencia (Uso puntual):** La clase `Impresora` no guarda al objeto `Documento` como atributo. Solo lo recibe un segundo en el método `imprimir(Documento doc)` y listo.
  ```java
  public class Impresora {
      public void imprimir(Documento doc) { // Dependencia puntual
          System.out.println(doc.getTexto());
      }
  }
  ```
* **Asociación (Conocimiento permanente):** La clase `Curso` guarda permanentemente un atributo del `Profesor`.
  ```java
  public class Curso {
      private Profesor tutor; // Asociación: mantiene la referencia guardada
  }
  ```

---

## 💎 3. Truco Infalible: ¿Agregación o Composición?

Hazte siempre esta pregunta:  
> **"Si destruyo el Todo, ¿las partes siguen teniendo sentido y vida propia?"**

* **Ejemplo 1 (Agregación):** Si se disuelve el `Departamento`, los `Profesores` no mueren; simplemente van a dar clase a otro sitio. **(Agregación: rombo blanco/vacío $\diamond$)**.
* **Ejemplo 2 (Composición):** Si borras un `Pedido`, las `LineasDePedido` ("3 unidades de tornillos de este pedido") no tienen ningún sentido flotando solas por el universo. Se destruyen con el pedido. **(Composición: rombo negro/relleno $\blacklozenge$)**.

---

## 📐 4. Simbología Oficial en Diagramas UML

En los exámenes de certificación te pedirán reconocer los conectores entre las cajas de clases:

```
Asociación:       ClaseA ---------------------> ClaseB   (Línea continua con flecha abierta)
Dependencia:      ClaseA - - - - - - - - - - -> ClaseB   (Línea discontinua con flecha abierta)
Agregación:       Todo   <>------------------- Parte    (Rombo hueco/vacío en el lado del Todo)
Composición:      Todo   <[x]>---------------- Parte    (Rombo relleno/negro en el lado del Todo)
Herencia:         Hija   --------------------|> Padre   (Línea continua con triángulo hueco blanco)
```

### Multiplicidades oficiales:
* `1` : Exactamente uno.
* `0..1` : Cero o uno (opcional).
* `*` o `0..*` : De cero a muchos.
* `1..*` : Al menos uno (uno o muchos).

---

## 🛡️ 5. Blindaje de Clases Contenedoras: Colecciones Seguras

Cuando tu clase guarda una colección interna de datos (`ArrayList`):

```java
public class Pedido {
    private List<Linea> lineas = new ArrayList<>();

    // ❌ ERROR GRAVE DE SEGURIDAD (Rompe el encapsulamiento)
    public List<Linea> getLineas() {
        return this.lineas; // Devuelve la lista viva original
    }
}
```

### ¿Por qué esto es peligroso?
Cualquiera desde el `main` podría escribir:
```java
pedido.getLineas().clear(); // ¡Te borraron todo el pedido desde fuera sin tu permiso!
```

### La Solución Profesional (Devolver copias de solo lectura):
```java
// ✅ BUENA PRÁCTICA: Devolver una vista inmutable
public List<Linea> getLineas() {
    return List.copyOf(this.lineas); // Si intentan modificarla, Java lanza UnsupportedOperationException
}
```
De este modo, nadie puede alterar tu lista interna a espaldas de tu clase.
