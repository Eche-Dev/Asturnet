# 🎓 Asturnet - Curso de Programación Java (250 Horas)

> Repositorio oficial de estudio, apuntes estructurados, tareas de clase y proyectos prácticos desarrollados durante el curso de **Programación Java (250 Horas)** en **Asturnet**.

---

## 🗺️ Mapa de Navegación del Repositorio

```text
Asturnet/
├── 📁 CursoCompleto250/             # Contenido oficial del curso (semana a semana)
│   ├── 📁 primeraSemana/           # Fundamentos, entrada/salida y primeros objetos
│   │   ├── 📁 Teoria-Notas/        # Apuntes de clase (Día 1 y Día 2)
│   │   └── 📁 practicas/           # Código fuente ordenado por día (Día 1 y Día 2)
│   └── 📁 segundaSemana/           # POO avanzada, arquitectura y relaciones
│       ├── 📁 Teoria-Notas/        # Apuntes oficiales (Asociación, Agregación, Composición, AOC)
│       └── 📁 practicas/           # Código fuente y tareas (Día 1: Garaje con LED y Debug)
│
├── 📁 TeoriaExplicadaPor-IA/        # Biblioteca de manuales y guías de apoyo
│   ├── 00_vision_general_que_quiere_el_profe.md
│   ├── 01_fundamentos_operadores_y_entrada_salida.md
│   ├── 02_poo_clases_objetos_new_this_y_encapsulacion.md
│   ├── 03_relaciones_entre_clases_y_control_de_excepciones.md
│   ├── 04_herencia_polimorfismo_static_y_casos_reales.md
│   └── 05_como_empezar_desde_cero_sin_bloquearse.md
│
├── 📁 EjercicioPracticos-AI/        # Retos progresivos extra para practicar (Fácil a Experto)
│
├── 📁 img/                         # Recursos gráficos y diagramas
├── 📄 .gitignore                   # Exclusión de binarios compilados (*.class) y temporales
└── 📄 170_L3SFC2400127_...pdf       # Horario y calendario oficial del curso
```

---

## 📅 Contenido del Curso por Semanas

### 🔹 [Semana 1: Fundamentos y Primeros Pasos](./CursoCompleto250/primeraSemana/README.md)
* **Teoría y Apuntes:**
  * [Apuntes Día 1](./CursoCompleto250/primeraSemana/Teoria-Notas/apuntes_S1-D1.md): Estructura de Java, `Scanner`, `println` vs `printf` y tipos primitivos.
  * [Apuntes Día 2](./CursoCompleto250/primeraSemana/Teoria-Notas/apuntes_poo_S1-D2.md): Moldes de clases, instancias con `new`, constructores y atributos privados.
* **Código de Prácticas:**
  * [Prácticas Día 1](./CursoCompleto250/primeraSemana/practicas/PrimerDia): Ejemplos básicos (`P1_ejemplo.java` a `P4_ejemplo.java`).
  * [Prácticas Día 2](./CursoCompleto250/primeraSemana/practicas/SegundoDia): Tienda `Producto` (`p1_ej_bloques.java`) y nóminas con herencia (`empleado.java`).

---

### 🔹 [Semana 2: Arquitectura de Objetos y Relaciones](./CursoCompleto250/segundaSemana/README.md)
* **Teoría y Apuntes:**
  * [Apuntes Día 1](./CursoCompleto250/segundaSemana/Teoria-Notas/Apuntes_S2-D1.md): Relaciones entre clases (**Asociación, Agregación y Composición**).
  * [Notas de Pizarra](./CursoCompleto250/segundaSemana/Teoria-Notas/resumeNOTASCOMPARTIDA.md): Los 4 pilares (AOC), visibilidad, `static`, herencia y manejo de errores con `try-catch`.
* **Código de Prácticas y Tareas:**
  * [Prácticas Día 1](./CursoCompleto250/segundaSemana/practicas/PrimerDia):
    * [garaje.java](./CursoCompleto250/segundaSemana/practicas/PrimerDia/garaje.java): Sistema de control de aforo (50 plazas) con semáforo LED indicador (< 25 verde, 25-49 naranja, 50 rojo).
    * [practicaT3.java](./CursoCompleto250/segundaSemana/practicas/PrimerDia/practicaT3.java): Tarea de depuración de jerarquía de empleados resuelta y comentada.
    * [tareas2.md](./CursoCompleto250/segundaSemana/practicas/PrimerDia/tareas2.md): Enunciado oficial de la tarea del garaje.
    * [resumen_debug_1.md](./CursoCompleto250/segundaSemana/practicas/PrimerDia/resumen_debug_1.md): Análisis de fallos de depuración resueltos.

---

## 🧠 [Biblioteca Teórica de Apoyo (Explicada por IA)](./TeoriaExplicadaPor-IA/README.md)

Guías diseñadas para afianzar conceptos, entender el porqué de cada línea y evitar el bloqueo al escribir código:

1. [**00. Visión General del Profe**](./TeoriaExplicadaPor-IA/00_vision_general_que_quiere_el_profe.md): El hilo conductor pedagógico de las clases.
2. [**01. Fundamentos y Operadores**](./TeoriaExplicadaPor-IA/01_fundamentos_operadores_y_entrada_salida.md): Diccionario de inglés, todos los operadores y puente con Python.
3. [**02. POO Esencial**](./TeoriaExplicadaPor-IA/02_poo_clases_objetos_new_this_y_encapsulacion.md): Qué hace `new`, qué hace `this.` y encapsulación.
4. [**03. Relaciones y Excepciones**](./TeoriaExplicadaPor-IA/03_relaciones_entre_clases_y_control_de_excepciones.md): Asociación, Agregación, Composición y `try-catch-throw`.
5. [**04. Herencia y Casos Reales**](./TeoriaExplicadaPor-IA/04_herencia_polimorfismo_static_y_casos_reales.md): `static`, `super`, `@Override`, y desgloses de `empleado.java` y `garaje.java`.
6. [**05. Cómo Empezar desde Cero**](./TeoriaExplicadaPor-IA/05_como_empezar_desde_cero_sin_bloquearse.md): La Regla de los 4 Pisos para no bloquearte ante un archivo vacío.

---

## 🥊 [Desafíos Prácticos Extra](./EjercicioPracticos-AI/README.md)
* [Desafío 1 (Fácil)](./EjercicioPracticos-AI/ejercicio_1_facil.md): Atributos, constructores y métodos básicos.
* [Desafío 2 (Intermedio)](./EjercicioPracticos-AI/ejercicio_2_intermedio.md): Encapsulación, validaciones y listas.
* [Desafío 3 (Avanzado)](./EjercicioPracticos-AI/ejercicio_3_avanzado.md): Herencia y polimorfismo.
* [Desafío 4 (Experto)](./EjercicioPracticos-AI/ejercicio_4_experto.md): Modelado complejo y desacoplamiento de componentes.

---

## ⚙️ Guía Rápida de Compilación y Ejecución (Consola)

Para compilar y ejecutar cualquier clase desde la raíz del proyecto:

```bash
# 1. Compilar especificando la carpeta de salida:
javac -d bin CursoCompleto250/segundaSemana/practicas/PrimerDia/garaje.java

# 2. Ejecutar la clase (indicando el paquete completo):
java -cp bin CursoCompleto250.segundaSemana.practicas.PrimerDia.garaje
```

---

## 📄 Horario y Documentación
* 📑 [Horario Oficial del Curso (PDF)](./170_L3SFC2400127_4061_horario-virtual-_copia.pdf)
