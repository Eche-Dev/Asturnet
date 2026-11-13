package garaje.vehiculos;

/**
 * Subclase Coche: hereda todo de Vehiculo y solo define su tipo.
 */
public class Coche extends Vehiculo {

    public Coche(String matricula) {
        super(matricula);
    }

    @Override
    public String getTipo() {
        return "Coche";
    }
}
