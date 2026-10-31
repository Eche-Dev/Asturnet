package EjercicioPracticos.LoQueMePusoLa_IA.DesafiosDeLa_IA;

import java.util.Scanner;

public class Ejercicio_Facil {

    public static void main(String[] args) {
        // LÍNEAS DE FORMATO VISUAL
        System.out.println("===========================================");
        System.out.println("            UPERMERCADO ASTURNET           ");
        System.out.println("===========================================");

        Scanner supermercado = new Scanner(System.in);

        // Pedimos los datos y saltamos lineas.
        System.out.print("Ingrese nombre del cliente: ");
        String cliente = supermercado.nextLine();

        System.out.print("Ingrese nombre del producto: ");
        String producto = supermercado.nextLine();

        System.out.print("Ingrese precio por unidad: ");
        double precio = supermercado.nextDouble();

        System.out.print("Ingrese cantidad: ");
        int cantidad = supermercado.nextInt();
    
        supermercado.nextLine(); // AQUÍ FALTA LIMPIO EL BUFFER, PORQUE SI NO, NO LE PIDES LA LINEAS SIGUIENTE AL USUARIO.
        //NOTA: PARA NO LIMPIAR BUFFER ES MEJOR PONERLOS EN ORDEEN PRIMERO LOS "STR" LUEGO LOS "DOUBLE" lUEGO LOS "INT".
        
        System.out.print("Ingrese ciudad de entrega: ");
        String ciudad = supermercado.nextLine();

        // 1. Paso a calcular la compra
        double subTotal = precio * cantidad;
        double iva = subTotal * 0.21;
        double descuento = 0;

        // 2. Compruebas si supera 50 euros
        if (subTotal > 50) {
            descuento = subTotal * 0.05; // 5% de descuento
        } else {
            descuento = 0.0; // Sin descuento
        }
        // 3. Ahora sí puedes usarla para calcular el total
        double totalApagar = subTotal + iva - descuento;

        // LÍNEAS DE FORMATO VISUAL IMPRESION usando print
        System.out.println("===============FACTURA SIMPLIFICADA===============");
        System.out.println("Cliente: " + cliente + " | " + "Destino: " + ciudad);
        System.out.println("Articulo: " + producto + " (" + cantidad + " usd x " + precio + " EUR" + ")" );
        System.out.println("--------------------------------------------------");
        System.out.printf("%-22s %8.2f EUR%n", "Subtotal:", subTotal);
        System.out.printf("%-22s %8.2f EUR%n", "IVA (21%):", iva);
        System.out.printf("%-22s %8.2f EUR%n", "Descuento aplicado:", descuento);
        System.out.println("--------------------------------------------------");
        System.out.printf("%-22s %8.2f EUR%n", "Total a pagar:", totalApagar);
        System.out.println("==================================================");

        supermercado.close();
    }

}
