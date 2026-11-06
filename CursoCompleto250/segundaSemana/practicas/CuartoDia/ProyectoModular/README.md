# 🏢 Proyecto Garaje Modular (Arquitectura Empresarial)

> **Paquete:** `CursoCompleto250.segundaSemana.practicas.CuartoDia.ProyectoModular`  
> **Propósito:** Separar cada clase en su propio archivo independiente para cumplir con el **Principio de Responsabilidad Única (SRP)**, evitar archivos gigantes difíciles de leer y trabajar con el estándar real de desarrollo en empresas.

---

## 🗂️ Estructura de Archivos del Proyecto

```text
ProyectoModular/
├── 📄 Vehiculos.java       --> Superclase base: atributos protegidos, aforo y acciones de entrar/salir.
├── 📄 Coches.java          --> Subclase hija: hereda de Vehiculos y fija 50 plazas estándar.
├── 📄 Motocicletas.java    --> Subclase hija: hereda de Vehiculos y fija 15 plazas estándar.
├── 📄 SemaforoLed.java     --> Dispositivo luminoso auxiliar (Verde, Naranja, Rojo).
├── 📄 Garaje.java          --> Coordinador del negocio: compone coches, motos y semáforo. Menú de consola.
├── 📄 App.java             --> Punto de entrada (main): arranca la aplicación de forma limpia.
└── 📘 README.md            --> Guía explicativa de este paquete modular.
```

---

## 💡 ¿Por qué esta arquitectura es mejor?

1. **Lectura Inmediata:** Cada archivo mide entre 25 y 80 líneas. Se puede ver y entender de un solo vistazo sin tener que hacer scroll hacia arriba y abajo.
2. **Mantenibilidad:** Si mañana quieres cambiar las reglas del semáforo, abres `SemaforoLed.java` sin peligro de tocar por error el código de los coches o el menú.
3. **Trabajo en Equipo:** Varios programadores pueden trabajar al mismo tiempo en ramas de Git sin pisarse el código (uno modifica `Coches.java` y otro `SemaforoLed.java`).
4. **Desacoplamiento:** La clase `App.java` solo se preocupa de arrancar; `Garaje.java` de coordinar; y cada subclase de su propia especialidad.
