package parte2reto.parteMODULAR;

//SEGUNDA CLASE HIJA
public class ConsultaEspecialista extends Consulta {
    // ATRIBUTOS PROPIOS
    protected String especialista;
    protected double ExtraEsp = 0.20;

    // CONSTRUCTOR DE LA HIJA
    public ConsultaEspecialista(int codigo, String paciente, double precioBase, String especialista) {


         // INVOCACION CON SUPER DE LOS ATRIBUTOS DEL PADRE.
        super(codigo, paciente, precioBase);

        // VALIDAMOS QUE NO SEA NULL Y NO SEA BLANCO EL INPUT
        if (especialista == null || especialista.isBlank()) {
            throw new IllegalArgumentException("Paciente no a solicitado ningun especialista.");
        }
        
        // AQUI GUARDAMOS PERO YA VALIDADOS
        this.especialista = especialista;

    }

    // METODO DE SOBRE ESCRITURA
    @Override
    public double calcularCoste() {
        //AQUI CALCULAMOS 
        double precioEspecialistaFinal = precioBase + (precioBase * ExtraEsp);
        //AQUI LO MANDAMOS AL PADRE
        return precioEspecialistaFinal;
    }

}
