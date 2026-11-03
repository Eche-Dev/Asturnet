package CursoCompleto250.segundaSemana.practicas.PrimerDia; // Paquete organizado por semana y día

// Clase para representar el LED indicador del garaje
class SemaforoLed { // Definición de la clase SemaforoLed

    private String color = "VERDE"; // Estado del LED: empieza en VERDE porque el garaje arranca vacío

    // Método que actualiza el color según la cantidad de coches en el garaje
    public void actualizarColor(int cochesDentro) { 
        if (cochesDentro < 25) { // Condición 1: Menos de 25 coches
            this.color = "VERDE"; // El LED brilla en color VERDE
        } else if (cochesDentro < 50) { // Condición 2: Entre 25 y 49 coches
            this.color = "NARANJA"; // El LED brilla en color NARANJA
        } else { // Condición 3: 50 coches o más (garaje lleno)
            this.color = "ROJO"; // El LED brilla en color ROJO
        } 
        System.out.println("LED brillando en: " + this.color); // Muestra el color activo del LED
    } 

    // Método para consultar qué color está brillando actualmente
    public String getColor() { 
        return this.color; // Devuelve el color actual del LED
    } 
} 

// Clase principal del garaje
public class garaje { // Definición de la clase garaje

    private int cochesDentro = 0; // Contador de coches que están dentro del garaje
    private int cochesQueSalieron = 0; // Contador acumulador de coches que han salido
    private SemaforoLed led; // Referencia para asociar el LED al garaje (a futuro o en uso)

    // Método que permite conectar o añadir el LED al garaje
    public void setLed(SemaforoLed led) { 
        this.led = led; // Asigna el objeto LED recibido a la variable de la clase
        if (this.led != null) { // Si el LED no es nulo
            this.led.actualizarColor(this.cochesDentro); // Actualiza su color al estado actual del garaje
        } 
    } 

    // Método para comprobar cuántos coches hay dentro
    public int cuantosCochesHay() { 
        return this.cochesDentro; // Devuelve el número actual de coches dentro
    } 

    // Método para dar entrada a un coche
    public void entrarCoche() { 
        if (this.cochesDentro >= 50) { // Comprueba si el garaje ya tiene 50 coches
            throw new IllegalStateException("Error: El garaje está lleno (50 coches). Entrada denegada."); // Deniega y tira error
        } 
        this.cochesDentro++; // Suma 1 al número de coches dentro
        System.out.println("Coche entró. Coches dentro: " + this.cochesDentro); // Informa de la entrada

        if (this.led != null) { // Si hay un LED conectado al garaje
            this.led.actualizarColor(this.cochesDentro); // Actualiza el color del LED con el nuevo total
        } 
    } 

    // Método para dar salida a un coche
    public void salirCoche() { 
        if (this.cochesDentro <= 0) { // Comprueba que no esté vacío
            throw new IllegalStateException("Error: No hay coches dentro del garaje."); // Tira error si no hay coches
        } 
        this.cochesDentro--; // Resta 1 a los que quedan dentro
        this.cochesQueSalieron++; // Suma 1 al total de salidas
        System.out.println("Coche salió. Coches dentro: " + this.cochesDentro + " | Total salidos: " + this.cochesQueSalieron); // Informa de la salida

        if (this.led != null) { // Si hay un LED conectado al garaje
            this.led.actualizarColor(this.cochesDentro); // Actualiza el color del LED con el nuevo total
        } 
    } 

    // Método para consultar cuántos han salido
    public int getCochesQueSalieron() { 
        return this.cochesQueSalieron; // Devuelve el total acumulado de salidas
    } 

    // Método para consultar el LED asociado
    public SemaforoLed getLed() { 
        return this.led; // Devuelve el objeto LED
    } 
} 
