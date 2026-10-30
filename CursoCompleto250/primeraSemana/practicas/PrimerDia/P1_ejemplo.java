package CursoCompleto250.primeraSemana.practicas.PrimerDia;

// CLASE: molde principal del programa P1_ejemplo
public class P1_ejemplo {

    // METODO main: punto de inicio del programa
    public static void main(String[] args) {    
        // VARIABLES: datos primitivos y referencias
        String nombre = "Juan";      // Nombre del cliente (texto)
        double precio = 19.99;       // Precio por unidad (decimal)
        int cantidad = 5;            // Cantidad de productos (entero)

        // CÁLCULO: multiplicación de precio por cantidad
        double total = precio * cantidad;

        // SALIDA POR CONSOLA
        System.out.println("Cliente: " + nombre);

        // SALIDA CON FORMATO (printf): %.2f redondea a 2 decimales y %n da salto de línea
        System.out.printf("Total a pagar: $%.2f%n", total);
    }
}
