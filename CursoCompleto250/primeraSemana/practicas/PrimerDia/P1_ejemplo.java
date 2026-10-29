package CursoCompleto250.primeraSemana.practicas.PrimerDia;

public class P1_ejemplo {
    public static void main(String[] args) {    
        String nombre = "Juan";
        double precio = 19.99;
        int cantidad = 5;
        double total = precio * cantidad;

        System.out.println("Cliente: " + nombre);
        System.out.printf("Total a pagar: $%.2f%n", total);
    }
}


