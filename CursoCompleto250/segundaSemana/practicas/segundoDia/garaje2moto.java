package CursoCompleto250.segundaSemana.practicas.segundoDia;

// Importo Scanner para poder leer lo que el usuario escribira en consola
import java.util.Scanner;                                                           

// SUPERCLASE: Vehiculos (Clase padre con la lógica compartida)
class Vehiculos { 
    protected int dentro = 0; 
    protected int queSalieron = 0; 
    protected final int limite;                             // Guarda el aforo máximo permitido (50 para coches, 15 para motos)
    protected final String tipo;                            // Guarda el nombre del tipo de vehículo ("Coche" o "Motocicleta")

    // Constructor padre: recibe el nombre del tipo y el número límite de plazas
    public Vehiculos(String tipo, int limite) { 
        this.tipo = tipo;                                   // Guarda el texto ("Coche" o "Motocicleta") en el atributo propio
        this.limite = limite;                               // Guarda el número de plazas máximas en el atributo propio
    } 

    // Método para gestionar la entrada de un vehículo = comprobaciones 
    public void entrar() { 
        if (this.dentro >= this.limite) { 
            throw new IllegalStateException("Error: El espacio de " + this.tipo + " está lleno (" + this.limite + " plazas). Entrada denegada."); 
        } 
        this.dentro++; 
        System.out.println("Entró " + this.tipo + ". Dentro: " + this.dentro + "/" + this.limite); 
    } 

    // Método para gestionar la salida de un vehículo = comprobaciones
    public void salir() { 
        if (this.dentro <= 0) { 
            throw new IllegalStateException("Error: No hay ningún " + this.tipo + " dentro del garaje para retirar."); 
        } 
        this.dentro--; 
        this.queSalieron++; 
        System.out.println("Salió " + this.tipo + ". Dentro: " + this.dentro + " | Total salidos: " + this.queSalieron); 
    } 

    // Método para consultar cuántos vehículos de este tipo hay dentro
    public int cuantosHay() { 
        return this.dentro; 
    } 

    // Método para consultar cuántos vehículos de este tipo han salido en total
    public int getQueSalieron() { 
        return this.queSalieron; 
    } 

    // Método para consultar el límite de plazas asignado
    public int getLimite() { 
        return this.limite; 
    } 

    // Método para consultar el nombre del tipo ("Coche" o "Motocicleta")
    public String getTipo() { 
        return this.tipo; 
    } 
} 

// SUBCLASE Coches: Hija que hereda de Vehiculos. Se reserva la palabra extends.
class Coches extends Vehiculos {                                                                        
    public Coches(int limite) { 
        super("Coche", limite);                     // Llama al constructor de Vehiculos pasando "Coche" y el límite recibido
    } 
    public Coches() { 
        this(50);                                // Llama al constructor de arriba fijando por defecto 50 plazas
    } 
} 

// SUBCLASE Motocicletas: Hija que hereda de Vehiculos. Se reserva la palabra extends.
class Motocicletas extends Vehiculos { 
    public Motocicletas(int limite) { 
        super("Motocicleta", limite);              // Llama al constructor de Vehiculos pasando "Motocicleta" y el límite recibido
    } 
    public Motocicletas() { 
        this(15);                               // Llama al constructor de arriba fijando por defecto 15 plazas
    } 
} 

// CLASE SemaforoLed: Controla el color de la luz led para los coches
class SemaforoLed { 
    private String color = "VERDE"; 

    // Método que calcula y cambia el color según los coches dentro
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

    // Método para consultar qué color está activo
    public String getColor() { 
        return this.color; 
    } 
} 

// CLASE PRINCIPAL: garaje2moto (Coordina coches, motos, semáforo y consola)
public class garaje2moto { 

    // CONSTANTES FIJAS
    public static final int PLAZAS_FIJAS_COCHES = 50; 
    public static final int PLAZAS_FIJAS_MOTOS = 15; 

    // ATRIBUTOS PRIVADOS: Guardan los objetos del sistema
    private Coches seccionCoches; 
    private Motocicletas seccionMotos;
    private SemaforoLed led; 

    // Constructor 
    public garaje2moto() { 
        this(PLAZAS_FIJAS_COCHES, PLAZAS_FIJAS_MOTOS);                              // Llama al constructor de abajo pasando las constantes 50 y 15
    } 

    // Constructor con parámetros dados
    public garaje2moto(int limiteCoches, int limiteMotos) { 
        this.seccionCoches = new Coches(limiteCoches);                             
        this.seccionMotos = new Motocicletas(limiteMotos);                          // Fabrica los objeto con nuevos límite del contructor de arriba
        this.led = new SemaforoLed();                                               
    } 

    // Setter para conectar o cambiar el LED
    public void setLed(SemaforoLed led) { 
        this.led = led; 
        if (this.led != null) { 
            this.led.actualizarColor(this.seccionCoches.cuantosHay());                // Sincroniza el color con los coches actuales
        } 
    } 

    // Método para dar entrada a un coche
    public void entrarCoche() { 
        this.seccionCoches.entrar(); 
        if (this.led != null) { 
            this.led.actualizarColor(this.seccionCoches.cuantosHay());                  // Avisa al LED para cambiar de color
        } 
    } 

    // Método para dar salida a un coche
    public void salirCoche() { 
        this.seccionCoches.salir(); 
        if (this.led != null) { 
            this.led.actualizarColor(this.seccionCoches.cuantosHay());                  // Avisa al LED para cambiar de color 
        } 
    } 

    // Consulta cuántos coches hay dentro
    public int cuantosCochesHay() { 
        return this.seccionCoches.cuantosHay(); 
    } 

    // Consulta cuántos coches han salido
    public int getCochesQueSalieron() { 
        return this.seccionCoches.getQueSalieron(); 
    } 

    // Método para dar entrada a una moto   
    public void entrarMoto() { 
        this.seccionMotos.entrar();                                                                        
    } 

    // Método para dar salida a una moto
    public void salirMoto() { 
        this.seccionMotos.salir(); 
    } 

    // Consulta cuántas motos hay dentro
    public int cuantasMotosHay() { 
        return this.seccionMotos.cuantosHay();
    } 

    // Consulta cuántas motos han salido
    public int getMotosQueSalieron() { 
        return this.seccionMotos.getQueSalieron(); 
    } 

    // Método para imprimir en pantalla el resumen de plazas del garaje 
    public void mostrarEstado() { 
        int libresCoches = seccionCoches.getLimite() - seccionCoches.cuantosHay(); 
        int libresMotos = seccionMotos.getLimite() - seccionMotos.cuantosHay(); 

        System.out.println("\n--------------------------------------------");                                                 // \n es un salto linea antes de poner la linea linda                                              
        System.out.println("         ESTADO ACTUAL DEL GARAJE             ");                                                             // Cabecera lindaa y línea separadora visual. Simplemente manias.
        System.out.println("--------------------------------------------"); 

        System.out.println("Coches: " + seccionCoches.cuantosHay() + " / " + seccionCoches.getLimite()               // Muestra coches dentro / límite
                + " (Libres: " + libresCoches + " | Salidos hoy: " + seccionCoches.getQueSalieron() + ")");         // Muestra plazas libres y salidas
                
        System.out.println("Motocicletas: " + seccionMotos.cuantosHay() + " / " + seccionMotos.getLimite()          // Muestra motos dentro / límite
                + " (Libres: " + libresMotos + " | Salidas hoy: " + seccionMotos.getQueSalieron() + ")");           // Muestra plazas libres y salidas

        if (this.led != null) { 
            System.out.println("Semáforo Coches: " + this.led.getColor());                                           // Imprime qué color está brillando ahora
        } 
        System.out.println("------------------------------------------");                                           // Línea separadora final, para mejor visibilidad.
    } 

    // Método interactivo por consola con Scanner
    public void iniciarConsola() { 
        Scanner motramosPantalla = new Scanner(System.in);                                                                        // Crea el lector de teclado conectado a System.in
        boolean continuar = true;                                                                                   // Interruptor booleano para mantener el menú repitiéndose

        System.out.println("¡Bienvenido al garaje de EcheDev!");                                        

        while (continuar) {                                                                                         // Mientras continuar sea true, el menú vuelve a mostrarse(apertura de continuar)
            System.out.println("\n=========================================="); 
            System.out.println("        MENÚ PRINCIPAL DEL GARAJE           ");                                                        // Cabecera lindaa y línea separadora visual. ajaja mania + upps
            System.out.println("=========================================="); 
            System.out.println("1. Entrada de vehículo"); 
            System.out.println("2. Salida de vehículo"); 
            System.out.println("3. Ver estado de ocupación");                                                        //opciones del sistema
            System.out.println("4. Salir del programa"); 
            System.out.print("Seleccione una opción (1-4): ");                                                       // Pregunta al usuario

            String opcion = motramosPantalla.nextLine().trim();                                                                    // Lee lo que escribió el usuario y Trim = quita espacios sobrantes

            switch (opcion) {                                                                                        // SWITCH: Evalúa qué valor tiene la variable opcion = Si es 1,2,3,4...
                case "1" -> {                                                                                                           // CASO 1: Si el usuario tecleó "1"
                    System.out.println("\n--- REGISTRO DE ENTRADA ---"); 
                    System.out.print("¿Qué vehículo entra? (1: Coche / 2: Moto): ");
                    String tipo = motramosPantalla.nextLine().trim();                                                              // Lee la respuesta del usuario ("1", "2", "coche", "moto")

                    try {                                                                                            // ZONA TRY: Por si el garaje está lleno.
                        if (tipo.equals("1") || tipo.equalsIgnoreCase("coche")) { 
                            entrarCoche(); 
                        } else if (tipo.equals("2") || tipo.equalsIgnoreCase("moto")) { 
                            entrarMoto(); 
                        } else { 
                            System.out.println("Opción invalida. Debe elegir 1 (Coche) o 2 (Moto)."); 
                        } 
                    } catch (IllegalStateException e) {                                                               // CATCH: Si la zona estaba llena y saltó el throw
                        System.out.println(e.getMessage());                                                           // Imprime el mensaje del error sin que se cierre el programa
                    } 
                } 

                case "2" -> {                                                                                                            // CASO 2: Si el usuario tecleó "2"
                    System.out.println("\n--- REGISTRO DE SALIDA ---"); 
                    System.out.print("¿Qué vehículo sale? (1: Coche / 2: Moto): ");
                    String tipo = motramosPantalla.nextLine().trim(); 

                    try {                                                                                             // ZONA TRY: Por si el garaje estaba vacío 
                        if (tipo.equals("1") || tipo.equalsIgnoreCase("coche")) { 
                            salirCoche(); 
                        } else if (tipo.equals("2") || tipo.equalsIgnoreCase("moto")) { 
                            salirMoto(); 
                        } else { 
                            System.out.println("Opción no reconocida. Debe elegir 1 (Coche) o 2 (Moto).");            // Aviso de error
                        } 
                    } catch (IllegalStateException e) { 
                        System.out.println(e.getMessage());                                                           // Imprime el mensaje de error sin romperse
                    } 
                } 

                case "3" -> mostrarEstado();                                                                          // CASO 3: Si tecleó "3", ejecuta directamente el método mostrarEstado()

                case "4" -> {                                                                                         // CASO 4: Si tecleó "4", quiere salir
                    System.out.println("\nCerrando el sistema del garaje. ¡Hasta pronto!");                           // Mensaje de despedida * la \n los puse por esteticidad para un salto de linea*
                    continuar = false;                                                                                // Pongo el interruptor en false para que el while termine y no se repita más(Apagador)
                } 

                default -> System.out.println("Opción inválida. Por favor, introduzca un número del 1 al 4.");        // DEFAULT: Si puso cualquier otro número o letra
            } 
        } 
    } 

    // MÉTODO MAIN: El punto de arranque del programa (Sin esto no correria y daria en consola un error de  = public static void main(String[] args)
    public static void main(String[] args) { 
        garaje2moto garaje = new garaje2moto();                                                                     // Fabrica el objeto principal del garaje en memoria RAM
        garaje.iniciarConsola();                                                                                    // Arranca el menú interactivo con el Scanner
    } 
} 
