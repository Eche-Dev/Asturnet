import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.DateTimeFormatter;
import java.time.Duration;


/**
 * Garaje con 50 plazas de coche y 15 de moto, con un LED de estado para cada tipo.
 * Compilar:  javac Garaje.java
 * Ejecutar:  java Garaje
 */
public class Garaje {

    // Colores posibles del LED
    enum Led { VERDE, NARANJA, ROJO }

    private static final int MAX_COCHES = 50;
    private static final int MAX_MOTOS = 15;

    static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    private final List<Coche> coches = new ArrayList<>();
    private final List<Moto> motos = new ArrayList<>();
    private final List<String> registro = new ArrayList<>();

    public static boolean matriculaValida(String matricula) {
        return matricula.matches("\\d{4}[BCDFGHJKLMNPRSTVWXYZ]{3}");
    }

    public Vehiculo buscar (String matricula) {
        //metodo que busca una matricula
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
        registro.add(getEntrada().format(FORMATO) + "  ENTRADA  " + v );//Recoges la info en Entrada y la añades al registro
        return true;

    }
    // He escrito hasta aqui ------------------------------------------

    // ====== Salida de vehículos ======
    public boolean salir(Vehiculo v) {
        if (v instanceof Coche) return coches.remove(v);
        if (v instanceof Moto)  return motos.remove(v);
        return false;
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

    public void mostrarEstado() {
        System.out.printf("Coches: %2d/%d  LED %-7s |  Motos: %2d/%d  LED %s%n",
                coches.size(), MAX_COCHES, ledCoches(),
                motos.size(), MAX_MOTOS, ledMotos());
    }

    // ====== Programa de prueba ======
    public static void main(String[] args) {
        Garaje garaje = new Garaje();
        // Tipodevariable nombredevariable = new Objeto() 
        garaje.mostrarEstado();

        // Entran coches poco a poco hasta llenar el garaje y uno más
        for (int i = 1; i <= 51; i++) {
            garaje.entrar(new Coche(String.format("%04d-ABC", i)));
            if (i == 24 || i == 25 || i == 49 || i == 50) garaje.mostrarEstado();
        }

        // Entran motos hasta llenar y una más
        for (int i = 1; i <= 16; i++) {
            garaje.entrar(new Moto(String.format("%04d-XYZ", i)));
            if (i == 14 || i == 15) garaje.mostrarEstado();
        }

        // Sale un coche y una moto
        garaje.salir(garaje.coches.get(0));
        garaje.salir(garaje.motos.get(0));
        System.out.println("Sale un coche y una moto:");
        garaje.mostrarEstado();
    }
}

// =====================================================================
// Superclase Vehiculo: lo común a todos los vehículos.
// Abstracta porque no existe un "vehículo" genérico, solo coches, motos...
// =====================================================================
abstract class Vehiculo {
    private final String matricula;

    public Vehiculo(String matricula) {
        this.matricula = matricula;
    }

    public String getMatricula() {
        return matricula;
    }

    public abstract String getTipo();

    @Override
    public String toString() {
        return getTipo() + " " + matricula;
    }
}

// =====================================================================
// Subclases
// =====================================================================
class Coche extends Vehiculo {
    public Coche(String matricula) {
        super(matricula);
    }

    @Override
    public String getTipo() {
        return "Coche";
    }
}

class Moto extends Vehiculo {
    public Moto(String matricula) {
        super(matricula);
    }

    @Override
    public String getTipo() {
        return "Moto";
    }
}