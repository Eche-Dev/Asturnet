package CursoCompleto250.segundaSemana.practicas.CuartoDia;

import java.util.Scanner;

// ==============================================================================================
// 🏛️ CLASE PRINCIPAL: garajeModularizado
// Es el director del sistema. Al abrir este archivo, ves directamente cómo funciona el garaje:
// 1. Sus constantes fijas (aforos)
// 2. Sus componentes internos (coches, motos, semáforo)
// 3. Sus acciones públicas (entrar, salir, ver estado)
// 4. Su menú interactivo y el método main de arranque
// ==============================================================================================
public class garajeModularizado {

    // ------------------------------------------------------------------------------------------
    // 1. CONSTANTES FIJAS (Valores que nunca cambian en todo el programa)
    // ------------------------------------------------------------------------------------------
    public static final int limiteCoches = 50;
    public static final int limiteMotos = 15;

    // ------------------------------------------------------------------------------------------
    // 2. ATRIBUTOS PRIVADOS (Los 3 componentes que tiene este garaje)
    // ------------------------------------------------------------------------------------------
    private final Coches seccionCoches;       // Módulo encargado de gestionar coches
    private final Motocicletas seccionMotos;  // Módulo encargado de gestionar motos
    private SemaforoLed led;                  // Módulo encargado de la señal luminosa

    // ------------------------------------------------------------------------------------------
    // 3. CONSTRUCTORES (Cómo se fabrica el garaje en la memoria RAM)
    // ------------------------------------------------------------------------------------------
    // Constructor por defecto: usa automáticamente los límites estándar de 50 y 15
    public garajeModularizado() {
        this(limiteCoches, limiteMotos);
    }

    // Constructor con parámetros: permite crear garajes a medida si se necesita
    public garajeModularizado(int limiteCoches, int limiteMotos) {
        this.seccionCoches = new Coches(limiteCoches);
        this.seccionMotos = new Motocicletas(limiteMotos);
        this.led = new SemaforoLed(); // Arranca con el semáforo instalado
    }

    // ------------------------------------------------------------------------------------------
    // 4. ACCIONES DE COCHES (Entrada, Salida y Consultas)
    // ------------------------------------------------------------------------------------------
    public void entrarCoche() {
        this.seccionCoches.entrar();
        sincronizarLed(); // Cada vez que entra un coche, avisamos al semáforo
    }

    public void salirCoche() {
        this.seccionCoches.salir();
        sincronizarLed(); // Cada vez que sale un coche, avisamos al semáforo
    }

    public int getCochesDentro() {
        return this.seccionCoches.getcuantosHay();
    }

    public int getCochesQueSalieron() {
        return this.seccionCoches.getQueSalieron();
    }

    // ------------------------------------------------------------------------------------------
    // 5. ACCIONES DE MOTOS (Entrada, Salida y Consultas)
    // ------------------------------------------------------------------------------------------
    public void entrarMoto() {
        this.seccionMotos.entrar();
    }

    public void salirMoto() {
        this.seccionMotos.salir();
    }

    public int cuantasMotosHay() {
        return this.seccionMotos.getcuantosHay();
    }

    public int getMotosQueSalieron() {
        return this.seccionMotos.getQueSalieron();
    }

    // ------------------------------------------------------------------------------------------
    // 6. GESTIÓN DEL SEMÁFORO LED
    // ------------------------------------------------------------------------------------------
    public void setLed(SemaforoLed led) {
        this.led = led;
        sincronizarLed();
    }

    // Método auxiliar privado para actualizar la luz sin repetir código
    private void sincronizarLed() {
        if (this.led != null) {
            this.led.actualizarColor(this.seccionCoches.getcuantosHay());
        }
    }

    // ------------------------------------------------------------------------------------------
    // 7. INFORME DE ESTADO POR PANTALLA
    // ------------------------------------------------------------------------------------------
    public void mostrarEstado() {
        int libresCoches = seccionCoches.getLimite() - seccionCoches.getcuantosHay();
        int libresMotos = seccionMotos.getLimite() - seccionMotos.getcuantosHay();

        System.out.println("\n--------------------------------------------");
        System.out.println("         ESTADO ACTUAL DEL GARAJE           ");
        System.out.println("--------------------------------------------");
        System.out.println("Coches: " + seccionCoches.getcuantosHay() + " / " + seccionCoches.getLimite()
                + " (Libres: " + libresCoches + " | Salidos hoy: " + seccionCoches.getQueSalieron() + ")");
        System.out.println("Motocicletas: " + seccionMotos.getcuantosHay() + " / " + seccionMotos.getLimite()
                + " (Libres: " + libresMotos + " | Salidas hoy: " + seccionMotos.getQueSalieron() + ")");

        if (this.led != null) {
            System.out.println("Semáforo Coches: " + this.led.getColor());
        }
        System.out.println("--------------------------------------------");
    }

// ==============================================================================================
// 🚗 BLOQUE AUXILIAR 1: SUPERCLASE Vehiculos (EL MOLDE BASE)
// Contiene la lógica común para coches y motos para no repetir código:
// - Control de plazas máximas (limite)
// - Contadores de dentro y salidos
// - Validaciones de entrada (aforo lleno) y salida (garaje vacío)
// ==============================================================================================
class Vehiculos {
    protected int dentro = 0;
    protected int queSalieron = 0;
    protected final int limite;
    protected final String tipo;

    public Vehiculos(String tipo, int limite) {
        this.tipo = tipo;
        this.limite = limite;
    }

    public void entrar() {
        if (this.dentro >= this.limite) {
            throw new IllegalStateException("Error: La zona de " + this.tipo + " está llena (" + this.limite + " plazas).");
        }
        this.dentro++;
        System.out.println("Entró " + this.tipo + ". Ocupación: " + this.dentro + "/" + this.limite);
    }

    public void salir() {
        if (this.dentro <= 0) {
            throw new IllegalStateException("Error: No hay ningún " + this.tipo + " dentro para retirar.");
        }
        this.dentro--;
        this.queSalieron++;
        System.out.println("Salió " + this.tipo + ". Dentro: " + this.dentro + " | Total salidos: " + this.queSalieron);
    }

    public int getcuantosHay() {
        return this.dentro;
    }

    public int getQueSalieron() {
        return this.queSalieron;
    }

    public int getLimite() {
        return this.limite;
    }

    public String getTipo() {
        return this.tipo;
    }
}


// ==============================================================================================
// 🚙 BLOQUE AUXILIAR 2: SUBCLASE Coches
// Hereda de Vehiculos fijando su etiqueta a "Coche" y su límite por defecto en 50
// ==============================================================================================
class Coches extends Vehiculos {
    public Coches(int limite) {
        super("Coche", limite);
    }

    public Coches() {
        this(50);
    }
}


// ==============================================================================================
// 🏍️ BLOQUE AUXILIAR 3: SUBCLASE Motocicletas
// Hereda de Vehiculos fijando su etiqueta a "Motocicleta" y su límite por defecto en 15
// ==============================================================================================
class Motocicletas extends Vehiculos {
    public Motocicletas(int limite) {
        super("Motocicleta", limite);
    }

    public Motocicletas() {
        this(15);
    }
}


// ==============================================================================================
// 🚥 BLOQUE AUXILIAR 4: CLASE SemaforoLed (DISPOSITIVO LUMINOSO)
// Cambia su bombilla según los coches presentes (<25 Verde, <50 Naranja, >=50 Rojo)
// ==============================================================================================
class SemaforoLed {
    private String color = "VERDE";

    public void actualizarColor(int cochesDentro) {
        if (cochesDentro < 25) {
            this.color = "VERDE";
        } else if (cochesDentro < 50) {
            this.color = "NARANJA";
        } else {
            this.color = "ROJO";
        }
        System.out.println("Semáforo LED actualizado a: " + this.color);
    }

    public String getColor() {
        return this.color;
    }

}
