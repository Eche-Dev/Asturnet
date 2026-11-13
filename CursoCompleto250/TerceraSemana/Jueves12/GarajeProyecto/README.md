# Garaje (Java orientado a objetos)

Garaje con 50 plazas de coche y 15 de moto, LED de ocupación,
control de matrículas y registro de entradas y salidas con fecha.

## Estructura

```
GarajeProyecto/
└── src/
    └── garaje/                  (paquete principal)
        ├── Main.java            punto de arranque (main)
        ├── Garaje.java          lógica del garaje
        ├── Led.java             colores del LED (enum)
        └── vehiculos/           (subpaquete garaje.vehiculos)
            ├── Vehiculo.java    superclase abstracta
            ├── Coche.java       subclase
            └── Moto.java        subclase
```

## Compilar y ejecutar desde consola (Java 9 o superior)

Desde la carpeta `GarajeProyecto`:

```
javac -d bin src/garaje/*.java src/garaje/vehiculos/*.java
java -cp bin garaje.Main
```

En Eclipse, IntelliJ o NetBeans: importa la carpeta como proyecto,
marca `src` como carpeta de código fuente y ejecuta `Main`.
