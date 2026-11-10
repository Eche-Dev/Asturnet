package CursoCompleto250.TerceraSemana.Lunes09.GarajeModularizado;

/**
 * Subclase Coche:
 * Hereda de Vehiculo y representa un automóvil.
 */
public class Coche extends Vehiculo {

    // Constructor con matrícula y modelo
    public Coche(String matricula, String modelo) {
        super(matricula, modelo); // Pasa los datos al constructor del padre (Vehiculo)
    }

    // Constructor solo con matrícula
    public Coche(String matricula) {
        super(matricula);
    }

    // Implementa el tipo de vehículo
    @Override
    public String getTipo() {
        return "Coche";
    }
}
