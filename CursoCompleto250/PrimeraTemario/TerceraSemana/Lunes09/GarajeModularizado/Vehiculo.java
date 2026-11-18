import java.time.LocalDateTime;

/**
 * Superclase abstracta Vehiculo:
 * Define todo lo común a cualquier vehículo (matrícula, modelo y fecha de entrada).
 */
public abstract class Vehiculo {

    // Atributos comunes
    private final String matricula;
    private String modelo;
    private LocalDateTime entrada;

    // Constructor completo: recibe matrícula y modelo
    public Vehiculo(String matricula, String modelo) {
        this.matricula = matricula;
        this.modelo = modelo;
    }

    // Constructor secundario: por si solo se proporciona la matrícula
    public Vehiculo(String matricula) {
        this(matricula, "Estándar");
    }

    // ====== Getters y Setters ======
    public String getMatricula() {
        return matricula;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public LocalDateTime getEntrada() {
        return entrada;
    }

    public void setEntrada(LocalDateTime entrada) {
        this.entrada = entrada;
    }

    // Método abstracto: obliga a que cada hijo diga qué tipo es ("Coche", "Moto"...)
    public abstract String getTipo();

    // Representación en texto del vehículo
    @Override
    public String toString() {
        return getTipo() + " " + matricula + " (" + modelo + ")";
    }
}
