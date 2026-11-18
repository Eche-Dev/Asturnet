# Tema 5. Acceso a bases de datos: JDBC y la capa de acceso a datos

- **Duración orientativa:** 14 horas
- **Criterios de evaluación:** CE2.1–2.5 (tecnologías de conexión, elección adecuada, clases de consulta/actualización, abrir/cerrar conexiones y acceso desde una clase).

---

## Análisis del tema

Este tema conecta el mundo orientado a objetos (Tema 2 y UF2404) con las bases de datos relacionales (Tema 4). Es un tema muy práctico: define las distintas APIs de conexión, la arquitectura de **JDBC** en Java (y su equivalente **DB-API / `sqlite3`** en Python), el ciclo completo de una operación contra BD y el **patrón DAO**. El punto crítico que debes dominar sí o sí es el uso de **`PreparedStatement`** frente a `Statement` y el cierre seguro de recursos con **`try-with-resources`**.

---

## Resumen de lo más importante

### 1. Tecnologías de conexión y acceso a datos
| Tecnología | Ecosistema | Nivel | Idea clave |
| :--- | :--- | :--- | :--- |
| **ODBC** | Multilenguaje (C, .NET…) | Bajo | API estándar con controladores nativos |
| **JDBC** | Java (`java.sql`) | Bajo | `Connection`, `Statement`, `ResultSet` |
| **DB-API (PEP 249)** | Python | Bajo | Interfaz común de `sqlite3`, `psycopg`, etc. |
| **ADO.NET** | .NET | Bajo | `SqlConnection`, `SqlCommand`, `DataReader` |
| **ORM** | Todos | Alto | Mapea clases a tablas y genera el SQL (JPA, SQLAlchemy, EF) |

### 2. Arquitectura y flujo de JDBC
- La aplicación solo usa las interfaces estándar de `java.sql`; el **driver** (`.jar` del fabricante) traduce al protocolo de cada SGBD.
- **URL JDBC:** identifica el SGBD y la BD (ej. `jdbc:postgresql://host:5432/tienda`, `jdbc:h2:mem:tienda`).
- **Procedimiento estándar:**
  1. Cargar el driver (automático en JDBC 4 si está en el *classpath*).
  2. Abrir conexión (`DriverManager.getConnection` o `DataSource` / pool de conexiones como HikariCP en producción).
  3. Preparar y ejecutar sentencia (`executeQuery` para `SELECT`, `executeUpdate` para `INSERT`/`UPDATE`/`DELETE`).
  4. Recorrer el `ResultSet` (`while (rs.next())`) y mapear filas a objetos.
  5. Cerrar `ResultSet`, `Statement` y `Connection` siempre con `try-with-resources`.

### 3. `Statement` frente a `PreparedStatement`
| Aspecto | `Statement` | `PreparedStatement` |
| :--- | :--- | :--- |
| **Parámetros** | Concatenados en el SQL | Marcadores `?` con `setXxx(...)` |
| **Seguridad** | **Vulnerable** a inyección SQL | **Inmune** a inyección SQL (el valor viaja como dato) |
| **Rendimiento** | Se analiza cada vez | Se precompila y reutiliza |
| **Uso** | Sentencias fijas / DDL | **Siempre** que haya datos variables |

### 4. El patrón DAO (*Data Access Object*)
- Clase que encapsula todo el SQL de una entidad (ej. `ProductoDao`) y expone métodos orientados a objetos (`buscarPorPrecioMinimo`, `insertar`).
- **Buenas prácticas en `ResultSet` y errores:**
  - Leer columnas por **nombre** (`rs.getString("nombre")`) en vez de por índice; usar `wasNull()` o `getObject()` para distinguir `0` de `NULL`.
  - Capturar `SQLException` en el DAO, registrar `getSQLState()` internamente y relanzar una excepción propia de la capa de datos (`RepositorioException`) sin exponer detalles de la BD al usuario final.
  - En Python, `cursor.row_factory = sqlite3.Row` permite acceder a columnas por nombre.
