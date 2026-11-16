import java.util.Scanner;

// =====================================================================
// RETO 1: Radar Meteorológico
// Manejo de arrays tradicionales de tamaño fijo, estadísticas y búsqueda
// =====================================================================
public class RadarMeteorologico {
    
    public static void main(String[] args) {
        
        // 1. Arrays puros (tamaño fijo de 7 elementos)
        double[] temperaturas = { 12.5, 18.0, 9.4, 22.1, 15.6, 8.0, 19.5 };
        String[] dias = { "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo" };

        // 2. Variables para acumular y calcular estadísticas
        // (Arrancamos el récord diciendo que el máximo y mínimo iniciales son el primer día: temperaturas[0])
        double suma = 0;
        double maximo = temperaturas[0];
        double minimo = temperaturas[0];
        String diaMaximo = dias[0];
        String diaMinimo = dias[0];
        int diasCalurosos = 0;

        // 3. Bucle para procesar todas las temperaturas
        // Usamos un 'for' con índice (i) para saber qué día corresponde a cada temperatura
        for (int i = 0; i < temperaturas.length; i++) {
            double temp = temperaturas[i];

            // Sumamos la temperatura actual a la suma total (para la media)
            suma += temp;

            // Comprobamos si bate el récord de calor
            if (temp > maximo) {
                maximo = temp;
                diaMaximo = dias[i]; // Guardamos el nombre del día
            }

            // Comprobamos si bate el récord de frío
            if (temp < minimo) {
                minimo = temp;
                diaMinimo = dias[i]; // Guardamos el nombre del día
            }

            // Contamos los días que superan los 15.0 ºC
            if (temp > 15.0) {
                diasCalurosos++;
            }
        }

        // 4. Calculamos la media después de que el bucle sumó toda la semana
        double media = suma / temperaturas.length;

        // 5. Mostramos el informe final por consola
        System.out.println("========================================");
        System.out.println("   📡 ESTADÍSTICAS DEL RADAR SEMANAL    ");
        System.out.println("========================================");
        System.out.printf("Temperatura media de la semana: %.2f ºC%n", media);
        System.out.println("Día más caluroso: " + diaMaximo + " con " + maximo + " ºC");
        System.out.println("Día más frío:     " + diaMinimo + " con " + minimo + " ºC");
        System.out.println("Días por encima de 15.0 ºC: " + diasCalurosos + " de " + temperaturas.length);
        System.out.println("========================================\n");

        // 6. Extra interactivo con Scanner: Búsqueda de una temperatura exacta
        Scanner datosUsuario = new Scanner(System.in);

        System.out.print("Introduce una temperatura a buscar (ej: 18,0): ");
        double tempBuscada = datosUsuario.nextDouble();

        boolean encontrada = false;
        String diaEncontrado = "";

        // Bucle de búsqueda: miramos si alguna coincide con la que busca el usuario
        for (int i = 0; i < temperaturas.length; i++) {
            if (temperaturas[i] == tempBuscada) {
                encontrada = true;
                diaEncontrado = dias[i];
                break; // Encontrada: salimos del bucle
            }
        }

        // Informamos del resultado de la búsqueda
        if (encontrada) {
            System.out.println("-> ¡Sí! La temperatura de " + tempBuscada + " ºC se registró el " + diaEncontrado + ".");
        } else {
            System.out.println("-> No, la temperatura de " + tempBuscada + " ºC no se registró ningún día de la semana.");
        }

        datosUsuario.close();
    }
}
