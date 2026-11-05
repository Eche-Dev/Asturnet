package CursoCompleto250.segundaSemana.practicas.TercerDia;

// ==============================================================================================
// IMPORTACIÓN DE LIBRERÍAS
// ==============================================================================================
// Importamos la clase Scanner del paquete java.util para poder capturar datos por teclado en consola
import java.util.Scanner;

// ==============================================================================================
// BLOQUE 1: SUPERCLASE VEHICULOS (CLASE PADRE)
// ==============================================================================================
// Define la estructura común y el comportamiento compartido para cualquier tipo de vehículo.
// Evita duplicar código entre coches y motos (Principio DRY - Don't Repeat Yourself).
class Vehiculos { 

    // --- ATRIBUTOS (ESTADO DEL OBJETO) ---
    // Usamos 'protected' para que las clases hijas (Coches y Motocicletas) puedan heredar y ver estos atributos directamente.
    protected int dentro = 0;              // Contador de vehículos que están actualmente estacionados dentro del garaje
    protected int queSalieron = 0;         // Contador acumulativo histórico de vehículos que han salido a lo largo del día
    
    // 'final': Constante por instancia. Una vez fijado en el constructor, su valor NO puede alterarse jamás (seguridad e inmutabilidad).
    protected final int limite;            // Aforo máximo permitido de plazas (ej. 50 para coches, 15 para motos)
    protected final String tipo;           // Etiqueta descriptiva del tipo de vehículo (ej. "Coche" o "Motocicleta")

    // --- CONSTRUCTOR DE LA SUPERCLASE ---
    // Recibe el nombre del tipo y el número máximo de plazas permitidas.
    // 'this.tipo' y 'this.limite': 'this' diferencia el atributo de la clase del parámetro que llega por el paréntesis.
    public Vehiculos(String tipo, int limite) { 
        this.tipo = tipo;                  // Asigna el texto identificativo al atributo propio
        this.limite = limite;              // Asigna el tope de aforo al atributo propio
    } 

    // --- MÉTODO GESTOR DE ENTRADA ---
    // Controla el acceso de un vehículo comprobando previamente el aforo disponible.
    public void entrar() { 
        // 1. COMPROBACIÓN DE AFORO: Si ya alcanzamos o superamos el límite, no se puede pasar
        if (this.dentro >= this.limite) { 
            // Lanzamos una excepción IllegalStateException (estado inválido del sistema: garaje lleno).
            // Detiene la ejecución de este método y lanza el mensaje al bloque 'catch' que lo esté esperando.
            throw new IllegalStateException("Error: El espacio de " + this.tipo + " está lleno (" + this.limite + " plazas). Entrada denegada."); 
        } 
        
        // 2. INCREMENTO: Sumamos 1 al número de vehículos aparcados dentro
        this.dentro++; 
        
        // 3. CONFIRMACIÓN: Mostramos por pantalla el resultado de la operación
        System.out.println("Entró " + this.tipo + ". Dentro: " + this.dentro + "/" + this.limite); 
    } 

    // --- MÉTODO GESTOR DE SALIDA ---
    // Controla la retirada de un vehículo comprobando que realmente haya vehículos dentro.
    public void salir() { 
        // 1. COMPROBACIÓN DE EXISTENCIA: No podemos sacar un vehículo si hay 0 (evita números negativos absurdos)
        if (this.dentro <= 0) { 
            // Lanzamos una excepción avisando de que es imposible sacar lo que no existe
            throw new IllegalStateException("Error: No hay ningún " + this.tipo + " dentro del garaje para retirar."); 
        } 
        
        // 2. ACTUALIZACIÓN DE CONTADORES:
        this.dentro--;          // Restamos 1 al número de vehículos presentes
        this.queSalieron++;     // Sumamos 1 al acumulador histórico de salidas
        
        // 3. CONFIRMACIÓN: Mostramos estado actual y acumulado
        System.out.println("Salió " + this.tipo + ". Dentro: " + this.dentro + " | Total salidos: " + this.queSalieron); 
    } 

    // --- MÉTODOS GETTERS (CONSULTAS DE ACCESO LECTURA) ---
    // Devuelve cuántos vehículos de este tipo hay dentro en este instante
    public int cuantosHay() { 
        return this.dentro; 
    } 

    // Devuelve el total acumulado de vehículos que salieron hoy
    public int getQueSalieron() { 
        return this.queSalieron; 
    } 

    // Devuelve la capacidad máxima total de plazas asignadas a este tipo
    public int getLimite() { 
        return this.limite; 
    } 

    // Devuelve el texto descriptivo del tipo de vehículo ("Coche" o "Motocicleta")
    public String getTipo() { 
        return this.tipo; 
    } 
} 

// ==============================================================================================
// BLOQUE 2: SUBCLASES (HERENCIA CON EXTENDS)
// ==============================================================================================

// Subclase Coches: Hereda todos los atributos y métodos de Vehiculos mediante la palabra reservada 'extends'.
class Coches extends Vehiculos { 
    
    // Constructor con límite personalizado: Llama al constructor padre con 'super("Coche", limite)'
    public Coches(int limite) { 
        super("Coche", limite);            // 'super' invoca el constructor de la superclase Vehiculos pasándole el tipo fijo "Coche"
    } 
    
    // Constructor por defecto (sin parámetros):
    // Aplica 'constructor chaining' (encadenamiento de constructores) usando 'this(50)'.
    // Si no nos especifican aforo, llamamos a nuestro propio constructor de arriba asignándole 50 plazas por defecto.
    public Coches() { 
        this(50); 
    } 
} 

// Subclase Motocicletas: Hereda también de Vehiculos.
class Motocicletas extends Vehiculos { 
    
    // Constructor con límite personalizado: Pasa "Motocicleta" como etiqueta fija al padre
    public Motocicletas(int limite) { 
        super("Motocicleta", limite);      // 'super' invoca a Vehiculos(String tipo, int limite)
    } 
    
    // Constructor por defecto (sin parámetros):
    // Usa 'this(15)' para fijar el límite estándar de 15 plazas en motos si no se indica otro.
    public Motocicletas() { 
        this(15); 
    } 
} 

// ==============================================================================================
// BLOQUE 3: CLASE SEMAFORO LED (DISPOSITIVO AUXILIAR DE SEÑALIZACIÓN)
// ==============================================================================================
// Representa una luz inteligente física que cambia de color según la cantidad de coches aparcados.
class SemaforoLed { 
    
    // Atributo privado: guarda el color actual. Por defecto inicia en VERDE porque el garaje arranca vacío.
    private String color = "VERDE"; 

    // Método que evalúa los umbrales de ocupación de coches y ajusta la bombilla LED:
    // - Menos de 25 coches: VERDE (Tráfico fluido, muchas plazas libres)
    // - De 25 a 49 coches: NARANJA (Atención, más de la mitad ocupado)
    // - 50 o más coches: ROJO (Aforo completo o saturado)
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

    // Getter para consultar desde fuera qué color está encendido en este momento
    public String getColor() { 
        return this.color; 
    } 
} 

// ==============================================================================================
// BLOQUE 4: CLASE PRINCIPAL 'garaje2moto' (EL COORDINADOR GENERAL)
// ==============================================================================================
// Esta clase representa el sistema integral del garaje.
// Coordina la sección de coches, la sección de motos, el semáforo y la interfaz de usuario en consola.
public class garaje2moto { 

    // --- CONSTANTES GLOBALES (PUBLIC STATIC FINAL) ---
    // 'public': accesible desde cualquier punto.
    // 'static': pertenece a la clase, no requiere instanciar un objeto para leerse (garaje2moto.PLAZAS_FIJAS_COCHES).
    // 'final': valor constante inmutable en tiempo de ejecución.
    public static final int PLAZAS_FIJAS_COCHES = 50; 
    public static final int PLAZAS_FIJAS_MOTOS = 15; 

    // --- ATRIBUTOS DE COMPOSICIÓN (HAS-A / TIENE-UN) ---
    // El garaje 'tiene un' gestor de coches, 'tiene un' gestor de motos y 'tiene un' semáforo LED.
    // 'private': Encapsulamiento estricto; nadie desde fuera puede manipular estos objetos sin pasar por los métodos del garaje.
    private Coches seccionCoches; 
    private Motocicletas seccionMotos; 
    private SemaforoLed led; 

    // --- CONSTRUCTOR 1: POR DEFECTO (SIN PARÁMETROS) ---
    // Encadenamiento con 'this(...)': Delega el trabajo en el constructor parametrizado pasando las constantes de 50 y 15.
    public garaje2moto() { 
        this(PLAZAS_FIJAS_COCHES, PLAZAS_FIJAS_MOTOS); 
    } 

    // --- CONSTRUCTOR 2: PARAMETRIZADO ---
    // Permite crear un garaje con aforos a medida si en el futuro se desea (ej: un garaje de 100 coches y 30 motos).
    // 'new Coches(...)': Crea en memoria RAM el objeto que gestionará los coches.
    // 'new Motocicletas(...)': Crea en memoria RAM el objeto que gestionará las motos.
    // 'new SemaforoLed()': Enciende el dispositivo de semáforo LED.
    public garaje2moto(int limiteCoches, int limiteMotos) { 
        this.seccionCoches = new Coches(limiteCoches); 
        this.seccionMotos = new Motocicletas(limiteMotos); 
        this.led = new SemaforoLed(); 
    } 

    // --- SETTER DEL LED (CONEXIÓN Y SINCRONIZACIÓN) ---
    // Permite cambiar o conectar otro semáforo si hiciera falta.
    public void setLed(SemaforoLed led) { 
        this.led = led; 
        // Programación defensiva: comprobamos que 'this.led != null' (que no nos hayan pasado una referencia vacía)
        // para evitar que el programa lance un NullPointerException.
        if (this.led != null) { 
            // Sincroniza de inmediato el color del semáforo con los coches que ya estén dentro del garaje
            this.led.actualizarColor(this.seccionCoches.cuantosHay()); 
        } 
    } 

    // --- GESTIÓN DE COCHES (DELEGACIÓN AL OBJETO seccionCoches) ---
    // Da entrada a un coche delegando en su objeto especializado y actualiza el semáforo LED
    public void entrarCoche() { 
        this.seccionCoches.entrar(); 
        // Si hay un semáforo conectado, le pedimos que recalcule su color según la nueva ocupación
        if (this.led != null) { 
            this.led.actualizarColor(this.seccionCoches.cuantosHay()); 
        } 
    } 

    // Da salida a un coche delegando en su objeto especializado y actualiza el semáforo LED
    public void salirCoche() { 
        this.seccionCoches.salir(); 
        // Si hay un semáforo conectado, recalculamos su color
        if (this.led != null) { 
            this.led.actualizarColor(this.seccionCoches.cuantosHay()); 
        } 
    } 

    // Consulta cuántos coches hay actualmente dentro del garaje
    public int cuantosCochesHay() { 
        return this.seccionCoches.cuantosHay(); 
    } 

    // Consulta cuántos coches han salido a lo largo del día
    public int getCochesQueSalieron() { 
        return this.seccionCoches.getQueSalieron(); 
    } 

    // --- GESTIÓN DE MOTOS (DELEGACIÓN AL OBJETO seccionMotos) ---
    // Da entrada a una motocicleta delegando en su objeto propio
    public void entrarMoto() { 
        this.seccionMotos.entrar(); 
    } 

    // Da salida a una motocicleta delegando en su objeto propio
    public void salirMoto() { 
        this.seccionMotos.salir(); 
    } 

    // Consulta cuántas motocicletas hay actualmente dentro del garaje
    public int cuantasMotosHay() { 
        return this.seccionMotos.cuantosHay(); 
    } 

    // Consulta cuántas motocicletas han salido en total hoy
    public int getMotosQueSalieron() { 
        return this.seccionMotos.getQueSalieron(); 
    } 

    // --- MÉTODO PARA MOSTRAR EL INFORME DE ESTADO GENERAL ---
    // Calcula plazas libres y resume la ocupación de coches, motos y estado de las luces en pantalla.
    public void mostrarEstado() { 
        // Cálculo matemático simple: Plazas Libres = Capacidad Límite - Vehículos Dentro
        int libresCoches = this.seccionCoches.getLimite() - this.seccionCoches.cuantosHay(); 
        int libresMotos = this.seccionMotos.getLimite() - this.seccionMotos.cuantosHay(); 

        // \n: Salto de línea para crear una separación visual limpia antes del encabezado
        System.out.println("\n--------------------------------------------"); 
        System.out.println("         ESTADO ACTUAL DEL GARAJE           "); 
        System.out.println("--------------------------------------------"); 

        // Imprime estadísticas de los coches: dentro / total (libres y salidas acumuladas)
        System.out.println("Coches: " + this.seccionCoches.cuantosHay() + " / " + this.seccionCoches.getLimite() 
                + " (Libres: " + libresCoches + " | Salidos hoy: " + this.seccionCoches.getQueSalieron() + ")"); 
                
        // Imprime estadísticas de las motocicletas: dentro / total (libres y salidas acumuladas)
        System.out.println("Motocicletas: " + this.seccionMotos.cuantosHay() + " / " + this.seccionMotos.getLimite() 
                + " (Libres: " + libresMotos + " | Salidas hoy: " + this.seccionMotos.getQueSalieron() + ")"); 

        // Si el semáforo existe y está conectado, muestra su color actual en el informe
        if (this.led != null) { 
            System.out.println("Semáforo Coches: " + this.led.getColor()); 
        } 
        System.out.println("--------------------------------------------"); 
    } 

    // --- MÉTODO DEL MENÚ INTERACTIVO POR CONSOLA ---
    // Gestiona el bucle de interacción continua con el usuario mediante Scanner
    public void iniciarConsola() { 
        // Creamos la instancia de Scanner conectada a System.in (el teclado de la consola)
        Scanner sc = new Scanner(System.in); 
        
        // Interruptor o bandera booleana para mantener el bucle while en ejecución hasta que el usuario elija salir
        boolean continuar = true; 

        System.out.println("¡Bienvenido al garaje de EcheDev!"); 

        // BUCLE WHILE: Se ejecuta repetidamente mientras 'continuar' siga valiendo true
        while (continuar) { 
            // '\n' introduce un salto de línea previo para no pegar el menú a las operaciones anteriores
            System.out.println("\n=========================================="); 
            System.out.println("        MENÚ PRINCIPAL DEL GARAJE         "); 
            System.out.println("=========================================="); 
            System.out.println("1. Entrada de vehículo"); 
            System.out.println("2. Salida de vehículo"); 
            System.out.println("3. Ver estado de ocupación"); 
            System.out.println("4. Salir del programa"); 
            System.out.print("Seleccione una opción (1-4): "); 

            // sc.nextLine(): Lee toda la línea introducida por el usuario como texto (String).
            // .trim(): Elimina espacios en blanco invisibles al principio y al final (ej: " 1 " se convierte en "1").
            String opcion = sc.nextLine().trim(); 

            // ESTRUCTURA SWITCH: Evalúa el valor de la cadena 'opcion'
            switch (opcion) { 
                case "1" -> { 
                    // CASO 1: REGISTRO DE ENTRADA
                    System.out.println("\n--- REGISTRO DE ENTRADA ---"); 
                    System.out.print("¿Qué vehículo entra? (1: Coche / 2: Moto): "); 
                    String tipo = sc.nextLine().trim(); 

                    // BLOQUE TRY-CATCH: Manejo controlado de excepciones
                    // 'try': Bloque protegido donde puede ocurrir un error (ej. garaje lleno).
                    try { 
                        // equals("1"): Compara si el texto es exactamente "1".
                        // || : Operador lógico 'O' (OR). Si se cumple cualquiera de las dos condiciones, entra al if.
                        // equalsIgnoreCase("coche"): Compara el texto ignorando si el usuario escribió en MAYÚSCULAS o minúsculas ("COCHE", "Coche", "coche").
                        if (tipo.equals("1") || tipo.equalsIgnoreCase("coche")) { 
                            entrarCoche(); 
                        } else if (tipo.equals("2") || tipo.equalsIgnoreCase("moto")) { 
                            entrarMoto(); 
                        } else { 
                            System.out.println("Opción inválida. Debe elegir 1 (Coche) o 2 (Moto)."); 
                        } 
                    } catch (IllegalStateException e) { 
                        // 'catch': Captura la excepción lanzada por entrar() cuando el aforo está completo.
                        // e.getMessage(): Extrae el texto del mensaje de error definido dentro de la clase Vehiculos.
                        // Gracias a esto, el programa NO se cuelga ni se interrumpe de forma brusca, sino que informa limpiamente al usuario.
                        System.out.println(e.getMessage()); 
                    } 
                } 

                case "2" -> { 
                    // CASO 2: REGISTRO DE SALIDA
                    System.out.println("\n--- REGISTRO DE SALIDA ---"); 
                    System.out.print("¿Qué vehículo sale? (1: Coche / 2: Moto): "); 
                    String tipo = sc.nextLine().trim(); 

                    try { 
                        if (tipo.equals("1") || tipo.equalsIgnoreCase("coche")) { 
                            salirCoche(); 
                        } else if (tipo.equals("2") || tipo.equalsIgnoreCase("moto")) { 
                            salirMoto(); 
                        } else { 
                            System.out.println("Opción no reconocida. Debe elegir 1 (Coche) o 2 (Moto)."); 
                        } 
                    } catch (IllegalStateException e) { 
                        // Captura la excepción si se intenta retirar un coche o moto cuando no queda ninguno (aforo en cero)
                        System.out.println(e.getMessage()); 
                    } 
                } 

                // CASO 3: CONSULTA DE ESTADO
                // Al ser una sola instrucción directa, la sintaxis con flecha (->) permite ejecutarla directamente sin abrir llaves.
                case "3" -> mostrarEstado(); 

                case "4" -> { 
                    // CASO 4: SALIDA DEL SISTEMA
                    System.out.println("\nCerrando el sistema del garaje. ¡Hasta pronto!"); 
                    // Cambiamos el interruptor 'continuar' a false: al llegar al final del switch, el while evaluará false y terminará limpiamente.
                    continuar = false; 
                } 

                // DEFAULT: Se ejecuta si el usuario introdujo cualquier texto que no coincida con "1", "2", "3" ni "4".
                default -> System.out.println("Opción inválida. Por favor, introduzca un número del 1 al 4."); 
            } 
        } 
    } 

    // --- MÉTODO MAIN (PUNTO DE ENTRADA PRINCIPAL A LA JVM) ---
    // 'public static void main(String[] args)': Es la firma obligatoria que busca Java para iniciar cualquier programa autónomo.
    // Sin este método, la máquina virtual de Java (JVM) no sabe por dónde empezar y daría error en consola.
    public static void main(String[] args) { 
        // 1. Instanciamos el garaje en la memoria RAM con sus 50 coches y 15 motos configurados por defecto
        garaje2moto garaje = new garaje2moto(); 
        
        // 2. Iniciamos el menú interactivo para que el usuario pueda operar el garaje desde la consola
        garaje.iniciarConsola(); 
    } 
}
