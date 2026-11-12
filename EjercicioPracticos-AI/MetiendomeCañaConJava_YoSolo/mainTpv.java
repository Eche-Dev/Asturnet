package MetiendomeCañaConJava_YoSolo;

import java.util.Scanner;

public class mainTpv {

    public static void main(String[] args) {

        // Array polimórfico
        tpv[] catalogo = {
                new tpv("A01", "Teclado USB", 20.0, 10),
                new ArticuloOferta("O02", "Auriculares Bluetooth", 40.0, 5, 20.0), // 20% descuento
                new ArticuloPerecedero("P03", "Yogures Artesanales", 2.50, 8, 2) // 2 días para caducar -> liquidación
                                                                                 // 40%
        };

        // bucle for-each asi lo recorremos el array facil
        for (tpv articulo : catalogo) {
            System.out.println(articulo);
        }
        //Pedimos datos 
        Scanner PedirDatosUser = new Scanner(System.in);
        
        System.out.print("Introduce el código del artículo a comprar: ");
        String codigoBuscado = PedirDatosUser.nextLine();

        System.out.print("¿Cuántas unidades quieres?: ");
        int unidades = PedirDatosUser.nextInt();

        // Creamos la variable vacía (null)
        tpv articuloEncontrado = null;

        // Recorremos el catálogo buscando el código
        for (tpv articulo : catalogo) {
            if (articulo.codigo.equalsIgnoreCase(codigoBuscado)) {
                articuloEncontrado = articulo;
                break; // ¡Encontrado! Paramos el bucle
            }
        }

        // ¿Y si el cliente escribió un código que no existe (ej: "Z99")?
        if (articuloEncontrado == null) {
            System.out.println("No existe ningún artículo con el código: " + codigoBuscado);
        } else {
            // Si sí existe, ¡aquí metes tu try - catch que ya tienes escrito!
            try {
                double totalCobrado = articuloEncontrado.vender(unidades);
                System.out.printf("¡Venta realizada con éxito! Total a pagar: %.2f EUR%n", totalCobrado);
            } catch (IllegalArgumentException e) {
                System.out.println("Error en la venta: " + e.getMessage());
            }
        }

    }

}
