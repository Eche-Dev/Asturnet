package garaje.vehiculos;

/**
 * Subclase Moto: hereda todo de Vehiculo y solo define su tipo.
 */
public class Moto extends Vehiculo {

    public Moto(String matricula) {
        super(matricula);
    }

    @Override
    public String getTipo() {
        return "Moto";
    }
}
