// CLASE PRINCIPAL: Contenedora del programa y de las clases de Empleado
public class empleado {

    // CLASE BASE (PADRE): Molde general con las características comunes a todos los empleados
    static class Empleado {
        // ATRIBUTOS PROTECTED: Visibles para las subclases (hijas), final para que no se alteren tras asignarse
        protected final String nombre;
        protected final double salarioBase;

        // CONSTRUCTOR PADRE: Se ejecuta al instanciar e inicializa nombre y salario base
        Empleado(String nombre, double salarioBase) {
            this.nombre = nombre;
            this.salarioBase = salarioBase;
        }

        // METODO calcularNomina: Calcula el salario que le corresponde al empleado genérico
        double calcularNomina() {
            return salarioBase;
        }

        // METODO toString: Representación en texto del objeto mostrando su tipo y nombre
        @Override
        public String toString() {
            return getClass().getSimpleName() + " " + nombre;
        }
    }

    // CLASE HIJA (SUBCLASE): Hereda de Empleado con la palabra 'extends'
    static class EmpleadoFijo extends Empleado {
        // ATRIBUTO PROPIO: Los empleados fijos cobran además por trienios acumulados
        private final int trienios;

        // CONSTRUCTOR HIJO: Recibe los datos y usa 'super' para enviárselos al padre
        EmpleadoFijo(String nombre, double salarioBase, int trienios) {
            super(nombre, salarioBase); // Llama al constructor de la clase base Empleado
            this.trienios = trienios;
        }

        // METODO calcularNomina (@Override): Sobrescribe la fórmula sumando 50€ por trienio
        @Override
        double calcularNomina() {
            return salarioBase + 50 * trienios;
        }
    }

    // CLASE HIJA (SUBCLASE): Empleado temporal que hereda de Empleado
    static class EmpleadoTemporal extends Empleado {
        // ATRIBUTO PROPIO: Duración en meses del contrato temporal
        private final int mesesContrato;

        // CONSTRUCTOR HIJO: Pasa nombre y salario al padre con 'super' y guarda los meses
        EmpleadoTemporal(String nombre, double salarioBase, int meses) {
            super(nombre, salarioBase);
            this.mesesContrato = meses;
        }

        // METODO calcularNomina (@Override): Cobra únicamente su salario base
        @Override
        double calcularNomina() {
            return salarioBase;
        }
    }

    // CLASE HIJA (SUBCLASE): Directivo que hereda de Empleado
    static class Directivo extends Empleado {
        // ATRIBUTO PROPIO: Bonus extra que recibe un directivo
        private final double bonus;

        // CONSTRUCTOR HIJO: Inicializa atributos base mediante 'super' y asigna el bonus
        Directivo(String nombre, double salarioBase, double bonus) {
            super(nombre, salarioBase);
            this.bonus = bonus;
        }

        // METODO calcularNomina (@Override): Sobrescribe sumando el salario base más el bonus
        @Override
        double calcularNomina() {
            return salarioBase + bonus;
        }
    }

    // METODO main: Punto de inicio del programa
    public static void main(String[] args) {
        // POLIMORFISMO: Array de tipo base Empleado[] que almacena distintos tipos de subclases
        Empleado[] plantilla = {
            new EmpleadoFijo("Ana", 1200, 2),
            new EmpleadoTemporal("Luis", 900, 6),
            new Directivo("Carmen", 2000, 300)
        };

        // BUCLE FOR-EACH: Itera por cada empleado ejecutando su propio método polimórfico
        for (Empleado e : plantilla) {
            System.out.println(e + " -> Nomina: " + e.calcularNomina() + " EUR");
        }
    }
}
