package CursoCompleto250.segundaSemana.practicas.CuartoDia.ProyectoModular;

/**
 * 🚗 SUPERCLASE BASE: Vehiculos
 * Contiene el comportamiento y los atributos comunes para cualquier vehículo del garaje.
 * Evita duplicar código entre coches y motos (Principio DRY - Don't Repeat Yourself).
 */
public class Vehiculos {

    // --- ATRIBUTOS (ESTADO) ---
    protected int dentro = 0;              // Cantidad de vehículos que están actualmente aparcados
    protected int queSalieron = 0;         // Acumulador total de los vehículos que han salido hoy
    protected final int limite;            // Aforo máximo de plazas permitido (inmutable)
    protected final String tipo;           // Nombre del tipo de vehículo ("Coche" o "Motocicleta")

    // --- CONSTRUCTOR ---
    public Vehiculos(String tipo, int limite) {
        this.tipo = tipo;
        this.limite = limite;
    }

    // --- ACCIÓN DE ENTRADA ---
    public void entrar() {
        if (this.dentro >= this.limite) {
            throw new IllegalStateException("Error: La zona de " + this.tipo + " está llena (" + this.limite + " plazas).");
        }
        this.dentro++;
        System.out.println("Entró " + this.tipo + ". Ocupación actual: " + this.dentro + "/" + this.limite);
    }

    // --- ACCIÓN DE SALIDA ---
    public void salir() {
        if (this.dentro <= 0) {
            throw new IllegalStateException("Error: No hay ningún " + this.tipo + " dentro para retirar.");
        }
        this.dentro--;
        this.queSalieron++;
        System.out.println("Salió " + this.tipo + ". Dentro: " + this.dentro + " | Total salidos hoy: " + this.queSalieron);
    }

    // --- GETTERS (MÉTODOS DE CONSULTA) ---
    public int cuantosHay() {
        return this.dentro;
    }

    public int getQueSalieron() {
        return this.queSalieron;
    }

    public int getLimite() {
        return this.limite;
    }

    public String getTipo() {
        return this.tipo;
    }
}
