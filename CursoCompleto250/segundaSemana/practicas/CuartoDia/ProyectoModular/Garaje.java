package CursoCompleto250.segundaSemana.practicas.CuartoDia.ProyectoModular;

import java.util.Scanner;

/**
 * 🏛️ COORDINADOR GENERAL: Garaje
 * Esta clase reúne todos los módulos (coches, motos y semáforo LED) y gestiona el flujo de trabajo.
 */
public class Garaje {

    // --- 1. CONSTANTES DEL SISTEMA ---
    public static final int PLAZAS_FIJAS_COCHES = 50;
    public static final int PLAZAS_FIJAS_MOTOS = 15;

    // --- 2. COMPONENTES PRIVADOS (COMPOSICIÓN) ---
    private final Coches seccionCoches;
    private final Motocicletas seccionMotos;
    private SemaforoLed led;

    // --- 3. CONSTRUCTORES ---
    public Garaje() {
        this(PLAZAS_FIJAS_COCHES, PLAZAS_FIJAS_MOTOS);
    }

    public Garaje(int limiteCoches, int limiteMotos) {
        this.seccionCoches = new Coches(limiteCoches);
        this.seccionMotos = new Motocicletas(limiteMotos);
        this.led = new SemaforoLed();
    }

    // --- 4. GESTIÓN DE COCHES ---
    public void entrarCoche() {
        this.seccionCoches.entrar();
        sincronizarLed();
    }

    public void salirCoche() {
        this.seccionCoches.salir();
        sincronizarLed();
    }

    public int cuantosCochesHay() {
        return this.seccionCoches.cuantosHay();
    }

    public int getCochesQueSalieron() {
        return this.seccionCoches.getQueSalieron();
    }

    // --- 5. GESTIÓN DE MOTOS ---
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

    // --- 6. GESTIÓN DEL SEMÁFORO LED ---
    public void setLed(SemaforoLed led) {
        this.led = led;
        sincronizarLed();
    }

    private void sincronizarLed() {
        if (this.led != null) {
            this.led.actualizarColor(this.seccionCoches.cuantosHay());
        }
    }

    // --- 7. INFORME DE ESTADO ---
    public void mostrarEstado() {
        int libresCoches = seccionCoches.getLimite() - seccionCoches.cuantosHay();
        int libresMotos = seccionMotos.getLimite() - seccionMotos.cuantosHay();

        System.out.println("\n--------------------------------------------");
        System.out.println("         ESTADO ACTUAL DEL GARAJE           ");
        System.out.println("--------------------------------------------");
        System.out.println("Coches: " + seccionCoches.cuantosHay() + " / " + seccionCoches.getLimite()
                + " (Libres: " + libresCoches + " | Salidos hoy: " + seccionCoches.getQueSalieron() + ")");
        System.out.println("Motocicletas: " + seccionMotos.cuantosHay() + " / " + seccionMotos.getLimite()
                + " (Libres: " + libresMotos + " | Salidas hoy: " + seccionMotos.getQueSalieron() + ")");

        if (this.led != null) {
            System.out.println("Semáforo Coches: " + this.led.getColor());
        }
        System.out.println("--------------------------------------------");
    }

    // --- 8. MENÚ INTERACTIVO EN CONSOLA ---
    public void iniciarConsola() {
        Scanner sc = new Scanner(System.in);
        boolean continuar = true;

        System.out.println("¡Bienvenido al sistema del garaje modular!");

        while (continuar) {
            System.out.println("\n==========================================");
            System.out.println("        MENÚ PRINCIPAL DEL GARAJE         ");
            System.out.println("==========================================");
            System.out.println("1. Registrar entrada de vehículo");
            System.out.println("2. Registrar salida de vehículo");
            System.out.println("3. Ver estado de ocupación");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción (1-4): ");

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
                            System.out.println("Opción no válida. Elija 1 (Coche) o 2 (Moto).");
                        }
                    } catch (IllegalStateException e) {
                        System.out.println(e.getMessage());
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
                            System.out.println("Opción no válida. Elija 1 (Coche) o 2 (Moto).");
                        }
                    } catch (IllegalStateException e) {
                        System.out.println(e.getMessage());
                    }
                }

                case "3" -> mostrarEstado();

                case "4" -> {
                    System.out.println("\nCerrando el garaje. ¡Hasta pronto!");
                    continuar = false;
                }

                default -> System.out.println("Opción incorrecta. Escriba un número del 1 al 4.");
            }
        }
    }
}
