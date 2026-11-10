package CursoCompleto250.TerceraSemana.Lunes09.GarajeModularizado;

import java.time.LocalDateTime;

/**
 * Superclase abstracta Vehiculo:
 * Define todo lo común a cualquier vehículo (matrícula, modelo y fecha de entrada).
 * Esto estaba en archivo entero entre la linea 142 y 166.
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

    //Getter 
    public String getMatricula() {
        return matricula;
    }
    public String getModelo() {
        return modelo;
    }

    //Setter
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    //Getter - Se agrego nuevo
    public LocalDateTime getEntrada() {
        return entrada;
    }

    //Setter - Se agrego nuevo
    public void setEntrada(LocalDateTime entrada) {
        this.entrada = entrada;
    }

    // Método abstracto: obliga a que cada hijo diga qué tipo es ("Coche", "Moto"...)
    public abstract String getTipo();

    // Sobre escritura
    @Override
    public String toString() {
        return getTipo() + " " + matricula + " (" + modelo + ")";
    }
}
