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
        String m = Vehiculo.normalizar(matricula);
        for (Coche c : coches)  { 
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
        registro.add(v.getEntrada().format(FORMATO) + "  ENTRADA  " + v );//Recoges la info en Entrada y la añades al registro
        return true;

    }

    // ====== Salida de vehículos ======
    public boolean salir(String matricula) {
        Vehiculo v = buscar(matricula)
        if (v == null) {
            System.out.println("La matrícula " + matricula + " no está en el garaje");
            return false;
        }
        if (v instanceof Coche) coches.remove(v);
        else motos.remove(v)

        //Saca foto al tiempo de salida y comprueba cuanto tiempo lleva dentro.
        v.setSalida(LocalDateTime.now());
        registro.add(v.getSalida().format(FORMATO) + " SALIDA " + v 
        + " (Estancia: " + tiempoEstancia() + ")");
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

    public void mostrarEstado() {
        System.out.printf("Coches: %2d/%d  LED %-7s |  Motos: %2d/%d  LED %s%n",
                coches.size(), MAX_COCHES, ledCoches(),
                motos.size(), MAX_MOTOS, ledMotos());
    }
// Coches: 2/50  LED Verde |  Motos:  1/15  LED Verde.
    public void mostrarRegistro() {
        System.out.println("\n----------- REGISTRO DE MOVIMIENTOS -----------");
        for (String linea : registro) {
            System.out.println(linea);
        }
        System.out.println("-----------------------------------------------");
    }

    // ====== Programa de prueba ======
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
// =====================================================================
// Superclase Vehiculo: lo común a todos los vehículos.
// Abstracta porque no existe un "vehículo" genérico, solo coches, motos...
// =====================================================================
abstract class Vehiculo {
    private final String matricula;
    private LocalDateTime entrada; 
    private LocalDateTime salida;

    public Vehiculo(String matricula) {
        this.matricula = normalizar(matricula);
    }

    public String getMatricula() {
        return matricula;
    }

    public LocalDateTime getEntrada() { 
        return entrada; 
    }
    public void setEntrada(LocalDateTime entrada) {
        this.entrada = entrada;
    }

    public LocalDateTime getSalida() { 
        return salida; 
    }
    public void setSalida(LocalDateTime salida) {
        this.salida = salida;
    }

    public String tiempoEstancia() {
        LocalDateTime fin = (salida != null) ? salida : LocalDateTime.now();
        Duration d = Duration.between(entrada, fin);
        return d.toHours() + "h" + d.toMinutesPart() + "m" + d.toSecondsPart() + "s";       
    }
/////////////////////////
    public static String normalizar(String matricula) {
        return matricula.replace(" ", "").replace("-", "").toUpperCase();
    }
/////////////////////////
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