package EjercicioPracticos.LasDelProfe;

// ============================================================================
// 📝 INFORME DE ANÁLISIS DE FALLOS - TAREA DEBUG 1
// ============================================================================
//
// 🎯 RESUMEN DE DETECCIONES:
//
// 1. [CAZADO POR TI 👏]:
//    - Falta de cierre "}" en el constructor Empleado (línea 17). Los métodos
//      calcularNomina() y toString() estaban atrapados dentro del constructor.
//
// 2. [DETECTADOS EN CONJUNTO 🔍]:
//    - Trampa de 'void class' en las 3 subclases (las clases no llevan 'void').
//    - Incongruencia en 'extends Empleados' (la clase padre es singular: 'Empleado').
//    - Falta de ';' en el return de EmpleadoFijo.
//    - Uso de ':' en lugar de ';' en el return de Directivo.
//    - Incongruencia de tipos en Directivo: atributo 'double bonus' vs parámetro 'int bonus'.
//    - 'import java.util.list;' erróneo (es 'List' y además no se utiliza).
//
// ============================================================================

public class practicaT3 {

    // 1. CLASE BASE
    static class Empleado {
        protected final String nombre;
        protected final double salarioBase;

        // CONSTRUCTOR
        Empleado(String nombre, double salarioBase) {
            this.nombre = nombre;
            this.salarioBase = salarioBase;
        } // <- FALLO 1 RESUELTO: Faltaba esta llave de cierre.

        // METODOS DE LA CLASE PADRE (ahora fuera del constructor, donde deben estar)
        double calcularNomina() {
            return salarioBase;
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + " " + nombre;
        }
    }

    // 2. SUBCLASE EMPLEADO FIJO
    // <- FALLO 2 Y 3 RESUELTOS: Se quitó 'void' y se corrigió 'extends Empleado' (sin 's')
    static class EmpleadoFijo extends Empleado {
        private final int trienios;

        EmpleadoFijo(String nombre, double salarioBase, int trienios) {
            super(nombre, salarioBase);
            this.trienios = trienios;
        }

        @Override
        double calcularNomina() {
            return salarioBase + 50 * trienios; // <- FALLO 4 RESUELTO: Faltaba el ';'
        }
    }

    // 3. SUBCLASE EMPLEADO TEMPORAL
    // <- FALLOS RESUELTOS: Sin 'void' y hereda de 'Empleado' (en singular)
    static class EmpleadoTemporal extends Empleado {
        private final int mesesContrato;

        EmpleadoTemporal(String nombre, double salarioBase, int meses) {
            super(nombre, salarioBase);
            this.mesesContrato = meses;
        }

        @Override
        double calcularNomina() {
            return salarioBase;
        }
    }

    // 4. SUBCLASE DIRECTIVO
    // <- FALLOS RESUELTOS: Sin 'void' y hereda de 'Empleado' (en singular)
    static class Directivo extends Empleado {
        private final double bonus;

        // <- FALLO 5 RESUELTO: Parámetro cambiado a 'double bonus' para coincidir con el atributo
        Directivo(String nombre, double salarioBase, double bonus) {
            super(nombre, salarioBase);
            this.bonus = bonus;
        }

        @Override
        double calcularNomina() {
            return salarioBase + bonus; // <- FALLO 6 RESUELTO: Se cambió ':' por ';'
        }
    }

    // METODO main para verificar que ahora compila y funciona perfectamente
    public static void main(String[] args) {
        Empleado[] equipo = {
            new EmpleadoFijo("Ana", 1200, 2),
            new EmpleadoTemporal("Luis", 900, 6),
            new Directivo("Carmen", 2000, 300)
        };

        for (Empleado e : equipo) {
            System.out.println(e + " -> Nomina: " + e.calcularNomina() + " EUR");
        }
    }
}
