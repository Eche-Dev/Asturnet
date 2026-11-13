package CursoCompleto250.TerceraSemana.Viernes13;

//Clase padre
public class tpv {
    
    //Atributos del padre
    protected String codigo;
    protected String nombre;
    protected double precioBase;
    protected int stock;
    public double ivaS = 1.21;
    
    //Constructor padre donde inicializamos los atribustos
    public tpv(String codigo, String nombre, double precioBase, int stock) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precioBase = precioBase;
        this.stock = stock;

    //Ahora pasamos a comprobar o validar.
    
    if(precioBase <= 0 ){
        throw new IllegalArgumentException("El precio tiene que ser mayor que 0.");
        }
    
    if (stock < 0 ) {
        throw new IllegalArgumentException("EL perdido no puede ser mas grande que el stock .");
        }

    if (codigo == null || codigo.isBlank()) {
        throw new IllegalArgumentException("Debe contener caracteres el ticket ");
        }

    if (nombre == null || nombre.isBlank()) {
        throw new IllegalArgumentException("Debes contener un nombre.");
        }
    }

    //Metodos del padre

    double getPrecioFinal(){
        return precioBase * ivaS;
    }

    void reponer(int cantidad){
        if (cantidad > 0)
            stock += cantidad;
    }
    
    double vender(int unidades){
        if (unidades <= 0) {
            throw new IllegalArgumentException("Las unidades deben ser mayores que 0");
        }
        if (unidades > stock) {
            throw new IllegalArgumentException("Stock insuficiente en " + nombre);
        }
        stock -= unidades;
        return unidades * getPrecioFinal();

    }
    //sobre escritura
    @Override 
        public String toString(){
            return "CODIGO: " + codigo + " | NOMBRE: " + nombre + " | STOCK: " + stock + " | PRECIO: " + getPrecioFinal();
        }
}


// Clase hija mayor

// He aplicado la herencia (extends)
// llamado al constructor padre (super).
// Encapsulado el atributo con private.
// Validado el rango de descuento y sobrescrito @Override el método con el cálculo exacto.
class ArticuloOferta extends tpv{
    //Atributo privado y propio de la hija
    private double porcentajeDescuento;

    //Constructor
    public ArticuloOferta(String codigo, String nombre, double precioBase, int stock, double porcentajeDescuento){
        super(codigo, nombre, precioBase, stock);
        this.porcentajeDescuento = porcentajeDescuento;

        if (porcentajeDescuento < 1 || porcentajeDescuento > 50) {
            throw new IllegalArgumentException("El porcentaje tiene que estar entre 1 y 50%");
        }
    }

    //Sobre escritura
    @Override 
        public double getPrecioFinal(){
            double precioConIva = super.getPrecioFinal();
            double descuento = precioConIva * (porcentajeDescuento / 100.0);
            return precioConIva - descuento;
        }

}

// Clase hijita menor
class ArticuloPerecedero extends tpv{
    //Atributo privado y propio de la hijita
    private int diasParaCaducar; 

    //Constructor
    public ArticuloPerecedero(String codigo, String nombre, double precioBase, int stock, int diasParaCaducar){
        super(codigo, nombre, precioBase, stock);
        this.diasParaCaducar = diasParaCaducar;
    }

    //Sobre escritura 
    @Override 
        public double getPrecioFinal(){
            double precioConIva = super.getPrecioFinal();
            //aqui preguntamos
        if (diasParaCaducar <= 2) {
            double rebaja = precioConIva * 0.40;
            return precioConIva - rebaja;     // Descuento por liquidación
        } else {
            return precioConIva;             // Precio normal sin descuento
        }
    }
}
