package CursoCompleto250.segundaSemana.practicas.CuartoDia.ProyectoModular;

/**
 * 🚀 PUNTO DE ENTRADA: App (Main)
 * En los proyectos reales de empresa, la clase de arranque está separada de la lógica de negocio.
 * Su única responsabilidad es arrancar la aplicación.
 */
public class App {

    public static void main(String[] args) {
        // 1. Fabricamos el garaje modular en memoria RAM
        Garaje miGaraje = new Garaje();

        // 2. Iniciamos el menú interactivo para el usuario
        miGaraje.iniciarConsola();
    }
}
