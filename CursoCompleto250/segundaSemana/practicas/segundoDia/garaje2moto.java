package CursoCompleto250.segundaSemana.practicas.segundoDia;

import java.util.Scanner;

// ============================================================================
// SUPERCLASE: Vehiculos
// Contiene la lógica común de aforo, control de entradas, salidas y excepciones
// ============================================================================
class Vehiculos {
    protected int dentro = 0;
    protected int queSalieron = 0;
    protected final int limite;
    protected final String tipo;

    // Constructor que recibe el tipo y el límite
    public Vehiculos(String tipo, int limite) {
        this.tipo = tipo;
        this.limite = limite;
    }

    // Da entrada: si alcanza el límite asignado tira error, si no suma 1
    public void entrar() {
        if (this.dentro >= this.limite) {
            throw new IllegalStateException("Error: El espacio de " + this.tipo + " está lleno (" + this.limite + " plazas). Entrada denegada.");
        }
        this.dentro++;
        System.out.println("✅ Entró " + this.tipo + ". Dentro: " + this.dentro + "/" + this.limite);
    }

    // Da salida: si está a 0 tira error, si no resta 1 y suma a salidas
    public void salir() {
        if (this.dentro <= 0) {
            throw new IllegalStateException("Error: No hay ningún " + this.tipo + " dentro del garaje para retirar.");
        }
        this.dentro--;
        this.queSalieron++;
        System.out.println("🚗💨 Salió " + this.tipo + ". Dentro: " + this.dentro + " | Total salidos: " + this.queSalieron);
    }

    public int cuantosHay() {
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

// ============================================================================
// SUBCLASE: Coches (Hereda de Vehiculos con límite configurable)
// ============================================================================
class Coches extends Vehiculos {
    public Coches(int limite) {
        super("Coche", limite);
    }

    public Coches() {
        this(50); // Por defecto 50 plazas
    }
}

// ============================================================================
// SUBCLASE: Motocicletas (Hereda de Vehiculos con límite configurable)
// ============================================================================
class Motocicletas extends Vehiculos {
    public Motocicletas(int limite) {
        super("Motocicleta", limite);
    }

    public Motocicletas() {
        this(15); // Por defecto 15 plazas
    }
}

// ============================================================================
// CLASE: SemaforoLed (Indicador visual de aforo para coches)
// ============================================================================
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
        System.out.println("🚦 Semáforo LED actualizado a: " + this.color);
    }

    public String getColor() {
        return this.color;
    }
}

// ============================================================================
// CLASE PRINCIPAL: garaje2moto
// Gestión global del aparcamiento interactivo con Scanner
// ============================================================================
public class garaje2moto {

    // ATRIBUTOS DE LÍMITE: Si el día de mañana piden aumentarlos, solo se cambian aquí:
    public static final int PLAZAS_FIJAS_COCHES = 50;
    public static final int PLAZAS_FIJAS_MOTOS = 15;

    // Secciones independientes
    private Coches seccionCoches;
    private Motocicletas seccionMotos;
    private SemaforoLed led;

    // Constructor por defecto usando las constantes
    public garaje2moto() {
        this(PLAZAS_FIJAS_COCHES, PLAZAS_FIJAS_MOTOS);
    }

    // Constructor flexible que permite cambiar límites si fuera necesario
    public garaje2moto(int limiteCoches, int limiteMotos) {
        this.seccionCoches = new Coches(limiteCoches);
        this.seccionMotos = new Motocicletas(limiteMotos);
        this.led = new SemaforoLed();
    }

    // Setter del LED
    public void setLed(SemaforoLed led) {
        this.led = led;
        if (this.led != null) {
            this.led.actualizarColor(this.seccionCoches.cuantosHay());
        }
    }

    // --- ACCIONES DE COCHES ---
    public void entrarCoche() {
        this.seccionCoches.entrar();
        if (this.led != null) {
            this.led.actualizarColor(this.seccionCoches.cuantosHay());
        }
    }

    public void salirCoche() {
        this.seccionCoches.salir();
        if (this.led != null) {
            this.led.actualizarColor(this.seccionCoches.cuantosHay());
        }
    }

    public int cuantosCochesHay() {
        return this.seccionCoches.cuantosHay();
    }

    public int getCochesQueSalieron() {
        return this.seccionCoches.getQueSalieron();
    }

    // --- ACCIONES DE MOTOS ---
    public void entrarMoto() {
        this.seccionMotos.entrar();
    }

    public void salirMoto() {
        this.seccionMotos.salir();
    }

    public int cuantasMotosHay() {
        return this.seccionMotos.cuantosHay();
    }

    public int getMotosQueSalieron() {
        return this.seccionMotos.getQueSalieron();
    }

    // Muestra un resumen visual de ocupación
    public void mostrarEstado() {
        int libresCoches = seccionCoches.getLimite() - seccionCoches.cuantosHay();
        int libresMotos = seccionMotos.getLimite() - seccionMotos.cuantosHay();

        System.out.println("\n------------------------------------------");
        System.out.println("📊 ESTADO ACTUAL DEL GARAJE");
        System.out.println("------------------------------------------");
        System.out.println("🚗 Coches:        " + seccionCoches.cuantosHay() + " / " + seccionCoches.getLimite() 
                + " (Libres: " + libresCoches + " | Salidos hoy: " + seccionCoches.getQueSalieron() + ")");
        System.out.println("🏍️ Motocicletas:  " + seccionMotos.cuantosHay() + " / " + seccionMotos.getLimite() 
                + " (Libres: " + libresMotos + " | Salidas hoy: " + seccionMotos.getQueSalieron() + ")");
        if (this.led != null) {
            System.out.println("💡 Semáforo Coches: " + this.led.getColor());
        }
        System.out.println("------------------------------------------");
    }

    // ========================================================================
    // MENÚ INTERACTIVO CON SCANNER
    // Pide los datos al cliente por consola de forma segura
    // ========================================================================
    public void iniciarConsola() {
        Scanner sc = new Scanner(System.in);
        boolean continuar = true;

        System.out.println("¡Bienvenido al sistema inteligente de garaje!");

        while (continuar) {
            System.out.println("\n==========================================");
            System.out.println("🅿️  MENÚ PRINCIPAL DEL GARAJE");
            System.out.println("==========================================");
            System.out.println("1. Entrada de vehículo");
            System.out.println("2. Salida de vehículo");
            System.out.println("3. Ver estado de ocupación");
            System.out.println("4. Salir del programa");
            System.out.print("👉 Seleccione una opción (1-4): ");

            String opcion = sc.nextLine().trim();

            switch (opcion) {
                case "1" -> {
                    System.out.println("\n--- REGISTRO DE ENTRADA ---");
                    System.out.print("¿Qué vehículo entra? (1: Coche / 2: Moto): ");
                    String tipo = sc.nextLine().trim();

                    try {
                        if (tipo.equals("1") || tipo.equalsIgnoreCase("coche")) {
                            entrarCoche();
                        } else if (tipo.equals("2") || tipo.equalsIgnoreCase("moto")) {
                            entrarMoto();
                        } else {
                            System.out.println("❌ Opción no reconocida. Debe elegir 1 (Coche) o 2 (Moto).");
                        }
                    } catch (IllegalStateException e) {
                        System.out.println("⚠️ " + e.getMessage());
                    }
                }

                case "2" -> {
                    System.out.println("\n--- REGISTRO DE SALIDA ---");
                    System.out.print("¿Qué vehículo sale? (1: Coche / 2: Moto): ");
                    String tipo = sc.nextLine().trim();

                    try {
                        if (tipo.equals("1") || tipo.equalsIgnoreCase("coche")) {
                            salirCoche();
                        } else if (tipo.equals("2") || tipo.equalsIgnoreCase("moto")) {
                            salirMoto();
                        } else {
                            System.out.println("❌ Opción no reconocida. Debe elegir 1 (Coche) o 2 (Moto).");
                        }
                    } catch (IllegalStateException e) {
                        System.out.println("⚠️ " + e.getMessage());
                    }
                }

                case "3" -> mostrarEstado();

                case "4" -> {
                    System.out.println("\nCerrando el sistema del garaje. ¡Hasta pronto!");
                    continuar = false;
                }

                default -> System.out.println("❌ Opción inválida. Por favor, introduzca un número del 1 al 4.");
            }
        }
    }

    // ========================================================================
    // MÉTODO MAIN
    // ========================================================================
    public static void main(String[] args) {
        garaje2moto garaje = new garaje2moto();
        garaje.iniciarConsola(); // Arranca el menú interactivo con Scanner
    }
}
