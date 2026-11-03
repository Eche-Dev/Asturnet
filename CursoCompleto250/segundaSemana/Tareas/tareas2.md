# 📋 Tareas y Prácticas: Semana 2 (Día 3 - 03/11/2026)

> **Archivo de resolución:** [**garaje.java**](./garaje.java)  
> **Estado:** ✅ Resuelto, comentado línea por línea y verificado.

---

## 🎯 Enunciado 1: Control de Aforo del Garaje

> *Escribe los métodos suficientes para un garaje. Lo que se necesita es que al menos compruebe cuántos coches hay y los vaya actualizando, negando la entrada y tirando error si hay 50 coches dentro, y que vaya contando cuántos salen y actualizando el número.*

### Requisitos Técnicos:
- [x] Variable para contar coches dentro (`cochesDentro`).
- [x] Variable acumuladora para coches salientes (`cochesQueSalieron`).
- [x] Método para consultar aforo actual (`cuantosCochesHay()`).
- [x] Método para dar entrada (`entrarCoche()`): si `cochesDentro >= 50`, lanza excepción (`throw new IllegalStateException(...)`).
- [x] Método para dar salida (`salirCoche()`): resta `cochesDentro--` y suma `cochesQueSalieron++`.

---

## 🚦 Enunciado 2: Ampliación con Semáforo LED

> *Expandiendo lo entregado en la Tarea 1 3/11, añade la clase con el nombre que veas más adecuado, organízalo lo más posible y añade código para que, a futuro, se pueda añadir un LED que, cuando el garaje tenga plazas brille verde, y cuando esté lleno brille rojo.*
>
> *Ampliación opcional:*
> * Verde cuando haya **menos de 25 coches** (`coches < 25`).
> * Naranja para el **resto de plazas ocupadas** (`25 <= coches < 50`).
> * Rojo si el garaje está **lleno** (`coches >= 50`).

### Diseño de la Solución:
- [x] **Nueva clase creada:** `SemaforoLed` con atributo `color` y método `actualizarColor(int cochesDentro)`.
- [x] **Asociación en `garaje`:** Atributo `private SemaforoLed led;` y método de conexión `setLed(SemaforoLed led)`.
- [x] **Sincronización:** Cada vez que entra o sale un coche, si hay un LED conectado, se actualiza automáticamente el color.

---

## 📁 Archivos Relacionados
* 💻 Código fuente: [**garaje.java**](./garaje.java)
* 📖 Manual explicativo: [**04_herencia_polimorfismo_static_y_casos_reales.md**](../../../TeoriaExplicadaPor-IA/04_herencia_polimorfismo_static_y_casos_reales.md)
