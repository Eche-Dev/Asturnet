package CursoCompleto250.primeraSemana.practicas.PrimerDia;
import java.util.Scanner;

public class P2_ejemplo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);  
        
        System.out.print("Ingrese su nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese su edad: ");
        int edad = scanner.nextInt();   

        System.out.print("Ingrese su ciudad: ");
        scanner.nextLine(); // Limpiar el buffer
        String ciudad = scanner.nextLine(); 

        System.out.println("Hola, " + nombre + "!");
        System.out.println("Tienes " + edad + " años.");
        System.out.println("Vives en " + ciudad + ".");

        scanner.close();
    }
}
