package CursoCompleto250.segundaSemana.practicas.CuartoDia.ProyectoModular;

/**
 * 🏍️ SUBCLASE: Motocicletas
 * Especialización de Vehiculos para motos con 15 plazas estándar.
 */
public class Motocicletas extends Vehiculos {

    // Constructor con límite personalizado
    public Motocicletas(int limite) {
        super("Motocicleta", limite); // 'super' le pasa "Motocicleta" y las plazas al padre
    }

    // Constructor por defecto: encadena fijando 15 plazas
    public Motocicletas() {
        this(15);
    }
}
