package parte2reto.parteMODULAR;

//PRIMERA CLASE HIJA
public class ConsultaUrgencia extends Consulta {
    // ATRIBUTOS PROPIOS
    protected boolean nocturna = false;
    public double precioNoc = 30;

    // CONSTRUCTOR DE LA CLASE HIJA
    public ConsultaUrgencia(int codigo, String paciente, double precioBase, boolean nocturna) {
        // INVOCACION CON SUPER DE LOS ATRIBUTOS DEL PADRE.
        super(codigo, paciente, precioBase);
        this.nocturna = nocturna;
    }

    // Metodo de sobre escritura override para el metodo del padre calcularCoste..
    @Override
    public double calcularCoste() {
        // validamos aqui dentro si es true, viene por defecto false.
        if (nocturna == true) {
            //Subimos 30 euros mas.
            return precioBase + precioNoc;
        }
        //Si no retornamos el precio  normal
        return precioBase;
    }
}
