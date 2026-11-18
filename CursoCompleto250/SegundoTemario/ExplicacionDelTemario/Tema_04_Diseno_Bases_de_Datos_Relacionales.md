# Tema 4. Diseño de bases de datos relacionales

- **Duración orientativa:** 12 horas
- **Criterios de evaluación:** CE2.1–2.2 (tecnologías de conexión y acceso a datos; diseño en varios niveles, relaciones y su implementación; correlación modelo relacional vs. orientado a objetos).

---

## Análisis del tema

Antes de escribir código SQL o Java/Python contra una base de datos, hay que saber modelar bien los datos. Este tema recorre el camino completo desde el **modelo conceptual (E/R)** hasta el **modelo lógico (tablas, claves y normalización hasta 3FN)** y el **modelo físico (`CREATE TABLE`)**. Además, explica por qué las clases de programación orientada a objetos no encajan de forma directa con las tablas (**desajuste de impedancia objeto–relacional**).

---

## Resumen de lo más importante

### 1. Diseño en tres niveles
1. **Conceptual:** entidades, atributos y relaciones del negocio (Diagrama E/R). Independiente del SGBD.
2. **Lógico:** tablas, claves, relaciones y normalización (Modelo relacional). Casi independiente del SGBD.
3. **Físico:** tipos de datos concretos, índices, particiones y scripts DDL. Depende del SGBD elegido (PostgreSQL, MySQL, Oracle, SQL Server).

### 2. Claves e integridad
- **Clave candidata:** conjunto mínimo de columnas que identifica de forma única una fila (p. ej. `dni`, `email`).
- **Clave primaria (PK):** la candidata elegida; **única y no nula** (*integridad de entidad*). Conviene usar **claves sustitutas** (`id` numérico) frente a claves naturales que pueden cambiar.
- **Clave foránea (FK):** columna que referencia la PK de otra tabla; garantiza que apunte a una fila existente (*integridad referencial*).
- **Otras restricciones:** `NOT NULL`, `UNIQUE`, `CHECK (...)`, `DEFAULT`.

### 3. Reglas de transformación E/R → Relacional
- **Relación 1:N:** FK en la tabla del lado «N» (ej. `pedido.cliente_id`).
- **Relación N:M:** crear una **tabla intermedia** con las dos FK formando normalmente una clave primaria compuesta (ej. `linea_pedido(pedido_id, producto_id)`).
- **Relación 1:1:** FK con restricción `UNIQUE` en una de las tablas, o fusionar tablas.
- **Herencia:** tabla por jerarquía, tabla por clase o tabla por subclase.

### 4. Normalización (1FN a 3FN)
Proceso para eliminar redundancias y anomalías de inserción, actualización y borrado:
- **1FN:** valores atómicos; sin grupos repetidos (evitar `telefonos = '600,611'`).
- **2FN:** 1FN + todo atributo no clave depende de **toda** la clave primaria (no solo de una parte en claves compuestas).
- **3FN:** 2FN + sin dependencias transitivas entre atributos no clave (ej. `ciudad` dependiendo de `codigo_postal` en `cliente`).
- *Nota:* a veces se desnormaliza a propósito por rendimiento o informes, pero siempre de forma consciente.

### 5. Desajuste de impedancia objeto–relacional
Los objetos y las tablas no encajan 1:1 (herencia, polimorfismo y colecciones no existen en tablas; los objetos navegan referencias y las tablas se unen con `JOIN`). Se resuelve mapeando **a mano (DAO + JDBC)** o con un **ORM** (JPA/Hibernate, SQLAlchemy, Entity Framework).
