package parte1reto;
//Se impoorta solito
import java.util.Scanner;


// Primer ejercicio del reto

// Clase principal 
public class RadarMeteorologico1 {

    public static void main(String[] args) {
        // Atributos: aqui pondre las variables y los array constante del ejercicio

        // Array 
        double[] temperaturas = { 12.5, 18.0, -1.0, 22.1, 15.6, 8.0, 39.5 };
        String[] diasSemana = { "LUNES", "MARTES", "MIERCOLES", "JUEVES", "VIERNES", "SABADO", "DOMINGO" };
        // Variables 
        double suma = 0;
        double maximo = temperaturas[0];
        double minimo = temperaturas[0];
        String diaMaximo = diasSemana[0];
        String diaMinimo = diasSemana[0];
        int diasCalurosos = 0;

        // Creamos el bucle para ver las temperaturas si es mayor si es menor y calculamos
        for (int i = 0; i < temperaturas.length; i++) {
            double temp = temperaturas[i];

            // Suma de las temperaturas
            suma += temp;

            // Revisamos la temperatura maxima
            if (temp > maximo) {
                maximo = temp;
                diaMaximo = diasSemana[i]; // Guardamos el nombre del día
            }

            // Revisamos la temperatura minimo
            if (temp < minimo) {
                minimo = temp;
                diaMinimo = diasSemana[i]; // Guardamos el nombre del día)
            }

            // Contamos los días que superan los 15.0 ºC 
            if (temp > 15.0) {
                diasCalurosos++;
            }
        }     //  <--------------------Cerramos el bucle

        // Calculamos la media después de que el bucle sumó toda la semana
        double media = suma / temperaturas.length;

        // Mostramos el informe final por consola los datos que nosotro ya tenemos por todos los calculos de arriba 
        
        System.out.println("========================================");
        System.out.println("      ESTADÍSTICAS DEL RADAR SEMANAL    ");
        System.out.println("========================================");
        System.out.printf("Temperatura media de la semana: %.2f ºC%n", media);  //Aqui usamos .2f para los decimales.
        System.out.println("Día más caluroso: " + diaMaximo + " con " + maximo + " ºC");
        System.out.println("Día más frío:     " + diaMinimo + " con " + minimo + " ºC");
        System.out.println("Días por encima de 15.0 ºC: " + diasCalurosos + " de " + temperaturas.length);
        System.out.println("========================================\n");



        // Ahora vamos a pedir los datos del usuario

        // Con scanner y le llamo A LA VARIABLE DatosUSER
        Scanner DatosUSER = new Scanner(System.in);

        // Linea que le saldra al usuario para que sepa que poner
        System.out.print("Introduce una temperatura a buscar (ej: 15,0): ");
        double tempPedida = DatosUSER.nextDouble();  //salto del buffer

        // Creamos una variable boolean con el nombre encontrado y la marcamos como false de primeras
        boolean encontrado = false; 
        // Variable diaEncontrado lo marcamos como un string vacio.
        String diaEncontrado = "";


        // Hago un bucle de busqueda de dia 
        for(int i = 0; i < temperaturas.length; i++){ 
            if (temperaturas[i] == tempPedida) {  //verificamos si temparatura es igual a la que el usuario metio
                encontrado = true;  // Si es igual marcamos true, recuerda que viene por defecto false.
                diaEncontrado = diasSemana[i];  //aqui asignamos a la variable diaEncontrado el dia de la semana
                break; // Si fue encontrado salimos del bucle.
            }
        }

        // Compruebo el resultado de la busqueda con un condicional if e Imprimios en consola
        if (encontrado) { 
            System.out.println("-> ¡Sí! La temperatura de " + tempPedida + " ºC se registró el " + diaEncontrado + ".");
        } else {
            System.out.println("-> No, la temperatura de " + tempPedida + " ºC no se registró ningún día de la semana.");
        }

        DatosUSER.close(); //  Cerramos el scanner. 
    }
}