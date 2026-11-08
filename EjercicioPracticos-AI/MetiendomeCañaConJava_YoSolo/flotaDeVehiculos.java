package EjercicioPracticosAI.MetiendomeCañaConJava_YoSolo;

//Clase padre
public class flotaDeVehiculos {

    //atributos principales del padre
    protected String marcaDeCoche; 
    protected String matricula;
    protected double costeFijoBase;

    //Contructor padre - Donde asignamos los parametros con valores para cada uno de nuestro atributos
    public flotaDeVehiculos(String marcaDeCoche, String matricula, double costeFijoBase){
        this.marcaDeCoche = marcaDeCoche;
        this.matricula = matricula;
        this.costeFijoBase = costeFijoBase;
    //Validaciones con el condicional IF.
        if (marcaDeCoche == null || marcaDeCoche.isBlank()) {
            throw new IllegalArgumentException("Cada coche debe contener su marca");  
        }
        if (matricula == null || matricula.isBlank()) {
            throw new IllegalArgumentException("Cada coche debe contener su matricula");  
        }
        if (costeFijoBase <= 0 ) {
            throw new IllegalArgumentException("Coste fijo incorrecto");  
        }
    }   

    //Metodos del padre - uno esta vacio y el otro sobre escribe 
    double calcularMantenimiento(){
        return costeFijoBase;
    }
    
    @Override 
        public String toString(){
            return "Marca: " + marcaDeCoche + " | Matricula: " + matricula + " | Base: " + costeFijoBase + " EUR";
        }



    //Esto es lo ultimo que eh creado al final del ejercicio
    public static void main (String[] args){
        double totalCalculoFlota = 0;
        
        //Array con tipos base (Guardamos camiones y furgonetas juntos gracias al Polimorfismo)
        flotaDeVehiculos[] flota = {
            new camionPesado("Volvo FH", "1234-BBB", 400.0, 4),
            new furgonetaReparto("Renault Master", "5678-CCC", 150.0, 4000.0),
            new camionPesado("Scania R", "9876-DDD", 300.0, 3),
            new furgonetaReparto("Honda civic", "4321-CCC", 50.0, 8000.0),
        };
    
        // Cabecera visual del reporte: se imprime 1 sola vez antes de que empiece a dar vueltas el bucle
        System.out.println("============= REPORTE MENSUAL DE FLOTA =============");

        // Bucle for-Each: Da tantas vueltas como vehículos haya en la lista (en este caso 4 vueltas)
        for(flotaDeVehiculos eVehiculos : flota){
            // En cada vuelta, 'eVehiculos' es el vehículo de turno.
            // Con printf imprimimos su nombre de clase, matrícula, marca y llamamos a calcularMantenimiento()
            
            System.out.printf("%s [%s - %s] -> Mantenimiento: %.2f EUR%n",   //print en formato f. CADA % representa un atributo.
                    eVehiculos.getClass().getSimpleName(),     //getClass busca en memoria lo que esta creado con NEW y getSimpleName lo unico que hace es limpiar el nombre para mostrarlo.
                    eVehiculos.marcaDeCoche,                   //coge los dato de new - igual abajo
                    eVehiculos.matricula, 
            // poliformismos en accion: Java sabe por sí solo si debe cobrar ejes o kilómetros según el tipo de hijo
                    eVehiculos.calcularMantenimiento());

            // En cada vuelta vamos sumando el coste de ese vehículo a la bucha total
            totalCalculoFlota += eVehiculos.calcularMantenimiento();
        } // <--- Cierre del bucle, todo lo que esté debajo se ejecutará solo 1 vez al final
        
        // Pie del reporte: Mostramos la separación y el coste total acumulado de todos los vehículos
        System.out.println("----------------------------------------------------");
        System.out.printf("Coste total acumulado de mantenimiento: %.2f EUR%n", totalCalculoFlota);
        System.out.println("====================================================");
    }   
}

//Clase hija 1
class camionPesado extends flotaDeVehiculos{

    //atributos propios de la hija 1 
    private final int numeroDeEjes;
    private int costeDeCadaEje = 120;

    //Constructor de la hija 1
    public camionPesado(String marcaDeCoche, String matricula, double costeFijoBase, int numeroDeEjes){
        super(marcaDeCoche, matricula, costeFijoBase);        //Aqui y arriba al llamarlo hay que ponerlo en mismo orden como estan en el padre.
        this.numeroDeEjes = numeroDeEjes;
    //Comprobacion del constructor
        if (numeroDeEjes < 2) {
            throw new IllegalArgumentException("Numero de ejes incorrectos");
        }
    }

    //SobreEscritura - Va fuera del constructo angel(Recuerdalo)
    @Override 
        double calcularMantenimiento(){
            return costeFijoBase + (costeDeCadaEje * numeroDeEjes);
        }

}

//Clase hija 2
class furgonetaReparto extends flotaDeVehiculos{
    private final double kilometrosMes;
    private final double costeDeKM = 0.08;

    //Constructor
    public furgonetaReparto(String marcaDeCoche, String matricula, double costeFijoBase, double kilometrosMes){
        super(marcaDeCoche, matricula, costeFijoBase); 
        this.kilometrosMes = kilometrosMes;

        //Validacion de que no deber ser negativo 
        if (kilometrosMes < 0){
            throw new IllegalArgumentException("Los kilómetros no pueden ser negativos");
        }
    }

    //SobreEscritura 
    @Override 
        double calcularMantenimiento(){
            return costeFijoBase + (kilometrosMes * 0.08);
        }

}
