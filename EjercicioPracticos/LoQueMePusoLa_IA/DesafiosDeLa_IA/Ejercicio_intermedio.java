package EjercicioPracticos.LoQueMePusoLa_IA.DesafiosDeLa_IA;

// CLASE: Molde para crear y gestionar cuentas bancarias
public class Ejercicio_intermedio {

    // 1. ATRIBUTOS (ESTADO DEL OBJETO)
    // NOTA: 'ingresar' y 'retirar' se eliminaron de aquí porque son ACCIONES (métodos), no datos permanentes.
    private String numeroCuenta; // Identificador único de la cuenta
    private String titular;      // Nombre del titular
    private double saldo;        // Dinero disponible en la cuenta

    // 2. CONSTRUCTOR: Inicializa la cuenta con validaciones
    public Ejercicio_intermedio(String numeroCuenta, String titular, double saldoInicial) {
        if (numeroCuenta == null || numeroCuenta.isBlank())
            throw new IllegalArgumentException("Numero de cuenta obligatorio");
        if (titular == null || titular.isBlank())
            throw new IllegalArgumentException("El titular es obligatorio");
        if (saldoInicial < 0)
            throw new IllegalArgumentException("El saldo inicial no puede ser negativo");

        this.numeroCuenta = numeroCuenta;
        this.titular = titular;
        // CORRECCIÓN: Tu atributo es 'saldo'. Asignamos 'saldoInicial' (el parámetro) a 'this.saldo'.
        this.saldo = saldoInicial;
    }

    // 3. MÉTODO INGRESAR (MODIFICADOR TIPO void)
    // CORRECCIÓN: Solo recibe la 'cantidad' a ingresar. No necesita recibir 'saldo' porque el objeto ya conoce su propio saldo.
    void ingresar(double cantidad) {
        if (cantidad <= 0)
            throw new IllegalArgumentException("La cantidad a ingresar debe ser positiva");
        
        // CORRECCIÓN: Sumamos la cantidad directamente al atributo 'saldo' de la cuenta.
        this.saldo += cantidad;
    }

    // 4. MÉTODO RETIRAR (MODIFICADOR CON RETORNO boolean)
    boolean retirar(double cantidad) {
        if (cantidad <= 0)
            throw new IllegalArgumentException("La cantidad a retirar debe ser positiva");
        if (cantidad > saldo)
            throw new IllegalArgumentException("Saldo insuficiente");

        // CORRECCIÓN 1: Se debe restar el dinero del saldo antes de terminar el método.
        this.saldo -= cantidad;

        // CORRECCIÓN 2: Se retorna 'true' incondicionalmente al final si pasó las validaciones previas.
        // (Antes tenías 'if (retirar < saldo) return true;' y si retirabas exactamente todo el saldo, Java daba error por falta de return).
        return true;
    }

    // 5. GETTER: Consulta el saldo actual sin modificarlo
    double getSaldo() {
        return this.saldo;
    }

    // 6. MÉTODO toString: Representación en texto del objeto para imprimir por consola
    @Override
    public String toString() {
        return "Cuenta: " + numeroCuenta + " | Titular: " + titular + " | Saldo: " + saldo + " EUR";
    }

    // 7. MÉTODO main: Punto de entrada para probar el funcionamiento de la clase
    public static void main(String[] args) {
        System.out.println("=== 1. CREACIÓN DE CUENTAS ===");
        Ejercicio_intermedio cuentaAna = new Ejercicio_intermedio("ES12-3456", "Ana Gómez", 1000.0);
        Ejercicio_intermedio cuentaPedro = new Ejercicio_intermedio("ES98-7654", "Pedro Ruiz", 250.0);

        System.out.println(cuentaAna);
        System.out.println(cuentaPedro);

        System.out.println("\n=== 2. OPERACIONES VÁLIDAS ===");
        System.out.println("Ingresando 300.0 EUR a Ana...");
        cuentaAna.ingresar(300.0);

        System.out.println("Retirando 50.0 EUR de Pedro...");
        cuentaPedro.retirar(50.0);

        System.out.println("\n=== 3. ESTADO TRAS OPERACIONES ===");
        System.out.println(cuentaAna);
        System.out.println(cuentaPedro);

        System.out.println("\n=== 4. PRUEBA DE SEGURIDAD (TRY - CATCH) ===");
        try {
            System.out.println("Intentando retirar 500.0 EUR de la cuenta de Pedro (solo tiene 200 EUR)...");
            cuentaPedro.retirar(500.0); // Esto lanzará IllegalArgumentException
        } catch (IllegalArgumentException e) {
            // El error es capturado y el programa no se rompe
            System.out.println(">> Error controlado con éxito: " + e.getMessage());
        }

        System.out.println("Saldo de Pedro tras el intento fallido: " + cuentaPedro.getSaldo() + " EUR");
    }
}
