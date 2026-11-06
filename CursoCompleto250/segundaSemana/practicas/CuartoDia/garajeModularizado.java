package CursoCompleto250.segundaSemana.practicas.CuartoDia; // Paquete que define la ruta de carpetas donde vive este archivo

// ==============================================================================================
// CLASE PRINCIPAL: garajeModularizado (Coordinador del sistema)
// ==============================================================================================
public class garajeModularizado {

    // --- 1. CONSTANTES FIJAS (Valores inmutables del negocio) ---
    public static final int limiteCoches = 50; // Aforo máximo para coches: fijo en 50 para todo el programa
    public static final int limiteMotos = 15;  // Aforo máximo para motos: fijo en 15 para todo el programa

    // --- 2. ATRIBUTOS PRIVADOS (Los 3 componentes que gestiona este garaje) ---
    private final Coches seccionCoches;       // Objeto de tipo Coches que controla la planta de coches
    private final Motocicletas seccionMotos;  // Objeto de tipo Motocicletas que controla la planta de motos
    private SemaforoLed led;                  // Objeto de tipo SemaforoLed que controla la luz indicadora

    // --- 3. CONSTRUCTORES ---
    // Constructor vacío (por defecto): llama al constructor de abajo pasando las constantes fijas (50 y 15)
    public garajeModularizado() {
        this(limiteCoches, limiteMotos); // Encadenamiento de constructores con 'this(...)'
    }

    // Constructor con parámetros: permite inicializar el garaje con aforos específicos si hiciera falta
    public garajeModularizado(int limiteCoches, int limiteMotos) {
        this.seccionCoches = new Coches(limiteCoches);       // Fabrica el objeto de Coches con el aforo dado
        this.seccionMotos = new Motocicletas(limiteMotos);   // Fabrica el objeto de Motos con el aforo dado
        this.led = new SemaforoLed();                        // Fabrica e instala el semáforo LED en memoria RAM
    }

    // --- 4. ACCIONES DE COCHES (Entrada, Salida y Consultas) ---
    // Método que registra la entrada de un coche
    public void entrarCoche() {
        this.seccionCoches.entrar(); // Delega en el objeto especialista de coches para sumar 1 y validar aforo
        sincronizarLed();            // Llama a nuestro método privado para actualizar el color del semáforo
    }

    // Método que registra la salida de un coche
    public void salirCoche() {
        this.seccionCoches.salir();  // Delega en el objeto de coches para restar 1 y sumar a salidos
        sincronizarLed();            // Actualiza el color del semáforo tras la salida
    }

    // Consulta cuántos coches hay aparcados dentro en este momento
    public int getCochesDentro() {
        return this.seccionCoches.getcuantosHay(); // Pide el dato a la sección de coches y lo devuelve
    }

    // Consulta el acumulado de coches que han salido en total
    public int getCochesQueSalieron() {
        return this.seccionCoches.getQueSalieron(); // Devuelve el historial de salidas de coches
    }

    // --- 5. ACCIONES DE MOTOS (Entrada, Salida y Consultas) ---
    // Método que registra la entrada de una moto
    public void entrarMoto() {
        this.seccionMotos.entrar(); // Delega en el objeto especialista de motos para sumar 1 y validar aforo
    }

    // Método que registra la salida de una moto
    public void salirMoto() {
        this.seccionMotos.salir(); // Delega en el objeto de motos para restar 1 y sumar a salidos
    }

    // Consulta cuántas motos hay aparcadas dentro en este momento
    public int cuantasMotosHay() {
        return this.seccionMotos.getcuantosHay(); // Pide el dato a la sección de motos y lo devuelve
    }

    // Consulta el acumulado de motos que han salido en total
    public int getMotosQueSalieron() {
        return this.seccionMotos.getQueSalieron(); // Devuelve el historial de salidas de motos
    }

    // --- 6. GESTIÓN DEL SEMÁFORO LED ---
    // Permite cambiar o conectar un semáforo diferente si fuera necesario
    public void setLed(SemaforoLed led) {
        this.led = led;   // Asigna el nuevo semáforo al atributo propio
        sincronizarLed(); // Sincroniza de inmediato la bombilla con los coches actuales
    }

    // Método auxiliar privado para actualizar la luz sin repetir código en entrar y salir
    private void sincronizarLed() {
        if (this.led != null) { // Programación defensiva: comprueba que el semáforo existe para evitar errores
            this.led.actualizarColor(this.seccionCoches.getcuantosHay()); // Cambia el color según coches actuales
        }
    }

    // --- 7. MÉTODO MAIN: El disparador para ejecutar y probar el código ---
    public static void main(String[] args) {
        garajeModularizado garaje = new garajeModularizado(); // 1. Construye el garaje en la memoria RAM
        garaje.entrarCoche();                                 // 2. Hace entrar un coche de prueba
        garaje.entrarMoto();                                  // 3. Hace entrar una moto de prueba
        garaje.salirCoche();                                  // 4. Hace salir un coche de prueba
    }

} // <--- Cierre de la clase garajeModularizado


// ==============================================================================================
// SUPERCLASE PADRE: Vehiculos (Contiene la lógica compartida para coches y motos)
// ==============================================================================================
class Vehiculos {
    protected int dentro = 0;              // Contador de vehículos actualmente dentro (accesible por subclases)
    protected int queSalieron = 0;         // Acumulador de vehículos que salieron hoy
    protected final int limite;            // Aforo máximo fijado en el constructor (no se puede modificar luego)
    protected final String tipo;           // Nombre del tipo de vehículo ("Coche" o "Motocicleta")

    // Constructor base que recibe el nombre del tipo y su capacidad de plazas
    public Vehiculos(String tipo, int limite) {
        this.tipo = tipo;     // Guarda la etiqueta recibida en el atributo tipo
        this.limite = limite; // Guarda el límite recibido en el atributo limite
    }

    // Lógica para registrar una entrada con comprobación previa de aforo
    public void entrar() {
        if (this.dentro >= this.limite) { // Si ya estamos al tope o más
            // Lanza una excepción para avisar de que está lleno y detiene la entrada
            throw new IllegalStateException("Error: La zona de " + this.tipo + " está llena (" + this.limite + " plazas).");
        }
        this.dentro++; // Incrementa en 1 los vehículos aparcados
        System.out.println("Entró " + this.tipo + ". Ocupación: " + this.dentro + "/" + this.limite); // Feedback en consola
    }

    // Lógica para registrar una salida con comprobación de existencia previa
    public void salir() {
        if (this.dentro <= 0) { // Si hay 0 vehículos, no se puede retirar ninguno
            // Lanza una excepción evitando números negativos absurdos
            throw new IllegalStateException("Error: No hay ningún " + this.tipo + " dentro para retirar.");
        }
        this.dentro--;      // Resta 1 a los vehículos que quedan dentro
        this.queSalieron++; // Suma 1 al historial de salidas
        System.out.println("Salió " + this.tipo + ". Dentro: " + this.dentro + " | Total salidos: " + this.queSalieron); // Muestra datos
    }

    // Getter para consultar la cantidad actual aparcada
    public int getcuantosHay() {
        return this.dentro; // Devuelve el atributo protegido dentro
    }

    // Getter para consultar el total acumulado de salidas
    public int getQueSalieron() {
        return this.queSalieron; // Devuelve el atributo protegido queSalieron
    }

    // Getter para consultar el límite de aforo
    public int getLimite() {
        return this.limite; // Devuelve la capacidad máxima permitida
    }

    // Getter para consultar el texto descriptivo del tipo ("Coche" o "Motocicleta")
    public String getTipo() {
        return this.tipo; // Devuelve la etiqueta guardada
    }
}


// ==============================================================================================
// SUBCLASE Coches: Hereda de Vehiculos fijando "Coche" y límite en 50
// ==============================================================================================
class Coches extends Vehiculos {
    // Constructor con límite personalizado: llama al constructor del padre pasándole "Coche" y el límite
    public Coches(int limite) {
        super("Coche", limite); // 'super' invoca a Vehiculos(String tipo, int limite)
    }

    // Constructor por defecto: si no le pasamos número, fija automáticamente 50 plazas
    public Coches() {
        this(50); // Llama al constructor de arriba con 50
    }
}


// ==============================================================================================
// SUBCLASE Motocicletas: Hereda de Vehiculos fijando "Motocicleta" y límite en 15
// ==============================================================================================
class Motocicletas extends Vehiculos {
    // Constructor con límite personalizado: llama al constructor del padre pasándole "Motocicleta" y el límite
    public Motocicletas(int limite) {
        super("Motocicleta", limite); // 'super' invoca a Vehiculos(String tipo, int limite)
    }

    // Constructor por defecto: si no le pasamos número, fija automáticamente 15 plazas
    public Motocicletas() {
        this(15); // Llama al constructor de arriba con 15
    }
}


// ==============================================================================================
// CLASE SemaforoLed: Controla el color según la ocupación de coches (<25 Verde, <50 Naranja, >=50 Rojo)
// ==============================================================================================
class SemaforoLed {
    private String color = "VERDE"; // Inicia por defecto en VERDE al arrancar vacío

    // Evalúa la cantidad de coches presentes y cambia el color de la luz
    public void actualizarColor(int cochesDentro) {
        if (cochesDentro < 25) {           // Menos de la mitad de plazas ocupadas
            this.color = "VERDE";
        } else if (cochesDentro < 50) {    // Ocupación media / advertencia
            this.color = "NARANJA";
        } else {                           // Aforo completo (50 o más)
            this.color = "ROJO";
        }
        System.out.println("Semáforo LED actualizado a: " + this.color); // Informa por consola del cambio de luz
    }

    // Getter para consultar qué color está activo
    public String getColor() {
        return this.color; // Devuelve el color actual en formato String
    }
}
