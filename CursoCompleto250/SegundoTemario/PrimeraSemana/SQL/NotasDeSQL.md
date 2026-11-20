# 🐘 Apuntes de SQL y Conexión PostgreSQL (Railway + VS Code)

Diario práctico de dudas, configuración, errores reales y consultas SQL usadas en la **UF2405 (Asturnet-BD)**.

---

## 1. Dudas iniciales: ¿qué herramientas usar?

| Herramienta | ¿Para qué sirve aquí? | Conclusión |
| :--- | :--- | :--- |
| **DBeaver** | Cliente gráfico de BD (muy bueno para ver diagramas E/R). | Opcional; si quieres seguir los clics del profe. |
| **Extensión PostgreSQL (Microsoft)** en VS Code | Ejecutar SQL, ver tablas y guardar scripts `.sql` sin salir del editor. | **Elegida** (no requiere instalar programas externos). |
| **PostgreSQL en Railway (`Asturnet-BD`)** | Base de datos relacional real en la nube. | **Elegida** (sirve al 100 % para SQL y JDBC). |
| **Docker** | Contenedores locales (requiere activar virtualización en BIOS). | No hace falta ahora teniendo Railway y SQLite/H2. |
| **MongoDB** | Base de datos **NoSQL** (documentos JSON). | **No sirve** para UF2405 (el temario es SQL relacional). |

---

## 2. Cómo conectar PostgreSQL de Railway en VS Code

1. En **Railway**, crear un proyecto limpio para prácticas (**`Asturnet-BD`**) con un servicio **Postgres**.
2. Usar siempre los datos de la **red pública** (`RAILWAY_TCP_PROXY_DOMAIN` + `RAILWAY_TCP_PROXY_PORT`), **nunca** `postgres.railway.internal` (esa solo funciona dentro de Railway).
3. En VS Code → icono del **elefante (PostgreSQL)** → **+ (Add New Connection)** → pestaña **Cadena de conexión**:
   * **Cadena de conexión (sin contraseña):**
     ```text
     postgresql://postgres@shinkansen.proxy.rlwy.net:35653/railway
     ```
   * **Tipo de autenticación:** `Contraseña`
   * **Contraseña:** pegar el valor real de `PGPASSWORD` (en Railway → Postgres → **Variables**).
   * **Nombre de conexión:** `Asturnet-BD`
   * *(Si se usa la pestaña **Parámetros** en vez de Cadena de conexión, hay que entrar en **Configuración avanzada** para cambiar el puerto por defecto `5432` al puerto público `35653` y poner **Modo SSL** en `require`).*

> **Nota para Java / JDBC (Tema 5):**
> La URL de conexión será:
> `jdbc:postgresql://shinkansen.proxy.rlwy.net:35653/railway?sslmode=require`
> *(La contraseña siempre en variables de entorno o fuera de Git).*

---

## 3. Errores que nos salieron y cómo los arreglamos

### ❌ Error 1: `FATAL: password authentication failed for user "postgres"`
* **Por qué pasó:** en el cuadro de *Cadena de conexión* se quedó escrita la palabra literal `CONTRASEÑA` en lugar de omitirla, y la extensión pide omitirla en la URL y poner el `PGPASSWORD` real en el campo **Contraseña**.
* **Solución:** dejar la cadena como `postgresql://postgres@shinkansen.proxy.rlwy.net:35653/railway` y pegar `PGPASSWORD` abajo.

### ❌ Error 2: `Connection failed: (http://localhost:443/v1/statement)`
* **Por qué pasó:** la extensión **SQL Preview (Mehul)** estaba instalada en VS Code y secuestraba la ejecución de los archivos `.sql` intentando enviarla a un servidor Trino/Presto local (`localhost:443/v1/statement`).
* **Solución:**
  1. Eliminar/deshabilitar **SQL Preview** en Extensiones (`Ctrl + Shift + X`).
  2. Abrir las consultas desde el elefante (**clic derecho en `railway` → New Query**) y ejecutarlas con **PostgreSQL (Microsoft)** (`Execute Query`).

### ❌ Error 3: `NOTICE: table "cliente" does not exist, skipping` (aunque sí salía en Railway)
* **Por qué pasó:** en PostgreSQL, si una tabla se crea con **mayúsculas** desde una interfaz (por ejemplo `"Cliente"` y `"Producto"`), PostgreSQL distingue mayúsculas y minúsculas. Si luego ejecutas `DROP TABLE cliente;` sin comillas, busca `cliente` en minúsculas y no la encuentra.
* **Solución:** consultar el nombre exacto en `information_schema.tables` y usar **comillas dobles** (`public."Cliente"`).

---

## 4. Consultas SQL usadas (chuleta de repaso)

### 🧱 Crear tablas a mano (DDL)
```sql
CREATE TABLE cliente (
    id INTEGER PRIMARY KEY,
    nombre VARCHAR(80) NOT NULL,
    email VARCHAR(120) NOT NULL UNIQUE
);

CREATE TABLE producto (
    id INTEGER PRIMARY KEY,
    nombre VARCHAR(80) NOT NULL,
    precio DECIMAL(10, 2) NOT NULL CHECK (precio >= 0),
    stock INTEGER NOT NULL DEFAULT 0
);
```

### 🔍 Ver qué tablas y columnas existen en la base de datos
```sql
-- Ver todas las tablas del usuario (esquema + nombre exacto con mayúsculas)
SELECT table_schema, table_name
FROM information_schema.tables
WHERE table_schema NOT IN ('pg_catalog', 'information_schema')
ORDER BY table_schema, table_name;

-- Ver todas las columnas y tipos de las tablas en 'public'
SELECT table_name, column_name, data_type
FROM information_schema.columns
WHERE table_schema = 'public'
ORDER BY table_name, ordinal_position;
```

### 🗑️ Borrar tablas o reiniciar la BD de prácticas
```sql
-- Borrar una tabla concreta (si tiene mayúsculas, entre comillas dobles)
DROP TABLE IF EXISTS public."Producto" CASCADE;
DROP TABLE IF EXISTS public."Cliente" CASCADE;

-- Borrar varias tablas a la vez
DROP TABLE IF EXISTS linea_pedido, pedido, producto, cliente CASCADE;

-- Reiniciar TODO el esquema public (deja la BD vacía desde cero)
DROP SCHEMA public CASCADE;
CREATE SCHEMA public;
```
> *`IF EXISTS` evita errores si la tabla no existe. `CASCADE` borra también dependencias (claves foráneas) que apunten a esa tabla.*

### 📌 Diferencia clave de examen: `DROP` vs `TRUNCATE` vs `DELETE`
| Sentencia | Qué borra | ¿Mantiene la tabla? |
| :--- | :--- | :---: |
| **`DROP TABLE t;`** | Toda la tabla (estructura + datos) | ❌ No |
| **`TRUNCATE TABLE t;`** | Todos los datos de golpe | ✅ Sí (vacía) |
| **`DELETE FROM t WHERE ...;`** | Solo las filas que cumplen el `WHERE` | ✅ Sí |
