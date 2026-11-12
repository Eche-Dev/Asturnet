# 🎓 Temario UF2404: Principios de la Programación Orientada a Objetos
### Módulo MF0227_3 | Temas 1 al 9 Completo (Java Oficial)

> **Propósito de esta carpeta:**  
> Esta carpeta contiene la explicación detallada, amena y académica de todos los conceptos teóricos, principios de diseño, diagramas UML, concurrencia, excepciones y buenas prácticas recogidos en el manual oficial **UF2404 (Temas 1 al 9 completo)**.  
> 
> Se ha dividido en **9 manuales modulares** para que cada tema sea directo, ameno y fácil de estudiar por separado antes de lanzarse al código.

---

## 🗂️ Índice de Manuales por Tema

| Tema | Manual | Conceptos clave explicados |
| :---: | :--- | :--- |
| **01** | [**Tema1_CicloDeVida_Metricas_y_Analisis.md**](./Tema1_CicloDeVida_Metricas_y_Analisis.md) | Ciclo de vida del software, técnica de sustantivos/verbos, qué es un Mensaje y Métricas de calidad OO (Cohesión LCOM, Acoplamiento CBO, Herencia DIT). |
| **02** | [**Tema2_GestionDeMemoria_GarbageCollector_y_Visibilidad.md**](./Tema2_GestionDeMemoria_GarbageCollector_y_Visibilidad.md) | Los 4 niveles de visibilidad, clasificación formal de métodos, Garbage Collector, fugas de memoria, `try-with-resources` y encadenamiento con `this`. |
| **03** | [**Tema3_HerenciaAvanzada_ClaseObject_y_Diamante.md**](./Tema3_HerenciaAvanzada_ClaseObject_y_Diamante.md) | La superclase raíz `Object` (`equals` + `hashCode`), clases y métodos `final`, el problema del diamante y el Principio de Sustitución de Liskov. |
| **04** | [**Tema4_RelacionesUML_Dependencia_y_ColeccionesSeguras.md**](./Tema4_RelacionesUML_Dependencia_y_ColeccionesSeguras.md) | Relación de Dependencia, cuadro de las 5 relaciones, notación UML oficial (rombos y multiplicidades) y cómo blindar colecciones (`List.copyOf`). |
| **05** | [**Tema5_Polimorfismo_ClasesAbstractas_Interfaces_y_Casting.md**](./Tema5_Polimorfismo_ClasesAbstractas_Interfaces_y_Casting.md) | Los 4 tipos de polimorfismo, Clases Abstractas vs Interfaces (tabla definitiva), Upcasting/Downcasting y Pattern Matching con `instanceof`. |
| **06** | [**Tema6_ProgramacionEstructurada_PasoParametros_y_Recursividad.md**](./Tema6_ProgramacionEstructurada_PasoParametros_y_Recursividad.md) | Teorema de Böhm-Jacopini, paso de parámetros **siempre por valor**, llamadas recursivas, consumo de pila (`StackOverflowError`) y refactorización. |
| **07** | [**Tema7_EstructurasDeInformacion_Colecciones_y_GestionMemoria.md**](./Tema7_EstructurasDeInformacion_Colecciones_y_GestionMemoria.md) | Tipos primitivos vs objetos, el peligro del dinero en float/double (`BigDecimal`), cuadro de decisión de colecciones (List, Set, Map, Pila, Cola), Stack vs Heap y Garbage Collector. |
| **08** | [**Tema8_LenguajesOO_Ecosistema_Herramientas_y_Librerias.md**](./Tema8_LenguajesOO_Ecosistema_Herramientas_y_Librerias.md) | Panorama comparativo de lenguajes (Java, C#, C++, Python), cadena de herramientas (IDE, Maven/Gradle, Git, JUnit), librerías estándar `java.*`, inmutabilidad de String (`StringBuilder`) y Streams/Optional. |
| **09** | [**Tema9_ImplementacionParadigma_Excepciones_Eventos_e_Hilos.md**](./Tema9_ImplementacionParadigma_Excepciones_Eventos_e_Hilos.md) | Receta en 8 pasos para construir una clase, `enum` y `record`, excepciones (Checked vs Unchecked vs Error), patrón Observador (eventos y lambdas), hilos y condiciones de carrera (`synchronized`/`AtomicInteger`), pools de hilos y JDBC. |

---

## 🧭 ¿Cómo estudiar este temario?

1. **Antes de los ejercicios prácticos:** Lee el resumen y los ejemplos del tema correspondiente en esta carpeta `ExplicacionDelTemario/`.
2. **Si tienes dudas de sintaxis o palabras sueltas:** Consulta las notas rápidas en `TeoriaExplicadaPor-IA/`.
3. **Para poner en práctica la teoría:** Realiza los ejercicios en `EjercicioPracticos-AI/MetiendomeCañaConJava_YoSolo/` y en `CursoCompleto250/`.
4. **Si el profesor hace preguntas teóricas de examen o arquitectura:** Esta carpeta contiene las definiciones exactas del certificado de profesionalidad oficial.
