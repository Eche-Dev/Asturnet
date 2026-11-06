package EjercicioPracticosAI.MetiendomeCañaConJav_YoSolo;
import java.util.Scanner;

public class FacturaSupermercado {
    
    public static void main(String[] args) {
        
        
        //Atributos que siempre seran contantes.   
        double Hacienda = 0.21;
        double Descuento = 0.05;

        // Println para verlo mas lindo en la terminar.
        System.out.println("==========================================");
        System.out.println("           SUPERMERCADO ASTURNET          ");
        System.out.println("==========================================");
        
        // Pedi los datos al usuario = uso "print" para solicitar los datos en la misma linea.
        Scanner FacScanner = new Scanner(System.in);                                       //FacScanner(Yo Le di ese nombre) se pone a escuchar con System.in para detectar lo escribimos.
        
        System.out.print("Ingrese el nombre del cliente: ");
        String nombreCliente = FacScanner.nextLine();

        System.out.print("Ingrese nombre del producto: ");                                                 
        String nombreProducto = FacScanner.nextLine();                                      //Uso de Nextline/Int/Double para saltar a la siguiente pregunta.

        System.out.print("Ingrese precio por unidad: ");
        double precioPorUnidad = FacScanner.nextDouble();

        System.out.print("Ingrese cantidad: ");
        int cantidaDeUnidades = FacScanner.nextInt();
        FacScanner.nextLine();                                                              //Con esto limpio el buffer, porque si no lo hago, no le pide al usuario que ingrese ciudad. 

        System.out.print("Ingrese ciudad de entrega: ");
        String ciudad = FacScanner.nextLine(); 

        System.out.println("\n========== FACTURA SIMPLIFICADA ==========");                // la \n hace separalo en el cli para que se vea junto como el modelo pide el ejemplo
        System.out.println("Cliente: " + nombreCliente + " | " + "Destino: " + ciudad );
        System.out.println("Articulo: " + nombreProducto + " (" + cantidaDeUnidades + " uds x " + precioPorUnidad + " EUR)");
        System.out.println("------------------------------------------");
        
        // 2. Hacer los calculos matematicos

        double subTotal = precioPorUnidad * cantidaDeUnidades;
        double ivaCalculo = subTotal * Hacienda;

        double DescuentoPorEmpleado = 0.0;                                                // Arranca en 0
        if (subTotal > 50.0) {
            DescuentoPorEmpleado = subTotal * Descuento;                                  // Solo aplica si supera los 50€
            }

        double totalApagar = subTotal + ivaCalculo - DescuentoPorEmpleado;
        
        // 3. Imprimir el ticket con printf
        System.out.printf("%-20s %10.2f EUR%n", "Subtotal:", subTotal);                          // AHORA EL( %-20s ) hace esto = (% hace referencia al string que dice "Subtotal:")
        System.out.printf("%-20s %10.2f EUR%n", "IVA (21%):", ivaCalculo);                       // EL (-20s ) hace que "Subtotal" se imprima pegado a la izquierda. Si te preguntas porque -20 espacio es porque esta columna "Descuento aplicado:" tiene 19 letras.
        System.out.printf("%-20s %10.2f EUR%n", "Descuento aplicado:", DescuentoPorEmpleado);    // AHORA El (%10.2f) misma logica. esto (% hace referencia a la (VARIBALE: subTotal)). 
                                                                                                 // EL (10 hace que subTotal salga a la derecha con 10 espacios y podemos cubrir compras de 9.999.999,99 )
        System.out.println("------------------------------------------");                        // EL (.2f) es para solo tomar dos numero despues de la coma.
        System.out.printf("%-20s %10.2f EUR%n", "TOTAL A PAGAR:", totalApagar);                  // AHORA EL (EUR%n) = EL "EUR" es un string sin mas. y (\n es un salto de linea)
        System.out.println("==========================================");

        FacScanner.close();                                                                       //Importante cerrar el scanner
    }
    

}
