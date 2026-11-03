Clase

Cuentas

Objetos

Ana, Angel, Gabriel, Jonatan

Atributos

saldo, deuda

Metodo

retirar, añadir, darAlta, darBaja

Mensajes

cuentaGabriel.añadir(1000)

AOC
    32 -> 41    



    Abstraccion
    Representar unicamente caracteristicas esenciales

    Encapsulacion
    Ocultar el  estado y exponer una interfaz controlada

    Herencia
    Clase que reutiliza y amplia a otra 

    Poliformismo
    Un mismo mensaje, comportamientos distintos segun el objeto
    

añadir Ana (100)

Clase               Biblioteca

    Objetos          Socio          Prestamo        Libro          
    Atributo         Nombre          fechadev        titulo
                     numero                          SKU

    Metodo           prestar(Libro)  devolver()      disponible()




public

Cualquier clase

protected  

Clase, mismo paquete y subclases

Por defecto () 

Solamente mismo paquete

private  

Solo la propia clase



Cuenta a = new Cuenta("Manuel", 500);
Cuenta b = new Cuenta("Matilde");
a.retirar(120);
b.añadir(120);
System.out.printIn(a);
System.out.printIn(b);
System.out.printIn("uentas creadas:" + Cuenta.getTotalCuentas());



Tipos de metodos segun funcion


Constructores
Reserva e inicializa el estado 
Cuenta(String, double)

Solamente mismo paquete
Consulta (getter)
Leer sin modificar el estado
getSaldo() getDeuda() get()

Modificador (setter / comando)
Cambia el estado
ingresar(double) retirar(double)


Utilidad 
Calculos auxiliares o conversion
toString() 

Destruccion / cierre
Liberar recursos externos
close()


static int totalCuentasActivas
 {Inserta codigo aqui}


Cuenta.getTotalCuentas()





Cuenta

Titular String
saldo   double
estado  String
totalCuentas int (static)

-------------------

ingresar(importe: double)
retirar (importe: double)
getSaldo(): double
toString(): String



System.out.printIn("uentas creadas:" + Cuenta.getTotalCuentas());

System.out.printIn("Tu saldo actual es: " + Cuenta.getSaldo() + " €");
                    Tu saldo actual es: 100,00€



System.out.printIn("Tu saldo actual es: " + Cuenta.getSaldoEnDolares() + " $");
                    Tu saldo acutal es: 98,00$ 


--------------
static class Cuenta{
    private double saldo;
    Cuenta (double inicial){this.saldo = inicial;}
    void ingresar(double importe) { saldo += importe; }
    double getSaldo() {return saldo;}
}







static class cuenta{
    private static int totalCuentas = 0;
    private final String titular;
    private double saldo;

Cuenta(String titular, double saldoInicial) {
    this.titular = titular;
    this.saldo = saldoInicial;
    totalCuentas++;
}

Cuenta (String titular) {this(Titular, 0); }

void retirar (double importe) {
    if (importe <= 0 || importe > saldo) {
        throw new IllegalArgumentException ("Importe no válido: " + importe);
    }

    saldo -= importe;

}

void ingresar (double importe) {
    if (importe <=0) {
        throw new IllegalArgumentException ("Importe no válido");
    }

    

    saldo += importe;

}

void prestamo (double prestamoInicial) { 
    if (importe <=0 || importe>500) {

        System.out.printeIn("No cumples los requisitos")

        throw new IllegalArgumentException ("No cumples requisitos");
    }
}
           
double getSaldo() {return saldo;}
string getTitular () {return titular;}
static int getTotalCuentas () {return totalCuentas;}


public String toString() {return titular + ": " + saldo + " EUR";}

}









public class EjercicioProducto {
    static class Producto {
        private final String nombre;
        private final double precio;
        private int stock;

        Producto (String nombre, double precio, int stock) {
            if (precio <= 0) 
                throw new IllegalArgumentException ("Precio negativo");

            if (stock <= 0) 
                throw new IllegalArgumentException ("Stock negativo");

            if (nombre == null || nombre.isBlank())
                throw new IllegalArgumentException ("Nombre obligatorio");

            this.nombre = nombre
            this.precio = precio
            this.stock = stock
        
        }
        
        double vender (int unidades) { 
            if (unidades > stock)  

                throw new IllegalArgumentException ("Stock insuficiente de: " + nombre);
            if (unidades <= 0) 
                throw new IllegalArgumentException ("Unidades no validas");
            
            stock -= unidades;
            return unidades * precio; // Muestra 3*2
        }          

        void reponer (int unidades) {
            if (unidades <= 20) 
                throw new IllegalArgumentException ("Unidades no validas");

            if (unidades > 300) 
                throw new IllegalArgumentException ("Avisa a un supervisor");

            stock += unidades;
        }
        @Override
        public String toString() {̣return nombre + " (" + precio + " EUR, stock " + 
        stock + ")"; }

    }
    public static void main (String[] args) {
        Producto patatas = new Producto("Patatas", 2, 7);
        Producto peras = new Producto("Peras", 12, 2);
        System.out.printIn("Venta patatas x3: " + patatas.vender(3) + " EUR");
        peras.reponer(25);        
        System.out.printIn(Patatas);
        System.out.printIn(Peras);
        try {
            peras.vender(50);
        } catch (IllegalStateException e) {
            System.out.printIn ("Error " + e.getMessage());
        }
    }

}

try { } // Codigo que deberia tirar excepcion

catch { } // Codigo para reaccionar a la excepcion

finally { } // Codigo que siempre se ejecuta



static class Vehiculo {
    protected String matricula;
    protected int velocidad;

    Vehiculo(String matricula) {
        this.matricula = matricula;
        System.out.printIn("Constructor del Vehiculo");
    }

    void acelerar (int incremento) {velocidad += incremento;}
    String describir() {return "Vehiculo " + matricula + " a " + velocidad + " km/h";}
}

static class Coche extends Vehiculo {
    private int puertas;

    Coche(String matricula, int puertas) {
        super(matricula); 
        this.puertas = puertas;
        System.out.printIn("Constructor de coche")
    }

    @Override
    String describir() {
        return "Coche de " + puertas + " puertas. " + super.describir(); 
    }
    
}  

super.metodo()

toString ()   Representacion en texto
equals (Objeto) Igualdad de contenido
hashCode()  Codigo para colecciones hash
getClass()   Tipo real de objeto

Vehiculo 
    matricula  
    velocidad 
        acelerar (int)
        describir(): String


        Coche
            puertas
            describir

        Motocicletas
            cilindrada
            describir


interface Volador {default String volar() {return "Volando;"}}
interface Nadador {default String nadar() {return "Nadando;"}}

static class Pato implements Volador, Nadador , Caminador{ }