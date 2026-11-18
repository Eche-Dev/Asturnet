package parte2reto.parteMODULAR;

import java.util.Scanner;

public class Clinica {

    //CLASE MAIN DESDE DONDE ARRANCARA.
    public static void main(String[] args) { 
        //array poliformico del main
        Consulta[] catalogo = {
                new Consulta(101, "Miguel Angel", 50.0),
                new ConsultaUrgencia(202, "Angel David", 100.0, true), // + 30 euros
                new ConsultaEspecialista(303, "Pepito Juanito", 180.0, "Traumatologia")    // especialista
        };
        //PARA VERLO MAS LINDO
        System.out.println("========== CATÁLOGO DE LA CLINICA ==========");
        //UN FOR PARA RECORRER EL CATALAGO
        for (Consulta paciente : catalogo) {
            System.out.println(paciente);
        }

        //INVOCAMOS A SCANNER
        Scanner PedirDatos = new Scanner(System.in);
        //aqui abro el try catch
        try {

        //PARA VERLO LINDO
        System.out.println("\n--- Cita interactiva ---");
        //PRIMER INPUT
        System.out.print("IntroduZca su codigo para poder ser atendido: ");
        int codigoBuscado = PedirDatos.nextInt();

        PedirDatos.nextLine(); //limpiamos el buffer

        //SEGUNDO INPUT
        System.out.print("Dime tu nombre por favor: ");
        String paciente = PedirDatos.nextLine();

        //Marcamos la consluta como null y la damos el nombre a la variable de citaEncontrada
        Consulta citaEncontrada = null;

        //recoremos catalago para ver si hay cita 
        for (Consulta cita : catalogo) {
            //si el codigo de la cita es igual al de codigo buscado tiene cita
            if (cita.codigo == codigoBuscado) {
                citaEncontrada = cita;
                break; // ¡Encontrado! Salimos del bucle para no dar vueltas innecesarias
            }
        }
        //SI LA CITA ES NULLA LANZAMOS ERROR PERO SI NO, LANZAMOS LA CITA MAS EL COSTO.
        if (citaEncontrada == null){
            throw new IllegalArgumentException("Codigo no encontrado amigo");
        }else{
            System.out.println("Cita encontrada esto es lo que debes pagar: " + citaEncontrada.calcularCoste());
        }
        //AQUI ESTAMOS EN ESCUCHA DE LOS ERRORES DE THROW DE LOS OTROS ARCHIVO PARA ATRAPARTLOS Y MOSTRARLO
        //USO Exception e POR SI ALGUIEN ESCRIBE ALGO MAL EL CODDIGO NO EXPLOTA 
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            //SI O SI SE IMPLIMIRA ESTO Y TERMINARA EL SCANNER
        } finally {
            PedirDatos.close();
        }
    }
    
}
