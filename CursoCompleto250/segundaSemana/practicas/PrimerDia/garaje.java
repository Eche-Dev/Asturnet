package CursoCompleto250.segundaSemana.practicas.PrimerDia;

// CLASE: SemaforoLed (Indicador visual de aforo)
class SemaforoLed {

    private String color = "VERDE";

    // Actualiza el color del LED según los coches presentes (<25 Verde, 25-49 Naranja, >=50 Rojo)
    public void actualizarColor(int cochesDentro) {
        if (cochesDentro < 25) {
            this.color = "VERDE";
        } else if (cochesDentro < 50) {
            this.color = "NARANJA";
        } else {
            this.color = "ROJO";
        }
        System.out.println("LED brillando en: " + this.color);
    }

    // Consulta el color actual del semáforo
    public String getColor() {
        return this.color;
    }
}


// CLASE: garaje (Gestión de aforo, entradas, salidas y conexión con LED)
public class garaje {

    // Atributos de estado interno
    private int cochesDentro = 0;
    private int cochesQueSalieron = 0;
    private SemaforoLed led;

    // Setter: Conecta el semáforo LED al garaje y sincroniza su color inicial
    public void setLed(SemaforoLed led) {
        this.led = led;
        if (this.led != null) {
            this.led.actualizarColor(this.cochesDentro);
        }
    }

    // Consulta cuántos coches hay dentro en este momento
    public int cuantosCochesHay() {
        return this.cochesDentro;
    }

    // Da entrada a un coche: lanza error si hay 50 o más, y notifica al LED
    public void entrarCoche() {
        if (this.cochesDentro >= 50) {
            throw new IllegalStateException("Error: El garaje está lleno (50 coches). Entrada denegada.");
        }
        this.cochesDentro++;
        System.out.println("Con el coche que acaba de entrar. Coches dentro: " + this.cochesDentro);

        if (this.led != null) {
            this.led.actualizarColor(this.cochesDentro);
        }
    }

    // Da salida a un coche: lanza error si está vacío, suma a las salidas y notifica al LED
    public void salirCoche() {
        if (this.cochesDentro <= 0) {
            throw new IllegalStateException("Error: No hay coches dentro del garaje.");
        }
        this.cochesDentro--;
        this.cochesQueSalieron++;
        System.out.println("Con el coche que acaba de salir. Coches dentro: " + this.cochesDentro + " | Total salidos: " + this.cochesQueSalieron);

        if (this.led != null) {
            this.led.actualizarColor(this.cochesDentro);
        }
    }

    // Consulta el total histórico de coches que han salido
    public int getCochesQueSalieron() {
        return this.cochesQueSalieron;
    }

    // Consulta el objeto LED conectado
    public SemaforoLed getLed() {
        return this.led;
    }

    
    // MÉTODO MAIN: Simulación y pruebas de aforo y excepciones
    public static void main(String[] args) {
        garaje miGaraje = new garaje();
        SemaforoLed miLed = new SemaforoLed();
        miGaraje.setLed(miLed);

        // Prueba 1: Intentamos entrar 55 coches para forzar el límite de 50
        for (int inicioContador = 0; inicioContador < 55; inicioContador++) {
            try {
                miGaraje.entrarCoche();
            } catch (IllegalStateException e) {
                System.out.println(e.getMessage());
            }
        }

        // Prueba 2: Salida de 10 coches y actualización de estado
        for (int salidaContador = 0; salidaContador < 10; salidaContador++) {
            try {
                miGaraje.salirCoche();
            } catch (IllegalStateException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
