package parte1reto;

import java.util.Scanner;

public class estanteriaDelAlmacen {

    // Método main
    public static void main(String[] args) {

        // Array --> Creo 5 huecos.
        String[] estanteria = new String[5];

        // Metiendo 3 posiciones en el array
        estanteria[0] = "Caja Tornillos";
        estanteria[1] = "Martillo";
        estanteria[2] = null;
        estanteria[3] = "Destornillador";
        estanteria[4] = null;

        // AQUI IMPRIMIMOS EL TAMAÑO DEL ARRAY
        System.out.println("Tamaño del array: " + estanteria.length);

        // Paso 1 --> Uso de bucle for. Con length para recorrer el tamaño de estanteria
        // que serian 5.
        for (int i = 0; i < estanteria.length; i++) {
            // Aqui digo si i en estanteria es igual a null imprimir system.out.println.
            if (estanteria[i] == null) {
                System.out.println("HUECOS " + i + ": [Vacio]");
                // aqui digo que si en caso contrario imprimirme los huecos que si estan llenos
            } else {
                System.out.println("HUECOS " + i + ": Esta lleno con " + estanteria[i]);
            }
        }

        // Creado para pedir los datos.
        Scanner EstanteriaPublica = new Scanner(System.in);

        // Creacion de la variable opcion
        int opcion = 0;

        // Bucle while para las 4 opciones creadas.
        while (opcion != 4) {
            System.out.println(" 1. Ver estanteria");
            System.out.println(" 2. Guardar producto");
            System.out.println(" 3. Retirar producto");
            System.out.println(" 4. Salir");

            // Pido el el input al usuario de que opcion va a elegir
            System.out.println("Elige una opción: ");
            // Guardamos la opción elegida para usarla en el while
            opcion = EstanteriaPublica.nextInt();
            EstanteriaPublica.nextLine(); // Limpiamos el buffer

            switch (opcion) {
                case 1:
                    // Paso 1 --> Uso de bucle for. Con length para recorrer el tamaño de estanteria
                    // que serian 5.
                    for (int i = 0; i < estanteria.length; i++) {
                        // Si el hueco es null imprimimos [Vacio]
                        if (estanteria[i] == null) {
                            System.out.println("HUECOS " + i + ": [Vacio]");
                            // aqui digo que si en caso contrario imprimirme los huecos que si estan llenos
                        } else {
                            System.out.println("HUECOS " + i + ": Esta lleno con " + estanteria[i]);
                        }
                    }
                    break;
                case 2:
                    System.out.println("Dime que libro quieres guardar: ");
                    String agregacion = EstanteriaPublica.nextLine();
                    //variable falsa.
                    boolean guardado = false;

                    for (int i = 0; i < estanteria.length; i++) {
                        // Si el hueco está vacío, guardamos el producto
                        if (estanteria[i] == null) {
                            System.out.println("HUECOS " + i + ": " + agregacion);
                            //AGREGAMOS EL PRODUCTO ESCRITO
                        estanteria[i] = agregacion;
                        //aqui guarado true porque hay espacio
                        guardado = true;
                        //este break hace que meta un libro por libro
                        break;
                        } 
                    }
                    //condicional if para decir que esta llena la libreria. 
                    if (guardado == false){
                            System.out.println("Estanteria llena, debe retirar un libro para poner guarda otro1");
                        }

                    break;
                case 3:
                    System.out.println("Dime que libro quieres retirar: ");
                    String retirarLibro = EstanteriaPublica.nextLine();
                    //variable falsa.
                    boolean retirarL = false;

                    for (int i = 0; i < estanteria.length; i++) {
                        // Si no es null y coincide el nombre, lo retiramos
                        if (estanteria[i] != null && estanteria[i].equalsIgnoreCase(retirarLibro)) {
                            System.out.println("Se retiró " + retirarLibro + " del hueco " + i);
                            //Retiramos EL PRODUCTO ESCRITO
                        estanteria[i] = null;
                        // Marcamos true porque se retiró
                        retirarL = true;
                        // Salimos para retirar solo uno
                        break;
                        } 
                    }
                    // Si sigue en false, no se encontró
                    if (retirarL == false){
                            System.out.println("No retiro ningun libro, PORQUE NO SE ENCONTRO");
                        }

                    break;
                case 4:
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida");
            } 
        }
        EstanteriaPublica.close(); //cierre de scanner
    }
}
