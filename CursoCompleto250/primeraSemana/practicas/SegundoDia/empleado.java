public class empleado {

    static class Empleado {
        protected final String nombre;
        protected final double salarioBase;

        Empleado(String nombre, double salarioBase) {
            this.nombre = nombre;
            this.salarioBase = salarioBase;
        }

        double calcularNomina() {
            return salarioBase;
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + " " + nombre;
        }
    }

    static class EmpleadoFijo extends Empleado {
        private final int trienios;

        EmpleadoFijo(String nombre, double salarioBase, int trienios) {
            super(nombre, salarioBase);
            this.trienios = trienios;
        }

        @Override
        double calcularNomina() {
            return salarioBase + 50 * trienios;
        }
    }

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

    static class Directivo extends Empleado {
        private final double bonus;

        Directivo(String nombre, double salarioBase, double bonus) {
            super(nombre, salarioBase);
            this.bonus = bonus;
        }

        @Override
        double calcularNomina() {
            return salarioBase + bonus;
        }
    }

    public static void main(String[] args) {
        Empleado[] plantilla = {
            new EmpleadoFijo("Ana", 1200, 2),
            new EmpleadoTemporal("Luis", 900, 6),
            new Directivo("Carmen", 2000, 300)
        };

        for (Empleado e : plantilla) {
            System.out.println(e + " -> Nomina: " + e.calcularNomina() + " EUR");
        }
    }
}