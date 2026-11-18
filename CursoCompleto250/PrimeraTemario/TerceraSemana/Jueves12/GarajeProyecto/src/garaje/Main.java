package garaje;

import garaje.vehiculos.Coche;
import garaje.vehiculos.Moto;

/**
 * Punto de arranque del programa: solo contiene el main de prueba.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        Garaje garaje = new Garaje();

        // Entradas correctas (la matrícula se limpia sola: minúsculas, guiones y espacios)
        garaje.entrar(new Coche("1234BCD"));
        garaje.entrar(new Coche("5678-fgh"));
        garaje.entrar(new Moto("4321 KLM"));

        // Entradas rechazadas
        garaje.entrar(new Coche("1234BCD"));   // ya está dentro
        garaje.entrar(new Coche("12ABC"));     // formato incorrecto
        garaje.entrar(new Coche("1234ABC"));   // lleva vocal (A), no es válida

        // Consultar si una matrícula está dentro
        System.out.println("¿Está dentro 5678FGH? " + garaje.estaDentro("5678FGH"));
        System.out.println("¿Está dentro 9999XYZ? " + garaje.estaDentro("9999XYZ"));
        garaje.mostrarEstado();

        // Esperamos 3 segundos para que se note el tiempo de estancia
        Thread.sleep(3000);

        garaje.salir("1234BCD");
        garaje.salir("4321KLM");
        garaje.salir("9999XYZ");               // no está dentro
        garaje.mostrarEstado();

        garaje.mostrarRegistro();
    }
}
