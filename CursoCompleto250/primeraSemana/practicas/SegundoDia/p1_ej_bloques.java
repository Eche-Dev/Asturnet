// CLASE: molde para crear objetos tipo Producto
class Producto {

    // ATRIBUTOS (datos del objeto)
    private String nombre;
    private double precio;
    private int stock;

    // CONSTRUCTOR: se ejecuta al crear el objeto con new
    public Producto(String nombre, double precio, int stock) {
        if (precio <= 0)
            throw new IllegalArgumentException("Precio negativo");

        if (stock <= 0)
            throw new IllegalArgumentException("Stock negativo");

        if (nombre == null || nombre.isBlank())
            throw new IllegalArgumentException("Nombre obligatorio");

        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    // METODO vender: realiza una venta y devuelve el dinero
    double vender(int unidades) {
        if (unidades > stock)
            throw new IllegalArgumentException("Stock insuficiente de: " + nombre);
        if (unidades <= 0)
            throw new IllegalArgumentException("Unidades no validas");

        stock -= unidades;
        return unidades * precio;
    }

    // METODO reponer: agrega unidades al stock
    void reponer(int unidades) {
        if (unidades <= 20)
            throw new IllegalArgumentException("Unidades no validas");

        if (unidades > 300)
            throw new IllegalArgumentException("Avisa a un supervisor");

        stock += unidades;
    }

    // METODO toString: convierte el objeto en texto
    @Override
    public String toString() {
        return nombre + " (" + precio + " EUR, stock " + stock + ")";
    }

    // METODO main: punto de inicio del programa
    public static void main(String[] args) {
        Producto patatas = new Producto("Patatas", 2, 7);
        Producto peras = new Producto("Peras", 12, 2);
        System.out.println("Venta patatas x3: " + patatas.vender(3) + " EUR");
        peras.reponer(25);
        System.out.println(patatas);
        System.out.println(peras);
        try {
            peras.vender(50);
        } catch (IllegalArgumentException e) {
            System.out.println("Error " + e.getMessage());
        }
    }
}