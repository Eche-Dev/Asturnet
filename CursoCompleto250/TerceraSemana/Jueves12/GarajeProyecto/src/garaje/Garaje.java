package garaje;

import garaje.vehiculos.Coche;
import garaje.vehiculos.Moto;
import garaje.vehiculos.Vehiculo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Garaje con 50 plazas de coche y 15 de moto, con un LED de estado para cada tipo,
 * control de matrículas y registro de entradas y salidas.
 */
public class Garaje {

    private static final int MAX_COCHES = 50;
    private static final int MAX_MOTOS = 15;

    static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    private final List<Coche> coches = new ArrayList<>();
    private final List<Moto> motos = new ArrayList<>();
    private final List<String> registro = new ArrayList<>();

    public static boolean matriculaValida(String matricula) {
        return matricula.matches("\\d{4}[BCDFGHJKLMNPRSTVWXYZ]{3}");
    }

    public Vehiculo buscar(String matricula) {
        String m = Vehiculo.normalizar(matricula);
        for (Coche c : coches) {
            if (c.getMatricula().equals(m)) return c;
        }
        for (Moto mo : motos) {
            if (mo.getMatricula().equals(m)) return mo;
        }
        return null;
    }

    public boolean estaDentro(String matricula) {
        return buscar(matricula) != null;
    }

    // ====== Entrada de vehículos ======
    public boolean entrar(Vehiculo v) {

        //Comprobar matricula
        if (!matriculaValida(v.getMatricula())) {
            System.out.println("Matrícula no válida: " + v.getMatricula());
            return false;
        }
        //Comprobar que no esta dentro
        if (estaDentro(v.getMatricula())) {
            System.out.println("La matrícula " + v.getMatricula() + " ya está dentro del garaje");
            return false;
        }

        if (v instanceof Coche) {
            if (coches.size() >= MAX_COCHES) {
                System.out.println("Garaje lleno para coches: " + v + " no puede entrar");
                return false;
            }
            coches.add((Coche) v);
        } else if (v instanceof Moto) {
            if (motos.size() >= MAX_MOTOS) {
                System.out.println("Garaje lleno para motos: " + v + " no puede entrar");
                return false;
            }
            motos.add((Moto) v);
        }

        //Anotar la entrada del coche o moto
        //LocalDateTime.now() fecha y hora actuales del sistema
        v.setEntrada(LocalDateTime.now()); //Sacas foto a la fecha y la añades a Entrada
        registro.add(v.getEntrada().format(FORMATO) + "  ENTRADA  " + v); //Recoges la info en Entrada y la añades al registro
        return true;
    }

    // ====== Salida de vehículos ======
    public boolean salir(String matricula) {
        Vehiculo v = buscar(matricula);
        if (v == null) {
            System.out.println("La matrícula " + matricula + " no está en el garaje");
            return false;
        }
        if (v instanceof Coche) coches.remove(v);
        else motos.remove(v);

        //Saca foto al tiempo de salida y comprueba cuanto tiempo lleva dentro.
        v.setSalida(LocalDateTime.now());
        registro.add(v.getSalida().format(FORMATO) + "  SALIDA   " + v
                + "  (Estancia: " + v.tiempoEstancia() + ")");
        return true;
    }

    // ====== LED de coches ======
    // Verde: menos de 25 · Rojo: 50 (lleno) · Naranja: de 25 a 49
    public Led ledCoches() {
        int n = coches.size();
        if (n < 25) return Led.VERDE;
        if (n == MAX_COCHES) return Led.ROJO;
        return Led.NARANJA;
    }

    // ====== LED de motos ======
    // Verde: mientras no haya 15 · Rojo: 15 (lleno)
    public Led ledMotos() {
        return motos.size() < MAX_MOTOS ? Led.VERDE : Led.ROJO;
    }

    // Ejemplo de salida -> Coches:  2/50  LED VERDE   |  Motos:  1/15  LED VERDE
    public void mostrarEstado() {
        System.out.printf("Coches: %2d/%d  LED %-7s |  Motos: %2d/%d  LED %s%n",
                coches.size(), MAX_COCHES, ledCoches(),
                motos.size(), MAX_MOTOS, ledMotos());
    }

    public void mostrarRegistro() {
        System.out.println("\n----------- REGISTRO DE MOVIMIENTOS -----------");
        for (String linea : registro) {
            System.out.println(linea);
        }
        System.out.println("-----------------------------------------------");
    }
}
