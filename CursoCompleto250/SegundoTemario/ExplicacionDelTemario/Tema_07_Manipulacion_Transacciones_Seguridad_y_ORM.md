# Tema 7. Manipulación de datos: transacciones, seguridad y persistencia de objetos

- **Duración orientativa:** 12 horas
- **Criterios de evaluación:** CE2.4 (ejecutar comandos y recoger resultados), CE2.5 (pruebas y documentación del acceso a BD), CE2.6 (cambios de diseño en app o BD), almacenamiento de objetos en BD relacionales.

---

## Análisis del tema

Este último tema cierra el círculo de la unidad llevando el acceso a datos a un nivel profesional: operaciones por lotes, control transaccional (**ACID**, `commit`/`rollback` y niveles de aislamiento), defensa activa contra la **inyección SQL**, estrategias para persistir jerarquías de objetos (introducción a **ORM** como JPA/Hibernate) y cómo **probar y versionar** la capa de datos.

---

## Resumen de lo más importante

### 1. Modificación desde la aplicación y procesamiento por lotes (*batch*)
- **`executeUpdate()`** (Java) / **`cursor.rowcount`** (Python): devuelven el **número de filas afectadas** (útil para saber si un `UPDATE` o `DELETE` afectó realmente a algún registro).
- Claves autogeneradas: `Statement.RETURN_GENERATED_KEYS` y `getGeneratedKeys()`.
- **Lotes (*batch*):** `ps.addBatch()` + `ps.executeBatch()` en Java (o `con.executemany(...)` en Python) acumulan sentencias para enviarlas en un solo viaje al servidor, reduciendo la latencia.

### 2. Transacciones y propiedades ACID
Una transacción es una unidad indivisible de trabajo (*todo o nada*, ejemplo clásico: transferencia bancaria).
- **A**tomicidad: todo o nada (`commit` / `rollback`).
- **C**onsistencia: la BD pasa de un estado válido a otro válido (se cumplen restricciones).
- **A**islamiento (*Isolation*): las transacciones concurrentes no se interfieren.
- **D**urabilidad: lo confirmado (`COMMIT`) sobrevive a caídas del sistema.

**Patrón en código (Java):** por defecto `autocommit` está activo. Para agrupar operaciones:
1. `con.setAutoCommit(false)`
2. Ejecutar sentencias; si todo va bien → `con.commit()`
3. En `catch` → `con.rollback()`
4. En `finally` → restaurar `con.setAutoCommit(true)` (en Python basta usar el gestor de contexto `with con:`).
- Mantener las transacciones **cortas** porque bloquean filas. `SAVEPOINT` permite deshacer parcialmente.

### 3. Concurrencia y niveles de aislamiento
Anomalías posibles: **lectura sucia** (datos no confirmados), **lectura no repetible** (la misma fila cambia entre dos lecturas) y **fantasmas** (aparecen filas nuevas al repetir consulta).

| Nivel de aislamiento | Lectura sucia | Lectura no repetible | Fantasmas |
| :--- | :--- | :--- | :--- |
| `READ UNCOMMITTED` | Posible | Posible | Posible |
| `READ COMMITTED` *(defecto habitual)* | Evitada | Posible | Posible |
| `REPEATABLE READ` | Evitada | Evitada | Posible |
| `SERIALIZABLE` | Evitada | Evitada | Evitada |

*Más aislamiento = menos anomalías, pero menor rendimiento.* Bloqueo optimista (columna de versión) vs. pesimista (`SELECT ... FOR UPDATE`).

### 4. Inyección SQL y defensas
Ocurre al **concatenar entrada de usuario** en la consulta (ej. `' OR '1'='1`).
- **Defensa principal:** **consultas parametrizadas** (`PreparedStatement` / `?`).
- **Otras defensas:** principio de mínimo privilegio en el usuario de BD, validar entradas, lista blanca para nombres de tablas/columnas (no parametrizables) y nunca mostrar errores SQL al usuario.

### 5. Persistencia de objetos (ORM) y pruebas
- **Estrategias de herencia en BD:**
  1. *Una tabla por jerarquía:* rápida, pero genera muchos `NULL`.
  2. *Tabla por clase concreta:* sin nulos, pero consultas polimórficas costosas.
  3. *Tabla por clase (unión):* normalizado, requiere `JOIN`.
- **ORM (JPA/Hibernate, SQLAlchemy, Django ORM, Entity Framework):** automatizan el mapeo (`@Entity`, `@Table`, `@ManyToOne`) y parametrizan por defecto, aunque hay que vigilar el SQL generado (problema de las *N+1 consultas*).
- **Pruebas y migraciones:** probar los DAO con **bases de datos en memoria** (`H2`, `SQLite :memory:`) y datos reproducibles, probando casos límite (clave duplicada, violación de FK, fila inexistente). Versionar los cambios de esquema con **migraciones** (Flyway, Liquibase, Alembic).
