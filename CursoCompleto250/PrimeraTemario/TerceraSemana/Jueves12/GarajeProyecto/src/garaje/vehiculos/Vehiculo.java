package garaje.vehiculos;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Superclase Vehiculo: lo común a todos los vehículos.
 * Abstracta porque no existe un "vehículo" genérico, solo coches, motos...
 */
public abstract class Vehiculo {
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
        return d.toHours() + "h " + d.toMinutesPart() + "m " + d.toSecondsPart() + "s";
    }

    // Quita espacios y guiones y pasa a mayúsculas: "1234-bcd" -> "1234BCD"
    public static String normalizar(String matricula) {
        return matricula.replace(" ", "").replace("-", "").toUpperCase();
    }

    public abstract String getTipo();

    @Override
    public String toString() {
        return getTipo() + " " + matricula;
    }
}
