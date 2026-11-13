package MetiendomeCañaConJava_YoSolo;

import java.util.Scanner;

public class mainTpv {

    public static void main(String[] args) {

        // =====================================================================
        // 1. POLIMORFISMO EN ACCIÓN:
        // Un array de tipo 'tpv[]' puede almacenar cualquier objeto que sea 'tpv'
        // o hijo de 'tpv' (ArticuloOferta, ArticuloPerecedero).
        // =====================================================================
        tpv[] catalogo = {
                new tpv("A01", "Teclado USB", 20.0, 10),
                new ArticuloOferta("O02", "Auriculares Bluetooth", 40.0, 5, 20.0), // 20% descuento
                new ArticuloPerecedero("P03", "Yogures Artesanales", 2.50, 8, 2)    // 2 días para caducar -> liquidación 40%
        };

        // Mostramos el catálogo inicial en pantalla (llama automáticamente al toString())
        System.out.println("========== CATÁLOGO INICIAL DE LA TIENDA ==========");
        for (tpv articulo : catalogo) {
            System.out.println(articulo);
        }

        // =====================================================================
        // 2. ENTRADA DE DATOS CON SCANNER:
        // =====================================================================
        Scanner PedirDatosUser = new Scanner(System.in);

        System.out.println("\n--- Venta Interactiva ---");
        System.out.print("Introduce el código del artículo a comprar: ");
        String codigoBuscado = PedirDatosUser.nextLine();

        System.out.print("¿Cuántas unidades quieres?: ");
        int unidades = PedirDatosUser.nextInt();

        // =====================================================================
        // 🧠 LECCIÓN APRENDIDA 6 (Búsqueda previa antes de operar):
        // No podemos llamar a 'articuloEncontrado.vender()' directamente porque
        // todavía no sabemos si el código introducido por el usuario existe en la tienda.
        // Primero buscamos en el array; si existe, lo guardamos en la variable.
        // =====================================================================
        tpv articuloEncontrado = null; // Inicializamos a null (vacío)

        for (tpv articulo : catalogo) {
            if (articulo.codigo.equalsIgnoreCase(codigoBuscado)) {
                articuloEncontrado = articulo;
                break; // ¡Encontrado! Salimos del bucle para no dar vueltas innecesarias
            }
        }

        // =====================================================================
        // 🧠 LECCIÓN APRENDIDA 7 (El bloque try - catch y la variable 'e'):
        // - 'throw new IllegalArgumentException(...)': en la clase tpv LANZA el error.
        // - 'catch (IllegalArgumentException e)': es el receptor que ATRAPA el error.
        // - 'e': es la variable que guarda ese objeto de error atrapado.
        // - 'e.getMessage()': lee la nota de texto exacta que se escribió dentro del throw.
        // =====================================================================
        if (articuloEncontrado == null) {
            System.out.println("No existe ningún artículo con el código: " + codigoBuscado);
        } else {
            try {
                double totalCobrado = articuloEncontrado.vender(unidades);
                System.out.printf("¡Venta realizada con éxito! Total a pagar: %.2f EUR%n", totalCobrado);
            } catch (IllegalArgumentException e) {
                // Si salta el throw de unidades <= 0 o de stock insuficiente, se ejecuta esto:
                System.out.println("Error en la venta: " + e.getMessage());
            }
        }

        // =====================================================================
        // 3. COMPROBACIÓN FINAL DE INVENTARIO:
        // Volvemos a listar el catálogo para verificar que si se vendió con éxito
        // el stock ha disminuido, y si dio error, el stock quedó intacto.
        // =====================================================================
        System.out.println("\n========== ESTADO FINAL DEL CATÁLOGO ==========");
        for (tpv articulo : catalogo) {
            System.out.println(articulo);
        }

        PedirDatosUser.close(); // Cerramos el Scanner al terminar
    }
}
