import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Garaje:
 * Gestiona la entrada, salida, búsqueda y estado LED de coches y motos.
 */
public class Garaje {

    // Colores posibles para los semáforos LED
    public enum Led { VERDE, NARANJA, ROJO }

    // Plazas máximas permitidas
    private static final int MAX_COCHES = 50;
    private static final int MAX_MOTOS = 15;

    // Formato de fecha y hora para el registro
    static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    // Colecciones para almacenar coches, motos y las notas de entrada
    private final List<Coche> coches = new ArrayList<>();
    private final List<Moto> motos = new ArrayList<>();
    private final List<String> registro = new ArrayList<>();

    // Comprueba que la matrícula tenga el formato español oficial (4 dígitos y 3 consonantes válidas)
    public static boolean matriculaValida(String matricula) {
        return matricula.matches("\\d{4}[BCDFGHJKLMNPRSTVWXYZ]{3}");
    }

    // ====== Búsqueda de vehículos ======
    // Busca si una matrícula ya se encuentra dentro del garaje (en coches o motos)
    public Vehiculo buscar(String matricula) {
        // Buscamos primero en la lista de coches
        for (Coche c : coches) {
            if (c.getMatricula().equals(matricula)) {
                return c; // Devuelve el coche si coincide
            }
        }
        // Si no está en coches, buscamos en la lista de motos
        for (Moto m : motos) {
            if (m.getMatricula().equals(matricula)) {
                return m; // Devuelve la moto si coincide
            }
        }
        // Si no se encuentra en ninguna lista, devuelve null
        return null;
    }

    // Devuelve true si el vehículo ya está dentro
    public boolean estaDentro(String matricula) {
        return buscar(matricula) != null;
    }

    // ====== Entrada de vehículos ======
    public boolean entrar(Vehiculo v) {

        // 1. Comprobar que la matrícula tenga formato válido
        if (!matriculaValida(v.getMatricula())) {
            System.out.println("Matrícula no válida: " + v.getMatricula());
            return false;
        }

        // 2. Comprobar que no esté ya dentro
        if (estaDentro(v.getMatricula())) {
            System.out.println("La matrícula " + v.getMatricula() + " ya está dentro del garaje");
            return false;
        }

        // 3. Comprobar plazas y añadir a la lista correspondiente según el tipo
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

        // 4. Guardar fecha/hora de entrada en el vehículo y añadir al historial de texto
        v.setEntrada(LocalDateTime.now());
        registro.add(v.getEntrada().format(FORMATO) + "  ENTRADA  " + v);
        return true;
    }

    // ====== Salida de vehículos ======
    public boolean salir(Vehiculo v) {
        if (v instanceof Coche) return coches.remove(v);
        if (v instanceof Moto)  return motos.remove(v);
        return false;
    }

    // ====== LED de coches ======
    // Verde: menos de 25 · Rojo: 50 (lleno) · Naranja: entre 25 y 49
    public Led ledCoches() {
        int n = coches.size();
        if (n < 25) return Led.VERDE;
        if (n == MAX_COCHES) return Led.ROJO;
        return Led.NARANJA;
    }

    // ====== LED de motos ======
    // Verde: mientras haya hueco (menos de 15) · Rojo: 15 (lleno)
    public Led ledMotos() {
        return motos.size() < MAX_MOTOS ? Led.VERDE : Led.ROJO;
    }

    // ====== Mostrar estado del garaje ======
    public void mostrarEstado() {
        System.out.printf("Coches: %2d/%d  LED %-7s |  Motos: %2d/%d  LED %s%n",
                coches.size(), MAX_COCHES, ledCoches(),
                motos.size(), MAX_MOTOS, ledMotos());
    }

    // ====== Programa de prueba ======
    public static void main(String[] args) {
        Garaje garaje = new Garaje();
        garaje.mostrarEstado();

        // Entran coches poco a poco hasta llenar el garaje y uno más
        for (int i = 1; i <= 51; i++) {
            garaje.entrar(new Coche(String.format("%04d-ABC", i), "Seat Ibiza"));
            if (i == 24 || i == 25 || i == 49 || i == 50) {
                garaje.mostrarEstado();
            }
        }

        // Entran motos hasta llenar y una más
        for (int i = 1; i <= 16; i++) {
            garaje.entrar(new Moto(String.format("%04d-XYZ", i), "Yamaha MT-07"));
            if (i == 14 || i == 15) {
                garaje.mostrarEstado();
            }
        }

        // Sale un coche y una moto
        if (!garaje.coches.isEmpty()) garaje.salir(garaje.coches.get(0));
        if (!garaje.motos.isEmpty())  garaje.salir(garaje.motos.get(0));
        System.out.println("\nSale un coche y una moto:");
        garaje.mostrarEstado();
    }
}
