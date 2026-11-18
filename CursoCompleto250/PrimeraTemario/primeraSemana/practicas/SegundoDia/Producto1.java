// CLASE: Molde o plantilla para crear objetos de tipo Producto
class Producto {

    // ATRIBUTOS PRIVADOS (ENCAPSULAMIENTO): Datos y estado interno protegidos de accesos indebidos
    private String nombre;  // Nombre o denominación del producto
    private double precio;  // Precio unitario del producto en euros
    private int stock;      // Cantidad de existencias disponibles en el inventario

    // CONSTRUCTOR: Se ejecuta automáticamente al crear una nueva instancia con 'new'
    public Producto(String nombre, double precio, int stock) {
        // VALIDACIONES: Lanzan excepciones si los valores no cumplen con las reglas de negocio
        if (precio <= 0)
            throw new IllegalArgumentException("Precio negativo");

        if (stock <= 0)
            throw new IllegalArgumentException("Stock negativo");

        if (nombre == null || nombre.isBlank())
            throw new IllegalArgumentException("Nombre obligatorio");

        // ASIGNACIÓN: 'this.atributo' se refiere al atributo del objeto, y el otro es el parámetro recibido
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    // METODO vender: Reduce las unidades disponibles y devuelve el importe total de la venta (double)
    double vender(int unidades) {
        // VALIDACIÓN: Comprueba que haya suficiente existencia disponible
        if (unidades > stock)
            throw new IllegalArgumentException("Stock insuficiente de: " + nombre);
        
        // VALIDACIÓN: Evita ventas de 0 o cantidades negativas
        if (unidades <= 0)
            throw new IllegalArgumentException("Unidades no validas");

        // ACTUALIZACIÓN DE ESTADO: Resta del inventario las unidades vendidas
        stock -= unidades;

        // RETORNO: Devuelve el dinero total recaudado por la venta
        return unidades * precio;
    }

    // METODO reponer: Método tipo 'void' (no devuelve nada), solo incrementa el stock
    void reponer(int unidades) {
        // VALIDACIÓN: Exige una reposición mínima de más de 20 unidades
        if (unidades <= 20)
            throw new IllegalArgumentException("Unidades no validas");

        // VALIDACIÓN: Límite de seguridad para compras muy grandes
        if (unidades > 300)
            throw new IllegalArgumentException("Avisa a un supervisor");

        // ACTUALIZACIÓN DE ESTADO: Añade las nuevas unidades al stock existente
        stock += unidades;
    }

    // METODO toString (@Override): Sobrescribe el método por defecto de Object para representar el producto como texto legible
    @Override
    public String toString() {
        return nombre + " (" + precio + " EUR, stock " + stock + ")";
    }

    // METODO main: Punto de entrada donde inicia la ejecución del programa
    public static void main(String[] args) {
        // CREACIÓN DE OBJETOS (INSTANCIACIÓN): Se crean dos instancias independientes de Producto en memoria
        Producto patatas = new Producto("Patatas", 2, 7);
        Producto peras = new Producto("Peras", 12, 2);

        // LLAMADA A MÉTODOS: Ejecutamos el método vender y mostramos el importe retornado
        System.out.println("Venta patatas x3: " + patatas.vender(3) + " EUR");

        // LLAMADA A MÉTODO REPONER: Aumenta el stock de peras
        peras.reponer(25);

        // SALIDA POR CONSOLA: Al pasar el objeto a println, Java llama automáticamente a su método toString()
        System.out.println(patatas);
        System.out.println(peras);
        
        // CONTROL DE EXCEPCIONES (TRY - CATCH): Atrapa posibles errores sin detener la aplicación
        try {
            // Intento de venta de 50 unidades (provoca error porque supera el stock disponible)
            peras.vender(50);
        } catch (IllegalArgumentException e) {
            // Se ejecuta si ocurre una excepción de tipo IllegalArgumentException
            System.out.println("Error " + e.getMessage());
        }
    }
}
