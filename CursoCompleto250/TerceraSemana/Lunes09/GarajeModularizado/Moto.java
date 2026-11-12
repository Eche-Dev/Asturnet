/**
 * Subclase Moto:
 * Hereda de Vehiculo y representa una motocicleta.
 */
public class Moto extends Vehiculo {

    // Constructor con matrícula y modelo
    public Moto(String matricula, String modelo) {
        super(matricula, modelo); // Pasa los datos al constructor del padre (Vehiculo)
    }

    // Constructor solo con matrícula
    public Moto(String matricula) {
        super(matricula);
    }

    // Implementa el tipo de vehículo
    @Override
    public String getTipo() {
        return "Moto";
    }
}
