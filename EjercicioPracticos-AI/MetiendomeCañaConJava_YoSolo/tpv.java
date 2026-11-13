package MetiendomeCañaConJava_YoSolo;

// =====================================================================
// CLASE PADRE: tpv (o Articulo)
// Representa un artículo base de la tienda con IVA estándar del 21%
// =====================================================================
public class tpv {
    
    // Atributos del padre:
    // 'protected' permite que las clases hijas (ArticuloOferta y ArticuloPerecedero)
    // puedan leer y usar directamente estas variables sin tener que usar getters.
    protected String codigo;
    protected String nombre;
    protected double precioBase;
    protected int stock;
    public double ivaS = 1.21;
    
    // Constructor padre: inicializa y valida el estado del objeto al nacer
    public tpv(String codigo, String nombre, double precioBase, int stock) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precioBase = precioBase;
        this.stock = stock;

        // 🧠 LECCIÓN APRENDIDA 1 (Signos en las validaciones):
        // En los 'if' con 'throw' se pone la condición de ERROR (cuándo está MAL), no cuándo está bien.
        // - El precio es incorrecto si es menor o igual a cero (<= 0).
        // - El stock es incorrecto si es negativo (< 0). Un stock de 0 es válido (agotado temporalmente).
        if (precioBase <= 0) {
            throw new IllegalArgumentException("El precio tiene que ser mayor que 0.");
        }
        
        if (stock < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo.");
        }

        // Validación de cadenas: comprobamos que no sea null ni esté vacío o lleno de espacios en blanco
        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("El código no puede estar vacío.");
        }

        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
    }

    // Métodos del padre

    // Devuelve el precio base incrementado con el IVA
    double getPrecioFinal() {
        return precioBase * ivaS;
    }

    // Permite reponer mercancía sumando existencias si la cantidad es positiva
    void reponer(int cantidad) {
        if (cantidad > 0) {
            stock += cantidad;
        }
    }
    
    // 🧠 LECCIÓN APRENDIDA 2 (Estructura de un método de venta/acción):
    // El orden siempre es:
    // 1º Filtros defensivos: lanzar excepción si los datos son inválidos o no hay suficiente stock.
    // 2º Modificar el estado: restar el stock si todo fue bien.
    // 3º Retorno final: calcular y devolver el total cobrado.
    // (Ojo: Nunca poner un 'return' antes de restar el stock ni usar 'else;' con punto y coma).
    double vender(int unidades) {
        // Filtro 1: Cantidad absurda o negativa
        if (unidades <= 0) {
            throw new IllegalArgumentException("Las unidades deben ser mayores que 0");
        }
        // Filtro 2: Pide más de lo que hay en el almacén
        if (unidades > stock) {
            throw new IllegalArgumentException("Stock insuficiente en " + nombre);
        }
        
        // Venta confirmada: restamos existencias y devolvemos el importe con IVA
        stock -= unidades;
        return unidades * getPrecioFinal();
    }

    // Sobrescritura de toString() para representar el artículo en consola de forma legible
    @Override 
    public String toString() {
        return "CODIGO: " + codigo + " | NOMBRE: " + nombre + " | STOCK: " + stock + " | PRECIO: " + getPrecioFinal();
    }
}


// =====================================================================
// CLASE HIJA 1: ArticuloOferta
// Hereda de tpv y aplica un porcentaje de descuento personalizado
// =====================================================================
class ArticuloOferta extends tpv {

    // Atributo propio y privado (encapsulado): solo lo conoce esta clase
    private double porcentajeDescuento;

    // 🧠 LECCIÓN APRENDIDA 3 (Constructor limpio):
    // El constructor SOLO inicializa ('super', 'this') y valida el rango permitido.
    // Nunca se debe meter la lógica de calcular precios dentro del constructor.
    // Además, hay que asegurarse de cerrar el constructor con '}' ANTES de escribir los métodos.
    public ArticuloOferta(String codigo, String nombre, double precioBase, int stock, double porcentajeDescuento) {
        super(codigo, nombre, precioBase, stock); // Pasa los datos comunes al padre
        this.porcentajeDescuento = porcentajeDescuento;

        // Comprobamos que el porcentaje esté entre el 1% y el 50%
        if (porcentajeDescuento < 1 || porcentajeDescuento > 50) {
            throw new IllegalArgumentException("El porcentaje tiene que estar entre 1 y 50%");
        }
    }

    // 🧠 LECCIÓN APRENDIDA 4 (Operaciones dentro del método):
    // Las operaciones de cálculo deben vivir DENTRO de las llaves del método, nunca sueltas en la clase.
    // Usamos 'super.getPrecioFinal()' para pedirle al padre el precio con IVA y aplicarle la rebaja.
    @Override 
    public double getPrecioFinal() {
        double precioConIva = super.getPrecioFinal();
        double descuento = precioConIva * (porcentajeDescuento / 100.0);
        return precioConIva - descuento;
    }
}


// =====================================================================
// CLASE HIJA 2: ArticuloPerecedero
// Hereda de tpv y liquida con 40% de descuento si caduca en <= 2 días
// =====================================================================
class ArticuloPerecedero extends tpv {

    // Atributo privado con los días restantes de consumo
    private int diasParaCaducar; 

    // Constructor: no olvidar pedir 'diasParaCaducar' como parámetro para poder guardarlo
    public ArticuloPerecedero(String codigo, String nombre, double precioBase, int stock, int diasParaCaducar) {
        super(codigo, nombre, precioBase, stock);
        this.diasParaCaducar = diasParaCaducar;
    }

    // 🧠 LECCIÓN APRENDIDA 5 (Dónde va el if de la condición de negocio):
    // La pregunta 'if (diasParaCaducar <= 2)' va dentro del método que calcula el precio,
    // porque es en el momento de consultar el precio cuando decidimos si liquidar o cobrar normal.
    @Override 
    public double getPrecioFinal() {
        double precioConIva = super.getPrecioFinal();
        
        // Si le quedan 2 días o menos, aplicamos rebaja agresiva del 40%
        if (diasParaCaducar <= 2) {
            double rebaja = precioConIva * 0.40;
            return precioConIva - rebaja; // Precio liquidado
        } else {
            return precioConIva;          // Precio normal con IVA
        }
    }
}
