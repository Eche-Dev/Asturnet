package CursoCompleto250.segundaSemana.practicas.CuartoDia.ProyectoModular;

/**
 * 🚙 SUBCLASE: Coches
 * Especialización de Vehiculos para automóviles con 50 plazas estándar.
 */
public class Coches extends Vehiculos {

    // Constructor con límite personalizado
    public Coches(int limite) {
        super("Coche", limite); // 'super' le pasa la etiqueta "Coche" y las plazas al padre
    }

    // Constructor por defecto: encadena fijando 50 plazas
    public Coches() {
        this(50);
    }
}
