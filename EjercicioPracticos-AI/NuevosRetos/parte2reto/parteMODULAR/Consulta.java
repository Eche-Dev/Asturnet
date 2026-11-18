package parte2reto.parteMODULAR;

//Clase padre
public class Consulta {

    // ATRIBUTOS
    protected int codigo;
    protected String paciente;
    protected double precioBase;

    // Constructor
    public Consulta(int codigo, String paciente, double precioBase) {
        // VALIDAMOS PRIMERO ANTES DE GUARDARLOS

        // Validacion del codigo
        if (codigo < 0) {
            throw new IllegalArgumentException("El codigo del paciente no puede ser negativo");
        }

        // Validacion de paciente
        if (paciente == null || paciente.isBlank()) {
            throw new IllegalArgumentException("Paciente debe contener al menos una letra.");
        }

        // Valicion de precio base que debe ser positivo
        if (precioBase <= 0) {
            throw new IllegalArgumentException("El precio debe ser positivo.");
        }

        // AQUI GUARDAMOS PERO YA VALIDADOS
        this.codigo = codigo;
        this.paciente = paciente;
        this.precioBase = precioBase;

    }

    // METODOS

    // Creacion del metodo PUBLIC DOUBLE con la VARIABLE LLAMADA "calcularCoste" que retorna el precioBase.
    public double calcularCoste() {
        return precioBase;
    }

    // Sobre escritura con override y metodo de utilidad de toString
    @Override
    public String toString() {
        return "PACIENTE: " + paciente + " | " + "CODIGO U/P: " + codigo + " | " + "PRECIO DEL BASE DE LA CLINICA: " + precioBase;
    }

}
