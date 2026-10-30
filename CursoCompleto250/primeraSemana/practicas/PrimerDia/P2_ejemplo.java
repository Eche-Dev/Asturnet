package CursoCompleto250.primeraSemana.practicas.PrimerDia;

// IMPORTACIÓN: Importamos la clase Scanner para poder leer datos por teclado
import java.util.Scanner;

// CLASE: molde principal del programa P2_ejemplo
public class P2_ejemplo {

    // METODO main: punto de inicio del programa
    public static void main(String[] args) {
        // OBJETO SCANNER: crea la herramienta para capturar lo que el usuario escribe
        Scanner scanner = new Scanner(System.in);  
        
        // ENTRADA DE DATOS: Lectura de texto con nextLine()
        System.out.print("Ingrese su nombre: ");
        String nombre = scanner.nextLine();

        // ENTRADA DE DATOS: Lectura de entero con nextInt()
        System.out.print("Ingrese su edad: ");
        int edad = scanner.nextInt();   

        // LIMPIEZA DE BUFFER: scanner.nextInt() deja el salto de línea (\n) pendiente en memoria.
        // Con nextLine() consumimos ese salto para que no se salte la siguiente pregunta.
        scanner.nextLine(); 

        // ENTRADA DE DATOS: Ahora sí espera a que el usuario introduzca su ciudad
        System.out.print("Ingrese su ciudad: ");
        String ciudad = scanner.nextLine(); 

        // SALIDA POR CONSOLA: Muestra los datos recopilados
        System.out.println("Hola, " + nombre + "!");
        System.out.println("Tienes " + edad + " años.");
        System.out.println("Vives en " + ciudad + ".");

        // CIERRE DEL OBJETO: Libera los recursos del sistema asociados al Scanner
        scanner.close();
    }
}
