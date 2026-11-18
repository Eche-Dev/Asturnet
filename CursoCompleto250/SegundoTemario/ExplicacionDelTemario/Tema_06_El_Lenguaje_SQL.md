# Tema 6. El lenguaje SQL

- **Duración orientativa:** 14 horas
- **Criterios de evaluación:** CE2.3 (operar con una BD relacional mediante consultas y actualizaciones; estándar SQL-92 vs. dialectos; DDL y DML).

---

## Análisis del tema

Tema puramente instrumental y de altísimo peso en el examen y en la práctica diaria. Repasa las 5 subfamilias de SQL, las diferencias entre el **estándar SQL-92** y los dialectos comerciales (MySQL, PostgreSQL, SQL Server, Oracle, SQLite) y cubre desde la creación de tablas (**DDL**) hasta consultas complejas con **`JOIN`**, **`GROUP BY`/`HAVING`** y **subconsultas**.

---

## Resumen de lo más importante

### 1. Familias de sentencias SQL
SQL es un lenguaje **declarativo** (indicas *qué* quieres, no *cómo* obtenerlo).
- **DDL (Definición):** `CREATE`, `ALTER`, `DROP`, `TRUNCATE` (vacía datos conservando estructura, más rápido que `DELETE` sin `WHERE` y sin vuelta atrás en muchos SGBD).
- **DML (Manipulación):** `INSERT`, `UPDATE`, `DELETE`.
- **DQL (Consulta):** `SELECT`.
- **DCL (Control de acceso):** `GRANT`, `REVOKE`.
- **TCL (Transacciones):** `COMMIT`, `ROLLBACK`, `SAVEPOINT`.

### 2. Estándar vs. dialectos habituales
- **Autoincremental:** estándar `GENERATED ... AS IDENTITY` (MySQL `AUTO_INCREMENT`, SQLite `AUTOINCREMENT`, SQL Server `IDENTITY`).
- **Limitar filas:** estándar `FETCH FIRST n ROWS ONLY` (MySQL/PostgreSQL `LIMIT n`, SQL Server `TOP n`).
- **Concatenar:** estándar `a || b` (MySQL `CONCAT(a, b)`, SQL Server `+`).
- **Cadena vacía y `NULL`:** son distintos en el estándar, pero Oracle los trata igual.

### 3. DDL y tipos de datos clave
- **Restricciones:** `PRIMARY KEY`, `FOREIGN KEY ... REFERENCES` (con `ON DELETE CASCADE | SET NULL | RESTRICT`), `NOT NULL`, `UNIQUE`, `CHECK`, `DEFAULT`.
- **Tipos:** enteros (`INTEGER`, `BIGINT`), **dinero siempre `DECIMAL(p,s)` / `NUMERIC` (nunca `FLOAT`)**, texto (`VARCHAR(n)`), fechas (`DATE`, `TIME`, `TIMESTAMP`), `BOOLEAN`/`BLOB`.

### 4. DML: precauciones esenciales
- En `INSERT`, indicar siempre las columnas.
- En `UPDATE` y `DELETE`, **la cláusula `WHERE` es vital** (sin ella modificas o borras toda la tabla). *Buena práctica:* ejecutar antes un `SELECT` con el mismo `WHERE`.

### 5. `SELECT`: orden de evaluación, `JOIN` y agregaciones
- **Orden de escritura:** `SELECT` → `FROM` → `WHERE` → `GROUP BY` → `HAVING` → `ORDER BY`.
- **Orden lógico de evaluación:** `FROM`/`JOIN` → `WHERE` → `GROUP BY` → `HAVING` → `SELECT` → `ORDER BY` (por eso un alias del `SELECT` no se puede usar en el `WHERE`).
- **`NULL`:** `col = NULL` nunca es verdadero; usar **`IS NULL`** o `IS NOT NULL`.
- **`JOIN`:**
  - `INNER JOIN`: solo filas con pareja en ambas tablas.
  - `LEFT JOIN`: todas las de la izquierda + pareja si existe (muy útil con `WHERE derecha.id IS NULL` para detectar ausencias).
  - `RIGHT JOIN`, `FULL JOIN`, `CROSS JOIN` (producto cartesiano).
- **Agregaciones (`COUNT`, `SUM`, `AVG`, `MIN`, `MAX`):**
  - `WHERE` filtra **filas antes** de agrupar; `HAVING` filtra **grupos después** de agrupar.
  - **Regla:** toda columna del `SELECT` que no esté dentro de una función de agregación debe figurar en el `GROUP BY`.
- **Subconsultas, vistas e índices:**
  - **Subconsulta:** `SELECT` dentro de otro (`WHERE`, `FROM`, `SELECT`).
  - **Vista (`CREATE VIEW`):** consulta guardada que actúa como tabla virtual.
  - **Índice (`CREATE INDEX`):** acelera búsquedas, pero ocupa espacio y ralentiza escrituras.
