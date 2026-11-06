package EjercicioPracticosAI.MetiendomeCañaConJava_YoSolo;

public class sistemaCuentasBancarias {

    //Atribustos de la clase
    private String numeroCuenta;
    private String titular;
    private double saldo;

    //Constructor con sus comprobaciones 
    public sistemaCuentasBancarias(String numeroCuenta, String titular, double saldoInicial){
        this.numeroCuenta = numeroCuenta;
        this.titular = titular;
        this.saldo = saldoInicial;

    //Comprobaciones
        if (numeroCuenta == null || numeroCuenta.isBlank()){
            throw new IllegalArgumentException("Numero de cuenta obligatorio");
    }

        if (titular == null || titular.isBlank()){
            throw new IllegalArgumentException("El titular es obligatorio");
    }

        if (saldoInicial <= 0){
            throw new IllegalArgumentException("El saldo inicial no puede ser negativo");
    }
}

    //Metodo de ingresar - Lanza el error o suma a saldo la cantidad ingresada. Es void porque lo pide el ejercicio y no devuelve nada solo suma.
    public void ingresar(double cantidad){
        if (cantidad < 0){
            throw new IllegalArgumentException("La cantidad a ingresar debe ser positiva");
        }
        this.saldo += cantidad;
    }

    //Metodo de retirar - Lanza el error o resta el saldo si la cantidad es correcta. Es boolean porque lo pide el ejercicio pide que devuelva TRUE si fue exitoso. pero se podia usar void tambien en esta.
    public boolean retirar(double cantidad) { 
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad a retirar debe ser positiva");
    }
        if (cantidad > this.saldo) {
            throw new IllegalArgumentException("Saldo insuficiente");
    }
        this.saldo -= cantidad; 
        return true;            
}

    //Un metodo de consulta a saldo por eso EL get.
    double getSaldo(){
        return saldo;
    }

    //Un metodo de sobre escritura con Override y toString.
    @Override 
        public String toString(){
            return "Cuenta: " + numeroCuenta +" | "+ "Titular: " + titular +" | "+ "Saldo: "+ saldo +" EUR";
        }
    
    //El main para que corra el programa y no chille java en la terminal.
    public static void main(String[] args) {
        
        //Linea visuales
        System.out.println("=== ESTADO INICIAL Y OPERACIONES ===");

        //Creacion de la cuenta bancaria de angel y david con new. 
        //Entre parentesis le estamos pasando los datos la clase sistemaCuentasBancarias y toString los toma y los imprime con el formato que le di
        //Se pone primero a donde lo vamos a mandar.
        //luego le damos el nombre a la variable.
        // luego le decimos que con new que esta la variable del otro lado del igual sera ahora los datos de sistemaCuentasBancarias
        // La clase ↓ |  variable ↓ \ la creamos ↓ y se la pasamos ↓
        sistemaCuentasBancarias cuentaDavid  = new sistemaCuentasBancarias ("ES12-3456", "David Villanueva" , 1000);
        sistemaCuentasBancarias cuentaAngel = new sistemaCuentasBancarias ("ES12-3456", "Angel Echenique" , 250);

        //imprimos los datos de la cuenta antes de ejecutar ingresar o retirar
        //Es decir la linea 73-74 suben a la linea 56 le dice tengo esta informacion
        //Y override le dice toma te presto este formato para que lo imprimas lindo
        //entonce viene y lo trae aqui con los datos que ya le di.
        System.out.println("Cuenta creada: " + cuentaDavid);
        System.out.println("Cuenta creada: " + cuentaAngel);
        
        //Ingreso a la cuenta de david. 
        //Aqui hago una varible con un nombre descriptivo "cantidadIngreso" y asignamos el tipo "double" y el valor de "300".
        //Invocamos al metodo ingresar con "cuentaDavid.ingresar".
        //Dentro de los parentisis le pasamos la variable que creamos que tiene 300 euro DENTRO.
        double cantidadIngreso = 300;
        cuentaDavid.ingresar(cantidadIngreso);
        System.out.println("\nIngresando " + cantidadIngreso + " EUR en la cuenta de David...");

        //Otra forma de hacer lo que hice arriba. Pasarlo directo sin crear una variable. 
        //Pero no me gusta porque, Si cambio cantidad y no cambio en el string la cantidad pueden retirar 100 y salir el en pantalla retirando 50.
        cuentaAngel.retirar(50);
        System.out.println("Retirando 50.0 EUR de la cuenta de Angel...");

        //Linea visual - la \n es un salto de linea para dejar un espacio entre lo que estamos imprimiendo
        System.out.println("\n=== ESTADO ACTUALIZADO ===");
        //Aqui imprimimos las cuenta pero con el dinero ya ingresado y retirado
        //en pocas palabra la linea 73-74 para llegar aqui primero pasaron por dos accion una de ingresar y retirar. desde la linea 87 a 94.
        //entonces lo que hace java es que dice vale de la 87 a la 89 ingresaron dinero. ahora aqui no puede ser 1000, es mas.
        //va a donde override y le dice hey que ingresar se ejecuto y ahora tengo mas plata, el dinero esta almacenado en el getSaldo ahora.
        //entonces override toma los nuevos datos y los muestra actualizado aqui
        System.out.println(cuentaDavid);
        System.out.println(cuentaAngel);

        //linea visual
        System.out.println("\n=== PRUEBA DE SEGURIDAD (TRY - CATCH) ===");
        //Esto es manejo de errores para que sepa que hacer cuando sucede algo que no debe
        //en este caso le estamos diciendo, todo lo que va en "TRY" es lo que queremos explotar o que sabemos que podria fallar.
        //en el catch ponemos el mensaje de error para que el usuario sepa porque fallo.
        //aqui intentamos retirar 500 de una cuenta que tiene solo 200 por lo tanto lanzara el error el catch
        try {
            cuentaAngel.retirar(500);
        } catch (IllegalArgumentException e) {
            // Se ejecuta si ocurre una excepción de tipo IllegalArgumentException
            //como funciono esto: en teoria lo que entendi es Throw tiene un carta con el mensaje que tenemos que mostrar. se lo manda a 
            //IllegalArgumentException lo atrapa y lo guarde en la variable llamada por convencion de programcion "e" 
            //Pero esta variable se puede llamar "PEPITO" si tu quieres.
            //luego imprimimos el error para llamarlo hacemos ej: "laVariable.getMessage" sin el get no podremos abrir el carta.
            System.out.println("Error controlado: " + e.getMessage());
        }
        //esto es una linea de comprobacion para ver que el dinero sigue alli, a pesar de intetar sacar una cantidad que no habia.
        System.out.println("Saldo final de Angel sigue siendo: " + cuentaAngel.getSaldo() + " EUR");
    }

    }





