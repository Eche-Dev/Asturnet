# 📝 Bitácora de Práctica - 03 de Noviembre

**Archivo:** [`gestionDeCurso.java`](file:///d:/ADEV/ProyectosVScode/Asturnet/EjercicioPracticos/LasDelProfe/gestionDeCurso.java)  
**Ubicación:** `EjercicioPracticos/LasDelProfe`  
**Objetivo:** Gestión de un curso (profesor, alumnos matriculados, bajas, aprobación/suspensión).

---

## 🔍 Análisis del Problema en Líneas 32 y 87

### ¿Qué ocurría?
No se trataba de un error de sintaxis que impidiera compilar, sino de una **excepción en tiempo de ejecución (`IllegalArgumentException`)** lanzada al arrancar el método `main`:

```
Exception in thread "main" java.lang.IllegalArgumentException: La fecha de baja no puede estar vacía
    at EjercicioPracticos.LasDelProfe.gestionDeCurso.<init>(gestionDeCurso.java:32)
    at EjercicioPracticos.LasDelProfe.gestionDeCurso.main(gestionDeCurso.java:84)
```

1. **La trampa en la línea 32 (Constructor):**
   ```java
   if (fechaBaja == null || fechaBaja.isEmpty()) {
       throw new IllegalArgumentException("La fecha de baja no puede estar vacía");
   }
   ```
   Obligaba a que todo objeto tuviese una fecha de baja no vacía al crearse.
2. **La llamada en el `main`:**
   ```java
   gestionDeCurso curso = new gestionDeCurso("Profesor Ejemplo", "Alumno123", "2024-01-15", "", 0.0);
   ```
   Pasaba `""` como `fechaBaja` (lo cual es lógico para un alumno matriculado activo), lo que disparaba la excepción en la línea 32.
3. **El corte en la línea 87:**
   Al lanzarse la excepción en la línea 84/85, el programa se detenía de inmediato y nunca llegaba a ejecutarse la línea 87 (`System.out.println("=== Estado del Alumno ===");`).
4. **Incongruencia lógica:**
   El método `matricularAlumno()` comprobaba que `fechaBaja` estuviese vacía o nula para considerar al alumno matriculado. Con la validación anterior en el constructor, era imposible crear un alumno que estuviese matriculado.

---

## 🛠️ Correcciones y Mejoras Implementadas

| # | Elemento | Qué se corrigió / añadió |
|---|---|---|
| **1** | **Constructor principal** | `fechaBaja` ahora puede ser nula o vacía `""` (alumno en alta). |
| **2** | **Sobrecarga de constructores** | Se añadió constructor por defecto `gestionDeCurso()` y constructor abreviado para altas sin baja. |
| **3** | **Robustez en `pedirDatos()`** | Manejo de coma/punto en decimales, repetición en caso de error y rango de nota `[0, 10]`. |
| **4** | **Acciones de negocio** | Añadidos métodos `darDeBaja(fecha)`, `matricular(fecha)` y `calificar(nota)`. |
| **5** | **Método `alumnoAprobadoOSuspendido()`** | Ahora retorna un texto descriptivo (`"APROBADO"` o `"SUSPENDIDO"`) en lugar de duplicar `alumnoAprobado()`. |
| **6** | **Ficha y encapsulamiento** | Método `mostrarFicha()` con formato visual y getters/setters completos. |
| **7** | **`main` demostrativo** | Casos de prueba sin caídas demostrando el ciclo de vida del curso. |
